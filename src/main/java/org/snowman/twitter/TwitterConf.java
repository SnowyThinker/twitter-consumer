package org.snowman.twitter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@Configuration
public class TwitterConf {

	@Bean
	public Twitter twitter() {
		Twitter twitter = new TwitterFactory().getInstance();
		return twitter;
	}
}
