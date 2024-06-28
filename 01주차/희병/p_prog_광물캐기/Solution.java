import java.io.*;
import java.util.*;

class Solution {
    static List<int[]> list = new ArrayList<>();
    
    public int solution(int[] picks, String[] minerals) {
        int pickNum = picks[0] + picks[1] + picks[2];
        int bundleNum = 0;
        for (int i = 0; i < minerals.length; i += 5) {
            int dia = 0, iron = 0, stone = 0;
            for (int j = 0; j < 5; j++) {
                if (i + j >= minerals.length) break;
                if (minerals[i + j].equals("diamond")) dia++;
                else if (minerals[i + j].equals("iron")) iron++;
                else stone++;
            }
            list.add(new int[] {dia + iron + stone, 
                               dia * 5 + iron + stone,
                               dia * 25 + iron * 5 + stone});
            if (++bundleNum == pickNum) break;
        }
        
        int answer = 0;
        answer += sortAndCalc(picks, 0, 1);
        answer += sortAndCalc(picks, 0, 2);
        answer += sortAndCalc(picks, 1, 2);
        while (!list.isEmpty()) {
            int[] cur = list.remove(0);
            if (picks[0] > 0) {
                answer += cur[0];
                picks[0]--;
            } else if (picks[1] > 0) {
                answer += cur[1];
                picks[1]--;
            } else {
                answer += cur[2];
                picks[2]--;
            }
        }
        return answer;
    }
    
    public int sortAndCalc(int[] picks, int a, int b) {
        int answer = 0;
        Collections.sort(list, (o1, o2) -> {
           return (o2[b] - o2[a]) - (o1[b] - o1[a]); 
        });
        while (picks[a] > 0 && picks[b] > 0 && !list.isEmpty()) {
            int[] cur = list.remove(0);
            answer += cur[a];
            picks[a]--;
        }
        return answer;
    }
}
