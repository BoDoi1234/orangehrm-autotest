package demo.orangehrm.utils;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateHelper {
    public static String getMonthName(int monthNumber) {
        return Month.of(monthNumber).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public static int getMonthNumber(String monthName) {
        return Month.valueOf(monthName.trim().toUpperCase(Locale.ROOT)).getValue();
    }
}
