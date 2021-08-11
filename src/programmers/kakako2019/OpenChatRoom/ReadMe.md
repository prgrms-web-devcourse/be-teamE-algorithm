# 프로그래머스 > 2019 KAKAO BLIND RECRUITMENT > 오픈채팅방

## 풀이과정

### 1. Map 이용하기

유저의 ID는 시종일관 동일하나 닉네임은 여러 번 임의의 순간에 변경될 수 있다.
하지만 출력에 필요한 결과는 마지막 순간의 닉네임이므로 이 값만 알면 된다.
이에 유저가 입장, 닉네임 변경을 할 때 마다 id - nickname을 맵핑시키고, 이를 최종 결과 반환에 사용한다.

---
`"Enter uid1234 Muzi"`
| ID | NickName |
|---|---|
| uid1234 | Muzi |

`"Enter uid4567 Prodo"`
| ID | NickName |
|---|---|
| uid1234 | Muzi |
| uid4567 | Prodo |

`"Leave uid1234"`
| ID | NickName |
|---|---|
| uid1234 | Muzi |

`"Enter uid1234 Prodo"`
| ID | NickName |
|---|---|
| uid1234 | ~~Muzi~~  Prodo |
| uid4567 | Prodo |

`"Change uid4567 Ryan"`
| ID | NickName |
|---|---|
| uid1234 | ~~Muzi~~  Prodo |
| uid4567 | ~~Prodo~~ Ryan |
---

### 3. Log 기록용("누가", "어떤 행동을" 했는지 기록용)으로는 Queue 사용

이 문제에서 로그는 순차적으로 write되고, 또 read 된다.
따라서 액션의 진행 순서를 지켜야 하므로 Queue를 사용한다.

* **이 과정에서 알게 된 사항**
  * (구현체는 모두 동일하게 LinkedList를 사용했어도) List와 Queue 사이에는 엄청난 속도 차이가 존재한다. 그러므로 내가 사용하는 메서드에 따라 인터페이스를 잘 선택하는 것이 생각보다 훨씬 중요한 듯 하다.
  * Queue 사용시 데이터 규모가 크면 Array 기반의 구현체는 배열 확장 과정 떄문에 LinkedList 보다 무조건 안좋다고 생각했는데, **딱히 그렇지도 않다고 한다.** [ArrayDeque](https://docs.oracle.com/javase/8/docs/api/)는 Array 기반의 구현체로 Stack이나 Queue에 주로 사용되지만 공식 API 문서에 떡하니 이렇게 적혀 있다. `This class is likely to be faster than Stack when used as a stack, and faster than LinkedList when used as a queue.`
  * 한 번 읽어보면 좋을것 같은 토론? 게시글 입니다. [링크](https://okky.kr/article/445763)
  * `array resizing이 매우 빈번하게 나타날 것을 확신 할 수 있는 상황이 아니라면 앞으로 ArrayDeque를 쓰자!`

### 2. 각 recoed 별 과정

* Enter의 경우
  * id-nickname 매핑 테이블을 업데이트 하고, id와 action(입장)을 Queue 에 넣는다.
* Leave의 경우
  * id와 action(퇴장)을 Queue 에 넣는다.
* Change의 경우
  * id-nickname 매핑 테이블을 업데이트한다.

### 3. 최종 결과 스트링 저장

log 길이만큼의 String array를 생성하고, id log와 action log에서 차례대로 poll 한 결과를 합쳐 저장한다.

```java
int size = idLog.size();
String[] answer = new String[size];
for (int i = 0; i < size; i++) {
    answer[i] = id2NickName.get(idLog.poll()) + actionLog.poll();
}
```
