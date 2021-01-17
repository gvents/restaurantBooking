package ge.tsu.restaurantBooking.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dish", nullable = false)
    private String dish;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "photo")
    private String photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;
}
