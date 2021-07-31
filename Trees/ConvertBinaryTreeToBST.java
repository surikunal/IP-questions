/*
 * convert BinaryTree to BST to do this.. convert BT to doubly linkedlist, sort
 * the linkedlist ( using merge sort ) then convert sorted dll to bst
 * 
 * assumptions: Time: NlogN Space: logN (recursive space)
 * 
 * it is balanced tree ( means -1 <= (lh - rh) <= +1 )
 * 
 * ans also have to do this inplace
 * 
 */

class ConvertBinaryTreeToBST {
    static class Node {
        Node left, right;
        int val;

        Node(int val) {
            this.left = null;
            this.right = null;
            this.val = val;
        }
    }

    public static Node prev;

    public static void helper(Node node) {
        if (node == null)
            return;
        helper(node.left);

        prev.right = node;
        node.left = prev;

        prev = node;

        helper(node.right);
    }

    public static Node bt_to_dll(Node node) {
        Node dummy = new Node(-1);
        prev = dummy;

        helper(node);

        Node head = dummy.right;

        dummy.right = head.left = null;
        return head;
    }

    public static Node midOfDLL(Node head) {
        if (head == null || head.right == null)
            return head;
        Node slow = head, fast = head;
        while (fast.right != null && fast.right.right != null) {
            slow = slow.right;
            fast = fast.right.right;
        }
        return slow;
    }

    public static Node mergeDLL(Node head, Node nhead) {
        if (head == null || nhead == null)
            return head != null ? head : nhead;

        Node dummy = new Node(-1);
        Node curr1 = head, curr2 = nhead;
        Node prev = dummy;
        while (curr1 != null && curr2 != null) {
            if (curr1.val <= curr2.val) {
                prev.right = curr1;
                curr1.left = prev;

                curr1 = curr1.right;
                prev = prev.right;
            } else {
                prev.right = curr2;
                curr2.left = prev;

                curr2 = curr2.right;
                prev = prev.right;
            }
        }

        if (curr1 != null) {
            prev.right = curr1;
            curr1.left = prev;
        } else if (curr2 != null) {
            prev.right = curr2;
            curr2.left = prev;
        }

        Node ans = dummy.right;
        dummy.right = ans.left = null;
        return ans;
    }

    public static Node sort_dll(Node head) {
        if (head == null || head.right == null) {
            return head;
        }
        Node mid = midOfDLL(head);
        Node midNext = mid.right;
        mid.right = midNext.left = null;

        return mergeDLL(sort_dll(mid), sort_dll(midNext));
    }

    // left : prev, right : next;
    public static Node dll_to_bst(Node node) {
        if (node == null || node.right == null)
            return node;

        Node mid = midOfDLL(node);
        Node left_DLL_Head = node;
        Node right_DLL_Head = mid.right;

        mid.left.right = mid.right.left = null;
        mid.left = mid.right = null;

        mid.left = dll_to_bst(left_DLL_Head);
        mid.right = dll_to_bst(right_DLL_Head);

        return mid;
    }

    public static Node bt_to_bst(Node root) {
        Node head = bt_to_dll(root); // T: O(N), S: O(logN)
        head = sort_dll(head); // T: O(NlogN), S: O(logN)
        return dll_to_bst(head); // T: O(NlogN), S: O(logN)
    }
}