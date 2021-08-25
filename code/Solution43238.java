
import java.io.*;
import java.util.*;

public class Solution43238 {
    public static void main(String[] args) throws IOException {
        int n = 6;
        int[] times = { 7, 10 };
        long answer = 0;



        Arrays.sort(times);

        long low = 0;
        long high = (long)times[times.length - 1] * n; //60
        long mid = 0;

        while (low <= high) {
            mid = (low + high) / 2; //30
            long sum = 0;
            for (long time : times) {
                sum += mid / time; //mid 시간까지 사용한 사람 수
            }
            if (sum >= n) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }




         System.out.println(answer);
            
    }
    

}
