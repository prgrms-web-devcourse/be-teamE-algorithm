package programmers.kakao2017;

import java.util.*;

public class BrianNoNayami {
	private static Set<Character> used;

	public String solution(String sentence) {
		sentence = sentence.trim();
		if (sentence.length() == 0) {
			return "invalid";
		}

		used = new HashSet<>();
		StringBuilder sb = new StringBuilder();

		Queue<String> words = new ArrayDeque<>();
		words.offer(sentence);

		while (!words.isEmpty()) {
			int size = words.size();

			for (int i = 0; i < size; i++) {
				String word = words.poll();

				List<String> parsed = parseByRuleTwo(word);
				if (parsed.size() == 0) {
					return "invalid";
				}

				// parsed에 대해 [규칙 1]의 효과 제거하기
				parsed = parseByRuleOne(parsed);
				if (parsed.size() == 0) {
					return "invalid";
				}

				for (String str : parsed) {
					if (!str.isEmpty()) {
						words.offer(str.trim());
					}
				}
			}

			while (!words.isEmpty() && isOnlyUpperCase(words.peek())) {
				sb.append(words.poll());
				sb.append(" ");
			}
		}

		return sb.toString().trim();
	}

	private List<String> parseByRuleOne(List<String> parsed) {
		if (parsed.size() == 0) {
			// 바로 직전에 마지막 단어가 words 큐에 들어갔다.
			return Collections.emptyList();
		}

		// 맨 앞 단어에 대해서만 과정을 수행하고, 뒤에 남은 단어(들)은 이후 같은 과정을 반복하면서 알아서 처리되도록 한다.
		List<String> result = new ArrayList<>(); // parsed.get(0)은 처리과정을 거친 후 result에 넣는다. 나머지 parsed의 원소들은 그냥 차례대로 result에 넣는다.

		String word = parsed.remove(0);
		if (isLowerCase(word.charAt(0))) {
			// 이미 [규칙 2]의 효과가 제거된 단어가 들어왔음에도 불구하고 소문자로 시작? INVALID
			return result;
		}

		if (word.length() == 1) {
			// 길이 1인 완성된 단어이다. 볼 것도 없다.
			result.add(word);
			result.addAll(parsed);
			return result;
		}
		int firstOccurrenceIndex = -1;
		int length = word.length();
		for (int i = 1; i < length; i++) {
			if (isLowerCase(word.charAt(i))) {
				firstOccurrenceIndex = i;
				break;
			}
		}
		char firstOccurrence = word.charAt(firstOccurrenceIndex);
		String[] split = word.split(String.valueOf(firstOccurrence));
		// [규칙 1]은 모든 글자 하나하나 마다 기호(소문자 영어)를 넣는다. 따라서 이 배열의 처음과 끝 원소를 제외한 모든 원소는 길이가 1이어야 한다.
		// 그런데 이 배열의 길이가 2라면(= 즉 기호가 단 하나만 쓰였다면) 그냥 합쳐서 반환하면 된다.
		if (split.length == 2) {
			result.add(split[0] + split[1]);
			result.addAll(parsed);
			return result;
		}

		for (int i = 1; i < split.length - 1; i++) {
			if (split[i].length() != 1) {
				return Collections.emptyList();
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < split.length - 1; i++) {
			sb.append(split[i]);
		}
		sb.append(split[split.length - 1].charAt(0));
		// [규칙 1]의 효과를 제거한 단어
		result.add(sb.toString());
		// 나머지 단어
		try {
			result.add(split[split.length - 1].substring(1));
		} catch (IndexOutOfBoundsException e) {
			// pass
		}
		result.addAll(parsed);

		if (!used.add(firstOccurrence)) {
			result.clear();
		}
		return result;
	}

	private boolean isOnlyUpperCase(String token) {
		for (char ch : token.toCharArray()) {
			if (isLowerCase(ch)) {
				return false;
			}
		}
		return true;
	}

	private List<String> parseByRuleTwo(String word) {
		List<String> result = new ArrayList<>();
		if (isUpperCase(word.charAt(0))) {
			// 맨 앞 단어에 [규칙 2]의 적용을 받지 않은, 온전히 복원된 단어가 존재함.
			result.add(word);
			return result;
		}

		int firstOccurrenceIndex = -1;
		int length = word.length();
		for (int i = 0; i < length; i++) {
			if (isLowerCase(word.charAt(i))) {
				firstOccurrenceIndex = i;
				break;
			}
		}

		// 이미 word는 복원이 완료된 단어임!
		if (firstOccurrenceIndex == -1) {
			result.add(word);
			return result;
		}

		char firstOccurrence = word.charAt(firstOccurrenceIndex);
		int count = countOccurrence(word, firstOccurrence);
		if (count != 2) {
			// [규칙 2]의 적용을 받기 위해서는 반드시 소문자 2개가 있어야 함.
			// [규칙 2]의 적용을 받지 않는데 소문자로 시작하는 경우는 INVALID 한 경우 뿐임
			return Collections.emptyList();
		}

		int secondOccurrenceIndex = word.lastIndexOf(firstOccurrence);
		// [규칙 2]에 따르면 반드시 소문자 사이에 1개 이상의 대문자가 존재해야 함.
		if (secondOccurrenceIndex == firstOccurrenceIndex + 1) {
			return Collections.emptyList();
		}

		// [규칙 2]의 효과 제거
		String[] split = word.split(String.valueOf(firstOccurrence));
		Collections.addAll(result, split);

		// 검증: 이미 등장한 소문자가 다시 사용되었으면 유효하지 않음
		if (!used.add(firstOccurrence)) {
			result.clear();
		}
		return result;
	}

	private int countOccurrence(String str, char target) {
		int count = 0;
		for (char ch : str.toCharArray()) {
			if (ch == target) {
				count++;
			}
		}
		return count;
	}

	private boolean isLowerCase(char ch) {
		return 'a' <= ch && ch <= 'z';
	}

	private boolean isUpperCase(char ch) {
		return 'A' <= ch && ch <= 'Z';
	}
}