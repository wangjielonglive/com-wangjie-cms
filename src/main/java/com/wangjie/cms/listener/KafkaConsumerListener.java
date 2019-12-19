package com.wangjie.cms.listener;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.wangjie.cms.entity.Article;
import com.wangjie.cms.service.ArticleService;
import com.google.gson.Gson;

@Component
public class KafkaConsumerListener implements MessageListener<String, String>{

	@Resource
	private ArticleService articleService;
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Override
	public void onMessage(ConsumerRecord<String, String> record) {
		
		//获取数据
		String key = record.key();
		
		if(key != null && key.startsWith("article_")) {
			String json = record.value();
			
			//转换成Article对象
			Gson gson= new Gson();
			Article article = gson.fromJson(json, Article.class);
			
			//存入数据库
			articleService.addFromKafka(article);
			
			//将数据存入es中
			IndexQuery query = new IndexQuery();
			query.setId(article.getId().toString());
			query.setObject(article);
			elasticsearchTemplate.index(query );
		}
		
		
		
		
	}

}
