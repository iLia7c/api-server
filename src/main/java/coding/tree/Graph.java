package coding.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {

    private List<Node> nodes;

    public Graph(int[][] routes) {
        nodes = new ArrayList<>();

        for (int i = 0; i < routes.length; i++) {
            nodes.add(new Node(routes[i][i]));
        }

        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (i == j) {
                    continue;
                }
                if (routes[i][j] > 0) {
                    nodes.get(i).children.add(nodes.get(j));
                    if (nodes.get(i).left == null) {
                        nodes.get(i).left = nodes.get(j);
                    } else if (nodes.get(i).right == null) {
                        nodes.get(i).right = nodes.get(j);
                    }
                }
            }
        }
    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }
}