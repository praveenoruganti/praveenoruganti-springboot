package com.praveen.security.student;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class Student {
	private Integer id;
	private String name;

}
