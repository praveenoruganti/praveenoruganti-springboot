package com.praveen.spring.jpa.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.praveen.spring.jpa.entity.Book1;

public class Book1RepositoryImpl implements Book1RepositoryCustom {

	@Autowired
	EntityManager em;

	@Override
	public List<Book1> findBooksByAuthorNameAndTitle(String authorName, String title) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book1> cq = cb.createQuery(Book1.class);

		Root<Book1> book = cq.from(Book1.class);
		List<Predicate> predicates = new ArrayList<>();

		if (authorName != null) {
			predicates.add(cb.equal(book.get("author"), authorName));
		}
		if (title != null) {
			predicates.add(cb.like(book.get("title"), "%" + title + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));

		return em.createQuery(cq).getResultList();
	}

}
