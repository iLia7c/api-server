package coding;


import java.util.LinkedList;
import java.util.List;

public class MinStack<T extends Comparable<? super T>> {
    private static class Node<T extends Comparable<? super T>> {
        public T element;
        public Node previous;
        public Node next;

        public Node(T element) {
            this.element = element;
        }
    }

    private List<Node> content;
    private Node min;

    public MinStack() {
        content = new LinkedList<>();
    }

    public void push(T element) {
        Node node = new Node(element);
        content.add(node);
        insertNode(node);
    }

    private void insertNode(Node node) {
        if (min == null) {
            min = node;
            return;
        }

        Node index = min;
        Node lastElement = min;
        boolean isNotInserted = true;
        while (index != null) {
            if (node.element.compareTo(index.element) < 0) {
                node.previous = index.previous;
                node.next = index;
                index.previous = node;
                isNotInserted = false;
                break;
            } else {
                lastElement = index;
                index = index.next;
            }
        }

        if (isNotInserted) {
            lastElement.next = node;
            node.previous = lastElement;
        }

        if (node.element.compareTo(min.element) < 0) {
            min = node;
        }
    }

    public T pop() {
        Node popped = content.remove(content.size() - 1);

        if (popped.element.compareTo(min.element) == 0) {
            min = popped.next;
        } else {
            popped.previous.next = popped.next;
        }

        return (T) popped.element;
    }

    public T min() {
        return (T) min.element;
    }

}
