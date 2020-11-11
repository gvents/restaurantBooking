package ge.tsu.restaurantBooking.controllers;

import ge.tsu.restaurantBooking.models.Tables;
import ge.tsu.restaurantBooking.services.RestaurantBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("booking")
public class RestaurantBookingController {
    private RestaurantBookingService restaurantBookingService;

    @Autowired
    public RestaurantBookingController(RestaurantBookingService restaurantBookingService) {
        this.restaurantBookingService = restaurantBookingService;
    }

    @GetMapping("tables")
    public @ResponseBody
    List<Tables> registerClient(@RequestParam(required = false) Long space) {
        return restaurantBookingService.registerClient(space);
    }

}
