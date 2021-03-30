package com.winphone.xjwrj.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

/**
 * @author jack
 */
public class XmlUtil {

    public static Document parse(String xmlStr) {
        DocumentBuilderFactory dbf = getDocumentBuilderFactory();

        StringReader sr = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            sr = new StringReader(xmlStr);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            return document;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(sr);
        }
    }

    /**
     * 获取xml节点中的文本
     *
     * @param element
     * @param name
     * @return
     */
    public static String elementText(Element element, String name) {
        NodeList node = element.getElementsByTagName(name);
        if (node.getLength() == 0) {
            return null;
        }
        return node.item(0).getTextContent();
    }

    /**
     * 获取xml文档中的文本
     *
     * @param doc
     * @param name
     * @return
     */
    public static String documentText(Document doc, String name) {
        NodeList node = doc.getElementsByTagName(name);
        if (node.getLength() == 0) {
            return null;
        }
        return node.item(0).getTextContent();
    }

    /**
     * 获取节点下的xml文档
     *
     * @param element
     * @param name
     * @return
     */
    public static Document element(Element element, String name) {
        NodeList list = element.getElementsByTagName(name);
        if (list.getLength() == 0) {
            return null;
        }
        return list.item(0).getOwnerDocument();
    }

    public static String toXml(Map<String, String> params) {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            // 略过空值
            if (StringUtils.isBlank(value)) {
                continue;
            }
            xml.append("<").append(key).append(">");
            xml.append(entry.getValue());
            xml.append("</").append(key).append(">");
        }
        xml.append("</xml>");
        return xml.toString();
    }

    private static DocumentBuilderFactory getDocumentBuilderFactory() {
        return DocumentBuilderFactoryHolder.INSTANCE;
    }

    private static class DocumentBuilderFactoryHolder {
        private static DocumentBuilderFactory INSTANCE = DocumentBuilderFactory.newInstance();
    }
}
