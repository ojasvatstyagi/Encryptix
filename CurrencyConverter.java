import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CurrencyConverter {

	public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// Create and show main window at startup
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}

class Currency {
	private String name;
	private String shortName;
	private final Map<String, Double> exchangeValues = new HashMap<>();

	// Currency constructor
	public Currency(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
		setDefaultValues();
	}

	// Getter for name
	public String getName() {
		return name;
	}

	// Setter for name
	public void setName(String name) {
		this.name = name;
	}

	// Getter for shortName
	public String getShortName() {
		return shortName;
	}

	// Setter for shortName
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	// Getter for exchangeValues
	public Map<String, Double> getExchangeValues() {
		return exchangeValues;
	}

	// Set default values for a currency
	private void setDefaultValues() {
		Map<String, Map<String, Double>> defaultValues = new HashMap<>();

		defaultValues.put("US Dollar", Map.of(
				"USD", 1.00,
				"EUR", 0.93,
				"GBP", 0.66,
				"CHF", 1.01,
				"CNY", 6.36,
				"JPY", 123.54,
				"INR", 84.57
		));
		defaultValues.put("Euro", Map.of(
				"USD", 1.073,
				"EUR", 1.00,
				"GBP", 0.71,
				"CHF", 1.08,
				"CNY", 6.83,
				"JPY", 132.57,
				"INR", 79.88
		));
		defaultValues.put("British Pound", Map.of(
				"USD", 1.51,
				"EUR", 1.41,
				"GBP", 1.00,
				"CHF", 1.52,
				"CNY", 9.60,
				"JPY", 186.41,
				"INR", 93.80
		));
		defaultValues.put("Swiss Franc", Map.of(
				"USD", 0.99,
				"EUR", 0.93,
				"GBP", 0.66,
				"CHF", 1.00,
				"CNY", 6.33,
				"JPY", 122.84,
				"INR", 75.02
		));
		defaultValues.put("Chinese Yuan Renminbi", Map.of(
				"USD", 0.16,
				"EUR", 0.15,
				"GBP", 0.11,
				"CHF", 0.16,
				"CNY", 1.00,
				"JPY", 19.41,
				"INR", 11.84
		));
		defaultValues.put("Japanese Yen", Map.of(
				"USD", 0.008,
				"EUR", 0.007,
				"GBP", 0.005,
				"CHF", 0.008,
				"CNY", 0.051,
				"JPY", 1.00,
				"INR", 0.61
		));
		defaultValues.put("Indian Rupee", Map.of(
				"USD", 0.012,
				"EUR", 0.012,
				"GBP", 0.011,
				"CHF", 0.013,
				"CNY", 0.084,
				"JPY", 1.63,
				"INR", 1.00
		));

		if (defaultValues.containsKey(this.name)) {
			this.exchangeValues.putAll(defaultValues.get(this.name));
		}
	}

	// Initialize currencies
	public static ArrayList<Currency> init() {
		ArrayList<Currency> currencies = new ArrayList<>();
		currencies.add(new Currency("US Dollar", "USD"));
		currencies.add(new Currency("Euro", "EUR"));
		currencies.add(new Currency("British Pound", "GBP"));
		currencies.add(new Currency("Swiss Franc", "CHF"));
		currencies.add(new Currency("Chinese Yuan Renminbi", "CNY"));
		currencies.add(new Currency("Japanese Yen", "JPY"));
		currencies.add(new Currency("Indian Rupee", "INR"));
		return currencies;
	}

	// Convert a currency to another
	public static double convert(double amount, double exchangeValue) {
		return Math.round(amount * exchangeValue * 100d) / 100d;
	}
}


class JTextFieldLimit extends PlainDocument {
	private final int limit;
	private boolean toUppercase = false;

	JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	JTextFieldLimit(int limit, boolean upper) {
		super();
		this.limit = limit;
		toUppercase = upper;
	}

	public void insertString
			(int offset, String  str, AttributeSet attr) throws BadLocationException {
		if (str == null) return;
		if ((getLength() + str.length()) <= limit) {
			if (toUppercase) str = str.toUpperCase();
			super.insertString(offset, str, attr);
		}
	}
}
