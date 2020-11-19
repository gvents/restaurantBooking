package ge.tsu.restaurantBooking.dao;

import ge.tsu.restaurantBooking.models.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, Long> {

}
