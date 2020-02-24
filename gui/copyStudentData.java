package fmi.informatics.gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class copyStudentData extends JDialog implements PropertyChangeListener, ActionListener {

	private static final long serialVersionUID = 1L;

	private String typedText;
	private JTextField textField;
	private JOptionPane optionPane;

	private String okLabel = "Ok";
	private String cancelLabel = "Cancel";

	private copyStudentData   parentGui;
	private EType type;

	public copyStudentData(String text, copyStudentData parent, EType type) {
		setTitle("Proffesor");

		this.parentGui = parent;
		this.type = type;

		this.textField = new JTextField(2);

		Object[] array = { text, textField };
		Object[] options = { okLabel, cancelLabel };

		optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, options,
				options[0]);

	
		setContentPane(optionPane);

		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				
				optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
			}
		});

		
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent ce) {
				textField.requestFocusInWindow();
			}
		});
		textField.addActionListener(this);

		optionPane.addPropertyChangeListener(this);
	} 

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();

		if (isVisible() && (evt.getSource() == optionPane) && (JOptionPane.VALUE_PROPERTY.equals(propertyName)
				|| JOptionPane.INPUT_VALUE_PROPERTY.equals(propertyName))) {

			Object value = optionPane.getValue();

			if (value == JOptionPane.UNINITIALIZED_VALUE) {
				return;
			}
			optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

			if (value.equals(okLabel)) {
				if (textField.getText() != null && !textField.getText().isEmpty()) {
					typedText = textField.getText();
					int intValue = Integer.parseInt(typedText);

					
					if (type.equals(EType.SORT) && (intValue >= 1 && intValue <= 6)) {
						parentGui.sortTable(intValue, parentGui.table, copyStudentData.student);
						clearAndHide();
					} else {
						
						textField.selectAll();

						JOptionPane.showMessageDialog(copyStudentData.this,
								 "Съжалявам, стойността: " + typedText + " не е валидна!", "\"Грешка\"",
								JOptionPane.ERROR_MESSAGE);

						typedText = null;
						textField.requestFocusInWindow();
					}
				} else {
					
					JOptionPane.showMessageDialog(copyStudentData.this, "Съжаляваме, трябва да добавите стойност!",
							"Грешка", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				typedText = null;
				clearAndHide();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent å) {
		optionPane.setValue(okLabel);

	}

	private void clearAndHide() {
		textField.setText(null);
		setVisible(false);
	}
}

