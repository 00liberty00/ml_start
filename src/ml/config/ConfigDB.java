/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.config;

import ml.crypt.SHAEncode;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

/**
 *
 * @author dave
 */
public class ConfigDB {

    private static SHAEncode shaEnc = new SHAEncode();

    public void conf(String url, String user, String pass, String port) throws Exception {

        shaEnc.newKey();

        try {
            //<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "src/main/resources/hibernate-configuration-3.0.dtd">
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("hibernate-configuration");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("session-factory");
            rootElement.appendChild(staff);

            // set attribute to staff element
            //Attr attr = doc.createAttribute("id");
            //attr.setValue("1");
            //staff.setAttributeNode(attr);
            // shorten way
            // staff.setAttribute("id", "1");
            Element property1 = doc.createElement("property");
            property1.setAttribute("name", "hibernate.dialect");
            property1.appendChild(doc.createTextNode("org.hibernate.dialect.MySQLDialect"));
            staff.appendChild(property1);

            Element property2 = doc.createElement("property");
            property2.setAttribute("name", "hibernate.connection.driver_class");
            property2.appendChild(doc.createTextNode("com.mysql.jdbc.Driver"));
            staff.appendChild(property2);

            Element property3 = doc.createElement("property");
            property3.setAttribute("name", "hibernate.connection.url");
            property3.appendChild(doc.createTextNode("jdbc:mysql://" + url + ":" + port + "/marleo?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull"));
            staff.appendChild(property3);

            Element property4 = doc.createElement("property");
            property4.setAttribute("name", "hibernate.connection.username");
            property4.appendChild(doc.createTextNode(user));
            staff.appendChild(property4);

            Element property5 = doc.createElement("property");
            property5.setAttribute("name", "hibernate.connection.password");
            shaEnc.encode(pass);
            String p = shaEnc.displayEncode();
            //String name = "root";
            property5.appendChild(doc.createTextNode(p));
            staff.appendChild(property5);

            /*Element property5 = doc.createElement("property");
             property5.setAttribute("name", "hibernate.connection.password");
             property5.appendChild(doc.createTextNode(pass));
             staff.appendChild(property5);*/
            Element property6 = doc.createElement("property");
            property6.setAttribute("name", "hibernate.show_sql");
            property6.appendChild(doc.createTextNode("true"));
            staff.appendChild(property6);

            Element mapping1 = doc.createElement("mapping");
            mapping1.setAttribute("resource", "ml/model/Accounting.hbm.xml");
            staff.appendChild(mapping1);

            Element mapping2 = doc.createElement("mapping");
            mapping2.setAttribute("resource", "ml/model/CategoryFavorite.hbm.xml");
            staff.appendChild(mapping2);

            Element mapping3 = doc.createElement("mapping");
            mapping3.setAttribute("resource", "ml/model/GoodsBackup.hbm.xml");
            staff.appendChild(mapping3);

            Element mapping4 = doc.createElement("mapping");
            mapping4.setAttribute("resource", "ml/model/Discount.hbm.xml");
            staff.appendChild(mapping4);

            Element mapping5 = doc.createElement("mapping");
            mapping5.setAttribute("resource", "ml/model/Goods.hbm.xml");
            staff.appendChild(mapping5);

            Element mapping6 = doc.createElement("mapping");
            mapping6.setAttribute("resource", "ml/model/Arrival.hbm.xml");
            staff.appendChild(mapping6);

            Element mapping7 = doc.createElement("mapping");
            mapping7.setAttribute("resource", "ml/model/UserSwing.hbm.xml");
            staff.appendChild(mapping7);

            Element mapping8 = doc.createElement("mapping");
            mapping8.setAttribute("resource", "ml/model/UserRoles.hbm.xml");
            staff.appendChild(mapping8);

            Element mapping9 = doc.createElement("mapping");
            mapping9.setAttribute("resource", "ml/model/WriteoffList.hbm.xml");
            staff.appendChild(mapping9);

            Element mapping10 = doc.createElement("mapping");
            mapping10.setAttribute("resource", "ml/model/CheckDiscount.hbm.xml");
            staff.appendChild(mapping10);

            Element mapping11 = doc.createElement("mapping");
            mapping11.setAttribute("resource", "ml/model/Total.hbm.xml");
            staff.appendChild(mapping11);

            Element mapping12 = doc.createElement("mapping");
            mapping12.setAttribute("resource", "ml/model/Barcode.hbm.xml");
            staff.appendChild(mapping12);

            Element mapping13 = doc.createElement("mapping");
            mapping13.setAttribute("resource", "ml/model/Trial.hbm.xml");
            staff.appendChild(mapping13);

            Element mapping14 = doc.createElement("mapping");
            mapping14.setAttribute("resource", "ml/model/Check.hbm.xml");
            staff.appendChild(mapping14);

            Element mapping15 = doc.createElement("mapping");
            mapping15.setAttribute("resource", "ml/model/Favorite.hbm.xml");
            staff.appendChild(mapping15);

            Element mapping16 = doc.createElement("mapping");
            mapping16.setAttribute("resource", "ml/model/CategoryGoods.hbm.xml");
            staff.appendChild(mapping16);

            Element mapping17 = doc.createElement("mapping");
            mapping17.setAttribute("resource", "ml/model/CheckList.hbm.xml");
            staff.appendChild(mapping17);

            Element mapping18 = doc.createElement("mapping");
            mapping18.setAttribute("resource", "ml/model/Writeoff.hbm.xml");
            staff.appendChild(mapping18);

            Element mapping19 = doc.createElement("mapping");
            mapping19.setAttribute("resource", "ml/model/CashIn.hbm.xml");
            staff.appendChild(mapping19);

            Element mapping20 = doc.createElement("mapping");
            mapping20.setAttribute("resource", "ml/model/CashOut.hbm.xml");
            staff.appendChild(mapping20);

            Element mapping21 = doc.createElement("mapping");
            mapping21.setAttribute("resource", "ml/model/ReportsAccounting.hbm.xml");
            staff.appendChild(mapping21);

            Element mapping22 = doc.createElement("mapping");
            mapping22.setAttribute("resource", "ml/model/Orders.hbm.xml");
            staff.appendChild(mapping22);

            Element mapping23 = doc.createElement("mapping");
            mapping23.setAttribute("resource", "ml/model/ArrivalList.hbm.xml");
            staff.appendChild(mapping23);

            Element mapping24 = doc.createElement("mapping");
            mapping24.setAttribute("resource", "ml/model/CaseRecord.hbm.xml");
            staff.appendChild(mapping24);

            Element mapping25 = doc.createElement("mapping");
            mapping25.setAttribute("resource", "ml/model/GoodsAccounting.hbm.xml");
            staff.appendChild(mapping25);

            DOMImplementation domImpl = doc.getImplementation();
            DocumentType doctype = domImpl.createDocumentType("doctype",
                    "-//Hibernate/Hibernate Configuration DTD 3.0//EN",
                    "src/hibernate-configuration-3.0.dtd");

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/hibernate.cfg.xml"));

            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
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
}
