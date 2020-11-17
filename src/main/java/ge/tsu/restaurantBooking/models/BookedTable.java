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
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "booked_table")
public class BookedTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessibility_id", nullable = false)
    private AccessibilityTable table;

    @Column(name = "customer_comment")
    private String comment;

    @Column(name = "create_ts", nullable = false)
    private LocalDateTime createTS;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookedTable that = (BookedTable) o;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccessibilityTable getTable() {
        return table;
    }

    public void setTable(AccessibilityTable table) {
        this.table = table;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreateTS() {
        return createTS;
    }

    public void setCreateTS(LocalDateTime createTS) {
        this.createTS = createTS;
    }
}
