import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindow extends JFrame {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("localization.translation");
	private final JTextField fieldAmount;
	private final ArrayList<Currency> currencies = Currency.init();

	public MainWindow() {
		setTitle(BUNDLE.getString("MainWindow.this.title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		setLocationRelativeTo(null);
		setResizable(false);

		// Window components
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Label "Convert"
		JLabel lblConvert = new JLabel(BUNDLE.getString("MainWindow.lblConvert.text"));
		lblConvert.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConvert.setBounds(50, 20, 100, 25);
		contentPane.add(lblConvert);

		// ComboBox of the first currency
		final JComboBox<String> comboBoxCountry1 = new JComboBox<>();
		comboBoxCountry1.setBounds(160, 20, 300, 25);
		populate(comboBoxCountry1, currencies);
		contentPane.add(comboBoxCountry1);

		// Label "To"
		JLabel lblTo = new JLabel(BUNDLE.getString("MainWindow.lblTo.text"));
		lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTo.setBounds(50, 60, 100, 25);
		contentPane.add(lblTo);

		// ComboBox of the second currency
		final JComboBox<String> comboBoxCountry2 = new JComboBox<>();
		comboBoxCountry2.setBounds(160, 60, 300, 25);
		populate(comboBoxCountry2, currencies);
		contentPane.add(comboBoxCountry2);

		// Label "Amount"
		JLabel lblAmount = new JLabel(BUNDLE.getString("MainWindow.lblAmount.text"));
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(50, 100, 100, 25);
		contentPane.add(lblAmount);

		// Textfield for amount
		fieldAmount = new JTextField();
		fieldAmount.setText("0.00");
		fieldAmount.setBounds(160, 100, 150, 25);
		fieldAmount.setColumns(10);
		fieldAmount.setDocument(new JTextFieldLimit(8));
		contentPane.add(fieldAmount);

		// Button "Convert"
		JButton btnConvert = new JButton(BUNDLE.getString("MainWindow.btnConvert.text"));
		btnConvert.setBounds(160, 140, 150, 30);
		contentPane.add(btnConvert);

		// Label displaying result of conversion
		JLabel lblResult = new JLabel("");
		lblResult.setHorizontalAlignment(SwingConstants.LEFT);
		lblResult.setBounds(50, 180, 500, 30);
		contentPane.add(lblResult);

		// Add action listener to the convert button
		btnConvert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nameCurrency1 = Objects.requireNonNull(comboBoxCountry1.getSelectedItem()).toString();
				String nameCurrency2 = Objects.requireNonNull(comboBoxCountry2.getSelectedItem()).toString();
				String result;
				String formattedPrice;
				String formattedAmount;
				double price;
				double amount;

				DecimalFormat format = new DecimalFormat("#0.00");

				try {
					amount = Double.parseDouble(fieldAmount.getText());
				} catch (NumberFormatException e) {
					e.printStackTrace();
					amount = 0.0;
				}

				price = convert(nameCurrency1, nameCurrency2, currencies, amount);

				formattedPrice = format.format(price);
				formattedAmount = format.format(amount);

				result = formattedAmount + " " + nameCurrency1 + " = " + formattedPrice + " " + nameCurrency2;
				lblResult.setText(result);
			}
		});
	}

	public static void populate(JComboBox<String> comboBox, ArrayList<Currency> currencies) {
		for (Currency currency : currencies) {
			comboBox.addItem(currency.getName());
		}
	}

	public static double convert(String currency1, String currency2, ArrayList<Currency> currencies, double amount) {
		String shortNameCurrency2 = null;
		double exchangeValue;
		double price = 0.0;

		for (Currency value : currencies) {
			if (Objects.equals(value.getName(), currency2)) {
				shortNameCurrency2 = value.getShortName();
				break;
			}
		}

		if (shortNameCurrency2 != null) {
			for (Currency currency : currencies) {
				if (Objects.equals(currency.getName(), currency1)) {
					exchangeValue = currency.getExchangeValues().get(shortNameCurrency2);
					price = Currency.convert(amount, exchangeValue);
					break;
				}
			}
		}

		return price;
	}
}
