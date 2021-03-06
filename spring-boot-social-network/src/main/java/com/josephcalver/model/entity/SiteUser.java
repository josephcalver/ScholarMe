package com.josephcalver.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.josephcalver.validation.PasswordMatch;

@Entity
@Table(name = "users")
@PasswordMatch(message = "{register.repeatpassword.mismatch}")
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "email", unique = true)
	@Email(message = "{register.email.invalid}")
	@NotBlank(message = "{register.email.invalid}")
	private String email;

	@Size(min = 2, max = 20, message = "{register.firstname.size}")
	@Column(name = "first_name", length = 20)
	private String firstName;

	@Size(min = 2, max = 25, message = "{register.lastname.size}")
	@Column(name = "last_name", length = 25)
	private String lastName;

	@Transient
	@Size(min = 5, max = 15, message = "{register.password.size}")
	private String plainPassword;

	@Transient
	private String repeatPassword;

	@Column(name = "password", length = 60)
	private String password;

	@Column(name = "role", length = 20)
	private String role;

	@Column(name = "enabled")
	private Boolean enabled = false;

	public SiteUser() {

	}

	public SiteUser(String email, String password, String firstName, String lastName) {
		this.email = email;
		this.plainPassword = password;
		this.repeatPassword = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.password = new BCryptPasswordEncoder().encode(plainPassword);
		this.plainPassword = plainPassword;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	// Create a user object that is safe to display via JSP
	public void safeCopyFrom(SiteUser unsafeUser) {

		if (unsafeUser.id != null) {
			this.id = unsafeUser.id;
		}

		if (unsafeUser.firstName != null) {
			this.firstName = unsafeUser.firstName;
		}

		if (unsafeUser.lastName != null) {
			this.lastName = unsafeUser.lastName;
		}
	}

	@Override
	public String toString() {
		return "SiteUser [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", plainPassword=" + plainPassword + ", repeatPassword=" + repeatPassword + ", password=" + password
				+ ", role=" + role + ", enabled=" + enabled + "]";
	}

}
