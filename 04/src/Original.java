import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * An mouse adapter for receiving mouse events of getting an original image
 * 
 * @author tian0
 *
 */
public class Original extends MouseAdapter {

	/**
	 * load image into computer's memory
	 */
	private BufferedImage img, changedImg;
	/**
	 * draw the image on the label
	 */
	private JLabel drawLabel;

	/**
	 * Constructor of original
	 * 
	 * @param img        BufferedImage
	 * @param changedImg BufferedImage
	 * @param drawLabel  JLabel
	 */
	public Original(BufferedImage img, BufferedImage changedImg, JLabel drawLabel) {
		this.img = img;
		this.changedImg = changedImg;
		this.drawLabel = drawLabel;
	}

	/**
	 * Get an original image
	 */
	public void mouseClicked(MouseEvent e) {
		int width = img.getWidth();
		int height = img.getHeight();

		// get pixel value
		for (int i = 0; i < height; i++) {// column(height)
			for (int j = 0; j < width; j++) {// row(width)
				int pRGB = img.getRGB(j, i);
				changedImg.setRGB(2 * j, 2 * i, pRGB);
				changedImg.setRGB(2 * j, 2 * i + 1, pRGB);
				changedImg.setRGB(2 * j + 1, 2 * i, pRGB);
				changedImg.setRGB(2 * j + 1, 2 * i + 1, pRGB);
			}
		}
		// draw image on Jlabel
		drawLabel.setIcon(new ImageIcon(changedImg));
	}
}
