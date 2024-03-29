package ge.tsu.restaurantBooking.dao;

import ge.tsu.restaurantBooking.models.AccessibilityTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccessibilityTableRepository extends CrudRepository<AccessibilityTable, Long> {
    Optional<List<AccessibilityTable>> getAllByBooked(@Param("is_booked") boolean booked);
    Optional<List<AccessibilityTable>> getAllByBookedAndTblTable_Restaurant_Id(@Param("is_booked") boolean booked, @Param("restaurant_id") Long id);
    Optional<List<AccessibilityTable>> getAllByTblTable_Restaurant_Id(@Param("restaurant_id") Long id);
    Optional<AccessibilityTable> getByIdAndBooked(@Param("id") Long id, @Param("is_booked") boolean booked);

}
