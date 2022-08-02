package coding.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    public final int index;
    public List<Node> children;
    public Node left, right;

    public Node(int index) {
        this.index = index;
        children = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return index == node.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

}
