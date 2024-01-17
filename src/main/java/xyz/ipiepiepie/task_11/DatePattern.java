package xyz.ipiepiepie.task_11;

import java.util.regex.Pattern;

public class DatePattern {
    // day //
    private static final String day = "\\s([1-9]|0[1-9]|[12][0-9]|3[01])";
    // month //
    private static final String month = "(0[1-9]|1[012])";
    private static final String[] months = {
            "декабрь", "январь", "февраль",
            "март", "апрель", "май",
            "июнь", "июль", "август",
            "сентябрь", "октябрь", "ноябрь",
            "декабря", "января", "февраля",
            "марта", "июля", "июня",
            "августа", "сентября", "октября",
            "ноября"
    };
    // year //
    private static final String year = "(\\d{4})";
    // divider //
    private static final String point = "\\.";
    private static final String space = " ";

    /// PATTERNS ///

    /**
     * Get 'DD.MM.YYYY' {@link Pattern}
     * @return {@link Pattern}
     */
    public static Pattern getDateMonthNumberYearPattern() {
        return Pattern.compile(day + point + month + point + year, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Get 'DD Month YYYY' {@link Pattern}
     * @return {@link Pattern}
     */
    public static Pattern getDateMonthNameYearPattern() {
        return Pattern.compile(day + space + getMonthsPattern() + space + year, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Get 'DD Month' {@link Pattern}
     * @return {@link Pattern}
     */
    public static Pattern getDateMonthNamePattern() {
        return Pattern.compile(day + space + getMonthsPattern(), Pattern.CASE_INSENSITIVE);
    }

    /// MONTHS ///

    /**
     * Get month names pattern
     *
     * @return months pattern
     */
    private static String getMonthsPattern() {
        StringBuilder pattern = new StringBuilder();

        // build pattern from known months
        for (String month : months) {
            pattern.append(month);
            pattern.append("|");
        }

        // return pattern
        return String.format("(%s)", pattern.substring(0, pattern.length() - 1));
    }

}
