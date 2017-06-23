package org.bsnyder.java18.examples;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Comparator;
import java.util.Date;

/**
 * Java 8 Date/Time API
 *
 */
public class TimeApiExample
{
    public static void main( String[] args ) {

        // Date only, no time zone in human readable form
        LocalDate today = LocalDate.now();
        System.out.println("Date only: " + today);
        // System.out.printf("Date only: " + String.format("%1$-15s%n", date));

        // Specific date
        LocalDate sept202017 = LocalDate.of(2017, Month.SEPTEMBER, 20);
        System.out.println("A special day: " + sept202017);

        // Compare two dates
        if (today.equals(sept202017)) {
            System.out.println("The dates match");
        } else {
            System.out.println("The dates do not match");
        }

        Period periodBetween = Period.between(today, sept202017);
        System.out.println("Days between the two days: " + periodBetween.getDays());
        System.out.println("Months between the two months: " + periodBetween.getMonths());

        // Create a MonthDay using the LocalDate
        MonthDay currentMonthDay = MonthDay.from(today);
        // Create a fixed date
        LocalDate dateOfBirth = LocalDate.of(1971, 9, 24);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());

        // More comparison
        if (currentMonthDay.equals(birthday)) {
            System.out.println("Happy Birthday! :-) ");
        } else {
            System.out.println("Not Happy Birthday :-( ");
        }

        // Even more comparison
        if (currentMonthDay.isBefore(birthday)) {
            System.out.println("Today is before my birthday");
        } else if (currentMonthDay.isAfter(birthday)) {
            System.out.println("Today is after my birthday");
        }

        // Grab the components of a date
        int y = today.getYear();
        Month m = today.getMonth();
        int d = today.getDayOfMonth();
        System.out.printf("Year: %d,  Month: %s, day: %d \t %n", y, m, d);

        // Parsing a string date
        String someDay = "20170416";
        LocalDate formattedDate = LocalDate.parse(someDay, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("LocalDate parsed from string '%s': %s %n", someDay, formattedDate);

        // Parsing a different format of string date
        someDay = "Apr 16 2017";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        formattedDate = LocalDate.parse(someDay, formatter);
        System.out.printf("LocalDate parsed from new string '%s': %s %n", someDay, formattedDate);

        // Convert date to formatted string
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        String aDate = LocalDateTime.now().format(formatter);
        System.out.printf("Formatted date: %s %n", aDate);

        // Time only, no date, no time zone
        LocalTime time = LocalTime.now();
        System.out.println("Time only: " + time);
        // System.out.printf("Time only: " + String.format("%1$-15s%n", time));

        // Timestamps using Instant
        Instant timestamp = Instant.now();
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Grab the nanoseconds from timestamp: " + timestamp.getNano());
        Date date = Date.from(timestamp);
        System.out.println("Date from timestamp: " + date);

        // Time and time zone only
        LocalTime localDenverTime = LocalTime.now(ZoneId.of("America/Denver"));
        System.out.println("Time only in Denver: " + localDenverTime);
        // System.out.printf("Time only in Denver: " + String.format("%1$-25s%n", localDenverTime));

        // Date and time
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("Date and time: " + dateTime);
        // Get the month
        Month month = dateTime.getMonth();
        System.out.println("Month: " + month);

        // Date, time and time zone in human readable form
        ZonedDateTime dateTimeAndTimeZone = ZonedDateTime.now();
        System.out.println("Date, time and time zone: " + dateTimeAndTimeZone);

        // More time zones
        ZoneId z = ZoneId.of("America/Los_Angeles");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, z);
        System.out.println("Date/time in specific timezone : " + zonedDateTime);

        ZonedDateTime current = timestamp.atZone(ZoneId.systemDefault());
        System.out.printf("Current time is %s%n%n", current);

        ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of)
                .filter(zoneId -> {
                    return zoneId.getId().contains("America");
                })
                .sorted(Comparator.comparingInt((zoneId ->
                    timestamp.atZone(zoneId).getOffset().getTotalSeconds())))
                .forEach(zoneId -> {
                    ZonedDateTime zdt = current.withZoneSameInstant(zoneId);
                    System.out.printf("%10s %25s %10s%n", zdt.getOffset(), zoneId,
                            zdt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
                });

        // Adding two hours
        time = LocalTime.now();
        LocalTime timePlusTwoHours = time.plusHours(2);
        System.out.println("Current time: " + time);
        System.out.println("Current time plus two hours: " + timePlusTwoHours);

        // Adding one week
        LocalDate todayPlusOneWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Current date: " + today);
        System.out.println("Current date plus one week: " + todayPlusOneWeek);

//        LocalDate nextMonday = LocalDateTime.now()
//                .plus(1)
//                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
//                .atZone(ZoneId.of("MDT"));

        // Timezones via the Clock
        Clock clock = Clock.systemUTC();
        System.out.println("UTC time zone via clock: " + clock );

        clock = Clock.systemDefaultZone();
        System.out.println("Default time zone via clock: " + clock );


    }
}
