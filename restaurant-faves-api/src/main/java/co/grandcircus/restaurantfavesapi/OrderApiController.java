package co.grandcircus.restaurantfavesapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.restaurantfavesapi.model.Order;
import co.grandcircus.restaurantfavesapi.repository.OrderRepository;

@RestController
@CrossOrigin // Allow any website to access this API
public class OrderApiController {

	@Autowired
	private OrderRepository repo;
	
	// C(R)UD -- Read All
	@GetMapping("/orders")
	public List<Order> readAll(@RequestParam(required=false) String restaurant, @RequestParam(required=false) Boolean orderAgain) {
		if (restaurant != null && !restaurant.isBlank()) {
			if (orderAgain != null) {
				return repo.findByRestaurantContainsIgnoreCaseAndOrderAgain(restaurant, orderAgain);
			} else {
				return repo.findByRestaurantContainsIgnoreCase(restaurant);
			}
		} else if (orderAgain != null) {
			return repo.findByOrderAgain(orderAgain);
		} else {
			return repo.findAll();
		}
	}
	
	// C(R)UD -- Read One
	@GetMapping("/orders/{id}")
	public Order readOne(@PathVariable("id") Long id) {
		return repo.findById(id).orElseThrow(() -> new OrderNotFoundException(id) );
	}
	
	// (C)RUD -- Create
	@PostMapping("/orders")
	@ResponseStatus(HttpStatus.CREATED)
	public Order create(@RequestBody Order avchar) {
		repo.save(avchar);
		return avchar;
	}
	
	// CRU(D) -- Delete
	@DeleteMapping("/orders/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		repo.deleteById(id);
	}
	
	// CR(U)D -- Update
	@PutMapping("/orders/{id}")
	public Order update(@PathVariable("id") Long id,
			@RequestBody Order avchar) {
		avchar.setId(id);
		return repo.save(avchar);
	}
	
	@ResponseBody
	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String characterNotFoundHandler(OrderNotFoundException ex) {
		return ex.getMessage();
	}
	
}
