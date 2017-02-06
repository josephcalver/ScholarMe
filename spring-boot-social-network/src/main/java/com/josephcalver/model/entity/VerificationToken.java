package com.josephcalver.model.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "verification")
public class VerificationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "token")
	private String token;

	@OneToOne(targetEntity = SiteUser.class)
	@JoinColumn(name = "user_id", nullable = false)
	private SiteUser siteUser;

	@Column(name = "expiry_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiry;

	@Column(name = "token_type")
	@Enumerated(EnumType.STRING)
	private VerificationTokenType type;

	@PrePersist
	private void setDate() {

		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 24);
		expiry = c.getTime();
	}

	public VerificationToken() {

	}

	public VerificationToken(String token, SiteUser siteUser, VerificationTokenType type) {
		this.token = token;
		this.siteUser = siteUser;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public SiteUser getSiteUser() {
		return siteUser;
	}

	public void setSiteUser(SiteUser siteUser) {
		this.siteUser = siteUser;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public VerificationTokenType getType() {
		return type;
	}

	public void setType(VerificationTokenType type) {
		this.type = type;
	}

}
