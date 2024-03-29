package ml.model;
// Generated 17.01.2017 17:00:10 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Barcode generated by hbm2java
 */
@Entity
@Table(name = "barcode",
         catalog = "marleo"
)
public class Barcode implements java.io.Serializable {

    private Integer idBarcode;
    private Goods goods;
    private Long barcode;

    public Barcode() {
    }

    public Barcode(Goods goods) {
        this.goods = goods;
    }

    public Barcode(Goods goods, Long barcode) {
        this.goods = goods;
        this.barcode = barcode;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id_barcode", unique = true, nullable = false)
    public Integer getIdBarcode() {
        return this.idBarcode;
    }

    public void setIdBarcode(Integer idBarcode) {
        this.idBarcode = idBarcode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_goods", nullable = false)
    public Goods getGoods() {
        return this.goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Column(name = "barcode")
    public Long getBarcode() {
        return this.barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

}
