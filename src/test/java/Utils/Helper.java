package Utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Helper — miscellaneous utility methods available across the test suite.
 */
public class Helper {

    private static final Faker faker = new Faker();

    /** Generate a random email address using Faker. */
    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    /** Generate a random full name. */
    public static String randomName() {
        return faker.name().fullName();
    }

    /** Generate a random alphanumeric string of given length. */
    public static String randomString(int length) {
        return faker.lorem().characters(length, true, true);
    }

    /** Current date/time formatted as a filename-safe string. */
    public static String timestamp() {
        return new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
    }

    /** Pause execution for the given number of seconds (use sparingly). */
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
