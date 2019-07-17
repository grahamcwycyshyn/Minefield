import javax.swing.JFrame; //imports JFrame library
import javax.swing.JButton; //imports JButton library
import java.awt.GridLayout; //imports GridLayout library
import java.awt.event.MouseEvent;
 
public class ButtonGrid {
	
		public static boolean[][] bomb;
		public static Field[][] gameState;
//		public static ;
	
 
        JFrame frame=new JFrame(); //creates frame
        JButton[][] grid; //names the grid of buttons
 
        public ButtonGrid(int width, int length, int density){ //constructor
        	bomb = MinefieldApp.placeMines(density, width, length);
        	gameState = new Field[width][length];
                frame.setLayout(new GridLayout(width,length)); //set layout
                grid=new JButton[width][length]; //allocate the size of grid
                for(int y=0; y<length; y++){
                        for(int x=0; x<width; x++){
                        	if(gameState[x][y] == null || gameState[x][y] == Field.covered) {
                        		grid[x][y] = new JButton(" ");
                                frame.add(grid[x][y]); //adds button to grid

                        	} else {
                                grid[x][y]=new JButton(); //creates new button     
                                frame.add(grid[x][y]); //adds button to grid
                        	}
                        	grid[x][y].addActionListener();
                        }
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack(); //sets appropriate size for frame
                frame.setVisible(true); //makes frame visible
        }
        
        public void click(MouseEvent event) {
        	System.out.println(event.getButton());
//        	if(event.MOUSE_EVENT == true)
        }
        
		
		
}