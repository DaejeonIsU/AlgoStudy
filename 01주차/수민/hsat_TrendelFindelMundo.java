import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] map = new int[N][2];
        for(int i=0;i<N;i++){
            for(int j=0;j<2;j++){
                map[i][j] = sc.nextInt();
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            min = Math.min(min, map[i][1]);
        }
        Arrays.sort(map, (o1, o2) -> {
            return o2[0]-o1[0];
        });
        for(int i=0;i<N;i++){
            if(map[i][1] == min){
                System.out.println(map[i][0] + " " + map[i][1]);
                break;
            }
        }
        
        
    }
}
