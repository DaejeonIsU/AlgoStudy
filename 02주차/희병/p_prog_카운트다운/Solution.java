import java.io.*;
import java.util.*;

class Solution {
    static int[][] mem;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        if (o1[0] == o2[0]) return o2[1] - o1[1];
        return o1[0] - o2[0];
    });
    
    public int[] solution(int target) {
        mem = new int[target + 1][2];
        if (target >= 50) mem[50] = new int[] {1, 1};
        for (int i = 1; i <= 20; i++) {
            if (target < i) break;
            if (target >= i * 3) {
                mem[i * 3][0] = 1; mem[i * 3][1] = 0;
            }
            if (target >= i * 2) {
                mem[i * 2][0] = 1; mem[i * 2][1] = 0;
            }
            mem[i][0] = 1; mem[i][1] = 1;
        }
        
        for (int i = 1; i <= target; i++) {
            if (mem[i][0] != 0) continue;
            if (i > 50) pq.offer(new int[] {mem[i - 50][0] + 1, mem[i - 50][1] + 1});
            for (int j = 1; j <= 20; j++) {
                pq.offer(new int[] {mem[i - j][0] + 1, mem[i - j][1] + 1});
                if (i - (j * 2) > 0) pq.offer(new int[] {mem[i - (j * 2)][0] + 1, mem[i - (j * 2)][1]});
                if (i - (j * 3) > 0) pq.offer(new int[] {mem[i - (j * 3)][0] + 1, mem[i - (j * 3)][1]});
            }
            mem[i] = pq.poll();
            pq.clear();
        }
        return mem[target];
    }
}
