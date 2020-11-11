package ge.tsu.restaurantBooking.dao;

import ge.tsu.restaurantBooking.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
