import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class Gui extends JFrame {

	protected JLabel item1; //for putting text items on the screen
	protected JTextField item2;
	protected JTextField item3;
	
	public Gui() {
		super("Minefield"); //the title bar
		setLayout(new FlowLayout()); //default layout
		
		item1 = new JLabel("Let's create your field.");
		//item1.setToolTipText("text which shows up on hover");
		add(item1);
		
		item2 = new JTextField("How many rows would you like?");
		add(item2);
		
		item3 = new JTextField("How many columns would you like?");
		add(item3);
		
		Handler handler = new Handler();
		item2.addActionListener(handler);
		item3.addActionListener(handler);
		
	}//Gui
	
	private class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			String string = null;
			
			if(event.getSource() == item2) {
				string = String.format("Number of rows: %s", event.getActionCommand());
			} else if (event.getSource() == item3) {
				string = String.format("Number of columns: %s", event.getActionCommand());
			}
			JOptionPane.showMessageDialog(null, string);//pop up
		}
		
		
	}//Handler
	
	
	
}//class
