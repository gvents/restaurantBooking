package ge.tsu.restaurantBooking.dao;

import ge.tsu.restaurantBooking.models.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends CrudRepository<Menu, Long> {
    Optional<List<Menu>> getMenuByRestaurant_Id(@Param("restaurant_id") Long id);

}
