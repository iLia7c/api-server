package coding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Chapter6RemoveDups {

    public static class UnsortedLinkedList {
        private Node root;

        public UnsortedLinkedList(Node[] nodes) {
            if (nodes.length > 0) {
                root = nodes[0];

                Node indexNode = root;
                for (int nodeIndex = 1; nodeIndex < nodes.length; nodeIndex++) {
                    indexNode.setNext(nodes[nodeIndex]);

                    indexNode = nodes[nodeIndex];
                }

            }
        }

        public Node getRoot() {
            return root;
        }

        @Override
        public boolean equals (Object other) {
            if (other == null) return false;
            if (other.getClass() == null || other.getClass() != this.getClass()) return false;
            UnsortedLinkedList o = (UnsortedLinkedList) other;

            boolean isNotEnd = true;
            boolean areEqual = true;
            Node indexThis = root;
            Node indexOther = o.getRoot();
            while (isNotEnd && areEqual) {
                isNotEnd = indexThis != null && indexOther != null;
                if (!isNotEnd) {
                    break;
                }

                areEqual &= indexThis.weight == indexOther.weight;

                indexThis = indexThis.next;
                indexOther = indexOther.next;
            }

            return areEqual;
        }

        // TODO not completed
        @Override
        public int hashCode() {
            List<Integer> nodesWeights = new ArrayList<>();

            return 0;


        }

    }

    public static class Node {
        public final int weight;

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        private Node next;

        public Node(int weight) {
            this.weight = weight;
        }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return weight == node.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight);
        }
    }

}
