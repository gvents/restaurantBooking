package ge.tsu.restaurantBooking.dao;

import ge.tsu.restaurantBooking.models.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

}
