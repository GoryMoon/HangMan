package me.gory_moon.hangman.core;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * This formats the LogRecord as follows:
 * date   level   localized message with parameters 
 */
public class HangmanFormatter extends Formatter {

	public HangmanFormatter() {
		super();
	}

	/**
	* Formates the Log before it get put in to the file.
	* @param record
	*			The log record provided by a log.
	*/
	public String format(LogRecord record) {
		
		// Create a StringBuffer to contain the formatted record
		// start with the date.
		StringBuffer sb = new StringBuffer();
		
		// Get the date from the LogRecord and add it to the buffer
		Date date = new Date(record.getMillis());
		sb.append(date.toString());
		sb.append(" ");
		
		// Get the level name and add it to the buffer
		sb.append(record.getLevel().getName());
		sb.append(": ");
		 
		// Get the formatted message (includes localization 
		// and substitution of paramters) and add it to the buffer
		sb.append(formatMessage(record));
		sb.append("\n");

		return sb.toString();
	}
}