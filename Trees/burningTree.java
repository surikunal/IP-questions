import java.util.List;
import java.util.ArrayList;
public class burningTree {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private static void kDown(TreeNode node, int time, TreeNode blockNode, List<List<Integer>> ans) {
    if (node == null) return;

    if (time == ans.size())
      ans.add(new ArrayList<>());
    ans.get(time).add(node.val);

    kDown(node.left, time + 1, blockNode, ans);
    kDown(node.right, time + 1, blockNode, ans);
  }

  private static int kAway(TreeNode node, int target, List<List<Integer>> ans) {
    if (node == null) return -1;
    if (node.val == target) {
      kDown(node, 0, null, ans);
      return 1;
    }
    int leftDown = kAway(node.left, target, ans);
    if (leftDown != -1) {
      kDown(node, leftDown, node.left, ans);
      return leftDown + 1;
    }
    int rightDown = kAway(node.right, target, ans);
    if (rightDown != -1) {
      kDown(node, rightDown, node.right, ans);
      return rightDown + 1;
    }
    return -1;
  }

  public static List<List<Integer>> distanceK(TreeNode root, int target, int k) {
    List<List<Integer>> ans = new ArrayList<>();
    kAway(root, target, ans);
    return ans;
  }
}
