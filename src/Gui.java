import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	protected int i = 5;
	protected int j = 5;
	protected int k = 5;
	protected int[] arr;
	
	public static void main(String[] args) {
		Gui tester = new Gui();
		tester.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminates program when the window is closed
		tester.setSize(725,240);
		tester.setVisible(true);
	}
	
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
	      
	      Handler handler = new Handler();
			rowsInput.addActionListener(handler);
			columnsInput.addActionListener(handler);
			bombDensityInput.addActionListener(handler);
			
		
		
		
	}//Gui
	
	public class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			String string = null;
			
			if(event.getSource() == rowsInput) {
				string = String.format("Number of rows: %s", event.getActionCommand());
				String a = event.getActionCommand();
				i = Integer.parseInt(a);
			} else if (event.getSource() == columnsInput) {
				string = String.format("Number of columns: %s", event.getActionCommand());
				String b = event.getActionCommand();
				j = Integer.parseInt(b);
			} else if(event.getSource() == bombDensityInput) {
				string = String.format("Bomb Density: %s", event.getActionCommand());
				String c = event.getActionCommand();
				k = Integer.parseInt(c);
			
			drawGrid(i,j,k);
			
			}
//			JOptionPane.showMessageDialog(null, string);//pop up
		}
	
		
	}
		
		//Handler
	
	JFrame frame=new JFrame(); //creates frame
    JButton[][] grid; //names the grid of buttons

    public void ButtonGrid(int width, int length, int density){ //constructor
    	boolean[][] bomb = bomb(density, width, length);
            frame.setLayout(new GridLayout(width,length)); //set layout
            grid=new JButton[width][length]; //allocate the size of grid
            for(int y=0; y<length; y++){
                    for(int x=0; x<width; x++){
                    	if(bomb[x][y] == true) {
                    		grid[x][y] = new JButton("bomb");
                            frame.add(grid[x][y]); //adds button to grid

                    	} else {
                            grid[x][y]=new JButton(); //creates new button     
                            frame.add(grid[x][y]); //adds button to grid
                    	}
                    }
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack(); //sets appropriate size for frame
            frame.setVisible(true); //makes frame visible
    }
    
    public boolean[][] bomb(int d, int width, int length){
    	boolean[][] containsBomb = new boolean[width][length];
    	int bombDensity = d;
    while (bombDensity > 0) {

		int bombRow = (int) (Math.random() * width);

		int bombColumn = (int) (Math.random() * length);

		if (containsBomb[bombRow][bombColumn] != true) {
			containsBomb[bombRow][bombColumn] = true;
			bombDensity--;
		} else {

			continue;

		}
	}
	return containsBomb;
    }
    
	
	
	
	
    public void drawGrid(int i, int j, int k) {
            new ButtonGrid(i, j, k);//makes new ButtonGrid with 2 parameters
    }
}

	
	
//class
