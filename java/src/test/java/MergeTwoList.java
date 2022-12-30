import com.cczu.algorithm.list.ListNode;

public class MergeTwoList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode root = new ListNode(), curr = root;
        while (list1 != null && list2 != null) {
            ListNode tmp = null;
            if (list1.val < list2.val) {
                tmp = list1;
                list1 = list1.next;
            } else {
                tmp = list2;
                list2 = list2.next;
            }
            curr.next = tmp;
            curr = curr.next;
        }
        if (list1 != null) {
            curr.next = list1;
        }
        if (list2 != null) {
            curr.next = list2;
        }

        return root.next;
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1,2,4};
        ListNode list1 = ListNode.build(arr1);
        Integer[] arr2 = {1,2,3};
        ListNode list2 = ListNode.build(arr2);
        MergeTwoList mergeTwoList = new MergeTwoList();

        System.out.println(mergeTwoList.mergeTwoLists(list1, list2));

    }
}
