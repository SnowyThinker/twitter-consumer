package org.snowman.twitter.consumer;

import java.util.List;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.snowman.twitter.dto.TweetDto;
import org.snowman.twitter.dto.UrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import twitter4j.MediaEntity;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;

@Component
@Slf4j
public class SpeficUserTimerConsumer {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SpeficUserTimerConsumer.class);
	
	private static final String USER = "LoneCapital";
	private static final String REDIS_KEY = "tweets";
	
	/*@Autowired
	private RedissonClient redissonClient;*/
	
	@Autowired
	private Twitter twitter;

	@PostConstruct
	public void init() {
		this.testConsume();
	}
	
	public synchronized void testConsume() {
		ObjectMapper om = new ObjectMapper();
	
		Integer page = 1;
		boolean hasMore = true;
		try {
			while (hasMore) {
				
				Paging paging = new Paging();
				paging.setCount(100);
				paging.setPage(page);
				List<Status> statuses = twitter.getUserTimeline(USER, paging);

				for (Status status : statuses) {
					TweetDto currentDto = statusToTweetDto(status);

					logger.info(om.writeValueAsString(currentDto));
				}
				
				page += 1;
				if(null == statuses || statuses.isEmpty()) {
					hasMore = false;
				}
			}

		} catch (Exception te) {
			logger.error("Consume data error", te);
		}
    }
	
	private TweetDto statusToTweetDto(Status status) {
		TweetDto tweetDto = new TweetDto(status.getId(), status.getCreatedAt(), status.getText(),
				status.getFavoriteCount(), status.getRetweetCount());

		for (MediaEntity entity : status.getMediaEntities()) {

			UrlDto urlDto = new UrlDto(entity.getId(), entity.getMediaURL(), entity.getType(), entity.getText());
			tweetDto.getUrls().add(urlDto);
		}

		return tweetDto;
	}
	
}
