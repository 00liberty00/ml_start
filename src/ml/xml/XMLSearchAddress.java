/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.xml;

/**
 *
 * @author dave
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLSearchAddress {

    private String address;

    public void findAddress() {
        resultAddress();
    }

    private void resultAddress() {

        try {
            FileInputStream file = new FileInputStream(new File("src/hibernate.cfg.xml"));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            XPath xPath = XPathFactory.newInstance().newXPath();
            String findPass = "/hibernate-configuration/session-factory/property[@name='hibernate.connection.url']";
            String str = xPath.compile(findPass).evaluate(xmlDocument);
            //вывод ip-адреса из строки
            int start = 13;
            int end = 30;
            char buf[] = new char[end - start];
            str.getChars(start, end, buf, 0);
            String newString =  String.valueOf(buf);
            int i = newString.indexOf(':');
            int startIp = 0;
            int endIp = i;
            char bufIp[] = new char[endIp - startIp];
            newString.getChars(startIp, endIp, bufIp, 0);
            address = String.valueOf(bufIp);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public String displayResult() {

        return address;
    }

}
