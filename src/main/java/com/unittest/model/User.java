package com.unittest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "firstName", nullable = false)
	private String firstName;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "email", nullable = false)
	private String email;


}


