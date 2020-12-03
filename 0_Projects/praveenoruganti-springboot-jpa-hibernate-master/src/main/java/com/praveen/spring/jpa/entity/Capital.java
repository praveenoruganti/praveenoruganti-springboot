package com.praveen.spring.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;


@Entity
@Data
public class Capital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CAPITAL_ID", updatable = false, nullable = false)
	private Integer capitalId;
	private String name;
	private long population;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;

}
