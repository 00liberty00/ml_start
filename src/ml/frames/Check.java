/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.frames;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import ml.authentication.GrantedAuth;
import ml.barcode.BarcodeConv;
import ml.model.CategoryFavorite;
import ml.model.Discount;
import ml.model.Goods;
import ml.query.favorite.CategoryByName;
import ml.query.favorite.CategoryFavoriteList;
import ml.query.goods.QueryAllGoodsList;
import ml.util.RegexpNameGoods;

/**
 *
 * @author Dave
 */
//42208587
public class Check extends javax.swing.JInternalFrame {

    private List<Goods> goodsList;
    private QueryAllGoodsList allGoodsList = new QueryAllGoodsList();
    private List<CategoryFavorite> catFavorList;
    private CategoryFavoriteList favoriteList = new CategoryFavoriteList();
    private CategoryFavorite catFavor = new CategoryFavorite();
    private BarcodeConv barcodeConv = new BarcodeConv();
    private String weight = "1";
    private BigDecimal percent = new BigDecimal(0.00);
    private Discount discount = new Discount();
    private GrantedAuth grantedAuth = new GrantedAuth();
    private Object auth = grantedAuth.role();
    private String nameCategory;
    private CategoryFavorite cf;
    private CategoryByName categoryByName = new CategoryByName();
    private RegexpNameGoods regexpNameGoods = new RegexpNameGoods();
    private int selectRow = -1;
    private Object[] columnNames = {
        "Код", "Название", "Цена", "Количество", "Сумма", "Остаток"
    };
    private DefaultTableModel dtm = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    /**
     * Creates new form Check
     */
    public Check() {
        initComponents();
        setTitle("Продажа товара");
        setMaximizable(true);
        setResizable(true);
        setIconifiable(true);
        setClosable(true);

        //Автофокус в поле Код товара
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                codeGoodsTextField.requestFocus();
                
                //Список всего товара
                goodsList = allGoodsList.listGoods();
            }

        });
    }

    /**
     * Вовзращает данные о товаре по коду
     *
     * @param code
     */
    private void getGoodCode(String code) {
        boolean checkGood = false;
        Goods goods = null;
        //qg.runQueryCodeGoods(code);
        //goods = qg.displayResultCode();

        for (Goods gg1 : goodsList) {
            if (gg1.getCode().equals(code)) {
                goods = gg1;
                checkGood = true;
            } else {
                checkGood = false;
            }
        }

        if (checkGood == false) {

            boolean firstLetter = barcodeConv.firstLetters(code);
            if (firstLetter == true) {
                String codeBarCode = barcodeConv.sixLetter(code);
                weight = barcodeConv.fourLetter(code);
                if ("0.001".equals(weight)) {
                    weight = "1";
                }
                for (Goods gg1 : goodsList) {
                    if (gg1.getCode().equals(codeBarCode)) {
                        goods = gg1;
                    }
                }
            }

        }
        if (goods != null) {
            displayResult(goods);
        }
        sumForTextField();
        codeGoodsTextField.requestFocus();
        codeGoodsTextField.setText(""); //Выделяет текст в поле

    }

    /**
     * Выводит на экран сумму столбца СУММА и СУММА СО СКИДКОЙ
     */
    private void sumForTextField() {
        //Сумма столбца СУММА и СУММА СО СКИДКОЙ и вывод на экран
        BigDecimal totalSum = new BigDecimal(0.00);
        BigDecimal totalSumDisc = new BigDecimal(0.00);
        int count = resultGoodsTable.getRowCount();
        for (int i = 0; i < count; i++) {
            totalSum = totalSum.add(new BigDecimal(resultGoodsTable.getValueAt(i, 4).toString())).setScale(2, RoundingMode.HALF_UP);
//            totalSum.setScale(2, RoundingMode.HALF_UP);
            if (percent.signum() != 0) {
                totalSumDisc = totalSum.subtract(totalSum.multiply(percent.divide(new BigDecimal(100)))).setScale(2, RoundingMode.HALF_UP);
//                totalSumDisc.setScale(2, RoundingMode.HALF_UP);
                sumCheckWithDiscount.setText(totalSumDisc.toString());
            }
        }
        sumCheck.setText(totalSum.toString());
    }

    /**
     * Конвертирует String в BigDecimal и , в .
     */
    private BigDecimal decimal(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        System.out.println(value + "  " + pattern + "  " + output);

        //Замена , на .
        String gWithDot = output.replace(',', '.');
        return new BigDecimal(gWithDot);
    }

    //Метод для просмотра результатов в "JTable".
    private void displayResult(Goods resultList) {
//CX6WhFXRWdDdx6kIWNTV3g==
//4823003205618
        for (int i = 0; i < 1; i++) {
            Goods g = resultList;
            if (!(g == null)) {

                BigDecimal sum = g.getPrice().multiply(new BigDecimal(weight));
                //System.out.println("SSSSSSS : " + ss);
                if ("ROLE_ADMIN".equals(auth.toString())) {

                    dtm.addRow(new Object[]{
                        g.getCode(), g.getName(), g.getPrice(), weight, sum, g.getResidue()
                    });
                } else {
                    dtm.addRow(new Object[]{
                        g.getCode(), g.getName(), g.getPrice(), weight, sum
                    });
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Новый товар! Поставьте на приход!");
            }

        }
        weight = "1";
        resultGoodsTable.setModel(dtm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        favoriteButton17 = new javax.swing.JButton();
        favoriteButton18 = new javax.swing.JButton();
        favoriteButton19 = new javax.swing.JButton();
        favoriteButton22 = new javax.swing.JButton();
        favoriteButton23 = new javax.swing.JButton();
        favoriteButton24 = new javax.swing.JButton();
        favoriteButton20 = new javax.swing.JButton();
        favoriteButton21 = new javax.swing.JButton();
        favoriteButton28 = new javax.swing.JButton();
        favoriteButton25 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        favoriteButton26 = new javax.swing.JButton();
        residueGoodsTextField = new javax.swing.JTextField();
        favoriteButton27 = new javax.swing.JButton();
        buttonOK = new javax.swing.JButton();
        favoriteButton29 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        favoriteButton30 = new javax.swing.JButton();
        sumCheck = new javax.swing.JTextField();
        favoriteButton31 = new javax.swing.JButton();
        paymentCheck = new javax.swing.JTextField();
        favoriteButton34 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        oddMoneyCheck = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        codeGoodsTextField = new javax.swing.JTextField();
        OkButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultGoodsTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        favoriteButton35 = new javax.swing.JButton();
        favoriteButton36 = new javax.swing.JButton();
        favoriteButton32 = new javax.swing.JButton();
        favoriteButton33 = new javax.swing.JButton();
        buttonNewCheck = new javax.swing.JButton();
        categoryButton1 = new javax.swing.JButton();
        nameComboBox = new javax.swing.JComboBox();
        categoryButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        categoryButton3 = new javax.swing.JButton();
        discountTextField = new javax.swing.JTextField();
        categoryButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        categoryButton5 = new javax.swing.JButton();
        sumCheckWithDiscount = new javax.swing.JTextField();
        categoryButton6 = new javax.swing.JButton();
        discountView = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        favoriteButton1 = new javax.swing.JButton();
        favoriteButton2 = new javax.swing.JButton();
        connectInternetLabel = new javax.swing.JLabel();
        favoriteButton3 = new javax.swing.JButton();
        favoriteButton6 = new javax.swing.JButton();
        favoriteButton5 = new javax.swing.JButton();
        favoriteButton4 = new javax.swing.JButton();
        favoriteButton10 = new javax.swing.JButton();
        favoriteButton7 = new javax.swing.JButton();
        favoriteButton8 = new javax.swing.JButton();
        favoriteButton9 = new javax.swing.JButton();
        favoriteButton11 = new javax.swing.JButton();
        favoriteButton12 = new javax.swing.JButton();
        favoriteButton16 = new javax.swing.JButton();
        favoriteButton13 = new javax.swing.JButton();
        favoriteButton14 = new javax.swing.JButton();
        favoriteButton15 = new javax.swing.JButton();

        favoriteButton17.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton17ActionPerformed(evt);
            }
        });

        favoriteButton18.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton18ActionPerformed(evt);
            }
        });

        favoriteButton19.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton19.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton19ActionPerformed(evt);
            }
        });

        favoriteButton22.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton22.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton22ActionPerformed(evt);
            }
        });

        favoriteButton23.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton23.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton23ActionPerformed(evt);
            }
        });

        favoriteButton24.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton24.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton24ActionPerformed(evt);
            }
        });

        favoriteButton20.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton20.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton20ActionPerformed(evt);
            }
        });

        favoriteButton21.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton21.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton21ActionPerformed(evt);
            }
        });

        favoriteButton28.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton28.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton28ActionPerformed(evt);
            }
        });

        favoriteButton25.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton25.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton25ActionPerformed(evt);
            }
        });

        jLabel5.setText("Количество товара: ");

        favoriteButton26.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton26.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton26ActionPerformed(evt);
            }
        });

        residueGoodsTextField.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        residueGoodsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                residueGoodsTextFieldActionPerformed(evt);
            }
        });

        favoriteButton27.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton27.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton27ActionPerformed(evt);
            }
        });

        buttonOK.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        buttonOK.setText("OK");
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
            }
        });

        favoriteButton29.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton29.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton29ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setText("Сумма: ");

        favoriteButton30.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton30.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton30ActionPerformed(evt);
            }
        });

        sumCheck.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        sumCheck.setForeground(new java.awt.Color(255, 0, 0));
        sumCheck.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sumCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumCheckActionPerformed(evt);
            }
        });

        favoriteButton31.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton31.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton31ActionPerformed(evt);
            }
        });

        paymentCheck.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        favoriteButton34.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton34.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton34ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setText("Наличные:");

        oddMoneyCheck.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        oddMoneyCheck.setForeground(new java.awt.Color(255, 0, 0));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setText("Сдача: ");

        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deleteButton.setText("Удалить");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Название товара: ");

        codeGoodsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeGoodsTextFieldActionPerformed(evt);
            }
        });

        OkButton.setText("Ok");
        OkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Код товара:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Продажа товара");

        resultGoodsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Код", "Название", "Цена", "Количество", "Сумма", "Остаток"
            }
        ));
        jScrollPane1.setViewportView(resultGoodsTable);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Чек");

        favoriteButton35.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton35.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton35ActionPerformed(evt);
            }
        });

        favoriteButton36.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton36.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton36ActionPerformed(evt);
            }
        });

        favoriteButton32.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton32.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton32ActionPerformed(evt);
            }
        });

        favoriteButton33.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton33.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton33ActionPerformed(evt);
            }
        });

        buttonNewCheck.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonNewCheck.setText("Новый чек");
        buttonNewCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewCheckActionPerformed(evt);
            }
        });

        categoryButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        categoryButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        categoryButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                categoryButton1MousePressed(evt);
            }
        });
        categoryButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryButton1ActionPerformed(evt);
            }
        });

        nameComboBox.setEditable(true);
        nameComboBox.setSelectedItem("");
        nameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameComboBoxActionPerformed(evt);
            }
        });
        nameComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameComboBoxKeyPressed(evt);
            }
        });

        categoryButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        categoryButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        categoryButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                categoryButton2MousePressed(evt);
            }
        });
        categoryButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryButton2ActionPerformed(evt);
            }
        });

        jLabel9.setText("Скидка: ");

        categoryButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        categoryButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        categoryButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                categoryButton3MousePressed(evt);
            }
        });
        categoryButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryButton3ActionPerformed(evt);
            }
        });

        discountTextField.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        discountTextField.setEnabled(false);
        discountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountTextFieldActionPerformed(evt);
            }
        });

        categoryButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        categoryButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        categoryButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                categoryButton4MousePressed(evt);
            }
        });
        categoryButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryButton4ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setText("Сумма со скидкой: ");

        categoryButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        categoryButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        categoryButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                categoryButton5MousePressed(evt);
            }
        });
        categoryButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryButton5ActionPerformed(evt);
            }
        });

        sumCheckWithDiscount.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        sumCheckWithDiscount.setForeground(new java.awt.Color(255, 0, 0));
        sumCheckWithDiscount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sumCheckWithDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumCheckWithDiscountActionPerformed(evt);
            }
        });

        categoryButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        categoryButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        categoryButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                categoryButton6MousePressed(evt);
            }
        });
        categoryButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryButton6ActionPerformed(evt);
            }
        });

        discountView.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        discountView.setForeground(new java.awt.Color(255, 0, 0));
        discountView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountViewActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setText("Товар");

        favoriteButton1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        favoriteButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton1ActionPerformed(evt);
            }
        });

        favoriteButton2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton2ActionPerformed(evt);
            }
        });

        favoriteButton3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton3ActionPerformed(evt);
            }
        });

        favoriteButton6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton6ActionPerformed(evt);
            }
        });

        favoriteButton5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton5ActionPerformed(evt);
            }
        });

        favoriteButton4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton4ActionPerformed(evt);
            }
        });

        favoriteButton10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton10ActionPerformed(evt);
            }
        });

        favoriteButton7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton7ActionPerformed(evt);
            }
        });

        favoriteButton8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton8ActionPerformed(evt);
            }
        });

        favoriteButton9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton9ActionPerformed(evt);
            }
        });

        favoriteButton11.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton11ActionPerformed(evt);
            }
        });

        favoriteButton12.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton12ActionPerformed(evt);
            }
        });

        favoriteButton16.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton16ActionPerformed(evt);
            }
        });

        favoriteButton13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton13ActionPerformed(evt);
            }
        });

        favoriteButton14.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton14ActionPerformed(evt);
            }
        });

        favoriteButton15.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        favoriteButton15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        favoriteButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(connectInternetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(277, 277, 277))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(codeGoodsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(34, 34, 34)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(nameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(OkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(residueGoodsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(1, 1, 1)
                                                .addComponent(discountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(discountView, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(categoryButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(categoryButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(categoryButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(categoryButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(categoryButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(categoryButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(favoriteButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(favoriteButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(favoriteButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(favoriteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(favoriteButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(favoriteButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(favoriteButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonNewCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sumCheckWithDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sumCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(paymentCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(oddMoneyCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4))
                    .addComponent(connectInternetLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonNewCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(sumCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sumCheckWithDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(paymentCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(oddMoneyCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(codeGoodsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(OkButton)
                            .addComponent(nameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(discountView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(residueGoodsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(discountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(favoriteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(favoriteButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(favoriteButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(favoriteButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(favoriteButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(favoriteButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(favoriteButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void favoriteButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton17ActionPerformed

    }//GEN-LAST:event_favoriteButton17ActionPerformed

    private void favoriteButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton18ActionPerformed

    }//GEN-LAST:event_favoriteButton18ActionPerformed

    private void favoriteButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton19ActionPerformed

    }//GEN-LAST:event_favoriteButton19ActionPerformed

    private void favoriteButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton22ActionPerformed

    }//GEN-LAST:event_favoriteButton22ActionPerformed

    private void favoriteButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton23ActionPerformed

    }//GEN-LAST:event_favoriteButton23ActionPerformed

    private void favoriteButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton24ActionPerformed

    }//GEN-LAST:event_favoriteButton24ActionPerformed

    private void favoriteButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton20ActionPerformed

    }//GEN-LAST:event_favoriteButton20ActionPerformed

    private void favoriteButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton21ActionPerformed

    }//GEN-LAST:event_favoriteButton21ActionPerformed

    private void favoriteButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton28ActionPerformed

    }//GEN-LAST:event_favoriteButton28ActionPerformed

    private void favoriteButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton25ActionPerformed

    }//GEN-LAST:event_favoriteButton25ActionPerformed

    private void favoriteButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton26ActionPerformed

    }//GEN-LAST:event_favoriteButton26ActionPerformed

    private void residueGoodsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_residueGoodsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_residueGoodsTextFieldActionPerformed

    private void favoriteButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton27ActionPerformed

    }//GEN-LAST:event_favoriteButton27ActionPerformed

    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed

    }//GEN-LAST:event_buttonOKActionPerformed

    private void favoriteButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton29ActionPerformed

    }//GEN-LAST:event_favoriteButton29ActionPerformed

    private void favoriteButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton30ActionPerformed

    }//GEN-LAST:event_favoriteButton30ActionPerformed

    private void sumCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sumCheckActionPerformed

    private void favoriteButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton31ActionPerformed

    }//GEN-LAST:event_favoriteButton31ActionPerformed

    private void favoriteButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton34ActionPerformed

    }//GEN-LAST:event_favoriteButton34ActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed

    }//GEN-LAST:event_deleteButtonActionPerformed

    private void codeGoodsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeGoodsTextFieldActionPerformed
        // TODO add your handling code here:
        String text = codeGoodsTextField.getText();

        //Проверка первого символа
        if (regexpNameGoods.nameGoods(text) == true) {
            //  getGoodName(text);
        } else {
            getGoodCode(text);
        }
    }//GEN-LAST:event_codeGoodsTextFieldActionPerformed

    private void OkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OkButtonActionPerformed

    private void favoriteButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton35ActionPerformed

    }//GEN-LAST:event_favoriteButton35ActionPerformed

    private void favoriteButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton36ActionPerformed

    }//GEN-LAST:event_favoriteButton36ActionPerformed

    private void favoriteButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton32ActionPerformed

    }//GEN-LAST:event_favoriteButton32ActionPerformed

    private void favoriteButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton33ActionPerformed

    }//GEN-LAST:event_favoriteButton33ActionPerformed

    private void buttonNewCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewCheckActionPerformed

    }//GEN-LAST:event_buttonNewCheckActionPerformed

    private void categoryButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryButton1MousePressed
        categoryButton1.setBackground(Color.LIGHT_GRAY);
        categoryButton2.setBackground(UIManager.getColor("Button.background"));
        categoryButton3.setBackground(UIManager.getColor("Button.background"));
        categoryButton4.setBackground(UIManager.getColor("Button.background"));
        categoryButton5.setBackground(UIManager.getColor("Button.background"));
        categoryButton6.setBackground(UIManager.getColor("Button.background"));
    }//GEN-LAST:event_categoryButton1MousePressed

    private void categoryButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryButton1ActionPerformed

    }//GEN-LAST:event_categoryButton1ActionPerformed

    private void nameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameComboBoxActionPerformed

    private void nameComboBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameComboBoxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameComboBoxKeyPressed

    private void categoryButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryButton2MousePressed
        categoryButton2.setBackground(Color.LIGHT_GRAY);
        categoryButton1.setBackground(UIManager.getColor("Button.background"));
        categoryButton3.setBackground(UIManager.getColor("Button.background"));
        categoryButton4.setBackground(UIManager.getColor("Button.background"));
        categoryButton5.setBackground(UIManager.getColor("Button.background"));
        categoryButton6.setBackground(UIManager.getColor("Button.background"));
    }//GEN-LAST:event_categoryButton2MousePressed

    private void categoryButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryButton2ActionPerformed

    }//GEN-LAST:event_categoryButton2ActionPerformed

    private void categoryButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryButton3MousePressed
        categoryButton3.setBackground(Color.LIGHT_GRAY);
        categoryButton1.setBackground(UIManager.getColor("Button.background"));
        categoryButton2.setBackground(UIManager.getColor("Button.background"));
        categoryButton4.setBackground(UIManager.getColor("Button.background"));
        categoryButton5.setBackground(UIManager.getColor("Button.background"));
        categoryButton6.setBackground(UIManager.getColor("Button.background"));
    }//GEN-LAST:event_categoryButton3MousePressed

    private void categoryButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryButton3ActionPerformed

    }//GEN-LAST:event_categoryButton3ActionPerformed

    private void discountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountTextFieldActionPerformed

    private void categoryButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryButton4MousePressed
        categoryButton4.setBackground(Color.LIGHT_GRAY);
        categoryButton2.setBackground(UIManager.getColor("Button.background"));
        categoryButton3.setBackground(UIManager.getColor("Button.background"));
        categoryButton1.setBackground(UIManager.getColor("Button.background"));
        categoryButton5.setBackground(UIManager.getColor("Button.background"));
        categoryButton6.setBackground(UIManager.getColor("Button.background"));
    }//GEN-LAST:event_categoryButton4MousePressed

    private void categoryButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryButton4ActionPerformed

    }//GEN-LAST:event_categoryButton4ActionPerformed

    private void categoryButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryButton5MousePressed
        categoryButton5.setBackground(Color.LIGHT_GRAY);
        categoryButton2.setBackground(UIManager.getColor("Button.background"));
        categoryButton3.setBackground(UIManager.getColor("Button.background"));
        categoryButton4.setBackground(UIManager.getColor("Button.background"));
        categoryButton1.setBackground(UIManager.getColor("Button.background"));
        categoryButton6.setBackground(UIManager.getColor("Button.background"));
    }//GEN-LAST:event_categoryButton5MousePressed

    private void categoryButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryButton5ActionPerformed

    }//GEN-LAST:event_categoryButton5ActionPerformed

    private void sumCheckWithDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumCheckWithDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sumCheckWithDiscountActionPerformed

    private void categoryButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryButton6MousePressed
        categoryButton6.setBackground(Color.LIGHT_GRAY);
        categoryButton2.setBackground(UIManager.getColor("Button.background"));
        categoryButton3.setBackground(UIManager.getColor("Button.background"));
        categoryButton4.setBackground(UIManager.getColor("Button.background"));
        categoryButton5.setBackground(UIManager.getColor("Button.background"));
        categoryButton1.setBackground(UIManager.getColor("Button.background"));
    }//GEN-LAST:event_categoryButton6MousePressed

    private void categoryButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryButton6ActionPerformed

    }//GEN-LAST:event_categoryButton6ActionPerformed

    private void discountViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountViewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountViewActionPerformed

    private void favoriteButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton1ActionPerformed

    }//GEN-LAST:event_favoriteButton1ActionPerformed

    private void favoriteButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton2ActionPerformed

    }//GEN-LAST:event_favoriteButton2ActionPerformed

    private void favoriteButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton3ActionPerformed

    }//GEN-LAST:event_favoriteButton3ActionPerformed

    private void favoriteButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton6ActionPerformed

    }//GEN-LAST:event_favoriteButton6ActionPerformed

    private void favoriteButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton5ActionPerformed

    }//GEN-LAST:event_favoriteButton5ActionPerformed

    private void favoriteButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton4ActionPerformed

    }//GEN-LAST:event_favoriteButton4ActionPerformed

    private void favoriteButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton10ActionPerformed

    }//GEN-LAST:event_favoriteButton10ActionPerformed

    private void favoriteButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton7ActionPerformed

    }//GEN-LAST:event_favoriteButton7ActionPerformed

    private void favoriteButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton8ActionPerformed

    }//GEN-LAST:event_favoriteButton8ActionPerformed

    private void favoriteButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton9ActionPerformed

    }//GEN-LAST:event_favoriteButton9ActionPerformed

    private void favoriteButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton11ActionPerformed

    }//GEN-LAST:event_favoriteButton11ActionPerformed

    private void favoriteButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton12ActionPerformed

    }//GEN-LAST:event_favoriteButton12ActionPerformed

    private void favoriteButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton16ActionPerformed

    }//GEN-LAST:event_favoriteButton16ActionPerformed

    private void favoriteButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton13ActionPerformed

    }//GEN-LAST:event_favoriteButton13ActionPerformed

    private void favoriteButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton14ActionPerformed

    }//GEN-LAST:event_favoriteButton14ActionPerformed

    private void favoriteButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButton15ActionPerformed

    }//GEN-LAST:event_favoriteButton15ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OkButton;
    private javax.swing.JButton buttonNewCheck;
    private javax.swing.JButton buttonOK;
    private javax.swing.JButton categoryButton1;
    private javax.swing.JButton categoryButton2;
    private javax.swing.JButton categoryButton3;
    private javax.swing.JButton categoryButton4;
    private javax.swing.JButton categoryButton5;
    private javax.swing.JButton categoryButton6;
    private javax.swing.JTextField codeGoodsTextField;
    private javax.swing.JLabel connectInternetLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField discountTextField;
    private javax.swing.JTextField discountView;
    private javax.swing.JButton favoriteButton1;
    private javax.swing.JButton favoriteButton10;
    private javax.swing.JButton favoriteButton11;
    private javax.swing.JButton favoriteButton12;
    private javax.swing.JButton favoriteButton13;
    private javax.swing.JButton favoriteButton14;
    private javax.swing.JButton favoriteButton15;
    private javax.swing.JButton favoriteButton16;
    private javax.swing.JButton favoriteButton17;
    private javax.swing.JButton favoriteButton18;
    private javax.swing.JButton favoriteButton19;
    private javax.swing.JButton favoriteButton2;
    private javax.swing.JButton favoriteButton20;
    private javax.swing.JButton favoriteButton21;
    private javax.swing.JButton favoriteButton22;
    private javax.swing.JButton favoriteButton23;
    private javax.swing.JButton favoriteButton24;
    private javax.swing.JButton favoriteButton25;
    private javax.swing.JButton favoriteButton26;
    private javax.swing.JButton favoriteButton27;
    private javax.swing.JButton favoriteButton28;
    private javax.swing.JButton favoriteButton29;
    private javax.swing.JButton favoriteButton3;
    private javax.swing.JButton favoriteButton30;
    private javax.swing.JButton favoriteButton31;
    private javax.swing.JButton favoriteButton32;
    private javax.swing.JButton favoriteButton33;
    private javax.swing.JButton favoriteButton34;
    private javax.swing.JButton favoriteButton35;
    private javax.swing.JButton favoriteButton36;
    private javax.swing.JButton favoriteButton4;
    private javax.swing.JButton favoriteButton5;
    private javax.swing.JButton favoriteButton6;
    private javax.swing.JButton favoriteButton7;
    private javax.swing.JButton favoriteButton8;
    private javax.swing.JButton favoriteButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox nameComboBox;
    private javax.swing.JTextField oddMoneyCheck;
    private javax.swing.JTextField paymentCheck;
    private javax.swing.JTextField residueGoodsTextField;
    private javax.swing.JTable resultGoodsTable;
    private javax.swing.JTextField sumCheck;
    private javax.swing.JTextField sumCheckWithDiscount;
    // End of variables declaration//GEN-END:variables
}
