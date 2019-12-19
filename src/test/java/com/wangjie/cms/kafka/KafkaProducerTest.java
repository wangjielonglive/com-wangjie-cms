package com.wangjie.cms.kafka;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.wangjie.cms.entity.Article;
import com.wangjie.cms.entity.Cat;
import com.wangjie.cms.entity.Channel;
import com.wangjie.cms.service.CatService;
import com.wangjie.cms.service.ChannelService;
import com.wangjie.utils.FileUtils;
import com.wangjie.utils.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class KafkaProducerTest {
	
	@Resource
	private ChannelService channelServie;
	
	@Resource
	private CatService catService;
	
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	/*(1)使用工具包中流工具方法读取文件，不得乱码。（4分）
	(2)将文件名作为Article对象的title属性值。（2分）
	(3)文本内容作为Article对象的content属性值。（2分）
	(4)在文本内容中截取前140个字作为摘要。（2分）
	(5)“点击量”和“是否热门”、“频道”字段要使用随机值。（2分）
	(6)文章发布日期从2019年1月1日模拟到今天。（2分）   -2
	(7)其它的字段随便模拟。
    (8)编写Kafka生产者，然后将生成Article对象通过Kafka发送到消费端。（4分）
	(9)编写Kafka消费者，将接到的数据保存到CMS项目数据库。（4分）*/
	
	@Test
	public void sendTest() throws IOException{
		//(1)使用工具包中流工具方法读取文件，不得乱码
		List<String> fileList = FileUtils.getFileList("D:\\1706EJsoup爬虫");
		for (String file_name : fileList) {
			String content = FileUtils.readFileByLine(file_name);
			Article article = new Article();
			//(2)将文件名作为Article对象的title属性值。（2分）
			String fileName = file_name.substring(file_name.lastIndexOf("\\") + 1, file_name.lastIndexOf("."));
			article.setTitle(fileName);	
			//(3)文本内容作为Article对象的content属性值
			article.setContent(content);
			//4)在文本内容中截取前140个字作为摘要。（2分）
			String abs = null;
			if (content.length() <= 140) {
				abs = content;
			}else{
				abs = content.substring(0, 140);
			}
			
			//(5)“点击量”和“是否热门”、“频道”字段要使用随机值。
				//点击量
				article.setHits(RandomUtil.random(0, 100000));
				
				//是否热门 0 1
				article.setHot(RandomUtil.random(0, 1));
				
				//获取所有栏目数据
				List<Channel> allChnls = channelServie.getAllChnls();
				//获取随机下标
				int ch_index = RandomUtil.random(0, allChnls.size() - 1);
			
			//设置随机栏目
			Channel channel = allChnls.get(ch_index);
			article.setChannelId(channel.getId());
			
			
			//根据栏目id查询所属分类
			List<Cat> catList = catService.getListByChnlId(channel.getId());
			
			if(catList != null && catList.size() > 0) {
				//获取随机下标
				int cat_index = RandomUtil.random(0, catList.size() - 1);
			
				//获取分类id
				Cat cat = catList.get(cat_index);
				article.setCategoryId(cat.getId());
			}
			//(6)文章发布日期从2019年1月1日模拟到今天。
			Date date = RandomUtil.randomDate("2019-01-01", "2019-11-20");
			
			article.setCreated(date);
			//(7)其它的字段随便模拟。
		    //(8)编写Kafka生产者，然后将生成Article对象通过Kafka发送到消费端。（4分）

			Gson gson = new Gson();
			String json = gson.toJson(article);
			
			kafkaTemplate.sendDefault("article_"+System.currentTimeMillis(), json);
			
		}
		System.out.println("发送完毕");
	}
}
