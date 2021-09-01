package programmers.kakao2017;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrianNoNayamiTest {
	BrianNoNayami obj = new BrianNoNayami();

	@Test
	void solution() {
		assertEquals("HELLO WORLD", obj.solution("HaEaLaLaObWORLDb"));
	}

	@Test
	void solution2() {
		assertEquals("SIGONG JOA", obj.solution("SpIpGpOpNpGJqOqA"));
	}

	@Test
	void solution3() {
		assertEquals("invalid", obj.solution("AxAxAxAoBoBoB"));
	}

	@Test
	void test4() {
		assertEquals("invalid", obj.solution("AbAaAbAaCa"));
	}

	@Test
	void test5() {
		assertEquals("AAAA AA", obj.solution("AAAAxAbAx"));
	}

	@Test
	void test6() {
		assertEquals("invalid", obj.solution("abHELLObaWORLD"));
	}

	@Test
	void test7() {
		assertEquals("AAA BBB", obj.solution("AcAcABaBaB"));
	}

	@Test
	void test8() {
		assertEquals("invalid", obj.solution(" "));
	}

	@Test
	void test9() {
		assertEquals("invalid", obj.solution("aaA"));
	}

	@Test
	void test10() {
		assertEquals("invalid", obj.solution("Aaa"));
	}

	@Test
	void test11() {
		assertEquals("invalid", obj.solution("TxTxTxbAb"));
	}

	@Test
	void test12() {
		assertEquals("invalid", obj.solution("bTxTxTaTxTbkABaCDk"));
	}

	@Test
	void test13() {
		assertEquals("invalid", obj.solution("AbAaAbAaCa"));
	}
}