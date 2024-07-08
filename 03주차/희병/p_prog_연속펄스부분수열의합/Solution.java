import java.io.*;
import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long sum = 0, max = 0, min = 0;
        for (int i = 0; i < sequence.length; i++) {
            if (i % 2 == 0) sequence[i] = -sequence[i];
            sum += sequence[i];
            max = Long.max(max, sum);
            min = Long.min(min, sum);
        }
        return Math.abs(max - min);
    }
}
