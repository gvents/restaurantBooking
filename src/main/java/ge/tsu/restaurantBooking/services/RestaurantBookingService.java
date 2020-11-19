package ge.tsu.restaurantBooking.services;

import ge.tsu.restaurantBooking.dao.AccessibilityRepository;
import ge.tsu.restaurantBooking.dao.AccessibilityTableRepository;
import ge.tsu.restaurantBooking.dao.BookedTableRepository;
import ge.tsu.restaurantBooking.dao.CustomerRepository;
import ge.tsu.restaurantBooking.dao.MenuRepository;
import ge.tsu.restaurantBooking.dao.TablesRepository;
import ge.tsu.restaurantBooking.dto.TableBookingDTO;
import ge.tsu.restaurantBooking.models.Accessibility;
import ge.tsu.restaurantBooking.models.AccessibilityTable;
import ge.tsu.restaurantBooking.models.BookedTable;
import ge.tsu.restaurantBooking.models.Customer;
import ge.tsu.restaurantBooking.models.Menu;
import ge.tsu.restaurantBooking.models.TblTable;
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

    private final AccessibilityRepository accessibilityRepository;

    private final CustomerRepository customerRepository;

    private final BookedTableRepository bookedTableRepository;

    private final MenuRepository menuRepository;

    @Autowired
    public RestaurantBookingService(TablesRepository tablesRepository,
                                    AccessibilityTableRepository accessibilityTableRepository,
                                    AccessibilityRepository accessibilityRepository,
                                    CustomerRepository customerRepository,
                                    BookedTableRepository bookedTableRepository,
                                    MenuRepository menuRepository) {
        this.tablesRepository = tablesRepository;
        this.accessibilityTableRepository = accessibilityTableRepository;
        this.accessibilityRepository = accessibilityRepository;
        this.customerRepository = customerRepository;
        this.bookedTableRepository = bookedTableRepository;
        this.menuRepository = menuRepository;
    }

    public List<TblTable> getTables(Long space) {
        return space == null || space < 1 ? tablesRepository.getTablesByIdIsNotNull().orElse(null) : tablesRepository.getTablesBySpace(space).orElse(null);
    }

    public List<Accessibility> getAccessibility() {
        return (List<Accessibility>) accessibilityRepository.findAll();
    }

    public List<AccessibilityTable> getAccessibilityTable(Boolean booked) {
        return booked != null ? accessibilityTableRepository.getAllByBooked(booked).orElse(null) : (List<AccessibilityTable>) accessibilityTableRepository.findAll();
    }

    @Transactional
    public boolean bookTableAndCreateCustomer(TableBookingDTO bookingDTO) {
        Optional<AccessibilityTable> accessibilityTableOptional = accessibilityTableRepository.getByIdAndBooked(bookingDTO.getAccessibilityID(), false);
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
            bookedTable.setAccessibilityTable(accessibilityTable);
            bookedTable.setComment(bookingDTO.getComment());
            bookedTable.setCreateTS(LocalDateTime.now());
            bookedTableRepository.save(bookedTable);
            return true;
        } else {
            return false;
        }
    }

    public List<BookedTable> getBookedTables() {
        return (List<BookedTable>) bookedTableRepository.findAll();
    }

    @Transactional
    public void clearBookings() {
        List<AccessibilityTable> accessibilities = accessibilityTableRepository.getAllByBooked(true).orElse(new ArrayList<>());
        accessibilities.forEach(ac -> {
            ac.setBooked(false);
            accessibilityTableRepository.save(ac);
            bookedTableRepository.delete(bookedTableRepository.getByAccessibilityTable(ac));
        });
    }

    public List<Customer> getCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    public List<Menu> getMenu() {
        return (List<Menu>) menuRepository.findAll();
    }
}
