package programmers.kakao2017;

import java.util.*;

public class BrianNoNayami {
	private static Set<Character> used = new HashSet<>();

	public String solution(String sentence) {
		sentence = sentence.trim();
		if (sentence.length() == 0) {
			return "invalid";
		}

		StringBuilder sb = new StringBuilder();

		Queue<String> words = new ArrayDeque<>();
		words.offer(sentence);

		while (!words.isEmpty()) {
			int size = words.size();
			for (int i = 0; i < size; i++) {
				String word = words.poll();

				List<String> checked = splitAlreadyPerfectWord(word);
				if (checked.size() == 2) {
					words.offer(checked.remove(0));
				}
				word = checked.get(0);

				if (isPossiblyCaseOne(word)) {
					// Case 1
					// 맨 앞 단어 분리하고 reverse Case 1 해주기
					List<String> parsed = parseAndReverseCase1(word);
					if (parsed.isEmpty()) {
						return "invalid";
					}

					// 검증 : "같은 규칙을 한 단어에 두 번 적용할 수 없다"
					if (isStillContainingCaseOne(parsed.get(0))) {
						return "invalid";
					}

					for (String str : parsed) {
						words.offer(str);
					}
				} else {
					// Case 2
					// 맨 앞 단어 분리하고 reverse Case 2 해주기
					List<String> parsed = parseAndReverseCase2(word);
					if (parsed.isEmpty()) {
						return "invalid";
					}

					// 검증 : "같은 규칙을 한 단어에 두 번 적용할 수 없다" -> Case 2의 경우 parseAndReverseCase2 메서드 내부에서 자동으로 같이 이루어짐

					for (String str : parsed) {
						words.offer(str);
					}
				}
			}

			if (hasOnlyCapitalLetters(words.peek())) {
				sb.append(words.poll());
				sb.append(" ");
			}
		}

		return sb.toString().trim();
	}

	private boolean isStillContainingCaseOne(String word) {
		// TODO: 구현
		return false;
	}

	private List<String> splitAlreadyPerfectWord(String word) {
		// length == 1 -> Not start with perfect word
		// length == 2 -> starts with perfect word

		List<String> result = new ArrayList<>();
		int length = word.length();
		for (int i = 0; i < length; i++) {
			if (!isUpperCase(word.charAt(i))) {
				if (i < 2) {
					return List.of(word);
				}

				// found lower letter -> need to distinguish case 1 or 2
				if (i + 2 <= length - 1) {
					if (!isUpperCase(word.charAt(i + 2))) {
						// it might be case 1
						result.add(word.substring(0, i));
						result.add(word.substring(i));
					} else {
						// might be case 2 or invalid. But it doesn't matter for this function.
						result.add(word.substring(0, i - 1));
						result.add(word.substring(i - 1));
					}
		        } else {
					result.add(word.substring(0, i - 1));
					result.add(word.substring(i - 1));
				}
				break;
			}
		}
		return result;
	}

	private boolean hasOnlyCapitalLetters(String word) {
		for (char ch : word.toCharArray()) {
			if ('a' <= ch && ch <= 'z') {
				return false;
			}
		}
		return true;
	}

	private List<String> parseAndReverseCase2(String word) {
		List<String> result = new ArrayList<>();

		if (word.length() <= 1) {
			result.add(word);
			return result;
		}

		char firstOccurrence = word.charAt(1);
		int nextOccurrenceIdx = 1;
		int length = word.length();
		for (int i = 2; i < length; i++) {
			if (word.charAt(i) == firstOccurrence) {
				if (i != nextOccurrenceIdx + 2) {
					result.clear();
					return result;
				}
				nextOccurrenceIdx = i;
			}
		}

		if (!isUpperCase(word.charAt(nextOccurrenceIdx + 1))) {
			return result;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= nextOccurrenceIdx + 1; i += 2) {
			sb.append(word.charAt(i));
		}

		result.add(sb.toString());
		if (nextOccurrenceIdx + 2 < word.length()) {
			result.add(word.substring(nextOccurrenceIdx + 2));
		}

		if (!used.add(firstOccurrence)) {
			result.clear();
		}
		return result;
	}

	private List<String> parseAndReverseCase1(String word) {
		List<String> result = new ArrayList<>();

		char firstOccurrence = word.charAt(0);
		if (word.charAt(1) == firstOccurrence) {
			return result;
		}
		int length = word.length();
		for (int i = 2; i < length; i++) {
			if (word.charAt(i) == firstOccurrence) {
				result.add(word.substring(1, i));
				if (i + 1 < word.length()) {
					result.add(word.substring(i + 1));
				}
				// check invalid case
				if (i + 2 <= word.length()) {
					if (word.charAt(i + 2) == firstOccurrence) {
						result.clear();
					}
				}
				break;
			}
		}

		if (!used.add(firstOccurrence)) {
			result.clear();
		}
		return result;
	}

	private boolean isPossiblyCaseOne(String sentence) {
		if (isUpperCase(sentence.charAt(0))) {
			// Possibly Case 2
			return false;
		}
		return true;
	}

	private boolean isUpperCase(char ch) {
		return 'A' <= ch && ch <= 'Z';
	}
}
