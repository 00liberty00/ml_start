/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml;

/**
 *
 * @author dave
 */
import java.awt.Color;
import java.awt.Image;
import javax.swing.JDesktopPane;
import javax.swing.UIManager;

/**
 *
 * @author dvidal
 */
public class DesktopMain extends JDesktopPane {

    private Image cache;
    private Color bgColor;

    public DesktopMain() {
        bgColor = UIManager.getDefaults().getColor("Desktop");
    }

    @Override
    public void updateUI() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    break;

                }
            }
        } catch (Exception ex) {

        }
    }

}
