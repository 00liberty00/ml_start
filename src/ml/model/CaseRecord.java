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
 * CaseRecord generated by hbm2java
 */
@Entity
@Table(name = "case_record",
        catalog = "marleo"
)
public class CaseRecord implements java.io.Serializable {

    private Long idCase;
    private Date date;
    private BigDecimal cashMustBe;
    private UserSwing userSwing;
    private Long idArrival;
    private CashIn cashIn;
    private CashOut cashOut;
    private Arrival arrival;
    private Writeoff writeOff;

    public CaseRecord() {
    }

    public CaseRecord(Date date, UserSwing userSwing) {
        this.date = date;
        this.userSwing = userSwing;
    }

    public CaseRecord(Date date, BigDecimal cashMustBe, CashIn cashIn, CashOut cashOut, Long idArrival, UserSwing userSwing, Writeoff writeOff, Arrival arrival) {
        this.date = date;
        this.cashMustBe = cashMustBe;
        this.cashIn = cashIn;
        this.cashOut = cashOut;
        this.userSwing = userSwing;
        this.idArrival = idArrival;
        this.writeOff = writeOff;
        this.arrival = arrival;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id_case", unique = true, nullable = false)
    public Long getIdCase() {
        return this.idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, length = 19)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "cashMustBe", precision = 9)
    public BigDecimal getCashMustBe() {
        return this.cashMustBe;
    }

    public void setCashMustBe(BigDecimal cashMustBe) {
        this.cashMustBe = cashMustBe;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    public UserSwing getUserSwing() {
        return this.userSwing;
    }

    public void setUserSwing(UserSwing userSwing) {
        this.userSwing = userSwing;
    }

    @Column(name = "id_arrival")
    public Long getIdArrival() {
        return this.idArrival;
    }

    public void setIdArrival(Long idArrival) {
        this.idArrival = idArrival;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cashIn", nullable = false)
    public CashIn getCashIn() {
        return this.cashIn;
    }

    public void setCashIn(CashIn cashIn) {
        this.cashIn = cashIn;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cashOut", nullable = false)
    public CashOut getCashOut() {
        return this.cashOut;
    }

    public void setCashOut(CashOut cashOut) {
        this.cashOut = cashOut;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_writeoff", nullable = false)
    public Writeoff getWriteOff() {
        return this.writeOff;
    }

    public void setWriteOff(Writeoff writeOff) {
        this.writeOff = writeOff;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_arrival", nullable = false)
    public Arrival getArrival() {
        return this.arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

}
