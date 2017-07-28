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
import java.math.BigDecimal;
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
import ml.xml.model.Settings;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Dave
 */
public class XMLSettings {

    private Document doc;
    private Element rootElement;

    private void create(Settings settings) {

        // staff elements
        Element staff = doc.createElement("appSettings");
        Attr attr = doc.createAttribute("num");
        attr.setValue("1");
        staff.setAttributeNode(attr);
        rootElement.appendChild(staff);

        Element roundingElem = doc.createElement("rounding");
        roundingElem.appendChild(doc.createTextNode(settings.getRounding().toString()));
        staff.appendChild(roundingElem);

        Element smsElem = doc.createElement("sms");
        smsElem.appendChild(doc.createTextNode(settings.getSmsCheck().toString()));
        staff.appendChild(smsElem);

//        Element adressElem = doc.createElement("adress");
//        adressElem.appendChild(doc.createTextNode(adress));
//        staff.appendChild(adressElem);
//        
//        Element infoElem = doc.createElement("info");
//        infoElem.appendChild(doc.createTextNode(info));
//        staff.appendChild(infoElem);
//
//        Element residueElem = doc.createElement("footer");
//        residueElem.appendChild(doc.createTextNode(footer));
//        staff.appendChild(residueElem);
    }

    public void newRecord(Settings settings) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("settings");
            doc.appendChild(rootElement);

            this.create(settings);

            writeXML();

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }

    }

    //Запись в XML файл
    private void writeXML() {
        try {
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            File file = new File("src/ml/resources/settings/settings.xml");
            try {

                if (file.createNewFile()) {
                    System.out.println("File is created!");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            StreamResult result = new StreamResult(file);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public BigDecimal getRounding() {
        BigDecimal rounding = new BigDecimal(0);
        try {
            FileInputStream file = new FileInputStream(new File("src/ml/resources/settings/settings.xml"));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            XPath xPath = XPathFactory.newInstance().newXPath();
            System.out.println("*************************");
            String findName = "/settings/appSettings/rounding";
            System.out.println(findName);
            rounding = new BigDecimal(xPath.compile(findName).evaluate(xmlDocument));

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
        return rounding;
    }

    public boolean getSms() {
        boolean smsCheck = false;
        try {
            FileInputStream file = new FileInputStream(new File("src/ml/resources/settings/settings.xml"));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            XPath xPath = XPathFactory.newInstance().newXPath();
            System.out.println("*************************");
            String findName = "/settings/appSettings/sms";
            System.out.println(findName);
            smsCheck = Boolean.parseBoolean(xPath.compile(findName).evaluate(xmlDocument));

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
        return smsCheck;
    }

}
