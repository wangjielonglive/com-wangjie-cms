package com.wangjie.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangjie.cms.dao.ArticleMapper;
import com.wangjie.cms.entity.Article;
import com.wangjie.cms.entity.Comment;
import com.wangjie.cms.entity.Term;
import com.wangjie.cms.service.ArticleService;
import com.wangjie.cms.utils.ESUtils;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	ArticleMapper articleMapper;
	
	@Resource
	private RedisTemplate<String, Article> redisTemplate;
	
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	/**
	 * 
	 * @param chnId 频道id
	 * @param catId 分类id
	 * @param page  页码
	 * @return
	 */
	@Override
	public PageInfo<Article> list(Integer chnId, 
			Integer catId, Integer page) {
		//设置页码
		PageHelper.startPage(page, 10);
		// TODO Auto-generated method stub
		//查询指定页码数据 并返回页面信息
		return new PageInfo(articleMapper.list(chnId,catId)) ;
	}
	
	//redis
	@Override
	public PageInfo<Article> hostList(Integer page) {
		
		ListOperations<String, Article> opsForList = redisTemplate.opsForList();
		PageInfo pageInfo = null;
		
		if (redisTemplate.hasKey("hot_list")) {
			//之后访问，都从redis中获取，手动设置分页下标，开始下标和结束下标
			//开始下标	(page - 1) * size
			//结束下标	page * size - 1
			List<Article> list = opsForList.range("hot_list", (page-1)*10,page*10-1);
			pageInfo = new PageInfo(list);
			//设置总条数
			Long size = opsForList.size("hot_list");
			pageInfo.setTotal(size);
			pageInfo.setPageNum(page);
			
		} else {
			//第一次访问时，从数据库中查询
			List<Article> listHot = articleMapper.listHot();
			//将数据存入redis中
			opsForList.rightPushAll("hot_list", listHot);
			
			//设置页码
			PageHelper.startPage(page, 10);
			//查询指定页码数据 并返回页面信息
			List<Article> list = articleMapper.listHot();
			pageInfo = new PageInfo(list);
		}
		
		return pageInfo;
	}
	
	//获取最新文章的数目
	@Override
	public List<Article> last(int sum) {
		ListOperations<String, Article> opsForList = redisTemplate.opsForList();
		
		List<Article> listLast = null;
		
		if(redisTemplate.hasKey("last_list")) {
			//如果存在数据
			//3、以后再访问，直接从redis中获取数据
			listLast = opsForList.range("last_list", 0, -1);
			
		}else {
			//1、首次访问时，从数据库中获取数据
			listLast = articleMapper.listLast(sum);
			
			//2、将数据存入redis中		
			opsForList.rightPushAll("last_list", listLast);
		}
		
		return listLast;
	}

	@Override
	public Article findById(Integer articleId) {
		// TODO Auto-generated method stub
		return articleMapper.findById(articleId);
				
	}

	@Override
	public int add(Article article) {
		// TODO Auto-generated method stub
		
		int result =  articleMapper.add(article);
		processTag(article);
		
		return result ;
	}
	
	/**
	 *  处理文章的标签
	 * @param article
	 */
	private void processTag(Article article){
		if(article.getTags()==null)
			return;
		
		String[] tags = article.getTags().split(",");
		for (String tag : tags) {
			// 判断这个tag在数据库当中是否存在
			Term tagBean = articleMapper.findTagByName(tag);
			if(tagBean==null) {
				tagBean = new Term(tag);
				articleMapper.addTag(tagBean);
			}
			
			//插入中间表
			try {
				articleMapper.addArticleTag(article.getId(),tagBean.getId());
			}catch(Exception e){
				System.out.println("插入失败 ");
			}
		}
	}
	
	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		int result = articleMapper.update(article);
		// 删除中间表中的
		articleMapper.delTagsByArticleId(article.getId());
		processTag(article);
		return result;
		
	}

	/**
	 * 
	 */
	@Override
	public PageInfo<Article> listArticleByUserId(Integer userId, Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 10);
		return new PageInfo<Article>(articleMapper.listByUserId(userId));
	
	}

	@Override
	public int remove(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.deleteById(id);
	}

	/*@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return  articleMapper.update(article);
	}*/

	@Override
	public PageInfo<Article> getAdminArticles(Integer page, Integer status) {
		// TODO Auto-generated method stub
		System.out.println(" ============ page is " + page);
		PageHelper.startPage(page, 10);
		return new PageInfo<Article>(articleMapper.listAdmin(status));
	}
	//修改热门的状态
	@Override
	public int updateHot(Integer articleId, int status) {
		//设置热门
				int result = articleMapper.updateHot(articleId,status);
				
				if(result > 0) {
					//如果文章设置热门成功，则删除redis中最新文章
					redisTemplate.delete("hot_list");
				}
				
				return result;
	}
	
	/**
	 * 审核文章
	 * @param articleId 文章ID
	 * @param status 审核后的状态 
	 * @return
	 */
	@Override
	public int updateStatus(Integer articleId, int status) {
		
		
		//审核文章
		int result = articleMapper.updateStatus(articleId,status);
		if (result > 0) {
			//如果文章审核成功，则删除redis中最新文章
			redisTemplate.delete("last_list");
			
			//
			Article article = articleMapper.findById(articleId);
			IndexQuery query = new IndexQuery();
			query.setId(articleId.toString());
			query.setObject(article);
			elasticsearchTemplate.index(query );
		}
		return result;
	}

	@Override
	public void comment(Integer userId, Integer articleId, String content) {
		// TODO Auto-generated method stub
		Comment comment = new Comment(articleId,userId,content);
		articleMapper.addComment(comment);
		articleMapper.increaseCommentCnt(articleId);
		
		
	}

	/**
	 * 获取评论
	 */
	@Override
	public PageInfo<Comment> getCommentByArticleId(Integer articleId, Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 5);
		return new PageInfo<Comment>(articleMapper.getCommnentByArticleId(articleId));
		
	}

	/**
	 * 增加文章点击次数
	 */
	@Override
	public int addHits(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.increaseHits(id);
	}

	@Override
	public PageInfo<Comment> getCommentByUserId(int id, Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 5);
		return new PageInfo<Comment>(articleMapper.getCommnentByUserId(id));
		
	}

	@Override
	public void addFromKafka(Article article) {
		// TODO Auto-generated method stub
		articleMapper.addFromKafka(article);
	}

	/**
	 * 查询elasticsearch的高亮显示
	 */
	@Override
	public PageInfo<Article> esList(Integer page,String key) {
		//模板对象，实体类的类对象，当前页，每页的记录条数，根据指定字段排序，要查询的字段，要模糊查询的值
		AggregatedPage<?> selectObjects = ESUtils.selectObjects(elasticsearchTemplate, Article.class, page - 1, 10, "id", new String[] {"title","content"}, key);
		
		List<Article> list = (List<Article>) selectObjects.getContent();
		
		PageInfo<Article> pageInfo = new PageInfo<Article>(list);
		//设置总条数
		pageInfo.setTotal(selectObjects.getTotalElements());
		//设置当前页
		pageInfo.setPageNum(page);
		
		return pageInfo;
	}

}
