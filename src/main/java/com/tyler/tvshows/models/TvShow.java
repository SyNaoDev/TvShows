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

import com.tyler.tvshows.utility.SortRatings;

@Entity
@Table(name="tvshows")
public class TvShow {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Size(min=3)
	private String title;
	@Size(min=3)
	private String network;
	@OneToMany(mappedBy="tvshow", fetch=FetchType.LAZY)
	private List<Rating> ratings;
	@Transient
	private double averageRating;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	public TvShow() {

	}
	public TvShow(String title, String network) {
		this.title = title;
		this.network = network;
	}
	public void calcAverageRating() {
		double total = 0.0;
		double values = 0.0;
		for(Rating rating : ratings) {
			total += 1.0;
			values += rating.getValue();
		}
		if(total != 0.0) {
			averageRating = values / total;
		} else {
			averageRating = 0.0;
		}
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	public void sortRatings() {
		SortRatings.sort(ratings);
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
	public String getTitle() {
		return title;
	}
	public String getNetwork() {
		return network;
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