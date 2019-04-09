package Printer;

import Tree.SimpleNode;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

public class PrintTree implements FilePrinter {
    private Element rootElement = null;
    private Document doc = null;


    private Element createElement(SimpleNode simpleNode) {
        Element newElement = null;
        if (simpleNode != null) {
            newElement = new Element(simpleNode.getTagName());
        }

        if (simpleNode.getAttributes().size() != 0) {
            for (Map.Entry<String, String> pair : simpleNode.getAttributes().entrySet()) {
                newElement.setAttribute(pair.getKey(), pair.getValue());
            }
        }
        if (simpleNode.getValue() != null) {
            newElement.setText(simpleNode.getValue());
        }
        if (simpleNode.getChildren() != null) {
            for (SimpleNode node : simpleNode.getChildren()) {
                newElement.addContent(createElement(node));
            }
        }
        return newElement;
    }

    @Override
    public void print(SimpleNode node, File file) {
        rootElement = createElement(node);
        doc = new Document(rootElement);
        try {
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            FileWriter fw = new FileWriter(file);
            outputter.output(doc, fw);
            fw.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
