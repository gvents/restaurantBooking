package ge.tsu.restaurantBooking.controllers;

import ge.tsu.restaurantBooking.dto.TableBookingDTO;
import ge.tsu.restaurantBooking.models.*;
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
    List<TblTable> getTables(@RequestParam(required = false) Long space,
                             @RequestParam(required = false) Long restaurantId) {
        return restaurantBookingService.getTables(space, restaurantId);
    }

    @GetMapping("accessibility")
    public @ResponseBody
    List<Accessibility> getAccessibility() {
        return restaurantBookingService.getAccessibility();
    }

    @GetMapping("accessibility_table")
    public @ResponseBody
    List<AccessibilityTable> getAccessibilityTable(@RequestParam(required = false) Boolean booked,
                                                   @RequestParam(required = false) Long restaurantId) {
        return restaurantBookingService.getAccessibilityTable(booked, restaurantId);
    }

    @PostMapping()
    public @ResponseBody
    ResponseEntity<String> bookTableAndCreateCustomer(@NotNull @Valid TableBookingDTO bookingDTO) {
        return restaurantBookingService.bookTableAndCreateCustomer(bookingDTO) ?
                ResponseEntity.status(HttpStatus.CREATED).build() :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("booked_tables")
    public @ResponseBody
    List<BookedTable> getBookedTables() {
        return restaurantBookingService.getBookedTables();
    }

    @PutMapping("clear")
    public @ResponseBody
    void clearBookings(Long restaurantId) {
        restaurantBookingService.clearBookings(restaurantId);
    }

    @GetMapping("customers")
    public @ResponseBody
    List<Customer> getCustomers() {
        return restaurantBookingService.getCustomers();
    }

    @GetMapping("menu")
    public @ResponseBody
    List<Menu> getMenu(@RequestParam(required = false) Long restaurantId) {
        return restaurantBookingService.getMenu(restaurantId);
    }

    @GetMapping("restaurants")
    public @ResponseBody
    List<Restaurant> getRestaurants() {
        return restaurantBookingService.getRestaurants();
    }

}
