import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class AppFrame extends JFrame {

	/**
	 * set serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public AppFrame(String title) {
		super(title);// call the constructor of parent class and set the title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close the frame to end the program
	}
}

/**
 * An mouse adapter for receiving mouse events of exit
 * 
 * @author tian0
 *
 */
class ExitAction extends MouseAdapter {
	// when mouse clicks, exit
	public void mouseClicked(MouseEvent e) {
		System.exit(0);
	}
}

/**
 * Realize the image converter frame
 * @author tian0
 *
 */
public class AppDrawEvent {
	
	
	public static void main(String[] args) {
		

		String ImagePath = args[0];//read the path of image
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(ImagePath));
		} catch (IOException e) {
			String workingDir = System.getProperty("user.dir");//look for the image
			System.out.println(
					ImagePath + " was not found,\nWe look for the image in this directory: "
							+ workingDir);
			return;
		}
		BufferedImage changedImg = new BufferedImage(img.getWidth() * 2, img.getHeight() * 2,
				BufferedImage.TYPE_INT_RGB);
		Image newImg = img.getScaledInstance(img.getWidth() * 2, img.getHeight() * 2, Image.SCALE_DEFAULT);//zoom in the image
		JLabel drawLabel = new JLabel(new ImageIcon(newImg));//Display twice as large image on the initial interface

		JFrame frame = new AppFrame("Output");
		JPanel buttonPanel = new JPanel(new BorderLayout());
		JPanel drawPanel = new JPanel(new BorderLayout());
		JPanel exitPanel = new JPanel(new BorderLayout());

		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.add(drawPanel, BorderLayout.CENTER);
		frame.add(exitPanel, BorderLayout.SOUTH);

		JButton oButton = new JButton("Original");
		JButton gsButton = new JButton("Grayscale");
		JButton pButton = new JButton("Pattern");
		JButton exit = new JButton("Exit");

		buttonPanel.add(oButton, BorderLayout.WEST);
		buttonPanel.add(gsButton, BorderLayout.CENTER);
		buttonPanel.add(pButton, BorderLayout.EAST);

		drawPanel.add(drawLabel, BorderLayout.SOUTH);
		
		exitPanel.add(exit, BorderLayout.EAST);

		ExitAction e = new ExitAction();
		exit.addMouseListener(e);

		Original o = new Original(img, changedImg, drawLabel);
		oButton.addMouseListener(o);

		GrayScale gs = new GrayScale(img, changedImg, drawLabel);
		gsButton.addMouseListener(gs);

		Pattern p = new Pattern(img, changedImg, drawLabel);
		pButton.addMouseListener(p);

		frame.pack();
		frame.setVisible(true);
	}
}
