import Console.Console;
import Parser.ParseXML;
import Printer.PrintTree;
import Tree.SimpleNode;

import java.io.File;

public class MainClass {

    public static void main(String[] args) {
        Console console = new Console();
        File sourse = console.consoleReader();
        SimpleNode root = new ParseXML().parse(sourse);
        File destination = console.consoleReader();
        PrintTree printTree = new PrintTree();
        printTree.print(root, destination);
        MainClass.printConsole(root);
        }
    public static void printConsole(SimpleNode node) {

        System.out.println(node.toString());
        node.getChildren().forEach(each -> printConsole(each));

    }
}
