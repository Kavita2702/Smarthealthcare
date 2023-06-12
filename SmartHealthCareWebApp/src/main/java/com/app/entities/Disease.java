package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Disease")
@Getter
@Setter
@ToString
public class Disease {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	private String name;
	
	private String email;
	
	private String phone;
	private String disease;
	private int age;
	@ManyToOne
	private User user;
	@Column(length=500)
	private String description;
}
