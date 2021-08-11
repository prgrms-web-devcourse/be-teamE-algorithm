
import java.io.*;

public class Solution43165 {
    static int answerN = 0;//전체 횟수를 저장하는 전역변수
    public static void main(String[] args) throws IOException {

        //기본 입력값
        int answer = 0;
        int[] numbers = { 1, 1, 1, 1, 1 };
        int target = 3;




        int sum = 0;
        int index = 0;

        dfs(numbers, target, sum, index);
        answer = answerN;


        System.out.println(answer + "\n");

    }

    /*
    numbers, target : 주어진 입력값
    sum : dfs 연산하면서 계산되는 합
    index : 재귀를 하면서 이번 차례에 계산할 numbers의 인덱스 값
    
    종료조건 : numbers의 숫자를 모두 연산했을 때 종료
                1) 모두 연산했을 때 계산값이 target과 일치할 경우 : answerN +=1;
                2) 계산값이 target과 일치하지 않을 경우 : return;
    
    연산 : 배열의 원소 하나당 2가지의 경우의 수가 있음(+, -)
            모든 배열 조합의 경우의 수를 확인하고 
            마지막 결과값이 일치할 경우에만 answerN +=1; 한다.
    
    */
    static void dfs(int[] numbers, int target, int sum, int index) {
        if (index == numbers.length) {
            if (sum == target) {
                answerN += 1;
            }
            return;
        }

        dfs(numbers, target, sum + numbers[index], index + 1);
        dfs(numbers, target, sum - numbers[index], index + 1);
        
    }
    
}
