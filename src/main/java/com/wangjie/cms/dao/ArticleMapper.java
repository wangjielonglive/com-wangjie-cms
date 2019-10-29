package com.wangjie.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wangjie.cms.entity.Article;
import com.wangjie.cms.entity.Comment;
import com.wangjie.cms.entity.Term;

public interface ArticleMapper {
	/**
	 * 频道id必须大于0，分类id可以为0，当分类id为0的时候，查询该频道下的所有分类的文章
	 * 否在查询该分类下的文章
	 * @param chnId  频道id
	 * @param catId  分类id
	 * @return
	 */
	List<Article> list(@Param("chnId") Integer chnId, 
			@Param("catId") Integer catId);

	/**
	 * 获取热门文章
	 * @return
	 */
	List<Article>  listHot();
	/**
	 * 获取最新文章
	 * @param sum
	 * @return
	 */
	List<Article> listLast(int sum);

	/**
	 * 
	 * @param articleId
	 * @return
	 */
	Article findById(Integer articleId);

	/**
	 * 添加文章
	 * @param article
	 * @return
	 */
	int add(Article article);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<Article> listByUserId(Integer userId);

	/**
	 * 根据文章id删除文章
	 * @param id
	 * @return
	 */
	@Update("UPDATE cms_article SET deleted=1 WHERE id=#{value} ")
	int deleteById(Integer id);
	
	
	/**
	 * 修改文章
	 * @param article
	 * @return
	 */
	@Update("UPDATE cms_article set title=#{title},content=#{content},picture=#{picture},channel_id=#{channelId},category_id=#{categoryId},updated=now() WHERE id=#{id}")
	int update(Article article);
	
	/**
	 * 获取需要管理的文章
	 * @return
	 */
	List<Article> listAdmin(@Param("status")Integer status);
	
	/**
	 * 修改文章状态
	 * @param articleId
	 * @param status
	 * @return
	 */
	@Update("UPDATE cms_article set status=#{status},updated=now() WHERE id=#{articleId}")
	int updateStatus(@Param("articleId")Integer articleId, @Param("status")int status);
	

	/**
	 * 修改文章热门状态
	 * @param articleId
	 * @param status
	 * @return
	 */
	@Update("UPDATE cms_article set hot=#{status},updated=now() WHERE id=#{articleId}")
	int updateHot(@Param("articleId")Integer articleId,@Param("status")int status);
	

	/**
	 * 根据标签名称获取标签对象
	 * @param tag
	 * @return
	 */
	@Select("SELECT * FROM cms_term where display_name=#{value} limit 1")
	Term findTagByName(String tag);
	
	/**
	 * 增加Tag实体备案
	 * @param tagBean
	 * @return
	 */ 
	int addTag(Term tagBean);
	
	/**
	 * 增加数据到文章标签中间表
	 * @param articleId 
	 * @param tagId  
	 */
	@Insert("INSERT INTO cms_article_term values(#{articleId},#{tagId}) ")
	void addArticleTag(@Param("articleId") Integer articleId, @Param("tagId") Integer tagId);
	

	/**
	 *  删除中间表
	 * @param articleId
	 */
	@Delete(" DELETE FROM cms_article_term WHERE aid=#{value}")
	void delTagsByArticleId(Integer id);
	
	@Insert("INSERT INTO cms_comment(userId,articleId,content,created) VALUES(#{userId},#{articleId},#{content},now() )")
	void addComment(Comment comment);
	
	@Update(" UPDATE cms_article SET commentCnt=commentCnt+1 WHERE id=#{value}")
	void increaseCommentCnt(Integer articleId);
	
	@Select("SELECT c.*,u.username as userName FROM cms_comment c LEFT JOIN cms_user u ON u.id=c.userId WHERE c.articleId=#{value} ORDER BY id desc")
	List<Comment> getCommnentByArticleId(Integer articleId);
	
	/**
	 * 根据主题id获取文章列表
	 * @param id
	 * @return
	 */
	@Select("SELECT a.id,a.title,a.created FROM cms_special_article  "
			+ " sa JOIN cms_article  a ON sa.aid=a.id "
			+ " WHERE sa.sid=#{value}")
	List<Article> findBySepecailId(Integer id);

	@Select("SELECT count(1) FROM cms_special_article  "
			+ " sa JOIN cms_article  a ON sa.aid=a.id "
			+ " WHERE sa.sid=#{value}")
	Integer getArticleNum(Integer id);
	
	@Update("UPDATE cms_article SET hits = hits + 1 WHERE id =  #{id} ")
	int increaseHits(Integer id);

	@Select("SELECT c.*,u.username as userName FROM cms_comment c "
			+ " LEFT JOIN cms_user u ON u.id=c.userId "
			+ " WHERE c.userId=#{value} ORDER BY id desc")
	List<Comment> getCommnentByUserId(int id);
}
