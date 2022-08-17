package leetCode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListsTests {

    @Test
    public void merge2ListsTests() {
        ListNode a = ListNode.getList(new ArrayList<>());
        ListNode b = ListNode.getList(Arrays.asList(-8, -3, -1, 1, 2, 4, 6, 6));

        assertTrue(Arrays.asList(-8, -3, -1, 1, 2, 4, 6, 6).equals(ListNode.convertToList(Lists.mergeTwoLists(a, b))));
    }
}
