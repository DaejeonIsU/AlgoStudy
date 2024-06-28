import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static int[] res;
    static int N;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        
        arr = new int[N]; //비옥함 배열
        res = new int[2];
        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }
        combination(0, 0);
        System.out.println(max);

    }
    static void combination(int cnt, int idx){
        if(cnt == 2){
            findMax(res);
            return;
        }
        for(int i=idx;i<N;i++){
            res[cnt] = arr[i];
            combination(cnt+1, i+1);
        }
    }

    static void findMax(int[] arr){
        max = Math.max(arr[0] * arr[1],max);
    }
}
