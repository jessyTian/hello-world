
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Realize the function of cell machine
 * @author tian0
 *
 */
public class CellMachine {

	/**
	 * the height of gamepanel
	 */
	private int height;
	/**
	 * the width of gamepanel
	 */
	private int width;
	/**
	 * 2D array saving cell's old 0 or 1 value of each pixel
	 */
	private int[][] field_old;
	/**
	 * 2D array saving cell's updated value of each pixel
	 */
	private int[][] field_new;
	/**
	 * the number of cycles
	 */
	private int rounds;
	/**
	 * load image into computer's memory
	 */
	private BufferedImage changedImg;
	/**
	 * draw the image on the label
	 */
	private JLabel drawLabel;

	/**
	 * 
	 * @param panelheight integer
	 * @param panelwidth  integer
	 * @param field_old 2D integer array
	 * @param field_new 2D integer array
	 * @param rounds integer
	 * @param changedImg BufferedImage
	 * @param drawLabel JLabel
	 */
	public CellMachine(int panelheight, int panelwidth, int[][] field_old, int[][] field_new, int rounds,
			BufferedImage changedImg, JLabel drawLabel) {
		this.height = panelheight;
		this.width = panelwidth;
		this.field_old = field_old;
		this.field_new = field_new;
		this.rounds = rounds;
		this.changedImg = changedImg;
		this.drawLabel = drawLabel;
		run();
	}

	/**
	 * realize the function cell machine
	 */
	private void run() {
		int number = 0;
		for (int t = 0; t < rounds; t++) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					//count the number of alive neighbor cells
					number = getNeighbour(i, j);
					//if the number is odd, central cell is alive
					if (number % 2 == 1) {
						field_new[i][j] = 1;
					} else// if even number, central cell is dead
						field_new[i][j] = 0;
				}
			}
			// update the old field
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					field_old[i][j] = field_new[i][j];
				}
			}
		}

		// redraw the image
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// if cell is alive, its color is black.
				if (field_old[i][j] == 1) {
					changedImg.setRGB(j, i, Color.black.getRGB());
				} else//if cell is dead, its color is white
					changedImg.setRGB(j, i, Color.white.getRGB());
			}
		}
		// draw the label
		drawLabel.setIcon(new ImageIcon(changedImg));
	}

	/**
	 * 
	 * @param i vertical coordinate(height)
	 * @param j abscissa(height)
	 * @return the number of alive neighbor cells
	 */
	private int getNeighbour(int i, int j) {
		int number = 0;
		//upper left corner
		if (i == 0 && j == 0) {
			number = field_old[0][1] + field_old[1][0] + field_old[1][1] + field_old[0][width - 1]
					+ field_old[1][width - 1] + field_old[height - 1][0] + field_old[height - 1][1]
					+ field_old[height - 1][width - 1];
		} 
		//upper right corner
		else if (i == 0 && j == width - 1) {
			number = field_old[0][width - 2] + field_old[1][width - 1] + field_old[1][width - 2] + field_old[0][0]
					+ field_old[1][0] + field_old[height - 1][width - 1] + field_old[height - 1][width - 2]
					+ field_old[height - 1][0];
		}
		//lower left corner
		else if (i == height - 1 && j == 0) {
			number = field_old[height - 2][0] + field_old[height - 1][1] + field_old[height - 2][1] + field_old[0][0]
					+ field_old[0][1] + field_old[height - 1][width - 1] + field_old[height - 2][width - 1]
					+ field_old[0][width - 1];
		} 
		//lower right corner
		else if (i == height - 1 && j == width - 1) {
			number = field_old[i - 1][j] + field_old[i][j - 1] + field_old[i - 1][j - 1] + field_old[i][0]
					+ field_old[i - 1][0] + field_old[0][j] + field_old[0][j - 1] + field_old[0][0];
		}
		//upper side
		else if (i == 0) {
			number = field_old[0][j - 1] + field_old[1][j - 1] + field_old[1][j] + field_old[1][j + 1]
					+ field_old[0][j + 1] + field_old[height - 1][j - 1] + field_old[height - 1][j]
					+ field_old[height - 1][j + 1];
		} 
		//underside
		else if (i == height - 1) {
			number = field_old[i][j - 1] + field_old[i - 1][j - 1] + field_old[i - 1][j] + field_old[i - 1][j + 1]
					+ field_old[i][j + 1] + field_old[0][j - 1] + field_old[0][j] + field_old[0][j + 1];
		} 
		//left side
		else if (j == 0) {
			number = field_old[i - 1][0] + field_old[i - 1][1] + field_old[i][1] + field_old[i + 1][1]
					+ field_old[i + 1][0] + field_old[i - 1][width - 1] + field_old[i][width - 1]
					+ field_old[i + 1][width - 1];
		} 
		//right side
		else if (j == width - 1) {
			number = field_old[i - 1][j] + field_old[i - 1][j - 1] + field_old[i][j - 1] + field_old[i + 1][j - 1]
					+ field_old[i + 1][j] + field_old[i - 1][0] + field_old[i][0] + field_old[i + 1][0];
		} 
		//normal situation
		else {
			number = field_old[i - 1][j - 1] + field_old[i - 1][j] + field_old[i - 1][j + 1] + field_old[i][j - 1]
					+ field_old[i][j + 1] + field_old[i + 1][j - 1] + field_old[i + 1][j] + field_old[i + 1][j + 1];
		}

		return number;
	}

}
