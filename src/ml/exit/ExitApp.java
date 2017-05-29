/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.exit;

import ml.query.created.Created;
import ml.trial.TestTrial;

/**
 *
 * @author Dave
 */
public class ExitApp {

    private TestTrial testTrial = new TestTrial();
    private Created c = new Created();

    public void close() {
        if (c.getUser(testTrial.licenseTrial()) > 0) {
            c.deleteUser(testTrial.licenseTrial());
        }
        System.exit(0);
    }
}
