package org.snowman.twitter.consumer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.User;

public class TwitterStatusListener implements StatusListener {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TwitterStatusListener.class);

	private KafkaTemplate<String, String> kafkaTemplate;
	
	public TwitterStatusListener(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void onException(Exception ex) {
	}

	public void onStatus(Status status) {
		
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			dataMap.put("source", "twitter");
			dataMap.put("geoLocation",  status.getGeoLocation());
			dataMap.put("favoriteCount", status.getFavoriteCount());
			dataMap.put("retweetCount", status.getRetweetCount());
			dataMap.put("lang", status.getLang());
			dataMap.put("id", status.getId());
			dataMap.put("timestamp", status.getCreatedAt());
			dataMap.put("content", status.getText());
			
			User user = status.getUser();
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("id", user.getId());
			userMap.put("name", user.getName());
			userMap.put("followersCount", user.getFollowersCount());
			userMap.put("friendsCount", user.getFriendsCount());
			userMap.put("favouritesCount", user.getFavouritesCount());
			userMap.put("createTime", user.getCreatedAt());

			dataMap.put("user", userMap);
			
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(dataMap);
			
			kafkaTemplate.send("twitter", json);
			
			logger.info("json: {}", json);
		} catch (Exception e) {
			logger.error("Send data to kafka error: {}", status, e);
		}
				
	}

	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	}

	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	}

	public void onScrubGeo(long userId, long upToStatusId) {
	}

	public void onStallWarning(StallWarning warning) {
	}


}
