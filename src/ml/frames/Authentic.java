/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.frames;

import ml.authentication.AuthenticationManag;
import ml.crypt.SHAEncode;
import ml.DesktopMain;
import ml.query.user.AuthUser;
import ml.model.UserSwing;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author dave
 */
public class Authentic extends javax.swing.JInternalFrame {

    private final ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();
    private DaoAuthenticationProvider daoAuthenticationProvider;
    private List res;
    private final AuthUser authUser = new AuthUser();
    private DesktopMain desktop = new DesktopMain();

    /**
     * Creates new form NewJInternalFrame
     */
    public Authentic() {
        initComponents();
        setTitle("Авторизация");
        setMaximizable(true);
        setResizable(true);
        setIconifiable(true);
        setClosable(true);
        //authen.addKeyListener(new KeyL());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                //При нажатии кнопки ENTER происходит вход
                passwordTextField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        int key = e.getKeyCode();
                        if (key == KeyEvent.VK_ENTER) {

                            String user = loginTextField.getText();
                            String pass = passwordTextField.getText();
                            authUser.authenticationUser(user);
                            res = authUser.displayResult();
                            result(res, pass, user);
                        }
                    }
                });
                
                //При нажатии кнопки ENTER происходит вход
                loginTextField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        int key = e.getKeyCode();
                        if (key == KeyEvent.VK_ENTER) {

                            String user = loginTextField.getText();
                            String pass = passwordTextField.getText();
                            authUser.authenticationUser(user);
                            res = authUser.displayResult();
                            result(res, pass, user);
                        }
                    }
                });
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        loginTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        passwordTextField = new javax.swing.JPasswordField();

        setName(""); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Логин");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(31, 12, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Пароль");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 12, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Вход");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 101, 0, 0);
        getContentPane().add(jLabel4, gridBagConstraints);

        loginTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 192;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 27, 0, 0);
        getContentPane().add(loginTextField, gridBagConstraints);

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 44;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(70, 17, 12, 12);
        getContentPane().add(jButton1, gridBagConstraints);

        passwordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 192;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 27, 0, 0);
        getContentPane().add(passwordTextField, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static final SHAEncode shaEnc = new SHAEncode();
    private static final AuthenticationManager am = new AuthenticationManag();

    private void result(List resultList, String pass, String user) {

        if (!(resultList.size() == 0)) {
            for (Object o : resultList) {
                UserSwing u = (UserSwing) o;

                if (shaPasswordEncoder.isPasswordValid(u.getPassword(), pass, null)) {
                    Authentication request = new UsernamePasswordAuthenticationToken(user, pass);
                    Authentication result = am.authenticate(request);
                    SecurityContextHolder.getContext().setAuthentication(result);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Пользователя с таким логином или паролем не существует");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Пользователя с таким логином или паролем не существует");
        }
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String user = loginTextField.getText();
        String pass = passwordTextField.getText();
        authUser.authenticationUser(user);
        res = authUser.displayResult();
        result(res, pass, user);

        //User g = (User) res;
        //select u.login, u.password, u.active from User u where u.login='admin'
        //select u.login, ur.authority from User u, UserRoles ur where u.admin = ur.idUser and u.login ='admin'
        //SecurityContext context = SecurityContextHolder.getContext();
        //Authentication authentication = context.getAuthentication();
        //Object userr = authentication.getPrincipal();
        //System.out.println("QQQQQQ : " + userr);
        //Object login = result.getName();
        //Object password = result.getCredentials();
        //Object auth = result.getAuthorities();
        //System.out.println("Пользователь : " + login);
        //System.out.println("Пароль : " + password.toString());
        //System.out.println("Уровень доступа : " + auth.toString());
        //Authentication authenticationn = SecurityContextHolder.getContext().
        //      getAuthentication();
        //Collection<? extends GrantedAuthority> ss = authenticationn.getAuthorities();
        //System.out.println("SSSSSSS : " + ss);
//INuxWWrZLWC9YgIpp6dcIg==
    }//GEN-LAST:event_jButton1ActionPerformed

    private void passwordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextFieldActionPerformed

    private void loginTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginTextFieldActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_loginTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JTextField loginTextField;
    public javax.swing.JPasswordField passwordTextField;
    // End of variables declaration//GEN-END:variables

}
