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
    private Boolean smsCheck;

    public Settings() {
    }

    public Settings(BigDecimal rounding, Boolean smsCheck) {
        this.rounding = rounding;
        this.smsCheck = smsCheck;
    }

    public BigDecimal getRounding() {
        return rounding;
    }

    public void setRounding(BigDecimal rounding) {
        this.rounding = rounding;
    }

    public Boolean getSmsCheck() {
        return smsCheck;
    }

    public void setSmsCheck(Boolean smsCheck) {
        this.smsCheck = smsCheck;
    }

}
