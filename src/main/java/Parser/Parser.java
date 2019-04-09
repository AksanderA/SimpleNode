package Parser;

import Tree.SimpleNode;

import java.io.File;
import java.io.FileNotFoundException;

public interface Parser {
    SimpleNode parse(File file) throws FileNotFoundException;
}
