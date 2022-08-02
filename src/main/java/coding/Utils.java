package coding;

import coding.tree.Graph;
import coding.tree.Node;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    private Utils() {
        throw new IllegalArgumentException("Should not be instantiated");
    }

    /**
     * Return amount of pairs with sum k
     *
     * @param array with no duplicates
     * @param k     sum of pair
     * @return amount of pairs with sum k
     */
    public static int countSumsOf2(int[] array, int k) {
        if (array.length < 2) {
            throw new RuntimeException("Incorrect array");
        }

        Objects.hashCode(array);
        Set<Integer> set = Arrays.stream(array).boxed().collect(Collectors.toSet());
        int pairs = 0;
        for (Integer integer : set) {
            if (set.contains(k - integer)) {
                ++pairs;
            }
        }

        return pairs / 2;
    }

    public static boolean chapter1isUnique(String input) {
        //return chapter1isUniqueArray(input);
        return chapter1isUniqueNoMemory(input);
    }

    private static boolean chapter1isUniqueNoMemory(String input) {
        char[] charArray = input.toCharArray();

        for (int aIndex = 0; aIndex < charArray.length - 1; aIndex++) {
            for (int bIndex = aIndex + 1; bIndex < charArray.length; bIndex++) {
                if (charArray[aIndex] == charArray[bIndex]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean chapter1isUniqueArray(String input) {
        int[] charArray = new int[Character.MAX_VALUE];
        for (char charIndex : input.toCharArray()) {
            if (charArray[charIndex] > 0) {
                return false;
            } else {
                charArray[charIndex]++;
            }
        }
        return true;
    }

    public static String chapter1StringCompression(final String input) {
        if (Objects.isNull(input)) {
            return "";
        }

        Map<Character, Integer> stringMap = new LinkedHashMap<>();

        // add chars to map
        for (char index : input.toCharArray()) {
            Character c = index;
            if (stringMap.containsKey(c)) {
                stringMap.put(c, stringMap.get(c) + 1);
            } else {
                stringMap.put(c, 1);
            }
        }

        // generate output string from map;
        StringBuilder output = new StringBuilder();
        for (Character key : stringMap.keySet()) {
            output.append(key);
            output.append(stringMap.get(key));
        }

        return output.toString();
    }

    public static List<Integer> chapter2RemoveDups(List<Integer> input) {
        if (Objects.isNull(input)) {
            return null;
        }

        Set<Integer> dups = new HashSet<>();
        Iterator<Integer> iterator = input.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (dups.contains(next)) {
                iterator.remove();
            } else {
                dups.add(next);
            }
        }

        return input;
    }

    public static boolean chapter2Palindrome(List<Integer> input) {
        if (Objects.isNull(input)) {
            return false;
        }

        ListIterator<Integer> forwardIterator = input.listIterator();
        ListIterator<Integer> backwardIterator = input.listIterator(input.size());

        while (forwardIterator.hasNext() && backwardIterator.hasPrevious()) {
            if (!forwardIterator.next().equals(backwardIterator.previous())) {
                return false;
            }
        }

        return true;
    }

    public static boolean chapter4RouteBetweenNodes(int[][] routes, int a, int b) {
        Graph g = new Graph(routes);

        Set<Node> visited = new HashSet<>();
        List<Node> queue = new LinkedList<>();
        queue.add(g.getNodes().get(a));
        Node index;
        while (!queue.isEmpty()) {
            index = queue.remove(0);
            if (visited.contains(index)) {
                continue;
            } else {
                visited.add(index);
                if (index.equals(g.getNodes().get(b))) {
                    return true;
                }
                queue.addAll(index.children);
            }
        }

        return false;
    }

    public static Node chapter4MinimalTree(int a[]) {
        return createMinimalBST(a, 0, a.length - 1);
    }

    private static Node createMinimalBST(int a[], int start, int end) {
        if (end < start) {
            return null;
        }

        int mid = (start + end) / 2;
        Node n = new Node(a[mid]);
        n.left = createMinimalBST(a, start, mid - 1);
        n.right = createMinimalBST(a, mid + 1, end);
        return n;
    }

    public static List<LinkedList<Node>> createLevelList(Node root) {
        List<LinkedList<Node>> lists = new ArrayList<>();
        createLevelList(root, lists, 0);

        return lists;
    }

    private static void createLevelList(Node root, List<LinkedList<Node>> lists, int level) {
        if (root == null) {
            return;
        }

        LinkedList<Node> list = null;
        if (lists.size() == level) { // level int contained in list
            list = new LinkedList<>();
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLevelList(root.left, lists, level + 1);
        createLevelList(root.right, lists, level + 1);
    }
    /*
    // O(n * log(n))

    private static int getHeight(Node root) {
        if (root == null) {
            return -1;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) +1;
    }

    public boolean chapter4CheckBalanced(Node root) {
        if (root == null) return true;

        int heightDiff = getHeight(root.left) - getHeight(root.right);

        if (Math.abs(heightDiff) > 1) {
            return false;
        } else {
            return chapter4CheckBalanced(root.left) && chapter4CheckBalanced(root.right);
        }

    }*/

    public static boolean chapter4CheckBalanced(Node root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }

    private static int checkHeight(Node root) {
        if (root == null) return -1;

        int leftHeight = checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static boolean chapter4validateBST(Node node) {
        return checkBST(node, null, null);
    }

    private static boolean checkBST(Node node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }

        if ((min != null && node.index <= min)
                || (max != null && node.index > max)) {
            return false;
        }

        if (!checkBST(node.left, min, node.index)
                || !checkBST(node.right, node.index, max)) {
            return false;
        }

        return true;
    }

    public static boolean chapter8RobotInAGrid(int[][] blockedCells, int x, int y) {
        return chapter8RobotInAGrid(blockedCells, 0, 0, x, y);
    }

    private static boolean chapter8RobotInAGrid(int[][] blockedCells, int currX, int currY, int x, int y) {
        if ((currX + 1 == x && currY == y)
                || (currX == x && currY + 1 == y)) {
            return true;
        }
        boolean isXPathFree = currX < x && !isBlocked(blockedCells, currX + 1, currY);
        boolean isYPathFree = currY < y && !isBlocked(blockedCells, currX, currY + 1);

        boolean xPath = isXPathFree && chapter8RobotInAGrid(blockedCells, currX + 1, currY, x, y);
        boolean yPath = isYPathFree && chapter8RobotInAGrid(blockedCells, currX, currY + 1, x, y);

        return xPath || yPath;
    }

    private static boolean isBlocked(int[][] blockedCells, int x, int y) {
        for (int[] rowIndex : blockedCells) {
            if (rowIndex[0] == x && rowIndex[1] == y) {
                return true;
            }
        }
        return false;
    }

    public static int[] chapter10SortedMerge(int[] a, int pos) {
        int aIndex = 0;
        int bIndex = pos;

        while (aIndex < a.length - 1) {
            if (a[aIndex] >= a[bIndex]) {
                shift(a, aIndex, bIndex);
                bIndex++;
            }
            aIndex++;
        }

        return a;
    }

    private static void shift(int[] a, int aIndex, int bIndex) {
        int oldB = a[bIndex];
        for (int index = bIndex; index > aIndex; index--) {
            a[index] = a[index - 1];
        }
        a[aIndex] = oldB;
    }

    public static int deleteAndEarn(int[] nums) {
        int sum = 0;

        ArrayList<Position> pos = getPositions(nums);
        List<ArrayList<Position>> splittedPos = getSplittedPositions(pos);


        for (ArrayList<Position> posIndex : splittedPos) {
            Map<Integer, Integer> memoryMap = new HashMap();
            sum += earnValue(posIndex, 0, memoryMap);
        }

        return sum;
    }

    static class Position {
        public final int number;
        public final int weight;

        public Position(Integer n, Integer w) {
            number = n;
            weight = w;
        }
    }

    private static List<ArrayList<Position>> getSplittedPositions(ArrayList<Position> pos) {
        List<ArrayList<Position>> splittedPos = new LinkedList<>();
        int prevNumber = -10;
        ArrayList<Position> listIndex = null;
        for (int index = 0; index < pos.size(); index++) {
            if (pos.get(index).number == prevNumber + 1) {
                listIndex.add(pos.get(index));
            } else {
                listIndex = new ArrayList<>();
                listIndex.add(pos.get(index));
                splittedPos.add(listIndex);
            }

            prevNumber = pos.get(index).number;
        }

        return splittedPos;
    }

    private static ArrayList<Position> getPositions(int[] nums) {
        TreeMap<Integer, Integer> distributionMap = new TreeMap<>();
        for (int index : nums) {
            Integer value = index;
            if (distributionMap.containsKey(value)) {
                distributionMap.put(value, distributionMap.get(value) + value);
            } else {
                distributionMap.put(value, value);
            }
        }

        ArrayList<Position> positions = new ArrayList<>();
        for (Integer key : distributionMap.keySet()) {
            positions.add(new Position(key, distributionMap.get(key)));
        }

        return positions;
    }

    private static int earnValue(ArrayList<Position> pos, int startPos, Map<Integer, Integer> memoryMap) {
        int key = /*startPos * 1000000 +*/ pos.size() - startPos;
        if (memoryMap.containsKey(key)) {
            return memoryMap.get(key);
        }

        if (pos.size() - startPos == 1) {
            memoryMap.put(key, pos.get(startPos).weight);
            return pos.get(startPos).weight;
        }

        if (pos.size() - startPos == 2) {
            int maxValue = Math.max(pos.get(startPos).weight,
                    pos.get(startPos + 1).weight);
            memoryMap.put(key, maxValue);
            return maxValue;
        }

        if (pos.size() - startPos == 3) {
            int maxValue = Math.max(pos.get(startPos).weight + pos.get(startPos + 2).weight,
                    pos.get(startPos + 1).weight);
            memoryMap.put(key, maxValue);
            return maxValue;
        }

        return Math.max(pos.get(startPos).weight + earnValue(pos, startPos + 2, memoryMap),
                pos.get(startPos + 1).weight + earnValue(pos, startPos + 3, memoryMap));
    }

    public static boolean divisorGame(int n) {
        Map<Integer, Boolean> resultsMap = new HashMap<>();
        resultsMap.put(1, false);
        resultsMap.put(2, true);
        resultsMap.put(3, false);

        fillResultsMap(n, resultsMap);

        return resultsMap.get(n);
    }

    private static void fillResultsMap(int n, Map<Integer, Boolean> resultsMap) {
        for (int i = 4; i <= n; i++) {
            List<Integer> divisors = new ArrayList<Integer>();
            for (int x = 1; x < i; x++) {
                if (i % x == 0) {
                    divisors.add(x);
                }
            }

            boolean isWin = false;
            for (int div : divisors) {
                int next = i - div;
                if (resultsMap.containsKey(next)) {
                    isWin |= !resultsMap.get(next);
                }

                if (isWin) {
                    break;
                }
            }

            resultsMap.put(i, isWin);
        }
    }

    public static int[] leetCodeSortArrayByParityII(int[] nums) {
        int oddIndex = 1;
        int evenIndex = 0;
        boolean needSwapOdd = false;
        boolean needSwapEven = false;
        while (oddIndex < nums.length && evenIndex < nums.length) {
            if (needSwapOdd && needSwapEven) {
                int temp = nums[evenIndex];
                nums[evenIndex] = nums[oddIndex];
                nums[oddIndex] = temp;

                needSwapOdd = false;
                needSwapEven = false;
            }
            if (nums[oddIndex] % 2 == 0) {
                needSwapOdd = true;
            } else {
                oddIndex += 2;
            }

            if (nums[evenIndex] % 2 == 1) {
                needSwapEven = true;
            } else {
                evenIndex += 2;
            }

        }

        return nums;
    }

    public static int leetCodeMaxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);

        int max = Integer.MIN_VALUE;;
        for (int i = 0; i < satisfaction.length; i++) {
            int iScore = getScore(satisfaction, i);
            if ( iScore > max) {
                max = iScore;
            } else {
                break;
            }
        }

        return max > 0 ? max : 0;
    }

    private static int getScore(int[] scores, int startPos) {
        int sum = 0;
        for (int index = 1; index <= scores.length - startPos; index++) {
            sum += index * scores[startPos + index - 1];
        }
        return sum;
    }

}
