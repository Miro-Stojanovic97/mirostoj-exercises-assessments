

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.time.LocalDate;

public class salary {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("What is the annual salary?");
        double salary = Double.parseDouble(console.nextLine());
        BigDecimal annualSalary = new BigDecimal(salary);

        System.out.println("What is the start year? [####]");
        int startYear = Integer.parseInt(console.nextLine());

        System.out.println("What is the start month? [##]");
        int startMonth = Integer.parseInt(console.nextLine());

        System.out.println("What is the start day? [##]");
        int startDay = Integer.parseInt(console.nextLine());

        LocalDate start = LocalDate.of(startYear, startMonth, startDay);
        BigDecimal totalPay = calculatePayTilEndOfYear(start, annualSalary);
        System.out.println(totalPay);
    }

    public static BigDecimal calculatePayTilEndOfYear(LocalDate date, BigDecimal yearlySalary) {
        // full year
        LocalDate endOfYear = LocalDate.of(date.getYear(), 12, 31);

        //3, 6 months after start date
        LocalDate threeMonthsLater = date.plusMonths(3);
        LocalDate sixMonthsLater = date.plusMonths(6);

        //weekly pay (salary / 52) and daily pay (salary / 261 work days)
        BigDecimal weeklyPay = yearlySalary.divide(BigDecimal.valueOf(52), 2, RoundingMode.HALF_DOWN);
        BigDecimal dailyPay = yearlySalary.divide(BigDecimal.valueOf(261), 2, RoundingMode.HALF_DOWN);

        // assume pay is on friday
        BigDecimal firstWeeksPay = firstWeekPay(date, dailyPay);
        LocalDate firstMondayOfSecondWeek = firstMondayOfSecondWeek(date);
        LocalDate currentDate = firstMondayOfSecondWeek;

        BigDecimal totalPay = firstWeeksPay;

        while ((currentDate.isBefore(threeMonthsLater)) || (currentDate.isEqual(threeMonthsLater))) {
            totalPay = totalPay.add(weeklyPay);
            currentDate = currentDate.plusWeeks(1);
        }
        weeklyPay = weeklyPay.multiply(BigDecimal.valueOf(1.03));

        while ((currentDate.isBefore(sixMonthsLater)) || (currentDate.isEqual(sixMonthsLater))) {
            totalPay = totalPay.add(weeklyPay);
            currentDate = currentDate.plusWeeks(1);
        }
        weeklyPay = weeklyPay.multiply(BigDecimal.valueOf(1.06));

        while ((currentDate.isBefore(endOfYear)) || (currentDate.isEqual(endOfYear))) {
            totalPay = totalPay.add(weeklyPay);
            currentDate = currentDate.plusWeeks(1);
        }

        return totalPay;

    }

    public static BigDecimal firstWeekPay(LocalDate date, BigDecimal dailyPay) {
        switch (date.getDayOfWeek()) {
            case MONDAY:
                return dailyPay.multiply(BigDecimal.valueOf(5));
            case TUESDAY:
                return dailyPay.multiply(BigDecimal.valueOf(4));
            case WEDNESDAY:
                return dailyPay.multiply(BigDecimal.valueOf(3));
            case THURSDAY:
                return dailyPay.multiply(BigDecimal.valueOf(2));
            case FRIDAY:
                return dailyPay.multiply(BigDecimal.valueOf(1));
            default:
                return dailyPay.multiply(BigDecimal.valueOf(5));
                //week ends treated as monday
        }
    }

    public static LocalDate firstMondayOfSecondWeek(LocalDate date) {
        switch (date.getDayOfWeek()) {
            case MONDAY:
                return date.plusDays(7);
            case TUESDAY:
                return date.plusDays(6);
            case WEDNESDAY:
                return date.plusDays(5);
            case THURSDAY:
                return date.plusDays(4);
            case FRIDAY:
                return date.plusDays(3);
            case SATURDAY:
                return date.plusDays(2);
            case SUNDAY:
                return date.plusDays(1);
            default:
                return null;
        }
    }

    // end of year
    // start of year variable
    // inputs start date, start salary
    // make weekly pay <= salary
    // start date.addMonths(3)



    //method that accepts yearly salary and start date
    //calculates gross salary from start date thru end of yr
}
