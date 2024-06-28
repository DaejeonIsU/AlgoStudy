import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }
        int min = Integer.MAX_VALUE;
        int[] arr2 = new int[N-1];
        for(int i=0;i<N-1;i++){
            min = Math.min(arr[i+1]-arr[i], min);
            arr2[i] = arr[i+1]-arr[i];
        }
        int cnt = 0;
        for(int i=0;i<N-1;i++){
            if(arr2[i]==min){
                cnt++;
            }
        }
        System.out.println(cnt);
        
        
    }
}
