package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message="value required")
	@Size(min=5, max=20,message="min 2 max 20 character is required")
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	private boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "user")
	private List<Disease>disease=new ArrayList<>();
	
	private String about;
	private String Imageurl;
	
}
