/* Consider a 2-D map with a horizontal river passing through its center. 
There are n cities on the southern bank with x-coordinates a(1) … a(n) and n cities on the northern bank with 
x-coordinates b(1) … b(n). You want to connect as many north-south pairs of cities as possible with bridges such that 
no two bridges cross. When connecting cities, you can only connect city a(i) on the northern bank to city b(i) on the
southern bank. Maximum number of bridges that can be built to connect north-south pairs with the aforementioned 
constraints.
The values in the upper bank can be considered as the northern x-coordinates of the cities and the values in the
bottom bank can be considered as the corresponding southern x-coordinates of the cities to which the northern
x-coordinate city can be connected. */

import java.util.Arrays;
import java.util.Scanner;

public class building_bridges {

  public static Scanner scn = new Scanner(System.in);

  public static void main(String[] args) {
    int n = scn.nextInt();
    int[][] arr = new int[n][2];
    for (int i = 0; i < n; ++i) {
      arr[i][0] = scn.nextInt();
      arr[i][1] = scn.nextInt();
    }
    System.out.println(buildingBridge(arr, n));
    return;
  }

  public static int buildingBridge(int[][] arr, int n) {
    int[] LIS = new int[n];
    for (int i = 0; i < n; i++) {
      LIS[i] = 1;
    }
    Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
    int ans = 0;
    for (int i = 0; i < arr.length; ++i) {
      int lo = 0, hi = ans;
      while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (LIS[mid] < arr[i][1]) {
          lo = mid + 1;
        } else {
          hi = mid;
        }
      }
      LIS[lo] = ans;
      if (ans == lo) ans++;
    }
  }

}
