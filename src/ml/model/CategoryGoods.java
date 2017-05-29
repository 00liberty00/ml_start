package ml.model;
// Generated 17.01.2017 17:00:10 by Hibernate Tools 4.3.1

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

/**
 * CategoryGoods generated by hbm2java
 */
@Entity
@Table(name = "categorygoods",
         catalog = "marleo"
)
public class CategoryGoods implements java.io.Serializable {

    private Integer idCategoryGoods;
    private String name;
    private String note;
    private Set goodses = new HashSet(0);
    private Set goodsBackups = new HashSet(0);

    public CategoryGoods() {
    }

    public CategoryGoods(String name, String note, Set goodses, Set goodsBackups) {
        this.name = name;
        this.note = note;
        this.goodses = goodses;
        this.goodsBackups = goodsBackups;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id_categoryGoods", unique = true, nullable = false)
    public Integer getIdCategoryGoods() {
        return this.idCategoryGoods;
    }

    public void setIdCategoryGoods(Integer idCategoryGoods) {
        this.idCategoryGoods = idCategoryGoods;
    }

    @Column(name = "name", length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "note", length = 50)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryGoods")
    public Set getGoodses() {
        return this.goodses;
    }

    public void setGoodses(Set goodses) {
        this.goodses = goodses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryGoods")
    public Set getGoodsBackups() {
        return this.goodsBackups;
    }

    public void setGoodsBackups(Set goodsBackups) {
        this.goodsBackups = goodsBackups;
    }

}
