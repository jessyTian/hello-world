
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Realize pattern
 * 
 * @author tian0
 *
 */
public class Pattern {

	/**
	 * load image into computer's memory
	 */
	private BufferedImage img, changedImg;
	/**
	 * draw the image on the label
	 */
	private JLabel drawLabel;
	/**
	 * 2D array saving cell's old 0 or 1 value of each pixel
	 */
	private int[][] field_old;
	/**
	 * 2D array saving cell's updated value of each pixel
	 */
	private int[][] field_new;
	/**
	 * the height of gamepanel
	 */
	private int panelHeight;
	/**
	 * the width of gamepanel
	 */
	private int panelWidth;

	
	/**
	 * Constructor of pattern class
	 * @param img BufferedImage
	 * @param changedImg BufferedImage
	 * @param drawLabel JLabel to draw the image
	 * @param panelHeight height of panel
	 * @param panelWidth width of panel
	 */
	public Pattern(BufferedImage img, BufferedImage changedImg, JLabel drawLabel, int panelHeight, int panelWidth) {
		this.img = img;
		this.changedImg = changedImg;
		this.drawLabel = drawLabel;
		this.panelHeight = panelHeight;
		this.panelWidth = panelWidth;
		change();
	}

	/**
	 * initialize 2d arrays and the background color of the new buffered image
	 */
	private void initial() {
		field_old = new int[panelHeight][panelWidth];
		field_new = new int[panelHeight][panelWidth];
		for (int i = 0; i < panelHeight; i++) {// vertical coordinate
			for (int j = 0; j < panelWidth; j++) {// abscissa
				field_old[i][j] = 0;
				field_new[i][j] = 0;
				// set the background color to white
				changedImg.setRGB(j, i, Color.white.getRGB());
			}
		}
	}	
	
	/**
	 * change the original image to pattern image
	 */
	private void change() {
		int width = img.getWidth();
		int height = img.getHeight();
		// calculate the central position of game panel
		int imageX = panelWidth - img.getWidth() * 2;
		int imageY = panelHeight - img.getHeight() * 2;

		initial();

		int v = 0;// v=highest R,G or B value
		// get pixel value
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Color c = new Color(img.getRGB(j, i));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();

				if (r > v)
					v = r;
				if (g > v)
					v = g;
				if (b > v)
					v = b;
			}
		}
		v = v / 5;// set the division range
		int wRGB = Color.white.getRGB();
		int bRGB = Color.black.getRGB();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Color c = new Color(img.getRGB(j, i));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();

				int mean = (r + g + b) / 3;

				// uebung 04
				// set the pixel value
				// The larger the R,G or B value, the lighter the color
				// 4 pixels are all white
				// uebung 05
				// if the color of a pixel is white, set the cell-value 0, which means the cell
				// is dead.
				// if the color is black, set 1, which means the cell is alive.
				if (mean > (4 * v)) {
					changedImg.setRGB(imageX / 2 + 2 * j, imageY / 2 + 2 * i, wRGB);
					field_old[imageY / 2 + 2 * i][imageX / 2 + 2 * j] = 0;
					changedImg.setRGB(imageX / 2 + 2 * j, imageY / 2 + 2 * i + 1, wRGB);
					field_old[imageY / 2 + 2 * i + 1][imageX / 2 + 2 * j] = 0;
					changedImg.setRGB(imageX / 2 + 2 * j + 1, imageY / 2 + 2 * i, wRGB);
					field_old[imageY / 2 + 2 * i][imageX / 2 + 2 * j + 1] = 0;
					changedImg.setRGB(imageX / 2 + 2 * j + 1, imageY / 2 + 2 * i + 1, wRGB);
					field_old[imageY / 2 + 2 * i + 1][imageX / 2 + 2 * j + 1] = 0;
				}
				// 3 pixels are white
				else if (mean > (3 * v)) {
					changedImg.setRGB(imageX / 2 + 2 * j, imageY / 2 + 2 * i, bRGB);
					field_old[imageY / 2 + 2 * i][imageX / 2 + 2 * j] = 1;
					changedImg.setRGB(imageX / 2 + 2 * j, imageY / 2 + 2 * i + 1, wRGB);
					field_old[imageY / 2 + 2 * i + 1][imageX / 2 + 2 * j] = 0;
					changedImg.setRGB(imageX / 2 + 2 * j + 1, imageY / 2 + 2 * i, wRGB);
					field_old[imageY / 2 + 2 * i][imageX / 2 + 2 * j + 1] = 0;
					changedImg.setRGB(imageX / 2 + 2 * j + 1, imageY / 2 + 2 * i + 1, wRGB);
					field_old[imageY / 2 + 2 * i + 1][imageX / 2 + 2 * j + 1] = 0;
				}
				// 2 pixels are white
				else if (mean > (2 * v)) {
					changedImg.setRGB(imageX / 2 + 2 * j, imageY / 2 + 2 * i, bRGB);
					field_old[imageY / 2 + 2 * i][imageX / 2 + 2 * j] = 1;
					changedImg.setRGB(imageX / 2 + 2 * j, imageY / 2 + 2 * i + 1, wRGB);
					field_old[imageY / 2 + 2 * i + 1][imageX / 2 + 2 * j] = 0;
					changedImg.setRGB(imageX / 2 + 2 * j + 1, imageY / 2 + 2 * i, wRGB);
					field_old[imageY / 2 + 2 * i][imageX / 2 + 2 * j + 1] = 0;
					changedImg.setRGB(imageX / 2 + 2 * j + 1, imageY / 2 + 2 * i + 1, bRGB);
					field_old[imageY / 2 + 2 * i + 1][imageX / 2 + 2 * j + 1] = 1;
				}
				// 1 pixel is white
				else if (mean > v) {
					changedImg.setRGB(imageX / 2 + 2 * j, imageY / 2 + 2 * i, wRGB);
					field_old[imageY / 2 + 2 * i][imageX / 2 + 2 * j] = 0;
					changedImg.setRGB(imageX / 2 + 2 * j, imageY / 2 + 2 * i + 1, bRGB);
					field_old[imageY / 2 + 2 * i + 1][imageX / 2 + 2 * j] = 1;
					changedImg.setRGB(imageX / 2 + 2 * j + 1, imageY / 2 + 2 * i, bRGB);
					field_old[imageY / 2 + 2 * i][imageX / 2 + 2 * j + 1] = 1;
					changedImg.setRGB(imageX / 2 + 2 * j + 1, imageY / 2 + 2 * i + 1, bRGB);
					field_old[imageY / 2 + 2 * i + 1][imageX / 2 + 2 * j + 1] = 1;
				}
				// no pixels are white
				else {
					changedImg.setRGB(imageX / 2 + 2 * j, imageY / 2 + 2 * i, bRGB);
					field_old[imageY / 2 + 2 * i][imageX / 2 + 2 * j] = 1;
					changedImg.setRGB(imageX / 2 + 2 * j, imageY / 2 + 2 * i + 1, bRGB);
					field_old[imageY / 2 + 2 * i + 1][imageX / 2 + 2 * j] = 1;
					changedImg.setRGB(imageX / 2 + 2 * j + 1, imageY / 2 + 2 * i, bRGB);
					field_old[imageY / 2 + 2 * i][imageX / 2 + 2 * j + 1] = 1;
					changedImg.setRGB(imageX / 2 + 2 * j + 1, imageY / 2 + 2 * i + 1, bRGB);
					field_old[imageY / 2 + 2 * i + 1][imageX / 2 + 2 * j + 1] = 1;
				}
			}
		}
		drawLabel.setIcon(new ImageIcon(changedImg));
	}

	/**
	 * get Draw label
	 * 
	 * @return draw label
	 */
	public JLabel getDrawLabel() {
		return drawLabel;
	}

	/**
	 * get 2d array of cells' old values
	 * 
	 * @return 2d array
	 */
	public int[][] getField_old() {
		return field_old;
	}

	/**
	 * get 2d array of cells' updated values
	 * 
	 * @return 2d array
	 */
	public int[][] getField_new() {
		return field_new;
	}

	/**
	 * get changed buffered image
	 * 
	 * @return buffered image
	 */
	public BufferedImage getChangedImg() {
		return changedImg;
	}

}
