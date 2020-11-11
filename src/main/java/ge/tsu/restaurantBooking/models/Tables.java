package ge.tsu.restaurantBooking.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Data
@Entity
@Table(name = "tbl_table")
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "space", nullable = false)
    private Long space;
}
