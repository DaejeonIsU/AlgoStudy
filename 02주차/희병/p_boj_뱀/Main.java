import java.io.*;
import java.util.*;

public class Main {
    public static class Snake {
        int r, c, d;
        Snake front;

        public Snake(int r, int c, int d, Snake front) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.front = front;
        }
    }

    public static class Turn {
        int t;
        String d;

        public Turn(int t, String d) {
            this.t = t;
            this.d = d;
        }
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int N, K, L;
    static boolean[][] map;
    static Queue<Turn> turns = new ArrayDeque<>();
    static Snake head, tail;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[N + 1][N + 1];
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            turns.offer(new Turn(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        int curT = 1;
        head = new Snake(1, 1, 0, null);
        tail = null;
        Turn curTurn = turns.poll();
        while (true) {
            int nextR = head.r + dr[head.d];
            int nextC = head.c + dc[head.d];
            if (!canMove(nextR, nextC)) break;
            if (map[nextR][nextC]) {
                grow(nextR, nextC);
                map[nextR][nextC] = false;
            } else {
                move(nextR, nextC);
            }
            if (curTurn != null && curTurn.t == curT) {
                if (curTurn.d.equals("D")) head.d = (head.d + 1) % 4;
                else head.d = (head.d + 3) % 4;
                curTurn = turns.poll();
            }
            curT++;
        }
        System.out.println(curT);
    }

    public static void move(int r, int c) {
        if (tail != null) {
            Snake cur = tail;
            while (cur.front != null) {
                cur.r = cur.front.r;
                cur.c = cur.front.c;
                cur.d = cur.front.d;
                cur = cur.front;
            }
        }
        head.r = r;
        head.c = c;
    }

    public static void grow(int r, int c) {
        if (tail == null) tail = head;
        Snake newHead = new Snake(r, c, head.d, null);
        head.front = newHead;
        head = newHead;
    }

    public static boolean canMove(int r, int c) {
        if (r <= 0 || c <= 0 || r > N || c > N) return false;
        return !crash(r, c);
    }

    public static boolean crash(int r, int c) {
        if (tail == null) return false;
        Snake cur = tail;
        while (cur.front != null) {
            if (cur.r == r && cur.c == c) return true;
            cur = cur.front;
        }
        return false;
    }
}
