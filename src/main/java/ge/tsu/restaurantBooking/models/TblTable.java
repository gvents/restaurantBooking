package ge.tsu.restaurantBooking.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@javax.persistence.Table(name = "tbl_table")
public class TblTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "space", nullable = false)
    private Long space;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;
}
