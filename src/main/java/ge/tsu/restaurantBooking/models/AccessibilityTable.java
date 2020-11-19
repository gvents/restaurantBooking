package ge.tsu.restaurantBooking.models;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Data
@Entity
@javax.persistence.Table(name = "accessibility_table")
public class AccessibilityTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_table_id")
    private TblTable tblTable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessibility_id")
    private Accessibility accessibility;

    @Column(name = "is_booked")
    private boolean booked;

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
}
