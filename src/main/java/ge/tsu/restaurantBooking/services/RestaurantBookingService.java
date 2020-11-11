package ge.tsu.restaurantBooking.services;

import ge.tsu.restaurantBooking.dao.TablesRepository;
import ge.tsu.restaurantBooking.models.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantBookingService {
    private final TablesRepository tablesRepository;

    @Autowired
    public RestaurantBookingService(TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    public List<Tables> registerClient(Long space) {
        return space == null || space < 1 ? tablesRepository.getTablesByIdIsNotNull().orElse(null) : tablesRepository.getTablesBySpace(space).orElse(null);
    }
}
