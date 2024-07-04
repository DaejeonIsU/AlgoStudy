import java.io.*;
import java.util.*;

class Solution {
    static int R, C, startR, startC;
    static char[][] map;
    static boolean[][] visited;
    static Queue<int[]> q = new ArrayDeque<>();
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    
    public int solution(String[] board) {
        R = board.length; C = board[0].length();
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = board[i].toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'R') {
                    startR = i; startC = j;
                }
            }
        }
        visited = new boolean[R][C];
        
        q.offer(new int[] {startR, startC, 0});
        visited[startR][startC] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (map[cur[0]][cur[1]] == 'G') {
                return cur[2];
            }
            for (int d = 0; d < 4; d++) {
                int[] next = go(cur, d);
                if (visited[next[0]][next[1]]) continue;
                q.offer(next);
                visited[next[0]][next[1]] = true;
            }
        }
        return -1;
    }
    
    public int[] go(int[] cur, int d) {
        int nextR = cur[0];
        int nextC = cur[1];
        while (true) {
            if (!canMove(nextR + dr[d], nextC + dc[d])) break;
            nextR += dr[d];
            nextC += dc[d];
        }
        return new int[] {nextR, nextC, cur[2] + 1};
    }
    
    public boolean canMove(int r, int c) {
        if (r < 0 || r >= R || c < 0 || c >= C) return false;
        return map[r][c] != 'D';
    }
}
