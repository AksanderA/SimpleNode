package Printer;

import Tree.SimpleNode;

import java.io.File;

public interface FilePrinter {
    public void print(SimpleNode node, File file);
}
