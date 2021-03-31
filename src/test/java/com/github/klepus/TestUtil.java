package com.github.klepus;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtil {
    public static <T> void assertMatch(T actual, T expected, String... ignoredFields) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, ignoredFields);
    }

    public static <T> void assertMatch(Iterable<T> actual, Iterable<T> expected, String... ignoredFields) {
        assertThat(actual).usingElementComparatorIgnoringFields(ignoredFields).isEqualTo(expected);
    }
}
