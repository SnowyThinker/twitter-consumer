package org.snowman.twitter.consumer;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Component
public class TwitterStreamConsumer {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TwitterStreamConsumer.class);
	
	private static final String[] keywords = {
			"经济", "股票", "股市", "美股", "中概股", "美元指数", "汇率", "外汇", "纳斯达克", "标普", "黄金", "原油", "白银", "大宗商品", "财经", "证券", "期货", "期指",
			"利率", "同业拆借", "国债", "资本", "华尔街", "彭博", "黑石", "量化交易",
			"巴菲特", "索罗斯",
			"散户", "韭菜",
			"中期选举", "大选", "选举", "川普", "特朗普", "贸易战", "中美", "美联储", "鸽派", "鹰派", "加息", "非农",
			"新华网", "两岸", "一国两制", "九二共识", "民主", "独裁","共和",
			"苹果", 
			"人工智能", "机器学习", 
			"明镜"
			// "Artificial Intelligence", "Machine Learning", "Deep Learning", "Robot", "AI",
			// "Federal Funds Rate"
	};
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@PostConstruct
	public void init() {
		
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		twitterStream.addListener(new TwitterStatusListener(kafkaTemplate));
		
		FilterQuery query = new FilterQuery();
		query.track(keywords);
		query.language(new String[]{"zh"});
		twitterStream.filter(query);
		//twitterStream.sample("zh");
		
		logger.info("Consumer started...");
	}

}
