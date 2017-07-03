/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.exit;

import ml.modelLicense.License;
import ml.query.license.DeleteUserLicense;
import ml.trial.TestTrial;

/**
 *
 * @author Dave
 */
public class ExitApp {
    
    private TestTrial testTrial = new TestTrial();
    private License license = testTrial.license();
    private DeleteUserLicense deleteUserLicense = new DeleteUserLicense();

    public void close() {
        
        if (license.getIncludeUser() > 0) {
            //удалить одного пользователя
            deleteUserLicense.update(license);
            //c.deleteUser(testTrial.licenseTrial());
        }
        System.exit(0);
    }
}
