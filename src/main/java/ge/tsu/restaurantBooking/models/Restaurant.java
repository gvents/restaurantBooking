package ge.tsu.restaurantBooking.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@javax.persistence.Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
