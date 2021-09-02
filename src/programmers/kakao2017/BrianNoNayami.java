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
		try {
			while (!words.isEmpty()) {
				int size = words.size();

				for (int i = 0; i < size; i++) {
					String word = words.poll();

					// [규칙 2]의 효과 제거하기
					List<String> parsed = parseByRuleTwo(word);

					// [규칙 2]의 중복 적용 여부 검증
					String str = parsed.get(0);
					if (isLowerCase(str.charAt(0))) {
						throw new RuntimeException("invalid");
					}

					// [규칙 1]의 효과 제거하기
					parsed = parseByRuleOne(parsed);

					for (String str2 : parsed) {
						if (!str2.isEmpty()) {
							words.offer(str2.trim());
						}
					}
				}

				while (!words.isEmpty() && isOnlyUpperCase(words.peek())) {
					sb.append(words.poll());
					sb.append(" ");
				}
			}
		} catch (RuntimeException e) {
			return "invalid";
		}

		return sb.toString().trim();
	}

	private List<String> parseByRuleOne(List<String> parsed) {
		List<String> result = new ArrayList<>();
		String word = parsed.remove(0);

		if (isOnlyUpperCase(word)) {
			result.add(word);
			result.addAll(parsed);
			return result;
		}

		// 이하, word에는 반드시 1개 이상의 소문자가 있음
		int firstOccurrenceIndex = 0;
		int length = word.length();
		for (int i = 1; i < length; i++) {
			if (isLowerCase(word.charAt(i))) {
				firstOccurrenceIndex = i;
				break;
			}
		}

		char firstOccurrenceChar = word.charAt(firstOccurrenceIndex);
		String[] split = word.split(String.valueOf(firstOccurrenceChar));
		// [규칙 1]은 원래 단어의 모든 글자 하나하나 마다 기호(소문자 영어)를 넣는다.
		if (split.length == 2) {
			String str1 = "";
			String str2 = "";
			String str3 = "";
			try {
				str1 = split[0].substring(0, split[0].length() - 1);
				str2 = "" + split[0].charAt(split[0].length() - 1) + split[1].charAt(0);
				str3 = split[1].substring(1);
			} catch (IndexOutOfBoundsException e) {
				// pass
			}
			result.add(str1);
			result.add(str2);
			result.add(str3);
			result.addAll(parsed);
			return result;
		}

		for (int i = 1; i < split.length - 1; i++) {
			if (split[i].length() != 1) {
				throw new RuntimeException("invalid");
			}
		}

		String wordBeforeIt = split[0].substring(0, split[0].length() - 1);
		StringBuilder sb = new StringBuilder();

		sb.append(split[0].charAt(split[0].length() - 1));
		for (int i = 1; i < split.length - 1; i++) {
			sb.append(split[i]);
		}
		sb.append(split[split.length - 1].charAt(0));

		result.add(wordBeforeIt);
		// [규칙 1]의 효과를 제거한 단어
		result.add(sb.toString());
		// 나머지 단어
		try {
			result.add(split[split.length - 1].substring(1));
		} catch (IndexOutOfBoundsException e) {
			// pass
		}
		result.addAll(parsed);

		if (!used.add(firstOccurrenceChar)) {
			result.clear();
		}

		while(result.remove("")) {}
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
		if (isOnlyUpperCase(word)) {
			// 이미 복원이 완료된 단어임
			result.add(word);
			return result;
		}
		// 이하, 반드시 소문자가 한 개 이상 존재한다.
		int firstOccurrenceIndex = 0;
		int length = word.length();
		for (int i = 0; i < length; i++) {
			if (isLowerCase(word.charAt(i))) {
				firstOccurrenceIndex = i;
				break;
			}
		}

		char firstOccurrenceChar = word.charAt(firstOccurrenceIndex);
		int count = countOccurrence(word, firstOccurrenceChar);
		if (count != 2) {
			// [규칙 2]의 적용을 받기 위해서는 반드시 소문자 2개가 있어야 함.
			// [규칙 2]에 의한 파싱을 할 수 없다.
			result.add(word);
			return result;
		}

		int secondOccurrenceIndex = word.lastIndexOf(firstOccurrenceChar);
		// [규칙 2]에 따르면 반드시 소문자 사이에 1개 이상의 대문자가 존재해야 한다.
		if (secondOccurrenceIndex == firstOccurrenceIndex + 1) {
			throw new RuntimeException("invalid");
		}

		// [규칙 2]의 효과 제거
		String[] split = word.split(String.valueOf(firstOccurrenceChar));
		Collections.addAll(result, split);

		// 검증: 이미 등장한 소문자가 다시 사용되었으면 유효하지 않음
		if (!used.add(firstOccurrenceChar)) {
			result.clear();
		}
		while(result.remove("")) {}
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