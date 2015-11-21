package com.javarush.test.level33.lesson10.bonus01;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no">
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
</first>
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        String result = null;
        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(obj, writer);
            //
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(new ByteArrayInputStream(writer.toString().getBytes("UTF-8")));

            NodeList nodeList = document.getElementsByTagName(tagName);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node currNode = nodeList.item(i);
                Node newNode = document.createComment(comment);
                nodeList.item(i).getParentNode().insertBefore(newNode, currNode);
            }

            convertTextToACDATA(document.getElementsByTagName(document.getDocumentElement().getTagName()));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            StringWriter sw = new StringWriter();
            StreamResult streamResult = new StreamResult(sw);
            transformer.transform(new DOMSource(document), streamResult);
            result = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void convertTextToACDATA(NodeList nodeList){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currNode = nodeList.item(i);
            if (currNode.getNodeType()==3){
                String text = currNode.getNodeValue();
                if (text.contains("<")){
                    CDATASection CDATAelement = currNode.getOwnerDocument().createCDATASection(text);
                    currNode.getParentNode().appendChild(CDATAelement);
                    currNode.getParentNode().removeChild(currNode);
                }
            }
            else {
                convertTextToACDATA(currNode.getChildNodes());
            }
        }
    }

    public static void main(String[] args) {
        ExampleClass exampleClass = new ExampleClass();
        String str = toXmlWithComment(exampleClass, "arrField1", "Комментарий");
        System.out.println(str);
    }

}

@XmlRootElement
class ExampleClass {
    public String CDATAField = "aaa <a> aaaa";
    public String field2 = "поле2";
    @XmlElementWrapper(name = "forArrField1")
    public String[] arrField1 = {"qq", "ww", ""};
    public String[] arrField2 = {"zz", "xx"};
    @XmlElementWrapper(name = "forListField")
    public List<AnotherClass> listField = new ArrayList<AnotherClass>(){{
       add(new AnotherClass());
    }};

}
class AnotherClass{
    public String[] arrField1 = {"QQ", "WW"};
    public String[] CDATAField = new String[]{"<arrField1>", "<"};
}
