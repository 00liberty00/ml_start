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

public class XMLSearchPass {

    private String pass;

    public void findPass() {
        resultPass();
    }

    private void resultPass() {

        try {
            FileInputStream file = new FileInputStream(new File("src/hibernate.cfg.xml"));

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document xmlDocument = builder.parse(file);

            XPath xPath = XPathFactory.newInstance().newXPath();

            System.out.println("*************************");
            ///Employees/Employee[@emplid='3333']/email
            String findPass = "/hibernate-configuration/session-factory/property[@name='hibernate.connection.password']";
            pass = xPath.compile(findPass).evaluate(xmlDocument);

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

        return pass;
    }

}
