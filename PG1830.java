class PG1830 {
    public String solution(String sentence) {
        StringBuilder answer = new StringBuilder();

        char sign1 = '0', sign2 = '0';
        int cntSign2 = 0;
        boolean isUsed[] = new boolean[26];
        int idx = 0;

        while (idx < sentence.length()) {
            char s = sentence.charAt(idx);

            if (Character.isUpperCase(s)) {
                if (idx > 0 && Character.isUpperCase(sentence.charAt(idx - 1))) {
                    if (sign2 == '0')
                        answer.append(" " + s);
                    else if (sign1 == '0')
                        answer.append(s);
                    else
                        break;
                } else
                    answer.append(s);
            } else if (Character.isLowerCase(s)) {
                /*
                 * sign1(규칙1)의 조건 : 
                 *  - idx가 1 또는 2이면서 앞에 문자가 대문자일 경우
                 */
                /*
                 * sign2(규칙2)의 조건 : 
                 *  - idx가 0이면서 뒤에 문자가 대문자일 경우 
                 *  - cntSign는 항상 2이하
                 */
                if (idx == 0 && Character.isUpperCase(sentence.charAt(idx + 1))) {
                    sign2 = s;
                    isUsed[s - 'a'] = true;
                    cntSign2 = 1;
                    idx++;
                    continue;
                } else if ((idx == 1 || idx == 2) && Character.isUpperCase(sentence.charAt(idx - 1))) {
                    sign1 = s;
                    if (isUsed[s - 'a'])
                        break;
                    else
                        isUsed[s - 'a'] = true;
                    idx++;
                    continue;
                }

                if (sign2 == s) {
                    if (cntSign2 >= 2)
                        break;
                    else {
                        cntSign2++;
                        if (idx < sentence.length() - 1) // 맨 마지막에 공백이 붙는 것을 방지
                            answer.append(" ");
                        sign1 = sign2 = '0';
                    }
                } else {
                    // 두번째 단어 이상부터 맞닥들이는 sign1과 sign2를 어떻게 구별하는지 모르겠음...
                }
            } else // 공백
                break;

            idx++;
        }

        if (idx < sentence.length())
            answer = new StringBuilder("invalid");

        return answer.toString();
    }

    public static void main(String[] args) {
        System.out.println(new PG1830().solution("SpIpGpOpNpGJqOqA"));
    }
}