package org.snowman.twitter.consumer;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.snowman.twitter.dto.TweetDto;

import junit.framework.TestCase;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class ConsumeSpecficUserTest extends TestCase {

	@Test
	public void testConsume() {
        // gets Twitter instance with default credentials
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            List<Status> statuses;
            String user;
            //if (args.length == 1) {
                user = "LoneCapital";
                statuses = twitter.getUserTimeline(user);
            /*} else {
                user = twitter.verifyCredentials().getScreenName();
                statuses = twitter.getUserTimeline();
            }*/
            System.out.println("Showing @" + user + "'s user timeline.");
            
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                this.appendToEvernoteHtml(status);
            }
        } catch (Exception te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }

	/**
	 * @param status
	 * @throws IOException 
	 */
	private void appendToEvernoteHtml(Status status) throws IOException {
		TweetDto currentDto = SpeficUserTimerConsumer.statusToTweetDto(status);
		String fileName = String.format("/Users/andrew/Documents/LoneCapital/%s.html", currentDto.getId());
		
		FileWriter fr = new FileWriter(fileName);
		String format = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"
				+ "<meta name=\"exporter-version\" content=\"Evernote Mac 6.13.1 (455786)\"/>"
				+ "<meta name=\"created\" content=\"%s\"/>"
				+ "<meta name=\"updated\" content=\"%s\"/>"
				+ "<title>%s</title></head>"
				+ "<body>%s</div></body></html>";
		
		String time = date2Gmt(currentDto.getCreateDate());
		String title = currentDto.getText();
		String content = currentDto.getText();
		String note = String.format(format, time, time, title, content);
		fr.append(note);
		fr.flush();
		fr.close();
	}

	/**
	 * @param createDate
	 * @return
	 */
	private String date2Gmt(Date createDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
		return sdf.format(createDate);
	}
	
	@Test
	public void testDate() {
		System.out.println(date2Gmt(new Date()));
	}
}
