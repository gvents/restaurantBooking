package ge.tsu.restaurantBooking.dao;

import ge.tsu.restaurantBooking.models.TblTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TablesRepository extends CrudRepository<TblTable, Long> {
    Optional<List<TblTable>> getTablesBySpace(@Param("space") Long space);
    Optional<List<TblTable>> getTablesByRestaurant_Id(@Param("restaurant_id") Long id);
    Optional<List<TblTable>> getTablesBySpaceAndRestaurant_Id(@Param("space") Long space, @Param("restaurant_id") Long id);
    Optional<List<TblTable>> getTablesByIdIsNotNull();
}
