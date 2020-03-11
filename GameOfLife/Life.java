/* This program is used to simulate Conway's Game of life
	The program will take in 3 arguments
	the first for the size of the 2D array
	The second to tell it how many iterations is will do
	The third will be whether you want it to be a Random or a Glider
	After you give the arguments (E.G java Life 100 200 R) it will open a seperate window displaying the Game of life simulation

*/
import java.util.*;
import java.awt.Color;

public class Life {

	static int x, y, iterate;
	static int[][] cells;
	static String type;
	static Random random = new Random();
	static Picture picture;
	final static Color DEAD= new Color(255,255,255);
	final static Color ALIVE = new Color(0,0,0);
	final static int magnification = 10;
	final static String Ran = "R";
	final static String glide = "G";

	public static void main(final String[] args) {
		x = (Integer.parseInt(args[0]));
		y = (Integer.parseInt(args[0]));
		iterate = Integer.parseInt(args[1]);
		cells = new int[x][y];
		type = args[2];
		picture = new Picture(x*magnification, y*magnification);

		if (type.equalsIgnoreCase(Ran)) {
			GOLRandomseed(); //Initializes the original array
		} else if (type.equalsIgnoreCase(glide)) {
			GOLGliderGun();
		}
		
		for (int i = 0; i < iterate; i++) {
			// these loops will go through each cell and draw if its alive (E.g cell == 1) or dead (cell == 0)
			for (int j = 0; j < x; j++) {
				for (int k = 0; k < y; k++) {
					if (cells[j][k] == 1) {
						DrawAlive(j, k);
					} else {
						DrawDead(j, k);
					}
				}
			}
			generate(); //This generated the next generation of cells
			picture.show();
			//This block of code will dealy the picture to show it taking time for cells to be born or to decay
			try {
				Thread.sleep(150);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	private static void GOLGliderGun(){
		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {
				cells[i][j] = 0;
			}
		}
		cells[26][2] = 1;
		cells[24][3] = 1;
		cells[26][3] = 1;
		cells[14][4] = 1;
		cells[15][4] = 1;
		cells[22][4] = 1;
		cells[23][4] = 1;
		cells[36][4] = 1;
		cells[37][4] = 1;
		cells[13][5] = 1;
		cells[17][5] = 1;
		cells[22][5] = 1;
		cells[23][5] = 1;
		cells[36][5] = 1;
		cells[37][5] = 1;
		cells[2][6] = 1;
		cells[3][6] = 1;
		cells[12][6] = 1;
		cells[18][6] = 1;
		cells[22][6]= 1;
		cells[23][6] = 1;
		cells[2][7] = 1;
		cells[3][7] = 1;
		cells[12][7] = 1;
		cells[16][7] = 1;
		cells[18][7] = 1;
		cells[19][7] = 1;
		cells[24][7] = 1;
		cells[26][7] = 1;
		cells[12][8] = 1;
		cells[18][8] = 1;
		cells[26][8] = 1;
		cells[13][9] = 1;
		cells[17][9] = 1;
		cells[14][10] = 1;
		cells[15][10] = 1;
	}

	private static void GOLRandomseed() {
		//randomly gives you cells
		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {
				cells[i][j] = random.nextInt(2);
			}
		}
	}

	private static void generate() {
		//this fuction will make the next generation
		final int[][] next = new int[x][y];
		
		for (int a = 1; a < x - 1; a++) { //these loops will check each cell
			for (int b = 1; b < y - 1; b++) {
				int neighbors = 0;
				for (int i = -1; i <= 1; i++) { // these loops are for checkign each cells neighbors
					for (int j = -1; j <= 1; j++) {
						neighbors += cells[a + i][b + j];
					}
				}
				neighbors -= cells[a][b]; //removes the original cell because it is not its own neighbor
				
				//The rules of life!
				if ((neighbors < 2) || (neighbors > 3)){
					next[a][b] = 0;
				} else if (neighbors == 3){
					next[a][b] = 1;
				} else {
					next[a][b] = cells[a][b];
				}
			}
		}
		cells = next; // makes the next generation its current cells

	}

	private static void DrawAlive(final int i, final int j) {
		for (int offsetX = 0; offsetX < magnification; offsetX++) {
			for (int offsetY = 0; offsetY < magnification; offsetY++) {
				// set() colours an individual pixel to Black for alive
				picture.set((i * magnification) + offsetX, (j * magnification) + offsetY, ALIVE);
			}
		}
	}

	private static void DrawDead(final int i, final int j) {
		for (int offsetX = 0; offsetX < magnification; offsetX++)
        {
            for (int offsetY = 0; offsetY < magnification; offsetY++)
            {
                // set() colours an individual pixel to White for dead
                picture.set((i * magnification) + offsetX, (j * magnification) + offsetY, DEAD);
            }
        }
	}
}
