package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * The Main method for this assignment.
     * You can optionally run this to interactively try the three methods.
     * @param args parameters are unused
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a string: ");
        final String userInput = scanner.nextLine();
        scanner.close();
        System.out.println("You entered \"" + userInput + "\"");
        System.out.println(checkForPassword(userInput, 6));
        System.out.println(extractEmails(userInput));
        System.out.println(checkForDoubles(userInput));
    }

    // ===============================================================
    // Method 1 — Password Validation
    // ---------------------------------------------------------------
    // Must return true *only if*:
    // ✔ Non-empty
    // ✔ Length >= minLength
    // ✔ Contains ≥ 1 lowercase letter
    // ✔ Contains ≥ 1 uppercase letter
    // ✔ Contains ≥ 1 digit
    // ===============================================================

    public static boolean checkForPassword(String str, int minLength) {
        // Explanation:
        // (?=.*[a-z])  → must contain lowercase
        // (?=.*[A-Z])  → must contain uppercase
        // (?=.*\\d)    → must contain digit
        // .{minLength,} → total required length
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{" + minLength + ",}$";
        return Pattern.matches(regex, str);
    }

    // ===============================================================
    // Method 2 — Extract valid UofT emails
    // ---------------------------------------------------------------
    // Must return *all* emails found in input string that:
    // ✔ have at least one char before "@"
    // ✔ end in @utoronto.ca **or** @mail.utoronto.ca
    // ===============================================================

    public static List<String> extractEmails(String str) {
        // [A-Za-z0-9._%+-]+ → one or more valid email characters BEFORE @
        // (?:@utoronto\\.ca|@mail\\.utoronto\\.ca) → accepted domains
        final Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+(?:@utoronto\\.ca|@mail\\.utoronto\\.ca)");
        final Matcher matcher = pattern.matcher(str);
        final List<String> result = new ArrayList<>();

        while (matcher.find()) {
            result.add(matcher.group()); // add matches in order found
        }
        return result;
    }

    // ===============================================================
    // Method 3 — Detect repeated uppercase letter
    // ---------------------------------------------------------------
    // Must return true if the *same capital letter* (A–Z)
    // appears twice anywhere in the string.
    // e.g.
    //  "Amazing Apple" → true (A repeats)
    //  "Hello" → false (H only once)
    // ===============================================================

    public static boolean checkForDoubles(String str) {
        // ([A-Z]) captures a capital letter
        // .*\\1.* ensures the same capital appears again later
        return str.matches(".*([A-Z]).*\\1.*");
    }
}