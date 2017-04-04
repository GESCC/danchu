package com.danchu.momuck.vo;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Food
 * 
 * @author lhbv1
 */
public class Food {
	
	private int seq;
	private String name;
	private int price;
	private String category;
	
	@Max(value=5, message="not allow type")
	@Min(value=0, message="not allow type")
	@JsonProperty("avg_score") 
	private float avgScore;
	
	@JsonProperty("image_main") 
	private String imageMain;
	
	@JsonProperty("image_extra")
	private String imageExtra;	
	
	private Date created;
	
	@JsonProperty("restaurant_seq") 
	private int restaurantSeq;
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(float avgScore) {
		this.avgScore = avgScore;
	}

	public String getImageMain() {
		return imageMain;
	}

	public void setImageMain(String imageMain) {
		this.imageMain = imageMain;
	}

	public String getImageExtra() {
		return imageExtra;
	}

	public void setImageExtra(String imageExtra) {
		this.imageExtra = imageExtra;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getRestaurantSeq() {
		return restaurantSeq;
	}

	public void setRestaurantSeq(int restaurantSeq) {
		this.restaurantSeq = restaurantSeq;
	}

	@Override
	public String toString() {
		return "Food [seq=" + seq + ", name=" + name + ", price=" + price + ", category=" + category + ", avgScore="
				+ avgScore + ", imageMain=" + imageMain + ", imageExtra=" + imageExtra + ", created=" + created
				+ ", restaurantSeq=" + restaurantSeq + "]";
	}
	
}
