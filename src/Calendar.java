/**
 * Given a month and a year, construct the corresponding calendar for that month
 * and year.
 */

public class Calendar {
	// Instance fields to compute the calendar
	private int month;

	private int year;

	private int firstDay; // 0 -> Sunday

	private int totalMonthDays;

	private String monthName;

	/**
	 * Define a calendar for a month and a year (assume that the input is valid)
	 * 
	 * @param year
	 *            the year of the calendar (>= 1)
	 * @param month
	 *            the month of the calendar (>= 1 AND &lt;= 12)
	 */
	public Calendar(int month, int year) {
		this.month = month;
		this.year = year;

		// find the day of the week of the first month
		dayOfTheWeek(month, year);

		// find the total days of each month
		totalDaysOfMonth(month, year);

		// find the name of the month in string
		nameOfTheMonth();

	}

	/**
	 * postcondition: result==the calendar nicely formatted in a String.<br>
	 * Example:<br>
	 * 
	 * <pre>
	 * 
	 *  August  2003
	 *  Su Mo Tu We Th Fr Sa
	 *                 1  2
	 *  3  4  5  6  7  8  9
	 *  10 11 12 13 14 15 16
	 *  17 18 19 20 21 22 23
	 *  24 25 26 27 28 29 30
	 *  31
	 * 
	 * </pre>
	 */
	public String toString() {

		int weekCount = 0;
		String output = monthName + " " + year + "\n";
		output += "Su Mo Tu We Th Fr Sa\n";
		for (int i = 0; i < firstDay; i++) {
			output += "   ";
			weekCount++;
		}
		for (int d = 1; d <= totalMonthDays; d++) {
			if (d <= 9) {
				output += " ";

			}
			weekCount++;
			output += d + " ";
			if (weekCount % 7 == 0) {
				output += "\n";
			}
		}
		return output;
	}

	// Add your own private methods below

	/**
	 * finds the day of the week of the first month
	 * 
	 * @param month
	 * @param year
	 */
	private void dayOfTheWeek(int month, int year) {
		int totalYearDays = 0;
		for (int startYear = 1; startYear < year; startYear++) {
			totalYearDays += 365;
			if (isLeapYear(startYear)) {
				totalYearDays++;
			}
		}

		for (int i = 1; i < month; i++) {
			totalYearDays += totalDaysOfMonth(i, year);
		}

		// totalYearDays % 7 from monday
		// (totalYearDays + 1) % 7 from sunday
		firstDay = ((totalYearDays % 7) + 1) % 7;
	}

	/**
	 * get the total days of each month
	 * 
	 * @param month
	 * @param year
	 * @return the int of the days of the month
	 */
	private int totalDaysOfMonth(int month, int year) {
		// ifs conditional to determine the total days of a month
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			totalMonthDays = 31;
		} else if (month == 2 && isLeapYear(year)) {
			totalMonthDays = 29;
		} else if (month == 2 && !isLeapYear(year)) {
			totalMonthDays = 28;
		} else {
			totalMonthDays = 30;
		}
		return totalMonthDays;
	}

	/**
	 * determine the month that is being input by user to string month
	 * 
	 * @return the string name of the month
	 */
	private String nameOfTheMonth() {

		// list of month name
		String[] name = { "", "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		monthName = name[month];
		return monthName;
	}

	/**
	 * return true if the given year is a leap year, and fals if not
	 * 
	 * @param year
	 * @return if the year is a leap year or not
	 */
	private boolean isLeapYear(int year) {
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}
}
