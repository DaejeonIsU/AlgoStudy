import java.io.*;
import java.util.*;

class Solution {
    static List<Integer>[] list;
    static int[] dist;
    static Queue<int[]> q = new ArrayDeque<>();
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < roads.length; i++) {
            list[roads[i][0]].add(roads[i][1]);
            list[roads[i][1]].add(roads[i][0]);
        }
        
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        q.offer(new int[] {destination, 0});
        dist[destination] = 0;
        while (!q.isEmpty()) {
            int[] curState = q.poll();
            for (Integer i : list[curState[0]]) {
                if (dist[i] <= curState[1] + 1) continue;
                dist[i] = curState[1] + 1;
                q.offer(new int[] {i, dist[i]});
            }
        }
        
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            if (dist[sources[i]] == Integer.MAX_VALUE) answer[i] = -1;
            else answer[i] = dist[sources[i]];
        }
        return answer;
    }
}
