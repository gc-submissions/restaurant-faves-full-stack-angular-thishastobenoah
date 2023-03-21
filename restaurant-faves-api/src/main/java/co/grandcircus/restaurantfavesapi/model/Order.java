package co.grandcircus.restaurantfavesapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	@Column(length = 60)
	private String restaurant;
	private Integer rating;
	private Boolean orderAgain;

	public Order() {
		super();
	}

	public Order(Long id, String description, String restaurant, Integer rating,
			Boolean orderAgain) {
		super();
		this.id = id;
		this.description = description;
		this.restaurant = restaurant;
		this.rating = rating;
		this.orderAgain = orderAgain;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Boolean getOrderAgain() {
		return orderAgain;
	}

	public void setOrderAgain(Boolean orderAgain) {
		this.orderAgain = orderAgain;
	}

}
