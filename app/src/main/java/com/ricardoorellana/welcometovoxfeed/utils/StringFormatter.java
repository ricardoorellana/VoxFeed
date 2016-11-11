package com.ricardoorellana.welcometovoxfeed.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * String formatter utilities
 */
public class StringFormatter {

    public static String currencyFormatter(double currency) {
        double amount =currency;
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(amount);
    }

    /**
     * Simple string format to add commas and acent to the int numbers
     * @param number to apply format
     * @return Srting formatted value
     */
    public static String audienceFormat(int number) {
        String value = String.valueOf(number);
        if (value.length() > 6) {
            value = value.replaceFirst("(\\d{1,3})(\\d{3})(\\d{3})", "$1\u00B4$2,$3");
        } else if (value.length() >=5 && value.length() <= 6) {
            value = value.replaceFirst("(\\d{2,3})(\\d{3})", "$1,$2");
        }  else if (value.length() == 4) {
            value = value.replaceFirst("(\\d{1})(\\d+)", "$1,$2");
        }
        return value;
    }

    /**
     * Formats date according to the specs
     * @param dateToFormat value to apply format
     * @return
     */
    public static String formatDate(String dateToFormat) {
        String currentDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String formatStyle = "dd MMMM";
        SimpleDateFormat parser = new SimpleDateFormat(formatStyle, Locale.getDefault());
        Date date = new Date();

        try {
            date = new SimpleDateFormat(currentDateFormat).parse(dateToFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parser.format(date);
    }
}
