package gcloudhelper.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gcloudhelper.LogQuerySpec;

public class QueryGUIController implements ActionListener {
	private QueryGUIListener listener = null;
	
	private JComboBox<String> logNameBox;
	private JFormattedTextField startDateField;
	private JFormattedTextField endDateField;
	private JFrame frame;

	public QueryGUIController() {
		logNameBox = createComboBox(new String[] {"Foo", "Bar", "Baz"});
		startDateField = createDateField();
		endDateField = createDateField();
		
		JPanel test = new JPanel();
		JTextField queryField = new JTextField(30);
		test.add(queryField);
		
		// Button
		JButton button = new JButton("OK");
		button.setPreferredSize(new Dimension(200, 42));
		button.addActionListener(this);
		
		// Set up content panel
		JPanel inner = new JPanel();
		inner.setBorder(new EmptyBorder(12, 24, 12, 24));
		BoxLayout layout = new BoxLayout(inner, BoxLayout.Y_AXIS);
		inner.setLayout(layout);
		inner.add(createControlPanelFor("Log", logNameBox));
		inner.add(createControlPanelFor("Start Datetime", startDateField));
		inner.add(createControlPanelFor("End Datetime", endDateField));
		inner.add(test);
		inner.add(Box.createRigidArea(new Dimension(0, 32)));
		inner.add(button);
		
		frame = new JFrame();
		frame.add(inner);
		frame.setTitle("New Logs Query");
		frame.pack();
	}
	
	public void showGUI() {
		frame.setVisible(true);
	}
	
	private static JComboBox<String> createComboBox(String[] logNames) {
		JComboBox<String> nb = new JComboBox<String>(logNames);
		if (logNames.length > 0) {
			nb.setSelectedIndex(0);
		}
		return nb;
	}

	private static JFormattedTextField createDateField() {
		JFormattedTextField ftf = new JFormattedTextField();
		ftf.setValue(new Date());
		return ftf;
	}
	
	private static JPanel createControlPanelFor(String labelText, JComponent component) {
		JPanel p = createControlPanel(labelText);
		p.add(component, BorderLayout.EAST);
		return p;
	}

	private static JPanel createControlPanel(String labelText) {
		JPanel startDatePanel = new JPanel();
		startDatePanel.setLayout(new BorderLayout());
		
		JLabel label = new JLabel();
		label.setText(labelText);
		startDatePanel.add(label, BorderLayout.WEST);
		return startDatePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LogQuerySpec spec = this.getQuerySpec();
		if (getListener() != null) {
			this.getListener().didSelectQuery(spec);
		}
	}

	private LogQuerySpec getQuerySpec() {
		String name = (String) this.logNameBox.getSelectedItem();
		LogQuerySpec spec = new LogQuerySpec(name);
		return spec;
	}

	public QueryGUIListener getListener() {
		return listener;
	}

	public void setListener(QueryGUIListener listener) {
		this.listener = listener;
	}

	public void hideGUI() {
		this.frame.setVisible(false);
	}
}
