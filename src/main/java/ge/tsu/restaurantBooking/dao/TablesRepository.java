package ge.tsu.restaurantBooking.dao;

import ge.tsu.restaurantBooking.models.Tables;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TablesRepository extends CrudRepository<Tables, Long> {
    Optional<List<Tables>> getTablesBySpace(@Param("space") Long space);
    Optional<List<Tables>> getTablesByIdIsNotNull();
}
