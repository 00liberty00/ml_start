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
 * Favorite generated by hbm2java
 */
@Entity
@Table(name = "favorite",
         catalog = "marleo"
)
public class Favorite implements java.io.Serializable {

    private Integer idFavorite;
    private Goods goods;;
    private CategoryFavorite categoryFavorite;
    private String buttonName;

    
    
    public Favorite() {
    }

    public Favorite(Goods goods, CategoryFavorite categoryFavorite) {
        this.goods = goods;
        this.categoryFavorite = categoryFavorite;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id_favorite", unique = true, nullable = false)
    public Integer getIdFavorite() {
        return this.idFavorite;
    }

    public void setIdFavorite(Integer idFavorite) {
        this.idFavorite = idFavorite;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_goods", nullable=false)
    public Goods getGoods() {
        return this.goods;
    }
    
    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_categoryFavorite", nullable = false)
    public CategoryFavorite getCategoryFavorite() {
        return this.categoryFavorite;
    }

    public void setCategoryFavorite(CategoryFavorite categoryFavorite) {
        this.categoryFavorite = categoryFavorite;
    }

    @Column(name = "buttonName", nullable = false)
    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }
    
}
