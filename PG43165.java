class PG43165 {
    static int answer = 0, t;
    public int solution(int[] numbers, int target) {
        t = target;
        int tmp = 0; // 중간값

        // i(operator) : 0 = +, 1 = -
        for(int i=0;i<2;i++)
            dfs(numbers, 0, i, tmp);
        
        return answer;
    }
    public void dfs(int[] numbers, int idx, int operator, int tmp){
        if(operator==0) // +
            tmp += numbers[idx];
        else tmp -= numbers[idx]; // -

        if(idx+1==numbers.length){
            if(tmp==t)
                answer++;
            return;
        }

        for(int i=0;i<2;i++)
            dfs(numbers, idx+1, i, tmp);
    }
}