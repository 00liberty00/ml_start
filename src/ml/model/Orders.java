package ml.model;
// Generated 17.01.2017 17:00:10 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Orders generated by hbm2java
 */
@Entity
@Table(name = "orders",
        catalog = "marleo"
)
public class Orders implements java.io.Serializable {

    private Integer idOrder;
    private BigDecimal payment;
    private Date date;
    private String note;
    private UserSwing userSwing;

    public Orders() {
    }

    public Orders(BigDecimal payment, Date date, String note) {
        this.payment = payment;
        this.date = date;
        this.note = note;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id_order", unique = true, nullable = false)
    public Integer getIdOrder() {
        return this.idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    @Column(name = "payment", nullable = false, precision = 9)
    public BigDecimal getPayment() {
        return this.payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, length = 19)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "note", nullable = false, length = 100)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    public UserSwing getUserSwing() {
        return this.userSwing;
    }

    public void setUserSwing(UserSwing userSwing) {
        this.userSwing = userSwing;
    }

}
