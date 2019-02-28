/**
 * 
 */
package org.snowman.twitter.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author andrew
 *
 */
@SuppressWarnings("serial")
public class TweetDto implements Serializable {

	public TweetDto() {
		super();
	}

	public TweetDto(Long id, Date createDate, String text, Integer favoriteCount, Integer retweetCount) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.text = text;
		this.favoriteCount = favoriteCount;
		this.retweetCount = retweetCount;
	}

	private Long id;
	private Date createDate;
	private String text;
	private Integer favoriteCount;
	private Integer retweetCount;
	
	private List<UrlDto> urls = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(Integer favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public Integer getRetweetCount() {
		return retweetCount;
	}

	public void setRetweetCount(Integer retweetCount) {
		this.retweetCount = retweetCount;
	}

	public List<UrlDto> getUrls() {
		return urls;
	}

	public void setUrls(List<UrlDto> urls) {
		this.urls = urls;
	}

	@Override
	public String toString() {
		return "TweetDto [id=" + id + ", createDate=" + createDate + ", text=" + text + ", favoriteCount="
				+ favoriteCount + ", retweetCount=" + retweetCount + ", urls=" + urls + "]";
	}
}
