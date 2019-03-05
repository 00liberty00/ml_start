/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.table;

import java.math.BigDecimal;

/**
 *
 * @author Dave
 */
public class AccountingGoodsTable {

    private String code;
    private String name;
    private BigDecimal price;
    private BigDecimal residue;
    private String residueFact;
    private BigDecimal residueNew;
    private BigDecimal redisueDifferent;

    public AccountingGoodsTable() {
    }

    public AccountingGoodsTable(String code, String name, BigDecimal price, BigDecimal residue, String residueFact, BigDecimal residueNew, BigDecimal redisueDifferent) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.residue = residue;
        this.residueFact = residueFact;
        this.residueNew = residueNew;
        this.redisueDifferent = redisueDifferent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getResidue() {
        return residue;
    }

    public void setResidue(BigDecimal residue) {
        this.residue = residue;
    }

    public String getResidueFact() {
        return residueFact;
    }

    public void setResidueFact(String residueFact) {
        this.residueFact = residueFact;
    }

    public BigDecimal getResidueNew() {
        return residueNew;
    }

    public void setResidueNew(BigDecimal residueNew) {
        this.residueNew = residueNew;
    }

    public BigDecimal getRedisueDifferent() {
        return redisueDifferent;
    }

    public void setRedisueDifferent(BigDecimal redisueDifferent) {
        this.redisueDifferent = redisueDifferent;
    }

}
