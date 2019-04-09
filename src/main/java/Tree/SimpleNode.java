package Tree;

import java.util.*;

public class SimpleNode {
    private String tagName;
    private String value;
    private SimpleNode parent;
    private List<SimpleNode> children = new ArrayList<>();
    private Map<String, String> attributes = new HashMap<>();

    public SimpleNode(String tagName) {
        this.tagName = tagName;
    }

    public SimpleNode addChild(SimpleNode child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<SimpleNode> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public SimpleNode getRoot() {
        if (parent == null) {
            return this;
        }
        return parent.getRoot();
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setParent(SimpleNode parent) {
        this.parent = parent;
    }

    public void setAttributes(String name, String value) {
        attributes.put(name,value);
    }

    public String getTagName() {
        return tagName;
    }

    public String getValue() {
        return value;
    }

    public SimpleNode getParent() {
        return parent;
    }

    public List<SimpleNode> getChildren() {
        return children;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public SimpleNode findDFS (String tagName) {
        if (this.tagName.equals(tagName)) {
            return this;
        }
        if (!children.isEmpty()) {
            for (SimpleNode child : children) {
                SimpleNode result = child.findDFS(tagName);
                if (result != null) return result;
            }
        }
        return null;
    }

    public SimpleNode findDFS (String key, String value) {
        if (!this.attributes.isEmpty()) {
            for (Map.Entry<String, String> pair : attributes.entrySet()) {
                if (pair.getKey().equals(key) && pair.getValue().equals(value))
                    return this;
            }
        }
        if (!children.isEmpty()) {
            for (SimpleNode child : children) {
                SimpleNode result = child.findDFS(key, value);
                if (result != null) return result;
            }
        }
        return null;
    }
    Queue<SimpleNode> simpleNodeQueue = new ArrayDeque<>();
    public SimpleNode findBFS (String tagName) {
        if (this.tagName.equals(tagName)) {
            return this;
        }
        if (!this.children.isEmpty()) {
            for (SimpleNode child : children) {
                simpleNodeQueue.add(child);
            }
            while (simpleNodeQueue.peek() != null) {
                SimpleNode result = simpleNodeQueue.remove().findBFS(tagName);
                if (result != null) {
                    simpleNodeQueue.clear();
                     return result;
                }
            }
        }
        return null;
    }
    public SimpleNode findBFS (String key, String value) {
        if (!this.attributes.isEmpty()) {
            for (Map.Entry<String, String> pair : attributes.entrySet()) {
                if (pair.getKey().equals(key) && pair.getValue().equals(value))
                    return this;
            }
        }
        if (!this.children.isEmpty()) {
            for (SimpleNode child : children) {
                simpleNodeQueue.add(child);
            }
            while (simpleNodeQueue.peek() != null) {
                SimpleNode result = simpleNodeQueue.remove().findBFS(key,value);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
    @Override
    public String toString() {
        String result = "";
        if (tagName == null) {
            result = String.format("<%s", " ");
        }else {
            result = String.format("<%s", tagName);
        }
        if (!attributes.isEmpty()) {
           for (Map.Entry<String, String> pair : attributes.entrySet()) {
               result = String.format("%s %2$s: %3$s", result, pair.getKey(), pair.getValue());
           }
        }
        result = String.format("%s>", result);
        if (value != null) {
            result = String.format("%s %s", result, value);
        }
        if (this.children.isEmpty()) {
            result = String.format("%s </%s>", result, tagName);
        }else {
            
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleNode node = (SimpleNode) o;
        return Objects.equals(tagName, node.tagName) &&
                Objects.equals(value, node.value) &&
                Objects.equals(parent, node.parent) &&
                Objects.equals(children, node.children) &&
                Objects.equals(attributes, node.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName, value, parent, children, attributes);
    }
}
