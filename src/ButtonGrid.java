import javax.swing.JFrame; //imports JFrame library
import javax.swing.JButton; //imports JButton library
import java.awt.GridLayout; //imports GridLayout library
 
public class ButtonGrid {
	
 
        JFrame frame=new JFrame(); //creates frame
        JButton[][] grid; //names the grid of buttons
 
        public ButtonGrid(int width, int length){ //constructor
        	boolean[][] bomb = bomb();
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
        
        public static boolean[][] bomb(){
        	boolean[][] containsBomb = new boolean[20][20];
        	int bombDensity = 20;
        while (bombDensity > 0) {

			int bombRow = (int) (Math.random() * 20);

			int bombColumn = (int) (Math.random() * 20);

			if (containsBomb[bombRow][bombColumn] != true) {
				containsBomb[bombRow][bombColumn] = true;
				bombDensity--;
			} else {

				continue;

			}
		}
		return containsBomb;
        }
        
		
		
		
		
        public static void main(String[] args) {
                new ButtonGrid(20,20);//makes new ButtonGrid with 2 parameters
        }
}