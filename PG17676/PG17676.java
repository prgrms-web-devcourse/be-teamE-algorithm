class PG17676 {
    public int solution(String[] lines) {
        float[] startTime = new float[lines.length]; // 시작시간
        float[] endTime = new float[lines.length]; // 끝시간
        int answer = 0;

        getTime(lines, startTime, endTime);

        for(int i=0;i<lines.length;i++){
            float e = endTime[i] + 1 - 0.001f;
            int cnt = 1;
            
            for(int j=i+1;j<lines.length;j++){
                if(startTime[j]>e)
                    continue;
                else cnt++;
            }

            answer = Math.max(answer, cnt);
        }

        return answer;
    }
    public void getTime(String[] lines, float[] startTime, float[] endTime){ // 시작시간과 끝시간 계산하기
        for(int i=0;i<lines.length;i++){
            String time = lines[i].substring(11,23);
            String[] str = time.split(":");
            float h = Float.parseFloat(str[0]) * 60 * 60;
            float m = Float.parseFloat(str[1]) * 60;
            float s = Float.parseFloat(str[2]);
            endTime[i] = h+m+s;

            time = lines[i].substring(24, lines[i].length()-1);
            float processingTime = Float.parseFloat(time);
            startTime[i] = endTime[i] - processingTime + 0.001f;
        }
    }
}