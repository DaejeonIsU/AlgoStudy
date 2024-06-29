import java.io.*;
import java.util.*;

class Solution {
    static int start, end, sum;
    
    public int[] solution(int[] sequence, int k) {
        for (int j = sequence.length - 1; j >= 0; j--) {
            if (sequence[j] == k) {
                while (j > 0 && sequence[j - 1] == sequence[j]) j--;
                return new int[] {j ,j};
            }
            if (sequence[j] < k) {
                start = j - 1;
                end = j;
                sum = sequence[j - 1] + sequence[j];
                break;
            }
        }
        
        while (true) {
            if (sum == k) {
                while (start > 0 && sequence[start - 1] == sequence[end]) {
                    start--;
                    end--;
                }  
                return new int[] {start, end};
            }
            if (sum < k) {
                sum += sequence[--start];
            } else {
                sum -= sequence[end--];
                if (start == end) sum += sequence[--start];
            }
        }
    }
}
