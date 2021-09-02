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

					List<String> parsed = parseFirstWord(word);
					if (parsed.size() == 2) {
						// 이미 복원 완료된 단어가 있음 (index 0)
						words.offer(parsed.remove(0));
					}

					// [규칙 2]의 효과 제거하기
					parsed = parseByRuleTwo(parsed.get(0));

					// [규칙 2]의 중복 적용 여부 검증
					for (String str : parsed) {
						if (isLowerCase(str.charAt(0))) {
							throw new RuntimeException("invalid");
						}
					}

					// [규칙 2]의 효과가 제거되었으므로 "bAAcAcAb" -> "AAcAcA" 가 되었으므로 맨 앞에 복원 완료된 단어가 나타날 수 있음
					parsed = parseFirstWord(parsed);
					// 맨 앞 단어 검증
					String firstWord = parsed.get(0);
					if (isOnlyUpperCase(firstWord)) {
						words.offer(parsed.remove(0));
					}

					// parsed에 대해 [규칙 1]의 효과 제거하기 <--
					parsed = parseByRuleOne(parsed);

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
		} catch (RuntimeException e) {
			return "invalid";
		}

		return sb.toString().trim();
	}

	private List<String> parseFirstWord(List<String> parsed) {
		List<String> result = parseFirstWord(parsed.remove(0));
		result.addAll(parsed);
		return result;

	}

	private List<String> parseFirstWord(String bigWord) {
		// Length 2 -> [복원 완료된 단어, 복원 해야할 단어군]
		// Length 1 -> [복원 해야 할 단어군]
		List<String> result = new ArrayList<>();
		if (isOnlyUpperCase(bigWord)) {
			result.add(bigWord);
			return result;
		}

		int length = bigWord.length();
		int cutPoint = 0;
		for (int i = 0; i < length; i++) {
			if (isLowerCase(bigWord.charAt(i))) {
				cutPoint = i;
				break;
			}
		}

		char firstOccurrenceChar = bigWord.charAt(cutPoint);
		int count = countOccurrence(bigWord, firstOccurrenceChar); // 2이면 [규칙 2]로 간주하기
		if (count == 2) {
			try {
				result.add(bigWord.substring(0, cutPoint));
				result.add(bigWord.substring(cutPoint));
			} catch (StringIndexOutOfBoundsException e) {
				// 맨 앞에 복원 완료된 단어로 이루어진 부분이 없음
				result.add(bigWord);
			}
		} else {
			try {
				result.add(bigWord.substring(0, cutPoint - 1));
				result.add(bigWord.substring(cutPoint - 1));
			} catch (StringIndexOutOfBoundsException e) {
				// 맨 앞에 복원 완료된 단어로 이루어진 부분이 없음
				result.add(bigWord);
			}
		}

		while(result.remove("")) {}
		return result;
	}

	private List<String> parseByRuleOne(List<String> parsed) {
		// 맨 앞 단어에 대해서만 과정을 수행하고, 뒤에 남은 단어(들)은 이후 같은 과정을 반복하면서 알아서 처리되도록 한다.
		List<String> result = new ArrayList<>(); // parsed.get(0)은 처리과정을 거친 후 result에 넣는다. 나머지 parsed의 원소들은 그냥 차례대로 result에 넣는다.
		String word;
		try {
			word = parsed.remove(0);
		} catch (IndexOutOfBoundsException e) {
			// 더이상 남은 단어가 없음!
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
		char firstOccurrenceChar = word.charAt(firstOccurrenceIndex);
		String[] split = word.split(String.valueOf(firstOccurrenceChar));
		// [규칙 1]은 모든 글자 하나하나 마다 기호(소문자 영어)를 넣는다. 따라서 이 배열의 처음과 끝 원소를 제외한 모든 원소는 길이가 1이어야 한다.
		// 그런데 이 배열의 길이가 2라면(= 즉 기호가 단 하나만 쓰였다면) 기호를 빼고 나뉘어진 두 구간을 합치면 원래 단어가 된다.
		// 그 이상의 크기일경우, 맨 앞에 있는 원소의 마지막 글자 부터 맨 뒤에 있는 원소의 첫 번째 글자까지가 [규칙 1]에 의해 나뉜 원래 단어이다.
		if (split.length == 2) {
			result.add(split[0] + split[1]);
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
		if (isUpperCase(word.charAt(0))) {
			// [규칙 2]의 적용을 받지 않음. (내부에는 있을수도 있음)
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

		char firstOccurrenceChar = word.charAt(firstOccurrenceIndex);
		int count = countOccurrence(word, firstOccurrenceChar);
		if (count != 2) {
			// [규칙 2]의 적용을 받기 위해서는 반드시 소문자 2개가 있어야 함.
			// 이는 [규칙 1]이 적용되는 것 임.
			result.add(word);
			return result;
		}

		int secondOccurrenceIndex = word.lastIndexOf(firstOccurrenceChar);
		// [규칙 2]에 따르면 반드시 소문자 사이에 1개 이상의 대문자가 존재해야 함. -> Invalid
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