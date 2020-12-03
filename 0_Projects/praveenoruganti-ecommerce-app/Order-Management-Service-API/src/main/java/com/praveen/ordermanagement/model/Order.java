package com.praveen.ordermanagement.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Order {

	private String item;
	private String description;
	private BigDecimal price;

}
