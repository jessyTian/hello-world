import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * An mouse adapter for receiving mouse events of getting a pattern image
 * 
 * @author tian0
 *
 */
public class Pattern extends MouseAdapter {

	/**
	 * load image into computer's memory
	 */
	private BufferedImage img, changedImg;
	/**
	 * draw the image on the label
	 */
	private JLabel drawLabel;

	/**
	 * Constructor of Pattern
	 * @param img        BufferedImage
	 * @param changedImg BufferedImage
	 * @param drawLabel  JLabel
	 */
	public Pattern(BufferedImage img, BufferedImage changedImg, JLabel drawLabel) {
		this.img = img;
		this.changedImg = changedImg;
		this.drawLabel = drawLabel;
	}

	public void mouseClicked(MouseEvent e) {
		int width = img.getWidth();
		int height = img.getHeight();
		int v = 0;// v=highest RGB
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

				// set the pixel value
				// The larger the RGB value, the lighter the color
				// 4 pixels are all white
				if (mean > (4 * v)) {
					changedImg.setRGB(2 * j, 2 * i, wRGB);
					changedImg.setRGB(2 * j, 2 * i + 1, wRGB);
					changedImg.setRGB(2 * j + 1, 2 * i, wRGB);
					changedImg.setRGB(2 * j + 1, 2 * i + 1, wRGB);
				}
				// 3 pixels are white
				else if (mean > (3 * v)) {
					changedImg.setRGB(2 * j, 2 * i, bRGB);
					changedImg.setRGB(2 * j, 2 * i + 1, wRGB);
					changedImg.setRGB(2 * j + 1, 2 * i, wRGB);
					changedImg.setRGB(2 * j + 1, 2 * i + 1, wRGB);
				}
				// 2 pixels are white
				else if (mean > (2 * v)) {
					changedImg.setRGB(2 * j, 2 * i, bRGB);
					changedImg.setRGB(2 * j, 2 * i + 1, wRGB);
					changedImg.setRGB(2 * j + 1, 2 * i, wRGB);
					changedImg.setRGB(2 * j + 1, 2 * i + 1, bRGB);
				}
				// 1 pixel is white
				else if (mean > v) {
					changedImg.setRGB(2 * j, 2 * i, wRGB);
					changedImg.setRGB(2 * j, 2 * i + 1, bRGB);
					changedImg.setRGB(2 * j + 1, 2 * i, bRGB);
					changedImg.setRGB(2 * j + 1, 2 * i + 1, bRGB);
				}
				// no pixels are white
				else {
					changedImg.setRGB(2 * j, 2 * i, bRGB);
					changedImg.setRGB(2 * j, 2 * i + 1, bRGB);
					changedImg.setRGB(2 * j + 1, 2 * i, bRGB);
					changedImg.setRGB(2 * j + 1, 2 * i + 1, bRGB);
				}
			}
		}
		drawLabel.setIcon(new ImageIcon(changedImg));
	}

}
