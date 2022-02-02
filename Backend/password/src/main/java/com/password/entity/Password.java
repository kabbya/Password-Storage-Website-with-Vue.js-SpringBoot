package com.password.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table (name = "Passwords")
public class Password {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pId;
	private String siteName;
	private String sitePassword;
	@Email
	private String siteUserName;
	
	@ManyToOne
	private User user;

	public Password() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSitePassword() {
		return sitePassword;
	}

	public void setSitePassword(String sitePassword) {
		this.sitePassword = sitePassword;
	}

	
	public String getSiteUserName() {
		return siteUserName;
	}

	public void setSiteUserName(String siteUserName) {
		this.siteUserName = siteUserName;
	}

	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
