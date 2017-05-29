package ml.model;
// Generated 17.01.2017 17:00:10 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Writeoff generated by hbm2java
 */
@Entity
@Table(name = "writeoff",
        catalog = "marleo"
)
public class Writeoff implements java.io.Serializable {

    private Long idWriteoff;
    private Date date;
    private BigDecimal sum;
    private String note;
    private UserSwing userSwing;
    private Set writeoffLists = new HashSet(0);
    private Set caseRecord = new HashSet(0);

    public Writeoff() {
    }

    public Writeoff(Date date, UserSwing userSwing) {
        this.date = date;
        this.userSwing = userSwing;
    }

    public Writeoff(Date date, BigDecimal sum, String note, UserSwing userSwing, Set writeoffLists, Set caseRecord) {
        this.date = date;
        this.sum = sum;
        this.note = note;
        this.userSwing = userSwing;
        this.writeoffLists = writeoffLists;
        this.caseRecord = caseRecord;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id_writeoff", unique = true, nullable = false)
    public Long getIdWriteoff() {
        return this.idWriteoff;
    }

    public void setIdWriteoff(Long idWriteoff) {
        this.idWriteoff = idWriteoff;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, length = 19)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "sum", precision = 9)
    public BigDecimal getSum() {
        return this.sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Column(name = "note", length = 500)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "id_user", nullable = false)
    public UserSwing getUserSwing() {
        return this.userSwing;
    }

    public void setUserSwing(UserSwing userSwing) {
        this.userSwing = userSwing;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "writeoff")
    public Set getWriteoffLists() {
        return this.writeoffLists;
    }

    public void setWriteoffLists(Set writeoffLists) {
        this.writeoffLists = writeoffLists;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "caseRecord")
    public Set getCaseRecord() {
        return this.caseRecord;
    }

    public void setCaseRecord(Set caseRecord) {
        this.caseRecord = caseRecord;
    }
}
