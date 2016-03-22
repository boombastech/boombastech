package uk.co.boombastech.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static java.util.Calendar.AUGUST;
import static org.assertj.core.api.StrictAssertions.assertThat;

public class DateUtilsTest {

	@Test
	public void shouldReturnOptionalWithDateForValidDate() throws Exception {
		Optional<Date> date = DateUtils.parseDate("21 August 2010");

		assertThat(date).isPresent();

		Calendar cal = Calendar.getInstance();
		cal.set(2010, AUGUST, 21);
		Date expectedTime = cal.getTime();

		assertThat(date.get()).isEqualToIgnoringHours(expectedTime);
	}
}