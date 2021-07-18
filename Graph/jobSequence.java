// class Solution {

//   int[] JobScheduling(Job arr[], int n) {
//     Arrays.sort(
//       arr,
//       (a, b) -> {
//         return b.profit - a.profit;
//       }
//     );
//     boolean[] vis = new boolean[n];
//     int count = 0, ans = 0;
//     for (int i = 0; i < n; ++i) {
//       for (int j = Math.min(n - 1, arr[i].deadline - 1); j >= 0; --j) {
//         if (vis[j] == false) {
//           vis[j] = true;
//           count++;
//           ans += arr[i].profit;
//           break;
//         }
//       }
//     }
//     return new int[] { count, ans };
//   }
// }
class Solution {

    int[] par;
  
    int find(int i) {
      if (par[i] == i) return i;
      return par[i] = find(par[i]);
    }
  
    int[] JobScheduling(Job arr[], int n) {
      Arrays.sort(
        arr,
        (a, b) -> {
          return b.profit - a.profit;
        }
      );
      par = new int[n + 1];
      for (int i = 0; i < par.length; i++) {
        par[i] = i;
      }
      int ans = 0, count = 0;
      for (Job temp: arr) {
          int par1 = find(temp.deadline);
          if (par1 > 0) {
              par[par1] = find(temp.deadline - 1);
              count++;
              ans += temp.profit;
          }
      }
      return new int[] { count, ans };
    }
  }
  