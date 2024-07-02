import java.io.*;
import java.util.*;

class Solution {
    class State {
        int r, c, t;
        boolean l;
        
        public State(int r, int c, int t, boolean l) {
            this.r = r;
            this.c = c;
            this.t = t;
            this.l = l;
        }
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][][] visited;
    static int startR, startC;
    static Queue<State> q = new ArrayDeque<>();
    
    public int solution(String[] maps) {
        map = new char[maps.length][];
        for (int i = 0; i < maps.length; i++) {
            map[i] = maps[i].toCharArray();
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'S') {
                    startR = i;
                    startC = j;
                }
            }
        }
        visited = new boolean[map.length][map[0].length][2];
        
        q.offer(new State(startR, startC, 0, false));
        visited[startR][startC][0] = true;
        while (!q.isEmpty()) {
            State curState = q.poll();
            if (map[curState.r][curState.c] == 'E' && curState.l) {
                return curState.t;
            }
            if (map[curState.r][curState.c] == 'L' && !curState.l) {
                curState.l = true;
            }
            for (int d = 0; d < 4; d++) {
                int nextR = curState.r + dr[d];
                int nextC = curState.c + dc[d];
                if (!canMove(nextR, nextC, curState.l)) continue;
                
                q.offer(new State(nextR, nextC, curState.t + 1, curState.l));
                if (!curState.l) visited[nextR][nextC][0] = true;
                else visited[nextR][nextC][1] = true;
            }
        }
        return -1;
    }
    
    public boolean canMove(int r, int c, boolean l) {
        if (r < 0 || r >= map.length || c < 0 || c >= map[0].length) return false;
        if (map[r][c] == 'X') return false;
        if (!l && visited[r][c][0]) return false;
        if (l && visited[r][c][1]) return false;
        return true;
    }
}
