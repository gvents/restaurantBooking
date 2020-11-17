package ge.tsu.restaurantBooking.dao;

import ge.tsu.restaurantBooking.models.AccessibilityTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccessibilityTableRepository extends CrudRepository<AccessibilityTable, Long> {
    Optional<List<AccessibilityTable>> getAccessibilityTableByBooked(@Param("is_booked") boolean booked);
    Optional<AccessibilityTable> getAccessibilityTableByIdAndBooked(@Param("id") Long id, @Param("is_booked") boolean booked);

}
