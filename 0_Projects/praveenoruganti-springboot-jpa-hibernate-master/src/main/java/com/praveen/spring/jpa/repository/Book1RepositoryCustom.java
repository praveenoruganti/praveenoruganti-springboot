package com.praveen.spring.jpa.repository;

import java.util.List;

import com.praveen.spring.jpa.entity.Book1;

public interface Book1RepositoryCustom {
	List<Book1> findBooksByAuthorNameAndTitle(String authorName, String title);
}
