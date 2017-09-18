package com.infotech.jersey.model;
import java.io.Serializable;
public class Topic implements Serializable { 
	
	private static final long serialVersionUID = 1L;
    private int topicId;  
    private String title;
	private String category;
	
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public int getTopicId() {
		return topicId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
} 