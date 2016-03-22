package uk.co.boombastech.utils;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberUtilsTest {

	@Test
	public void shouldReturnOptionalContainingCorrectValueForValidInput() throws Exception {
		Optional<Integer> integer = NumberUtils.parseInteger("123");
		assertThat(integer).contains(123);
	}

	@Test
	public void shouldReturnEmptyOptionalForInvalidInput() throws Exception {
		Optional<Integer> integer = NumberUtils.parseInteger("asdf");
		assertThat(integer).isEmpty();
	}
}