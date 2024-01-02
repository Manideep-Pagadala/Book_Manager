package com.boot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	@Id
	@NotBlank(message = "User Name is Mandatory")
	@Pattern(regexp  = "^[A-Z][A-Za-z0-9]{4,8}", message = "User Name pattern is Xxxx.... ")
	private String userName;
	
	private String email;

	@Size(min = 10, max = 10, message = "Phno should contain 10 digits")
	private String phNo;

	@Size(min = 4, message = "User Pwd should contain 4 chars")
	private String password;
}
