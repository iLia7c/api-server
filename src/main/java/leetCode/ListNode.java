package leetCode;

import java.util.LinkedList;
import java.util.List;

public class ListNode {

    public final int val;
    public ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }


    public static ListNode getList(List<Integer> values) {
         ListNode prev = null;
         ListNode root = null;
         for (int value : values) {
              ListNode node = new ListNode(value);
              if (root == null) {
                   root = prev = node;
              } else {
                   prev.next = node;
                   prev = node;
              }
         }

         return root;
    }

    public static List<ListNode> convertToList(ListNode header) {
        List<ListNode> listNodes = new LinkedList<>();
        ListNode index = header;
        while(index != null) {
            listNodes.add(index);
            index = index.next;
        }
        return listNodes;
    }

}
