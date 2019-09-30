package com.tyler.tvshows.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Size(min=3)
	private String firstName;
	@Size(min=3)
	private String lastName;
	@Size(min=6)
	private String email;
	@Size(min=8)
	private String password;
	@Transient
	private String passwordConfirm;
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Rating> ratings;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	public User() {

	}
	public User(String firstName, String lastName, String email, String password, String passwordConfirm) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
	}
	public boolean hasRatedShowBefore(TvShow tvshow) {
		for(Rating rating : ratings) {
			TvShow ratedShow = rating.getTvshow();
			if(ratedShow.getId() == tvshow.getId()) {
				return true;
			}
		}
		return false;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	public void addRating(Rating rating) {
		ratings.add(rating);
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
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public List<Rating> getRatings() {
		return ratings;
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