import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * An mouse adapter for receiving mouse events of getting a grayscale image
 * 
 * @author tian0
 *
 */
public class GrayScale extends MouseAdapter {

	/**
	 * load image into computer's memory
	 */
	private BufferedImage img, changedImg;
	/**
	 * draw the image on the label
	 */
	private JLabel drawLabel;

	/**
	 * Constructor of grayscale
	 * 
	 * @param img        BufferedImage
	 * @param changedImg BufferedImage
	 * @param drawLabel  JLabel
	 */
	public GrayScale(BufferedImage img, BufferedImage changedImg, JLabel drawLabel) {
		this.img = img;
		this.changedImg = changedImg;
		this.drawLabel = drawLabel;
	}

	/**
	 * Get a grayscale image
	 */
	public void mouseClicked(MouseEvent e) {
		int width = img.getWidth();
		int height = img.getHeight();

		// get pixel value
		for (int i = 0; i < height; i++) {// column(height)
			for (int j = 0; j < width; j++) {// row(width)
				Color c = new Color(img.getRGB(j, i));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				int a = c.getAlpha();// opacity

				// gray is the average of red, green and blue
				int gray = (r + g + b) / 3;

				// reassemble color pixel and get its RGB
				Color pixelColor = new Color(gray, gray, gray, a);
				int pRGB = pixelColor.getRGB();

				// set the pixel value 2*2
				changedImg.setRGB(2 * j, 2 * i, pRGB);
				changedImg.setRGB(2 * j, 2 * i + 1, pRGB);
				changedImg.setRGB(2 * j + 1, 2 * i, pRGB);
				changedImg.setRGB(2 * j + 1, 2 * i + 1, pRGB);
			}
		}
		drawLabel.setIcon(new ImageIcon(changedImg));
	}

}
