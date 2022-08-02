package coding;

import coding.tree.Graph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static coding.Utils.*;

public class SumOf2 {

    @Test
    public void checkSumOf2Trivial() {
        assertEquals(1, Utils.countSumsOf2(new int[]{1, 2, 3}, 3));
    }

    @Test
    public void checkSumOf2Big() {
        assertEquals(2, Utils.countSumsOf2(new int[]{1, 2, 3, 4, 5, 0}, 4));
    }

    @Test
    public void checkSumOf2Negative() {
        Exception thrown = assertThrows(
                RuntimeException.class,
                () -> Utils.countSumsOf2(new int[]{1}, 9),
                "Expected RuntimeException, but it didn't"
        );

    }

    @Test
    public void chapter1IsUniqueA() {
        assertFalse(chapter1isUnique("abca"));
    }

    @Test
    public void chapter1IsUniqueB() {
        assertTrue(chapter1isUnique("abcd"));
    }

    @Test
    public void chapter1StringCompressionA() {
        assertEquals("a2b1c5d7", chapter1StringCompression("aabcccccddddddd"));
    }

    @Test
    public void chapter1StringCompressionB() {
        assertEquals("", chapter1StringCompression(null));
    }

    @Test
    public void chapter1StringCompressionC() {
        assertEquals("a1b1 1", chapter1StringCompression("ab "));
    }

    @Test
    public void chapter2RemoveDupsA() {
        assertNull(chapter2RemoveDups(null));
    }

    @Test
    public void chapter2RemoveDupsB() {
        assertTrue(List.of(1, 2, 3, 4, 5).equals(chapter2RemoveDups(new ArrayList<>(List.of(1, 2, 3, 4, 4, 5)))));
    }

    @Test
    public void chapter2RemoveDupsC() {
        assertTrue(List.of(1, 2, 3, 4, 5).equals(chapter2RemoveDups(new ArrayList<>(List.of(1, 2, 3, 4, 4, 5, 5, 5)))));
    }

    @Test
    public void chapter2PalindromeA() {
        assertFalse(chapter2Palindrome(null));
    }

    @Test
    public void chapter2PalindromeB() {
        assertFalse(chapter2Palindrome(new ArrayList<>(List.of(1, 2, 3, 4, 4, 5))));
    }

    @Test
    public void chapter2PalindromeC() {
        assertTrue(chapter2Palindrome(new ArrayList<>(List.of(1, 2, 3, 4, 4, 3, 2, 1))));
    }

    @Test
    public void chapter3StackMinA() {
        MinStack<Integer> minStack = new MinStack<>();

        minStack.push(5);
        minStack.push(2);
        minStack.push(4);
        assertEquals(2, minStack.min());

        minStack.push(1);
        minStack.push(3);
        // 3 1 5 2 4
        assertEquals(1, minStack.min());

        minStack.pop();
        minStack.pop();
        assertEquals(2, minStack.min());
    }

    @Test
    public void chapter4RouteBetweenTwoNodesA() {
        int[][] routes = new int[][]{
                {1, 1},
                {0, 1}};
        assertTrue(chapter4RouteBetweenNodes(routes, 0, 0));

        int[][] routes2 = new int[][]{
                {1, 0},
                {0, 1}};
        assertFalse(chapter4RouteBetweenNodes(routes2, 0, 1));

        int[][] routes3 = new int[][]{
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 1}};
        assertTrue(chapter4RouteBetweenNodes(routes3, 0, 2));
    }

    @Test
    public void chapter4CheckBalancedA() {
        int[][] routes = new int[][]{
                {0, 1, 2, 0, 0, 0, 0},
                {0, 1, 0, 3, 4, 0, 0},
                {0, 0, 2, 0, 0, 5, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        Graph g = new Graph(routes);
        assertTrue(chapter4CheckBalanced(g.getNodes().get(0)));
    }

    @Test
    public void chapter4ValidateBSTA() {
        int[][] routes = new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0},
                {0, 0, 2, 0, 0, 0, 0},
                {0, 1, 0, 3, 0, 1, 0},
                {0, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 0, 1, 5, 0},
                {0, 0, 0, 0, 0, 0, 6}};
        Graph g = new Graph(routes);
        assertTrue(chapter4validateBST(g.getNodes().get(3)));
    }

    @Test
    public void chapter8RobotInAGridA() {
        int[][] blockedCells = new int[][]{
                {0, 3},
                {1, 0},
                {1, 1},
                {1, 3},
                {2, 3},
                {3, 3}};
        assertTrue(chapter8RobotInAGrid(blockedCells, 4, 3));

        int[][] blockedCells2 = new int[][]{
                {0, 1},
                {1, 1},
                {2, 1},
                {3, 1}};
        assertFalse(chapter8RobotInAGrid(blockedCells2, 3, 4));
    }

    @Test
    public void chapter10SortedMergeA() {
        int[] a = new int[]{1,2,4, 6, 9, 3, 5, 7};
        int[] aCopy = new int[]{1,2,4, 6, 9, 3, 5, 7};
        Arrays.sort(aCopy);
        assertArrayEquals(aCopy, chapter10SortedMerge(a, 5));
    }

    @Test
    public void leetCodeDeleteAndEarnA() {
        //assertEquals(4, deleteAndEarn(new int[]{3,1}));
        //assertEquals(22, deleteAndEarn(new int[]{8,7,3,8,1,4,10,10,10,2}));
        //assertEquals(61, deleteAndEarn(new int[]{8,3,4,7,6,6,9,2,5,8,2,4,9,5,9,1,5,7,1,4}));
        //assertEquals(62, deleteAndEarn(new int[]{6,5,10,2,8,6,6,5,2,9,9,4,6,3,3,7,7,8,9,5}));
        /*assertEquals(338, deleteAndEarn(new int[]{10,8,4,2,1,3,4,8,2,9,10,4,8,5,9,1,5,1,6,8,1,1,6,7,8,9,1,7,6,8,
                4,5,4,1,5,9,8,6,10,6,4,3,8,4,10,8,8,10,6,4,4,4,9,6,9,10,7,1,5,3,4,4,8,1,1,2,1,4,1,1,4,9,4,7,1,5,1,10,3,5,
                10,3,10,2,1,10,4,1,1,4,1,2,10,9,7,10,1,2,7,5}));*/
        assertEquals(338, deleteAndEarn(new int[]{94,27,27,27,34,82,97,93,62,10,78,25,23,41,53,16,81,93,52,53,74,
                78,18,27,66,62,40,50,8,20,31,77,26,82,28,60,98,94,26,30,23,49,54,80,69,28,25,32,78,7,1,73,2,31,99,78,50,
                95,28,53,60,78,71,52,25,85,21,16,20,78,96,96,65,1,19,18,24,18,55,69,88,76,14,23,58,17,83,43,63,9,41,6,71,
                7,2,20,21,63,18,36,53,95,36,11,32,64,52,48,52,11,50,48,35,49,24,89,72,33,60,57,46,3,24,90,20,95,87,8,93,
                1,47,2,66,45,57,75,18,76,96,67,65,92,92,41,57,60,98,98,10,64,23,86,100,20,21,93,49,54,77,77,34,98,94,4,9,
                75,67,4,31,82,87,26,70,26,59,86,100,22,15,61,57,73,54,54,76,82,56,63,49,46,53,71,32,1,64,48,20,71,2,60,
                83,80,97,30,2,57,31,82,21,63,52,46,71,55,58,94,16,9,62,67,74,79,87,31,53,27,80,11,33,52,73,2,88,80,9,38,
                37,3,79,24,89,75,10,97,24,63,24,47,80,56,75,23,32,58,72,80,95,28,57,37,17,48,14,85,58,61,58,1,37,14,34,
                76,11,63,67,7,9,8,74,38,97,56,25,67,9,34,62,58,72,77,15,15,90,36,60,39,95,61,28,44,43,56,22,12,81,13,10,
                91,84,46,39,35,39,65,82,41,51,19,76,99,75,88,43,89,21,83,6,35,21,47,4,21,51,76,63,43,71,39,43,16,36,78,
                35,68,75,81,91,97,7,82,44,73,56,39,76,21,76,87,98,6,38,96,84,96,77,84,83,28,52,100,6,52,78,7,91,96,97,
                62,32,26,7,80,71,25,58,23,54,74,81,4,84,35,83,58,64,42,38,30,88,87,52,95,23,31,31,55,7,20,18,84,40,14,
                93,40,45,69,84,30,66,6,88,41,88,98,80,69,64,1,100,48,2,89,6,21,45,73,77,31,20,70,89,30,53,33,59,8,82,63,
                17,10,46,49,86,9,14,68,6,15,55,36,71,64,80,59,40,60,46,24,49,45,78,38,92,43,99,78,5,83,57,76,34,11,93,
                71,71,54,54,29,29,74,83,72,1,6,56,22,85,35,48,29}));
    }

    @Test
    public void leetCodeDivisorGameA() {
        //assertEquals(false, divisorGame(5));
        assertEquals(false, divisorGame(9));
    }

    @Test
    public void leetCodeSortArrayByParityIIA() {
        assertArrayEquals(new int[]{4,5,2,7}, leetCodeSortArrayByParityII(new int[]{4,2,5,7}));
    }

    @Test
    public void leetCodeMaxSatisfactionA() {
        assertEquals(0, leetCodeMaxSatisfaction(new int[]{-1,-4,-5}));
    }

}
