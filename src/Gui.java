import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Gui extends JFrame {

	protected JLabel item1; //for putting text items on the screen
	protected JLabel rowsLabel;
	protected JLabel columnsLabel;
	protected JTextField rowsInput;
	protected JTextField columnsInput;
	protected JTextField bombDensityInput;
	protected JLabel bombDensityLabel;
	
	public Gui() {
		super("Minefield"); //the title bar
		setLayout(new FlowLayout()); //default layout
		// Used to specify GUI component layout
	      GridBagConstraints layoutConst = null;

	      // Set frame's title
	      setTitle("Let's create your field.");
	      
	      //item1 = new JLabel("Let's create your field.");
			//item1.setToolTipText("text which shows up on hover");
			//add(item1);
			
			rowsLabel = new JLabel("How many rows would you like?");
			add(rowsLabel);
			
			rowsInput = new JTextField(15);
			rowsInput.setEditable(true);
			rowsInput.setText("0");
			//rowsInput.addActionListener(this);
			add(rowsInput);
			
			columnsLabel = new JLabel("How many columns would you like?");
			add(columnsLabel);
			
			columnsInput = new JTextField(15);
			columnsInput.setEditable(true);
			columnsInput.setText("0");
			//columnsInput.addActionListener(this);
			add(columnsInput);
			
			bombDensityLabel = new JLabel("What bomb density would you like? (1-100): ");
			add(bombDensityLabel);
			
			bombDensityInput = new JTextField(15);
			bombDensityInput.setEditable(true);
			bombDensityInput.setText("0");
			//columnsInput.addActionListener(this);
			add(bombDensityInput);
			
			Handler handler = new Handler();
			rowsInput.addActionListener(handler);
			columnsInput.addActionListener(handler);


	      // Use a GridBagLayout
	      setLayout(new GridBagLayout());
	      layoutConst = new GridBagConstraints();

	      // Specify component's grid location
	      layoutConst.gridx = 0;
	      layoutConst.gridy = 0;

	      // 10 pixels of padding around component
	      layoutConst.insets = new Insets(10, 10, 10, 10);

	      // Add component using the specified constraints
	      add(rowsLabel, layoutConst);

	      layoutConst = new GridBagConstraints();
	      layoutConst.gridx = 1;
	      layoutConst.gridy = 0;
	      layoutConst.insets = new Insets(10, 10, 10, 10);
	      add(rowsInput, layoutConst);

	      layoutConst = new GridBagConstraints();
	      layoutConst.gridx = 0;
	      layoutConst.gridy = 1;
	      layoutConst.insets = new Insets(10, 10, 10, 10);
	      add(columnsLabel, layoutConst);

	      layoutConst = new GridBagConstraints();
	      layoutConst.gridx = 1;
	      layoutConst.gridy = 1;
	      layoutConst.insets = new Insets(10, 10, 10, 10);
	      add(columnsInput, layoutConst);
	      
	      layoutConst = new GridBagConstraints();
	      layoutConst.gridx = 0;
	      layoutConst.gridy = 2;
	      layoutConst.insets = new Insets(10, 10, 10, 10);
	      add(bombDensityLabel, layoutConst);

	      layoutConst = new GridBagConstraints();
	      layoutConst.gridx = 1;
	      layoutConst.gridy = 2;
	      layoutConst.insets = new Insets(10, 10, 10, 10);
	      add(bombDensityInput, layoutConst);
		
		
		
	}//Gui
	
	private class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			String string = null;
			
			if(event.getSource() == rowsInput) {
				string = String.format("Number of rows: %s", event.getActionCommand());
			} else if (event.getSource() == columnsInput) {
				string = String.format("Number of columns: %s", event.getActionCommand());
			}
			JOptionPane.showMessageDialog(null, string);//pop up
		}
		
		
	}//Handler
	
	
	
}//class
