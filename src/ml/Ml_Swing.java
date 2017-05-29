/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml;

import ml.authentication.GrantedAuth;
import ml.crypt.SHAEncode;
import ml.frames.Authentic;
import ml.ipmac.IpMac;
import java.awt.KeyboardFocusManager;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import ml.frames.Check;
import org.apache.log4j.Logger;

/**
 *
 * @author dave
 */
public class Ml_Swing extends javax.swing.JFrame {

    // Инициализация логера
    private static final Logger log = Logger.getLogger(Ml_Swing.class);
    private DesktopMain desktop = new DesktopMain();
    private GrantedAuth grantedAuth = new GrantedAuth();
    private static SHAEncode shaEnc = new SHAEncode();
    private final static KeyboardFocusManager manag = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    private IpMac ipMac = new IpMac();

    //private Trial trial = new Trial();
    /**
     * Creates new form Goods
     */
    public Ml_Swing() {

        //testTrial.countTrial();
        //testTrial.firstDate();
        //Дальнейшее создание программы
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("My Cash");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);

        //При запуске программы проверка на Trial-Version
        start();

        //Предупреждение о закрытии окна
        Ml_Swing.this.addWindowListener(new WindowListener() {

            public void windowClosing(WindowEvent event) {
                Object[] options = {"Да", "Нет!"};
                int n = JOptionPane
                        .showOptionDialog(event.getWindow(), "Закрыть окно?",
                                "Подтверждение", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (n == 0) {
                    event.getWindow().setVisible(false);
                    System.exit(0);
                }
            }

            public void windowActivated(WindowEvent event) {
            }

            public void windowClosed(WindowEvent event) {
            }

            public void windowDeactivated(WindowEvent event) {
            }

            public void windowDeiconified(WindowEvent event) {
            }

            public void windowIconified(WindowEvent event) {
            }

            public void windowOpened(WindowEvent event) {
            }
        });

    }

    protected void start() {
        File f = new File("src/hibernate.cfg.xml");
        if (f.exists()) {
//            TestTrial testTrial = new TestTrial();
//            if (testTrial.testDateTrial() == false && testTrial.falseTrial() == false) {
//                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!! Триал-версия окончена");
//
//            } else {
            frameAuthentication();
//                System.out.println(ipMac.getIp());
//                System.out.println(ipMac.getMac());
//            }
        } else {
            frameSettingsDB();
            // frameAddAdminUser();
        }
    }

    protected void frameAuthentication() {
        Authentic frame = new Authentic();
        frame.setVisible(true);
        //desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        setContentPane(desktop);
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void frameCheck() {
        Check frame = new Check();
        frame.setVisible(true);
        //desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        setContentPane(desktop);
        desktop.add(frame);
        try {
            frame.setMaximum(true);
        } catch (PropertyVetoException e) {
        }
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void frameGoodsList() {

    }

    protected void frameFavoriteGoods() {

    }

    protected void frameSettingsDB() {

    }

    protected void frameCancellation() {

    }

    protected void frameNewCookProd() {

    }

    protected void frameNewCategoryFavorite() {

    }

    protected void frameNewCategory() {

    }

    protected void frameOfficialIntro() {

    }

    protected void frameWithDrawals() {

    }

    protected void frameEndShift() {

    }

    protected void frameTechMap() {

    }

    protected void frameChangePassUser() {

    }

    protected void frameDayMenu() {

    }

    protected void frameReportsMenu() {

    }

    protected void frameAllReports() {

    }

    protected void frameNewUser() {

    }

    protected void frameViewCheck() {

    }

    protected void frameViewArrival() {

    }

    protected void frameLicenseMenu() {

    }

    protected void frameReportsOfGoods() {

    }

    protected void frameReportsOfGoodsForAdmin() {

    }

    protected void frameSettingsPrintersMenu() {

    }

    protected void framePrint() {

    }

    protected void framePrintBarcode() {

    }

    protected void frameAccounting() {

    }

    protected void frameViewCancellation() {

    }

    protected void frameGeneralReports() {

    }

    protected void frameSettingsUser() {

    }

    protected void frameAccountingAndBackup() {

    }

    protected void frameViewAccounting() {

    }

    protected void frameArrival() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        indMenu = new javax.swing.JMenu();
        indexMenu = new javax.swing.JMenuItem();
        arrGoodsMenu = new javax.swing.JMenu();
        arrivalGoodsMenu = new javax.swing.JMenuItem();
        arrivalCookProdMenu = new javax.swing.JMenuItem();
        productCancellationMenu = new javax.swing.JMenuItem();
        cashMenu = new javax.swing.JMenu();
        officialIntrodMenu = new javax.swing.JMenuItem();
        widthdrawalMenu = new javax.swing.JMenuItem();
        endShMenu = new javax.swing.JMenu();
        endShiftMenu = new javax.swing.JMenuItem();
        gooMenu = new javax.swing.JMenu();
        goodsMenu = new javax.swing.JMenuItem();
        accountingMenu = new javax.swing.JMenuItem();
        priMenu = new javax.swing.JMenu();
        printMenu = new javax.swing.JMenuItem();
        printBarcodeMenu = new javax.swing.JMenuItem();
        ordMenu = new javax.swing.JMenu();
        ordersMenu = new javax.swing.JMenuItem();
        dMenu = new javax.swing.JMenu();
        dayMenu = new javax.swing.JMenuItem();
        reportMenu = new javax.swing.JMenuItem();
        generalReportsMenu = new javax.swing.JMenuItem();
        viewCheck = new javax.swing.JMenuItem();
        viewArrival = new javax.swing.JMenuItem();
        viewCancellation = new javax.swing.JMenuItem();
        viewAccountingMenu = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        newUserMenu = new javax.swing.JMenuItem();
        settingUserMenu = new javax.swing.JMenuItem();
        changePassMenu = new javax.swing.JMenuItem();
        newDiscountMenu = new javax.swing.JMenuItem();
        newGoodMenu = new javax.swing.JMenuItem();
        categoryMenu = new javax.swing.JMenuItem();
        categoryFavoriteMenu = new javax.swing.JMenuItem();
        addSettingsMenu = new javax.swing.JMenuItem();
        favoriteMenu = new javax.swing.JMenuItem();
        addSettingsMenu1 = new javax.swing.JMenuItem();
        TechMapMenu = new javax.swing.JMenuItem();
        licenseMenu = new javax.swing.JMenuItem();
        settingsPrintersMenu = new javax.swing.JMenuItem();
        accountingAndBackupMenu = new javax.swing.JMenuItem();
        exitMenu = new javax.swing.JMenu();
        newSessionMenu = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        indMenu.setText("Главная");

        indexMenu.setText("Главная");
        indexMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indexMenuActionPerformed(evt);
            }
        });
        indMenu.add(indexMenu);

        jMenuBar1.add(indMenu);

        arrGoodsMenu.setText("Движение товара");

        arrivalGoodsMenu.setText("Приход товара");
        arrivalGoodsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrivalGoodsMenuActionPerformed(evt);
            }
        });
        arrGoodsMenu.add(arrivalGoodsMenu);

        arrivalCookProdMenu.setText("Приход продуктов");
        arrivalCookProdMenu.setEnabled(false);
        arrivalCookProdMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrivalCookProdMenuActionPerformed(evt);
            }
        });
        arrGoodsMenu.add(arrivalCookProdMenu);

        productCancellationMenu.setText("Списание товара");
        productCancellationMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productCancellationMenuActionPerformed(evt);
            }
        });
        arrGoodsMenu.add(productCancellationMenu);

        jMenuBar1.add(arrGoodsMenu);

        cashMenu.setText("Денежные средства");

        officialIntrodMenu.setText("Служебное внесение средств");
        officialIntrodMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                officialIntrodMenuActionPerformed(evt);
            }
        });
        cashMenu.add(officialIntrodMenu);

        widthdrawalMenu.setText("Служебный вывод средств");
        widthdrawalMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                widthdrawalMenuActionPerformed(evt);
            }
        });
        cashMenu.add(widthdrawalMenu);

        jMenuBar1.add(cashMenu);

        endShMenu.setText("Конец смены");

        endShiftMenu.setText("Конец смены");
        endShiftMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endShiftMenuActionPerformed(evt);
            }
        });
        endShMenu.add(endShiftMenu);

        jMenuBar1.add(endShMenu);

        gooMenu.setText("Продукты");

        goodsMenu.setText("Продукты");
        goodsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goodsMenuActionPerformed(evt);
            }
        });
        gooMenu.add(goodsMenu);

        accountingMenu.setText("Учет");
        accountingMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountingMenuActionPerformed(evt);
            }
        });
        gooMenu.add(accountingMenu);

        jMenuBar1.add(gooMenu);

        priMenu.setText("Печать");

        printMenu.setText("Печать");
        printMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printMenuActionPerformed(evt);
            }
        });
        priMenu.add(printMenu);

        printBarcodeMenu.setText("Печать штрих-кода");
        printBarcodeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBarcodeMenuActionPerformed(evt);
            }
        });
        priMenu.add(printBarcodeMenu);

        jMenuBar1.add(priMenu);

        ordMenu.setText("Заказы");

        ordersMenu.setText("Заказы");
        ordersMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersMenuActionPerformed(evt);
            }
        });
        ordMenu.add(ordersMenu);

        jMenuBar1.add(ordMenu);

        dMenu.setText("Отчеты");

        dayMenu.setText("Отчет за день");
        dayMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayMenuActionPerformed(evt);
            }
        });
        dMenu.add(dayMenu);

        reportMenu.setText("Отчеты по дням");
        reportMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportMenuActionPerformed(evt);
            }
        });
        dMenu.add(reportMenu);

        generalReportsMenu.setText("Общие отчеты");
        generalReportsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalReportsMenuActionPerformed(evt);
            }
        });
        dMenu.add(generalReportsMenu);

        viewCheck.setText("Просмотр продаж");
        viewCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCheckActionPerformed(evt);
            }
        });
        dMenu.add(viewCheck);

        viewArrival.setText("Просмотр прихода");
        viewArrival.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewArrivalActionPerformed(evt);
            }
        });
        dMenu.add(viewArrival);

        viewCancellation.setText("Просмотр списания");
        viewCancellation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCancellationActionPerformed(evt);
            }
        });
        dMenu.add(viewCancellation);

        viewAccountingMenu.setText("Просмотр учета");
        viewAccountingMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAccountingMenuActionPerformed(evt);
            }
        });
        dMenu.add(viewAccountingMenu);

        jMenuBar1.add(dMenu);

        settingsMenu.setText("Настройки");

        newUserMenu.setText("Новый пользователь");
        newUserMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(newUserMenu);

        settingUserMenu.setText("Настройки пользователя");
        settingUserMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingUserMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(settingUserMenu);

        changePassMenu.setText("Изменить пароль");
        changePassMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(changePassMenu);

        newDiscountMenu.setText("Новый дисконт");
        settingsMenu.add(newDiscountMenu);

        newGoodMenu.setText("Новый продукт");
        newGoodMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGoodMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(newGoodMenu);

        categoryMenu.setText("Категория товара");
        categoryMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(categoryMenu);

        categoryFavoriteMenu.setText("Категория избранного");
        categoryFavoriteMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryFavoriteMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(categoryFavoriteMenu);

        addSettingsMenu.setText("Доп.настройки");
        addSettingsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSettingsMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(addSettingsMenu);

        favoriteMenu.setText("Избранное");
        favoriteMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(favoriteMenu);

        addSettingsMenu1.setText("Настройки базы данных");
        addSettingsMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSettingsMenu1ActionPerformed(evt);
            }
        });
        settingsMenu.add(addSettingsMenu1);

        TechMapMenu.setText("Технологическая карта");
        TechMapMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TechMapMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(TechMapMenu);

        licenseMenu.setText("Лицензионный ключ");
        licenseMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                licenseMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(licenseMenu);

        settingsPrintersMenu.setText("Настройки принтера");
        settingsPrintersMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsPrintersMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(settingsPrintersMenu);

        accountingAndBackupMenu.setText("Учет и backup");
        accountingAndBackupMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountingAndBackupMenuActionPerformed(evt);
            }
        });
        settingsMenu.add(accountingAndBackupMenu);

        jMenuBar1.add(settingsMenu);

        exitMenu.setText("Выход");

        newSessionMenu.setText("Новая сессия");
        newSessionMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSessionMenuActionPerformed(evt);
            }
        });
        exitMenu.add(newSessionMenu);

        exitMenuItem.setText("Выход");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        exitMenu.add(exitMenuItem);

        jMenuBar1.add(exitMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 677, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void productCancellationMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productCancellationMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameCancellation();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }    }//GEN-LAST:event_productCancellationMenuActionPerformed

    private void indexMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indexMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_USER".equals(auth.toString()) || "ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameCheck();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
        // логируем инфо
        log.info("Открыл чек");
    }//GEN-LAST:event_indexMenuActionPerformed

    private void goodsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goodsMenuActionPerformed
        // TODO add your handling code here:
        try {
            Object auth = grantedAuth.role();
            //System.out.println("SSSSSSS : " + ss);
            if ("ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameGoodsList();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_goodsMenuActionPerformed

    private void favoriteMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteMenuActionPerformed
        // TODO add your handling code here:
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_ADMIN".equals(auth.toString())) {
                frameFavoriteGoods();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_favoriteMenuActionPerformed

    private void addSettingsMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSettingsMenu1ActionPerformed
        frameSettingsDB();

        /*try {
         Object auth = grantedAuth.role();
         if ("ROLE_ADMIN".equals(auth.toString())) {
         frameSettingsDB();
         } else {
         JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
         }
         } catch (Exception e) {
         JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
         }*/
    }//GEN-LAST:event_addSettingsMenu1ActionPerformed

    private void newUserMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserMenuActionPerformed
        frameNewUser();
    }//GEN-LAST:event_newUserMenuActionPerformed

    private void newSessionMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSessionMenuActionPerformed
//        TestTrial testTrial = new TestTrial();
//        if (testTrial.testDateTrial() == false && testTrial.falseTrial() == false) {
//            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!! Триал-версия окончена");
//
//        } else {
        frameAuthentication();
        // }
        //frameAuthentication();
    }//GEN-LAST:event_newSessionMenuActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);

    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void arrivalGoodsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrivalGoodsMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameArrival();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }

    }//GEN-LAST:event_arrivalGoodsMenuActionPerformed

    private void newGoodMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGoodMenuActionPerformed

        try {
            Object auth = grantedAuth.role();
            if ("ROLE_ADMIN".equals(auth.toString())) {
                frameNewCookProd();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }

    }//GEN-LAST:event_newGoodMenuActionPerformed

    private void categoryFavoriteMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryFavoriteMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameNewCategoryFavorite();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }

    }//GEN-LAST:event_categoryFavoriteMenuActionPerformed

    private void categoryMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameNewCategory();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }

    }//GEN-LAST:event_categoryMenuActionPerformed

    private void officialIntrodMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_officialIntrodMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameOfficialIntro();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }

    }//GEN-LAST:event_officialIntrodMenuActionPerformed

    private void widthdrawalMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_widthdrawalMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameWithDrawals();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_widthdrawalMenuActionPerformed

    private void endShiftMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endShiftMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameEndShift();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_endShiftMenuActionPerformed

    private void arrivalCookProdMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrivalCookProdMenuActionPerformed
        //frameArrivalCookProd();
        /*try {
         Object auth = grantedAuth.role();
         if ("ROLE_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
         System.out.println("Вход разрешен!!!");
         frameArrivalCookProd();
         } else {
         JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
         }
         } catch (Exception e) {
         JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
         }*/
    }//GEN-LAST:event_arrivalCookProdMenuActionPerformed

    private void TechMapMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TechMapMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_ADMIN".equals(auth.toString())) {
                frameTechMap();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_TechMapMenuActionPerformed

    private void changePassMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePassMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_USER".equals(auth.toString()) || "ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameChangePassUser();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_changePassMenuActionPerformed

    private void dayMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameDayMenu();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }


    }//GEN-LAST:event_dayMenuActionPerformed

    private void reportMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                frameReportsMenu();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_reportMenuActionPerformed

    private void ordersMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ordersMenuActionPerformed

    private void viewCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCheckActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_USER".equals(auth.toString()) || "ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameViewCheck();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_viewCheckActionPerformed

    private void viewArrivalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewArrivalActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameViewArrival();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_viewArrivalActionPerformed

    private void licenseMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_licenseMenuActionPerformed
        frameLicenseMenu();
    }//GEN-LAST:event_licenseMenuActionPerformed

    private void settingsPrintersMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsPrintersMenuActionPerformed
        // TODO add your handling code here:
        frameSettingsPrintersMenu();
    }//GEN-LAST:event_settingsPrintersMenuActionPerformed

    private void printMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printMenuActionPerformed
        // TODO add your handling code here:
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_USER".equals(auth.toString()) || "ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                framePrint();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_printMenuActionPerformed

    private void printBarcodeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBarcodeMenuActionPerformed
        // TODO add your handling code here:

        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                framePrintBarcode();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_printBarcodeMenuActionPerformed

    private void accountingMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountingMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameAccounting();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_accountingMenuActionPerformed

    private void viewCancellationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCancellationActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_HEAD_USER".equals(auth.toString()) || "ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameViewCancellation();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_viewCancellationActionPerformed

    private void generalReportsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generalReportsMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameGeneralReports();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_generalReportsMenuActionPerformed

    private void addSettingsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSettingsMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addSettingsMenuActionPerformed

    private void settingUserMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingUserMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameSettingsUser();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_settingUserMenuActionPerformed

    private void accountingAndBackupMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountingAndBackupMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_ADMIN".equals(auth.toString())) {
                System.out.println("Вход разрешен!!!");
                frameAccountingAndBackup();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_accountingAndBackupMenuActionPerformed

    private void viewAccountingMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAccountingMenuActionPerformed
        try {
            Object auth = grantedAuth.role();
            if ("ROLE_ADMIN".equals(auth.toString())) {
                frameViewAccounting();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Доступ запрещен!!!");
        }
    }//GEN-LAST:event_viewAccountingMenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    break;

                }
            }
        } catch (Exception ex) {

        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Ml_Swing().setVisible(true);

                /*//Проверка соединения с сетью
                 CheckInternetConnection inetConn = new CheckInternetConnection();
                 inetConn.call();*/
            }
        });

        /*SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
         new MyCash().setVisible(true);
         }
         });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem TechMapMenu;
    private javax.swing.JMenuItem accountingAndBackupMenu;
    private javax.swing.JMenuItem accountingMenu;
    private javax.swing.JMenuItem addSettingsMenu;
    private javax.swing.JMenuItem addSettingsMenu1;
    private javax.swing.JMenu arrGoodsMenu;
    private javax.swing.JMenuItem arrivalCookProdMenu;
    private javax.swing.JMenuItem arrivalGoodsMenu;
    private javax.swing.JMenu cashMenu;
    private javax.swing.JMenuItem categoryFavoriteMenu;
    private javax.swing.JMenuItem categoryMenu;
    private javax.swing.JMenuItem changePassMenu;
    private javax.swing.JMenu dMenu;
    private javax.swing.JMenuItem dayMenu;
    private javax.swing.JMenu endShMenu;
    private javax.swing.JMenuItem endShiftMenu;
    private javax.swing.JMenu exitMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem favoriteMenu;
    private javax.swing.JMenuItem generalReportsMenu;
    private javax.swing.JMenu gooMenu;
    private javax.swing.JMenuItem goodsMenu;
    private javax.swing.JMenu indMenu;
    private javax.swing.JMenuItem indexMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem licenseMenu;
    private javax.swing.JMenuItem newDiscountMenu;
    private javax.swing.JMenuItem newGoodMenu;
    private javax.swing.JMenuItem newSessionMenu;
    private javax.swing.JMenuItem newUserMenu;
    private javax.swing.JMenuItem officialIntrodMenu;
    private javax.swing.JMenu ordMenu;
    private javax.swing.JMenuItem ordersMenu;
    private javax.swing.JMenu priMenu;
    private javax.swing.JMenuItem printBarcodeMenu;
    private javax.swing.JMenuItem printMenu;
    private javax.swing.JMenuItem productCancellationMenu;
    private javax.swing.JMenuItem reportMenu;
    private javax.swing.JMenuItem settingUserMenu;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JMenuItem settingsPrintersMenu;
    private javax.swing.JMenuItem viewAccountingMenu;
    private javax.swing.JMenuItem viewArrival;
    private javax.swing.JMenuItem viewCancellation;
    private javax.swing.JMenuItem viewCheck;
    private javax.swing.JMenuItem widthdrawalMenu;
    // End of variables declaration//GEN-END:variables

}
