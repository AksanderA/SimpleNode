import Parser.ParseXML;
import Printer.PrintTree;
import Tree.SimpleNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {

        public static void main(String[] args) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String path = bufferedReader.readLine();
                String destination = bufferedReader.readLine();
                ParseXML parseXML = new ParseXML();
                File pathFile = null;
                if (path != null) {
                    pathFile = new File(path);
                }
                SimpleNode root = parseXML.parse(pathFile);
                PrintTree printTree = new PrintTree();
                if (destination != null) {
                    File pathDestination = new File(destination);
                    printTree.print(root, pathDestination);
                }
                MainClass.printConsole(root);
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    public static void printConsole(SimpleNode node) {

        System.out.println(node.toString());
        node.getChildren().forEach(each -> printConsole(each));

    }
}
