package Parser;

import Tree.SimpleNode;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParseXML implements Parser {
    SimpleNode root = null;
    @Override
    public SimpleNode parse(File file) {
        Node node = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            node = doc.getDocumentElement();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return createNode(node) ;
    }
    private SimpleNode createNode(Node rootElement) {
        root = new SimpleNode(rootElement.getNodeName());
        if (rootElement.getNodeValue() != null) {
            root.setValue(rootElement.getNodeValue());
        }
        root.setParent(null);
        if (rootElement.hasAttributes()) {
            NamedNodeMap map = rootElement.getAttributes();
            for (int i = 0; i < map.getLength(); i++) {
                Node nodeAttr = map.item(i);
                root.setAttributes(nodeAttr.getNodeName(), nodeAttr.getNodeValue());
            }
        }
        if (rootElement.hasChildNodes()) {
            NodeList nodeList = rootElement.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    root.addChild(getChildElement(root, nodeList.item(i)));
                }
            }
        }
        return root;
    }

    private SimpleNode getChildElement(SimpleNode parent, Node childRootElement) {
                SimpleNode simpleNode = null;
                    simpleNode = new SimpleNode(childRootElement.getNodeName());
                    if (!childRootElement.getTextContent().isEmpty()) {
                        simpleNode.setValue(childRootElement.getTextContent().trim());
                    }
                    simpleNode.setParent(parent);
                    if (childRootElement.hasAttributes()) {
                        NamedNodeMap map = childRootElement.getAttributes();
                        for (int i = 0; i < map.getLength(); i++) {
                            Node nodeAttr = map.item(i);
                            simpleNode.setAttributes(nodeAttr.getNodeName(), nodeAttr.getNodeValue());
                        }
                    }
                    if (childRootElement.hasChildNodes()) {
                        NodeList nodeList = childRootElement.getChildNodes();
                        for (int i = 0; i < nodeList.getLength(); i++) {
                            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                                simpleNode.addChild(getChildElement(simpleNode, nodeList.item(i)));
                            }
                        }
                    }

        return simpleNode;
    }
}
