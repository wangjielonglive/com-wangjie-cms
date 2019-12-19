package com.wangjie.cms.es;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangjie.cms.common.ArticleType;
import com.wangjie.cms.entity.Article;
import com.wangjie.cms.utils.ESUtils;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ESTest {
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Test
	public void testhl() {
		AggregatedPage<?> selectObjects = ESUtils.selectObjects(elasticsearchTemplate, Article.class, 0, 10, "id", new String[] {"title","content"}, "湘西");

		List<?> content = selectObjects.getContent();
		for (Object object : content) {
			
			System.out.println(object);
			
		}
	}
}
