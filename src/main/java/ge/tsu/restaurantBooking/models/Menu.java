package ge.tsu.restaurantBooking.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;

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
}
