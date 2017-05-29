/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author dave
 */

//Запись всей таблицы товаров в XML - файл
public class XMLPrinter {

    private Document doc;
    private Element rootElement;

    private void create(String header, String adress, String info, String footer) {

        // staff elements
        Element staff = doc.createElement("printerSettings");
        Attr attr = doc.createAttribute("idPrinter");
        attr.setValue("1");
        staff.setAttributeNode(attr);
        rootElement.appendChild(staff);

        Element codeElem = doc.createElement("header");
        codeElem.appendChild(doc.createTextNode(header));
        staff.appendChild(codeElem);
        
        Element adressElem = doc.createElement("adress");
        adressElem.appendChild(doc.createTextNode(adress));
        staff.appendChild(adressElem);
        
        Element infoElem = doc.createElement("info");
        infoElem.appendChild(doc.createTextNode(info));
        staff.appendChild(infoElem);

        Element residueElem = doc.createElement("footer");
        residueElem.appendChild(doc.createTextNode(footer));
        staff.appendChild(residueElem);

    }
    
    public void newPrinter(String header, String adress, String info, String footer) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("printer");
            doc.appendChild(rootElement);

            this.create(header, adress, info, footer);
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/ml/resources/printer/printer.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

    public String getNamePrinter(){
        String namePrinter = null;
        try {
            FileInputStream file = new FileInputStream(new File("src/ml/resources/printer/printer.xml"));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            XPath xPath = XPathFactory.newInstance().newXPath();
            System.out.println("*************************");
            String findName = "/printer/printerSettings/name";
            System.out.println(findName);
            namePrinter = xPath.compile(findName).evaluate(xmlDocument);

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
        return namePrinter;
    }
    
    public String getHeaderPrinter(){
        String headerPrinter = null;
        try {
            FileInputStream file = new FileInputStream(new File("src/ml/resources/printer/printer.xml"));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            XPath xPath = XPathFactory.newInstance().newXPath();
            System.out.println("*************************");
            String findName = "/printer/printerSettings/header";
            System.out.println(findName);
            headerPrinter = xPath.compile(findName).evaluate(xmlDocument);

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
        return headerPrinter;
    }
    
    public String getAdressPrinter(){
        String adressPrinter = null;
        try {
            FileInputStream file = new FileInputStream(new File("src/ml/resources/printer/printer.xml"));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            XPath xPath = XPathFactory.newInstance().newXPath();
            System.out.println("*************************");
            String findName = "/printer/printerSettings/adress";
            System.out.println(findName);
            adressPrinter = xPath.compile(findName).evaluate(xmlDocument);

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
        return adressPrinter;
    }
    
    public String getInfoPrinter(){
        String infoPrinter = null;
        try {
            FileInputStream file = new FileInputStream(new File("src/ml/resources/printer/printer.xml"));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            XPath xPath = XPathFactory.newInstance().newXPath();
            System.out.println("*************************");
            String findName = "/printer/printerSettings/info";
            System.out.println(findName);
            infoPrinter = xPath.compile(findName).evaluate(xmlDocument);

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
        return infoPrinter;
    }
    
    
    public String getFooterPrinter(){
        String footerPrinter = null;
        try {
            FileInputStream file = new FileInputStream(new File("src/ml/resources/printer/printer.xml"));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            XPath xPath = XPathFactory.newInstance().newXPath();
            System.out.println("*************************");
            String findName = "/printer/printerSettings/footer";
            System.out.println(findName);
            footerPrinter = xPath.compile(findName).evaluate(xmlDocument);

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
        return footerPrinter;
    }
}
