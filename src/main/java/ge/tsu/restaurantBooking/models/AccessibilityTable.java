package ge.tsu.restaurantBooking.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table(name = "accessibility_table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccessibilityTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "tbl_table_id")
    private TblTable tblTable;

    @ManyToOne()
    @JoinColumn(name = "accessibility_id")
    private Accessibility accessibility;

    @Column(name = "is_booked")
    private Boolean booked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessibilityTable that = (AccessibilityTable) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TblTable getTblTable() {
        return tblTable;
    }

    public void setTblTable(TblTable tblTable) {
        this.tblTable = tblTable;
    }

    public Accessibility getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Accessibility accessibility) {
        this.accessibility = accessibility;
    }

    public Boolean isBooked() {
        return booked;
    }

    public void setBooked(Boolean booked) {
        this.booked = booked;
    }
}
