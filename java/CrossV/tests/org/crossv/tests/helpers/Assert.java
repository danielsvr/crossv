package org.crossv.tests.helpers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

public class Assert {

	public static <T> void assertThat(String reason, T actual,
			Matcher<T> matcher) {
		if (!matcher.matches(actual)) {
			Description description = new StringDescription();
			description.appendText(reason);
			description.appendText("\nExpected: ");
			description.appendDescriptionOf(matcher);
			description.appendText("\n     got: ");

			if (matcher instanceof ActualDescription)
				((ActualDescription) matcher).describeActualValue(description);
			else
				description.appendValue(actual);

			description.appendText("\n");
			throw new java.lang.AssertionError(description.toString());
		}
	}

	public static <T> void assertThat(T actual, Matcher<T> matcher) {
		assertThat("", actual, matcher);
	}
}
