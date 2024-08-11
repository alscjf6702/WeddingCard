package com.wedding.card.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String formatDate(String input) throws IllegalArgumentException {
        input = input.trim();

        // Case 1: Already in yyyy-MM-dd format
        if (input.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return input;
        }

        // Case 2: If input is in yyyyMMdd format (e.g., 20240910)
        if (input.matches("\\d{8}")) {
            return input.substring(0, 4) + "-" + input.substring(4, 6) + "-" + input.substring(6, 8);
        }

        // Case 3: If input is in yyMMdd format (e.g., 240910)
        if (input.matches("\\d{6}")) {
            String year = "20" + input.substring(0, 2); // Assuming 20xx century
            return year + "-" + input.substring(2, 4) + "-" + input.substring(4, 6);
        }

        // If none of the formats match, throw an exception
        throw new IllegalArgumentException("0000-00-00의 형식으로 입력 해주세요.");
    }

    public static void main(String[] args) {
        // Test cases
        try {
            System.out.println(formatDate("2024-09-10")); // Output: 2024-09-10
            System.out.println(formatDate("20240910"));   // Output: 2024-09-10
            System.out.println(formatDate("240910"));     // Output: 2024-09-10
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
