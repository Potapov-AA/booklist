package com.jester.booklist.service;

import lombok.Data;

@Data
public class BookCreationRequest {
	
	private String title;
	private String anotation;
	private String opinion;
	private Boolean status;
	
	private Long authorId;
}
