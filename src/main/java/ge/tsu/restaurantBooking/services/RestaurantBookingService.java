package ge.tsu.restaurantBooking.services;

import ge.tsu.restaurantBooking.dao.AccessibilityTableRepository;
import ge.tsu.restaurantBooking.dao.BookedTableRepository;
import ge.tsu.restaurantBooking.dao.CustomerRepository;
import ge.tsu.restaurantBooking.dao.TablesRepository;
import ge.tsu.restaurantBooking.dto.TableBookingDTO;
import ge.tsu.restaurantBooking.models.AccessibilityTable;
import ge.tsu.restaurantBooking.models.BookedTable;
import ge.tsu.restaurantBooking.models.Customer;
import ge.tsu.restaurantBooking.models.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantBookingService {
    private final TablesRepository tablesRepository;

    private final AccessibilityTableRepository accessibilityTableRepository;

    private final CustomerRepository customerRepository;

    private final BookedTableRepository bookedTableRepository;

    @Autowired
    public RestaurantBookingService(TablesRepository tablesRepository,
                                    AccessibilityTableRepository accessibilityTableRepository,
                                    CustomerRepository customerRepository,
                                    BookedTableRepository bookedTableRepository) {
        this.tablesRepository = tablesRepository;
        this.accessibilityTableRepository = accessibilityTableRepository;
        this.customerRepository = customerRepository;
        this.bookedTableRepository = bookedTableRepository;
    }

    public List<Tables> registerClient(Long space) {
        return space == null || space < 1 ? tablesRepository.getTablesByIdIsNotNull().orElse(null) : tablesRepository.getTablesBySpace(space).orElse(null);
    }

    public List<AccessibilityTable> getAccessibilityTable() {
        return accessibilityTableRepository.getAccessibilityTableByBooked(false).orElse(null);
    }

    @Transactional
    public boolean bookTableAndCreateCustomer(TableBookingDTO bookingDTO) {
        Optional<AccessibilityTable> accessibilityTableOptional = accessibilityTableRepository.getAccessibilityTableByIdAndBooked(bookingDTO.getAccessibilityID(), false);
        if (accessibilityTableOptional.isPresent()) {
            Customer customer = new Customer();
            customer.setFirstName(bookingDTO.getFirstName());
            customer.setLastName(bookingDTO.getLastName());
            customer.setPhoneNumber(bookingDTO.getPhone());
            customerRepository.save(customer);

            AccessibilityTable accessibilityTable = accessibilityTableOptional.get();
            accessibilityTable.setBooked(true);
            accessibilityTableRepository.save(accessibilityTable);

            BookedTable bookedTable = new BookedTable();
            bookedTable.setCustomer(customer);
            bookedTable.setTable(accessibilityTable);
            bookedTable.setComment(bookingDTO.getComment());
            bookedTable.setCreateTS(LocalDateTime.now());
            bookedTableRepository.save(bookedTable);
            return true;
        } else {
            return false;
        }
    }

    public List<BookedTable> getBookedTables() {
        return bookedTableRepository.getBookedTablesByIdIsNotNull().orElse(null);
    }

    public void clearBookings() {
        List<AccessibilityTable> accessibilityTables = accessibilityTableRepository.getAccessibilityTableByBooked(true).orElse(new ArrayList<>());
        accessibilityTables.forEach(ac -> {
            ac.setBooked(false);
            accessibilityTableRepository.save(ac);
        });
    }
}
