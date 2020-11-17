package ge.tsu.restaurantBooking.dao;

import ge.tsu.restaurantBooking.models.BookedTable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookedTableRepository extends CrudRepository<BookedTable, Long> {
    Optional<List<BookedTable>> getBookedTablesByIdIsNotNull();

}
