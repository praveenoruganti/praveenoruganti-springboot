package com.praveen.spring.jpa.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Country implements Serializable {
	private static final long serialVersionUID = -4489062599286034483L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUNTRY_ID", updatable = false, nullable = false)
	private Integer countryId;
	@Column(name = "COUNTRY_NAME", unique = true, nullable = false, length = 100)
	private String countryName;
	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private Set<City> cities = new HashSet<>();	
	@OneToOne(mappedBy = "country")
	private Capital capital;

}
