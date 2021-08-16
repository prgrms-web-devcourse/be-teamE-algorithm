import java.io.*;
import java.text.*;
import java.util.*;

public class Solution17676 {
    public static void main(String[] args) throws IOException, ParseException {
        String[] lines = { "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" };
        int answer = 0;

        Arrays.sort(lines, Collections.reverseOrder()); //내림차순 정렬
        String[] startTime = new String[lines.length];
        String[] endTime = new String[lines.length];

        
        //완성X
        StringTokenizer st;
        for (int i = 0; i < lines.length; i++) {
            st = new StringTokenizer(lines[i]);
            endTime[i] = st.nextToken();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date date = dateFormat.parse(endTime[i]);
            String miliSecond = st.nextToken();
            Double miliSecondD = Double.parseDouble(miliSecond.substring(0, miliSecond.length() - 1));
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MILLISECOND, (int) (miliSecondD * 1000 * (-1)));

            startTime[i] = dateFormat.format(cal.getTime());
        }
        
        Arrays.sort(endTime, Comparator.reverseOrder());
        Arrays.sort(startTime, Comparator.reverseOrder());


        Queue<String> startTimeQueue = new ArrayDeque<>();
        Queue<String> endTimeQueue = new ArrayDeque<>();



        int dupCount = 0;

        startTimeQueue.add(startTime[0]);
        int index = lines.length - 2;
        
        
        //sorting
        while (!startTimeQueue.isEmpty() || index >= 0) {
            st = new StringTokenizer(lines[index]);

            startTimeQueue.add(st.nextToken());
            endTimeQueue.add(st.nextToken());

            if (startTimeQueue.peek().compareTo(endTimeQueue.peek()) > 0) {
                dupCount -= 1;
                startTimeQueue.poll();
            } else {
                dupCount += 1;
                answer = isMaxAnswer(dupCount, answer);
                endTimeQueue.poll();
            }

            index -= 1;
        }

        System.out.println(Arrays.toString(lines));

    }

    static int isMaxAnswer(int dupCount, int answer) {
        if (dupCount > answer) {
            return dupCount;
        } else {
            return answer;
        }
    }
    
}
