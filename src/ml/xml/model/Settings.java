/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.xml.model;

import java.math.BigDecimal;

/**
 *
 * @author Dave
 */
public class Settings {

    private BigDecimal rounding;

    public Settings() {
    }

    public Settings(BigDecimal rounding) {
        this.rounding = rounding;
    }

    public BigDecimal getRounding() {
        return rounding;
    }

    public void setRounding(BigDecimal rounding) {
        this.rounding = rounding;
    }

}
