package ge.tsu.restaurantBooking.services;

import ge.tsu.restaurantBooking.dao.*;
import ge.tsu.restaurantBooking.dto.TableBookingDTO;
import ge.tsu.restaurantBooking.models.*;
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

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantBookingService(TablesRepository tablesRepository,
                                    AccessibilityTableRepository accessibilityTableRepository,
                                    AccessibilityRepository accessibilityRepository,
                                    CustomerRepository customerRepository,
                                    BookedTableRepository bookedTableRepository,
                                    MenuRepository menuRepository,
                                    RestaurantRepository restaurantRepository) {
        this.tablesRepository = tablesRepository;
        this.accessibilityTableRepository = accessibilityTableRepository;
        this.accessibilityRepository = accessibilityRepository;
        this.customerRepository = customerRepository;
        this.bookedTableRepository = bookedTableRepository;
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<TblTable> getTables(Long space, Long restaurantId) {
        return restaurantId != null || space != null ? (
                restaurantId == null ?
                        tablesRepository.getTablesBySpace(space).orElse(null) :
                        space == null ? tablesRepository.getTablesByRestaurant_Id(restaurantId).orElse(null) :
                        tablesRepository.getTablesBySpaceAndRestaurant_Id(space, restaurantId).orElse(null)
                ) : (List<TblTable>) tablesRepository.findAll();
    }

    public List<Accessibility> getAccessibility() {
        return (List<Accessibility>) accessibilityRepository.findAll();
    }

    public List<AccessibilityTable> getAccessibilityTable(Boolean booked, Long restaurantId) {
        return booked != null || restaurantId != null ?
                booked == null ?
                        accessibilityTableRepository.getAllByTblTable_Restaurant_Id(restaurantId).orElse(null) :
                        restaurantId == null ? accessibilityTableRepository.getAllByBooked(booked).orElse(null) :
                        accessibilityTableRepository.getAllByBookedAndTblTable_Restaurant_Id(booked, restaurantId).orElse(null)
                 : (List<AccessibilityTable>) accessibilityTableRepository.findAll();
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
    public void clearBookings(Long restaurantId) {
        List<AccessibilityTable> accessibilities = accessibilityTableRepository.getAllByBookedAndTblTable_Restaurant_Id(true, restaurantId).orElse(new ArrayList<>());
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
