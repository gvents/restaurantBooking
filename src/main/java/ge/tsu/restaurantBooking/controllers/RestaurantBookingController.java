package ge.tsu.restaurantBooking.controllers;

import ge.tsu.restaurantBooking.dto.TableBookingDTO;
import ge.tsu.restaurantBooking.models.AccessibilityTable;
import ge.tsu.restaurantBooking.models.BookedTable;
import ge.tsu.restaurantBooking.models.TblTable;
import ge.tsu.restaurantBooking.services.RestaurantBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("booking")
public class RestaurantBookingController {
    private final RestaurantBookingService restaurantBookingService;

    @Autowired
    public RestaurantBookingController(RestaurantBookingService restaurantBookingService) {
        this.restaurantBookingService = restaurantBookingService;
    }

    @GetMapping("tables")
    public @ResponseBody
    List<TblTable> registerClient(@RequestParam(required = false) Long space) {
        return restaurantBookingService.registerClient(space);
    }

    @GetMapping("accessibility")
    public @ResponseBody
    List<AccessibilityTable> getAccessibilityTable() {
        return restaurantBookingService.getAccessibilityTable();
    }

    @PostMapping()
    public @ResponseBody
    ResponseEntity<String> bookTableAndCreateCustomer(@NotNull @Valid TableBookingDTO bookingDTO) {
        return restaurantBookingService.bookTableAndCreateCustomer(bookingDTO) ?
                ResponseEntity.status(HttpStatus.CREATED).build() :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("bookedtables")
    public @ResponseBody
    List<BookedTable> getBookedTables() {
        return restaurantBookingService.getBookedTables();
    }

    @PutMapping("clear")
    public @ResponseBody
    void clearBookings() {
        restaurantBookingService.clearBookings();
    }

}
