package co.grandcircus.restaurantfavesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.restaurantfavesapi.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByRestaurantContainsIgnoreCase(String restaurant);

	List<Order> findByOrderAgain(Boolean orderAgain);

	List<Order> findByRestaurantContainsIgnoreCaseAndOrderAgain(String restaurant,
			Boolean orderAgain);
	
}
