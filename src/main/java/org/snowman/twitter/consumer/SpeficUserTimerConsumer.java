package org.snowman.twitter.consumer;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Component
public class SpeficUserTimerConsumer {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SpeficUserTimerConsumer.class);

	@PostConstruct
	public void init() {
		this.testConsume();
	}
	
	public void testConsume() {
        // gets Twitter instance with default credentials
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            String user = "LoneCapital";
            List<Status> statuses = twitter.getUserTimeline(user);
           
            System.out.println("Showing @" + user + "'s user timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (TwitterException te) {
        	logger.error("Consume data error", te);
        }
    }
}
