package leetCode;

import java.util.List;

public class Lists {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode root = list1;

        List<ListNode> secondList = ListNode.convertToList(list2);

        for (ListNode node : secondList) {
            if (root == null) {
                root = node;
                root.next = null;
                continue;
            }
            ListNode index = root;
            do {
                // end
                if (index.next == null && node.val >= index.val) {
                    index.next = node;
                    node.next = null;
                    break;
                }
                // beginning
                if (index == root && node.val <= index.val) {
                    root = node;
                    node.next = index;

                    break;
                }
                // middle
                if (index.next != null && node.val >= index.val && node.val < index.next.val) {
                    node.next = index.next;
                    index.next = node;

                    break;
                }
                index = index.next;
            } while (index != null);
        }

        return root;
    }


}
