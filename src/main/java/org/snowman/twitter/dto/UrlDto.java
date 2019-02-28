/**
 * 
 */
package org.snowman.twitter.dto;

import java.io.Serializable;

/**
 * @author andrew
 *
 */
@SuppressWarnings("serial")
public class UrlDto implements Serializable {

	private Long id;
	private String url;
	private String type;
	private String text;
	
	public UrlDto() {
		super();
	}
	
	public UrlDto(Long id, String url, String type, String text) {
		this.id = id;
		this.url = url;
		this.type = type;
		this.text = text;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
		
	@Override
	public String toString() {
		return "UrlDto [url=" + url + ", id=" + id + ", type=" + type + ", text=" + text + "]";
	}
}
