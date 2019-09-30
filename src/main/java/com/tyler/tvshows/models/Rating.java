package com.tyler.tvshows.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name = "ratings")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@DecimalMin("1.0") @DecimalMax("5.0")
	private double value;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tvshow_id")
	private TvShow tvshow;
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	public Rating() {
		
	}
	public Rating(double value, User user, TvShow tvshow) {
		this.value = value;
		this.user = user;
		this.tvshow = tvshow;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTvshow(TvShow tvshow) {
		this.tvshow = tvshow;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Long getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public TvShow getTvshow() {
		return tvshow;
	}
	public double getValue() {
		return value;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}
}