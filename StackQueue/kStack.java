import java.util.Arrays;
public class kStack {
    static int[] arr;
    static int[] next;
    static int[] top;
    static int n, k, free;
    public kStack(int n, int k) {
        arr = new int[n];
        next = new int[n];
        top = new int[k];

        Arrays.fill(top, -1);
        free = 0;
        int i = 0;
        for (i = 0; i < n - 1; i++) {
            next[i] = i + 1;
        }
        next[i] = -1;
    }

    public static boolean isFull(int sn) {
        return free == -1;
    }

    public static boolean isEmpty(int sn) {
        return top[sn] == -1;
    }

    public static void push(int val, int sn) {
        arr[free] = val;
        int addIdx = free;

        free = next[addIdx];
        next[addIdx] = top[sn];
        top[sn] = addIdx;
    }

    public static int pop(int sn) {
        int remIdx = top[sn];
        int ans = arr[remIdx];
        arr[remIdx] = 0;

        top[sn] = next[remIdx];
        next[remIdx] = free;
        free = remIdx;

        return ans;
    }

    public static void main(String[] args) {
        int n = 11;
        int k = 3;
        kStack ks = new kStack(n, k);
        boolean check = true;
        int ele = -1;
        ks.push(10, 0);
        ks.push(20, 0);
        ks.push(30, 1);
        ks.push(40, 1);
        check = ks.isEmpty(2);
        System.out.println(check);
        ks.push(50, 2);
        check = ks.isFull(1);
        System.out.println(check);
        ks.push(60, 2);
        ks.push(70, 1);
        ele = ks.pop(2);
        System.out.println(ele);
        ele = ks.pop(1);
        System.out.println(ele);
        ele = ks.pop(0);
        System.out.println(ele);
    }
}