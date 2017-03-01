import uwcse.graphics.*;
import uwcse.io.*;

//import java.util.StringTokenizer; // To parse a String
import java.awt.Font; // To select a font for the display of the calendar

import javax.swing.JOptionPane; // To use dialog boxes 

import java.awt.Color; // To select colors

/**
 * CSC 142 - Homework<br>
 * 
 * A CalendarDisplay is a graphics window that displays a calendar for a given
 * year and month. A button on the window allows the user to select the year and
 * month of the calendar.
 * 
 * @author Hans Christian
 */

public class CalendarDisplay extends GWindowEventAdapter {
	// Graphics display
	private GWindow window;

	// To compute the calendar
	private Calendar calendar;

	private TextShape[] t = new TextShape[8];

	// To draw the rectangle for new calendar button and background color
	private Rectangle newCalendar, background;

	// for month and year input
	private Input in = new Input();

	/**
	 * Create the display
	 */
	public CalendarDisplay() {
		// Create the window
		window = new GWindow("Calendar", 400, 300);
		background = new Rectangle(0, 0, window.getWindowWidth(),
				window.getWindowHeight(), Color.LIGHT_GRAY, true);
		window.add(background);

		window.setExitOnClose();

		// Listen for any click on the window
		window.addEventHandler(this);

		// To display the calendar
		int y = 30;
		for (int i = 0; i < t.length; i++) {
			t[i] = new TextShape("", 75, y, Color.WHITE, new Font("Courier",
					Font.BOLD, 20));
			window.add(t[i]);
			y += 23;
		}

		// (call some private methods)
		// Call the calendarInput method to ask input from user
		calendarInput();
		
		// Calendar button on the graphic window
		calendarButton();
	}

	/**
	 * Click on the window. If it is on the button, input a new calendar
	 */
	public void mousePressed(GWindowEvent e) {

		// Location of the click
		int x = e.getX();
		int y = e.getY();

		// New calendar button click when the user click within the
		// assigned coordinates
		if (x >= newCalendar.getX()
				&& x <= newCalendar.getX() + newCalendar.getWidth()
				&& y >= newCalendar.getY()
				&& y <= newCalendar.getY() + newCalendar.getHeight()) {
			
			// call the input method to let the user input new calendar
			calendarInput();
		}
	}

	/**
	 * Displays the calendar in the graphics window
	 */
	public void displayCalendar() {
		String s = calendar.toString();
		// extract all of the lines in s
		// split s on \n
		String[] lines = s.split("\n");
		int index = 0;
		for (String line : lines) {
			t[index].setText(line);
			index++;
		}
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new CalendarDisplay();
	}

	// Add your own private methods below
	/**
	 * create the button for user to click and enter wanted calendar
	 */
	private void calendarButton() {
		newCalendar = new Rectangle(120, 250, 150, 35, Color.DARK_GRAY, true);
		TextShape t = new TextShape("New Calendar", 131, 259, Color.WHITE,
				new Font("Courier", Font.BOLD, 18));
		window.add(newCalendar);
		window.add(t);

	}

	/*
	 * create the input for user to enter month and followed by year this method
	 * allow user to re-enter inputs if they input an invalid month or year
	 */
	private void calendarInput() {
		// boolean initialize
		boolean inputMonth = false;
		boolean inputYear = false;

		// using a while loop to ask user for month and year's
		// input and let the user to re-enter input if they
		// have entered invalid inputs for month and year
		while (inputMonth != true) {
			int month = in.readIntDialog("Enter a valid "
					+ "month between 1 and 12");

			// ifs conditional if the month is invalid
			if (month <= 0 || month > 12) {
				JOptionPane.showMessageDialog(null,
						"You have entered an invalid month", "Invalid month",
						JOptionPane.WARNING_MESSAGE);

			} else {
				inputMonth = true;

				while (inputYear != true) {
					int year = in.readIntDialog("Enter a valid year");

					// ifs conditional if the year is invalid
					if (year <= 0) {
						JOptionPane.showMessageDialog(null,
								"You have entered an invalid year",
								"Invalid year", JOptionPane.WARNING_MESSAGE);
					} else {
						inputYear = true;

						// take the input value of month an year
						calendar = new Calendar(month, year);

						// display the calendar in the graphic window
						displayCalendar();
					}
				}
			}
		}
	}
}
