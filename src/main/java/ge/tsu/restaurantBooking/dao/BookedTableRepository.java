package ge.tsu.restaurantBooking.dao;

import ge.tsu.restaurantBooking.models.AccessibilityTable;
import ge.tsu.restaurantBooking.models.BookedTable;
import org.springframework.data.repository.CrudRepository;

public interface BookedTableRepository extends CrudRepository<BookedTable, Long> {
    BookedTable getByAccessibilityTable(AccessibilityTable accessibilityTable);
}
