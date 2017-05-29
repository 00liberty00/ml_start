/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.print;

import ml.xml.XMLPrinter;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;

/**
 *
 * @author dave
 */
public class PrinterCheck {

    private final XMLPrinter xmlPrinter = new XMLPrinter();
    private final ByteArrayOutputStream printData = new ByteArrayOutputStream();
    private final byte[] initPrinter = {0x1B, 0x40};
    private final byte[] charset = {0x1B, 0x74, 6};
    private final byte[] newLine = {0x0A};
    private final byte[] cutLineSpacing = {27, 100, 5};
    private final byte[] cut = {27, 105};
    private final byte[] boldOn = {0x1B, 0x45, 1};
    private final byte[] boldOff = {0x1B, 0x45, 0};
    private final byte[] fontSize = {0x1B, 0x4D, 48};
    private final byte[] lineSpacing = {27, 100, 1};                  //пропуск кол-во строк
    private final byte leftJustification[] = {0x1B, 0x61, 48};        //выравнивание по левому краю
    private final byte centerJustification[] = {0x1B, 0x61, 49};        //выравнивание по середине
    private final byte rightJustification[] = {0x1B, 0x61, 50};       //выравнивание по правому краю
    private final byte[] fontSmallSize = {0x1B, 0x4D, 49};
    private final byte[] fontBigSize = {0x1B, 0x4D, 48};
    public static byte[] PRINT_BAR_CODE_1 = {29, 107, 2};
    private final byte[] defineImage = {0x1D, 0x2A, 3, 2, 5, 100, 10, 1, 50, 10};
    private final byte[] downloadImage = {0x1D, 0x2F, 0, 1};
    private final byte[] moneyBox = {27, 112, 48, 25, 100};         //Открыть денежный ящик
            
    
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final Date date = new Date();
    private final String nowDate = dateFormat.format(date);

    private final String sum = "СУММА ЧЕКА: ";
    private final String sumDiscount = "Сумма чека со скидкой: ";
    private final String numCheck = "Номер чека: №";
    private final String discountString = "Ваша скидка составила ";
    private final String line = "_____________________";
    private final String dublicate = "Дубликат чека";

    public void printer(String name, String amount, String price) {

        Double d = Double.parseDouble(amount) * Double.parseDouble(price);
        Double sumG = new BigDecimal(d).setScale(2, RoundingMode.UP).doubleValue(); //Округление до 0,00
        String sumGoods = sumG.toString();
        try {
            byte spaceLeft[] = {0x09};        //выравнивание по левому краю

            byte feedPaper[] = {27, 74, 100};
            byte beep[] = {0x1B, 0x42, 1, 2};

            printData.write(leftJustification);
            printData.write(fontSmallSize);
            printData.write(name.getBytes("windows-1251"));
            printData.write(spaceLeft);
            printData.write(newLine);
            printData.write(rightJustification);
            printData.write(amount.getBytes("windows-1251"));
            printData.write(" x ".getBytes("windows-1251"));
            printData.write(price.getBytes("windows-1251"));
            printData.write(" = ".getBytes("windows-1251"));
            printData.write(sumGoods.getBytes("windows-1251"));
            printData.write(lineSpacing);
            printData.write(fontBigSize);

        } catch (Exception e) {
            System.out.println("Whoa bro. The printer is balls. Check it:");
            e.printStackTrace();
        }
    }

    public void startPrint() {
        
        
        try {
            String header = xmlPrinter.getHeaderPrinter();
            String adress = xmlPrinter.getAdressPrinter();
            String info = xmlPrinter.getInfoPrinter();

            //String hello = "Вас приветствует 'Кондитерский магазин ESSA'. ";
            printData.write(initPrinter);
            printData.write(charset);
            printData.write(boldOn);
            printData.write(header.getBytes("windows-1251"));
            printData.write(newLine);
            printData.write(adress.getBytes("windows-1251"));
            printData.write(newLine);
            printData.write(info.getBytes("windows-1251"));
            printData.write(newLine);
            printData.write(boldOff);
            printData.write(centerJustification);
            printData.write(lineSpacing);
            printData.write(line.getBytes("windows-1251"));
            printData.write(line.getBytes("windows-1251"));
            printData.write(leftJustification);
            printData.write(newLine);
        } catch (Exception e) {
        }

    }

    public void finishPrint(Long numberCheck, String sumCheck, String sumCheckDiscount, String discountPercent) {

        String namePrinter = xmlPrinter.getNamePrinter();
        String footerPrinter = xmlPrinter.getFooterPrinter();
        PrintService tsp100 = getPrintService(namePrinter);

        File file = new File("/home/dave/Desktop/essa.png");

        Double d = Double.parseDouble(sumCheck) - Double.parseDouble(sumCheckDiscount);     //Вычисление скидки
        Double discount = new BigDecimal(d).setScale(2, RoundingMode.UP).doubleValue(); //Округление до 0,00

        DocPrintJob job = tsp100.createPrintJob();
        try {
            printData.write(boldOn);
            printData.write(line.getBytes("windows-1251"));
            printData.write(line.getBytes("windows-1251"));
            printData.write(leftJustification);
            printData.write(newLine);
            printData.write(leftJustification);
            printData.write(sum.getBytes("windows-1251"));
            printData.write(rightJustification);
            printData.write(sumCheck.getBytes("windows-1251"));

            printData.write(boldOff);
            printData.write(newLine);
            printData.write(line.getBytes("windows-1251"));
            printData.write(line.getBytes("windows-1251"));
            printData.write(leftJustification);
            printData.write(newLine);
            printData.write(leftJustification);
            printData.write(discountString.getBytes("windows-1251"));
            printData.write(discountPercent.getBytes("windows-1251"));
            printData.write(" : ".getBytes("windows-1251"));
            printData.write(discount.toString().getBytes("windows-1251"));
            printData.write(newLine);

            printData.write(rightJustification);
            printData.write(sumDiscount.getBytes("windows-1251"));
            printData.write(newLine);

            printData.write(rightJustification);
            printData.write(sumCheckDiscount.getBytes("windows-1251"));
            printData.write(newLine);

            printData.write(leftJustification);
            printData.write(numCheck.getBytes("windows-1251"));
            printData.write(numberCheck.toString().getBytes("windows-1251"));
            printData.write(newLine);

            printData.write(rightJustification);
            printData.write(nowDate.getBytes("windows-1251"));
            printData.write(newLine);

            printData.write(centerJustification);
            printData.write(footerPrinter.getBytes("windows-1251"));
            printData.write(newLine);
            //printData.write(fontSize);
            //printData.write(downloadImage);
            printData.write(cutLineSpacing);
            printData.write(cut);
            printData.write(moneyBox);
            byte b[] = printData.toByteArray();

            //FileInputStream fin = new FileInputStream("/home/dave/Desktop/logo.png");
            //Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, null);
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(b, flavor, null);
            job.print(doc, null);
            printData.reset();
        } catch (Exception e) {
        }
    }

    public void finishViewNumberCheckPrint(Long numberCheck, String sumCheck, Date date) {

        String namePrinter = xmlPrinter.getNamePrinter();
        String footerPrinter = xmlPrinter.getFooterPrinter();
        PrintService tsp100 = getPrintService(namePrinter);

        File file = new File("/home/dave/Desktop/essa.png");

        DocPrintJob job = tsp100.createPrintJob();
        
        String checkDate = dateFormat.format(date);
        try {
            printData.write(boldOn);
            printData.write(line.getBytes("windows-1251"));
            printData.write(line.getBytes("windows-1251"));
            printData.write(leftJustification);
            printData.write(newLine);
            printData.write(leftJustification);
            printData.write(sum.getBytes("windows-1251"));
            printData.write(rightJustification);
            printData.write(sumCheck.getBytes("windows-1251"));

            printData.write(boldOff);
            printData.write(newLine);
            printData.write(line.getBytes("windows-1251"));
            printData.write(line.getBytes("windows-1251"));
            printData.write(leftJustification);
            printData.write(newLine);
            printData.write(leftJustification);
            printData.write(newLine);

            printData.write(rightJustification);
            printData.write(sumDiscount.getBytes("windows-1251"));
            printData.write(newLine);

            printData.write(leftJustification);
            printData.write(numCheck.getBytes("windows-1251"));
            printData.write(numberCheck.toString().getBytes("windows-1251"));
            printData.write(newLine);

            printData.write(rightJustification);
            printData.write(checkDate.getBytes("windows-1251"));
            printData.write(newLine);

            printData.write(centerJustification);
            printData.write(footerPrinter.getBytes("windows-1251"));
            printData.write(newLine);
            
            printData.write(dublicate.getBytes("windows-1251"));
            printData.write(newLine);
            //printData.write(fontSize);
            //printData.write(downloadImage);
            printData.write(cutLineSpacing);
            printData.write(cut);
            
            byte b[] = printData.toByteArray();

            //FileInputStream fin = new FileInputStream("/home/dave/Desktop/logo.png");
            //Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, null);
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(b, flavor, null);
            job.print(doc, null);
            printData.reset();
        } catch (Exception e) {
        }
    }
    
    public void openMoneyBox(){
        
        String namePrinter = xmlPrinter.getNamePrinter();
        String footerPrinter = xmlPrinter.getFooterPrinter();
        PrintService tsp100 = getPrintService(namePrinter);
        DocPrintJob job = tsp100.createPrintJob();
        try {
            
            printData.write(moneyBox);
            
            byte b[] = printData.toByteArray();

            //FileInputStream fin = new FileInputStream("/home/dave/Desktop/logo.png");
            //Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, null);
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(b, flavor, null);
            job.print(doc, null);
            printData.reset();
        } catch (Exception e) {
        }
    }
    
    private PrintService getPrintService(String serviceName) {
        PrintService[] ps = PrinterJob.lookupPrintServices();
        for (int i = 0; i < ps.length; i++) {
            if (ps[i].getName().indexOf(serviceName) >= 0) {
                return ps[i];
            }
        }
        System.out.println("Aw SNAP! I like, can't find a printer with "
                + serviceName + " in the name dude.");
        System.exit(1);
        return null;
    }
    
    

}
