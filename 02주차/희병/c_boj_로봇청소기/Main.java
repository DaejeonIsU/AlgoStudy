import java.io.*;
import java.util.*;

public class Main {
    static class State {
        int r, c, t, d;

        public State(int r, int c, int t, int d) {
            this.r = r;
            this.c = c;
            this.t = t;
            this.d = d;
        }
    }

    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    static int h, w, startR, startC, dustCnt;
    static char[][] map;
    static int[][] dustMap;
    static boolean[][][] visited;
    static Queue<State> q;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) break;
            dustCnt = 0;
            map = new char[h][w];
            dustMap = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                map[i] = st.nextToken().toCharArray();
                Arrays.fill(dustMap[i], -1);
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 'o') {
                        startR = i;
                        startC = j;
                    } else if (map[i][j] == '*') {
                        dustMap[i][j] = dustCnt++;
                    }
                }
            }

            q = new ArrayDeque<>();
            int answer = -1;
            int goal = (1 << dustCnt) - 1;
            visited = new boolean[h][w][goal + 1];
            q.offer(new State(startR, startC, 0, 0));
            visited[startR][startC][0] = true;
            while (!q.isEmpty()) {
                State curState = q.poll();
                if (dustMap[curState.r][curState.c] != -1) {
                    int dustNum = dustMap[curState.r][curState.c];
                    curState.d = curState.d | (1 << dustNum);
                    visited[curState.r][curState.c][curState.d] = true;
                }
                if (curState.d == goal) {
                    answer = curState.t;
                    break;
                }
                for (int d = 0; d < 4; d++) {
                    int nextR = curState.r + dr[d];
                    int nextC = curState.c + dc[d];
                    if (!canMove(nextR, nextC, curState.d)) continue;
                    q.offer(new State(nextR, nextC, curState.t + 1, curState.d));
                    visited[nextR][nextC][curState.d] = true;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    public static boolean canMove(int r, int c, int d) {
        if (r < 0 || r >= map.length || c < 0 || c >= map[0].length) return false;
        if (map[r][c] == 'x') return false;
        if (visited[r][c][d]) return false;
        return true;
    }
}
