import java.io.*;
import java.util.*;

class Solution {
    class Task {
        String name;
        int[] start;
        int playtime;
        
        public Task(String name, int[] start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
    
    static Stack<Task> stack = new Stack<>();
    
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int ansIdx = 0;
        
        Arrays.sort(plans, (o1, o2) -> {
            return calcMin(calcStart(o2[1]), calcStart(o1[1]));
        });
        for (int i = 0; i < plans.length - 1; i++) {
            Task curTask = new Task(plans[i][0], calcStart(plans[i][1]), Integer.valueOf(plans[i][2]));
            int[] nextStart = calcStart(plans[i + 1][1]);
            
            int rest = calcMin(curTask.start, nextStart);
            if (rest >= curTask.playtime) {
                answer[ansIdx++] = curTask.name;
                rest -= curTask.playtime;
                
                while (rest > 0 && !stack.isEmpty()) {
                    Task stackTask = stack.pop();
                    if (rest >= stackTask.playtime) {
                        answer[ansIdx++] = stackTask.name;
                        rest -= stackTask.playtime;
                    } else {
                        stackTask.playtime -= rest;
                        stack.push(stackTask);
                        rest = 0;
                    }
                }
            } else {
                curTask.playtime -= rest;
                stack.push(curTask);
            }
        }
        answer[ansIdx++] = plans[plans.length - 1][0];
        while (!stack.isEmpty()) {
            Task stackTask = stack.pop();
            answer[ansIdx++] = stackTask.name;
        }
        return answer;
    }
    
    public int[] calcStart(String start) {
        int[] result = new int[2];
        result[0] = Integer.valueOf(start.substring(0, 2));
        result[1] = Integer.valueOf(start.substring(3, 5));
        return result;
    }
    
    public int calcMin(int[] start, int[] end) {
        return (end [0] - start[0]) * 60 + (end[1] - start[1]);
    }
}
