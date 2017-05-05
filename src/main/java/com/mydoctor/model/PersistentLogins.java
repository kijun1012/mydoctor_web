package com.mydoctor.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {
	private String username;
	@Id
	private String series;
	private String token;
	private Timestamp last_used;
}
