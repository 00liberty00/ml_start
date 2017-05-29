/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.window;

import javafx.beans.Observable;
import ml.Ml_FX;

/**
 * Новая сессия
 * @author dave
 */
public class NewSessionWindow {

    public NewSessionWindow() {
        Ml_FX ml = new Ml_FX();
        Observable o = null;
        ml.update(o, ml);

    }
}
