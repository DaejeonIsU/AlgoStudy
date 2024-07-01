import java.io.*;
import java.util.*;

public class Main {
    static class Problem {
        int degree;
        List<Integer> list;

        public Problem() {
            this.degree = 0;
            this.list = new ArrayList<>();
        }
    }

    static int N, M;
    static Problem[] list;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new Problem[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new Problem();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].list.add(b);
            list[b].degree++;
        }

        for (int i = 1; i <= N; i++) {
            if (list[i].degree == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur + " ");
            for (Integer i : list[cur].list) {
                if (--list[i].degree == 0) {
                    pq.offer(i);
                };
            }
        }
        System.out.println(sb);
    }
}
