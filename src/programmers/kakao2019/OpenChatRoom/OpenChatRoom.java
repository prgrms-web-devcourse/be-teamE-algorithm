package programmers.kakako2019.OpenChatRoom;

import java.util.*;

public class OpenChatRoom {
    public String[] solution(String[] records) {
        Map<String, String> id2NickName = new HashMap<>();
        
        String entered = "님이 들어왔습니다.";
        String leave = "님이 나갔습니다.";

        // log를 위해 Queue가 아니라 List를 사용하면 25번부터 시간초과
        Queue<String> idLog = new ArrayDeque<>();
        Queue<String> actionLog = new ArrayDeque<>();

        for(String record : records) {
            String[] token = record.split(" "); // Action Id (Nickname)

            switch (token[0]) {
                case "Enter":
                    // 입장시에는 아이디-닉네임 등록이 이루어짐.
                    // 닉네임 변경 후 재입장시에는 자동으로 replace된다.
                    id2NickName.put(token[1], token[2]);

                    // 입장 로그 기록하기
                    idLog.offer(token[1]);
                    actionLog.offer(entered);
                    break;
                case "Leave":
                    // 퇴장 로그 기록하기
                    idLog.offer(token[1]);
                    actionLog.offer(leave);
                    break;
                case "Change":
                    // 아이디-닉네임 매핑 변경
                    id2NickName.replace(token[1], token[2]);

                    // 닉네임 변경은 로그 X
                    break;
            }
        }

        /**
         * 반환용 정보로 변환하기
         * ```map.get(id) + entered or exited```
         * 최종 매핑된 닉네임 + 출입여부가 기록된다.
         */
        int size = idLog.size();
        String[] answer = new String[size];
        for (int i = 0; i < size; i++) {
            answer[i] = id2NickName.get(idLog.poll()) + actionLog.poll();
        }

        return answer;
    }
}
