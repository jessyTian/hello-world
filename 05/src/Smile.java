import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Click to exit
 * @author tian0
 *
 */
class ExitMouseAdapter extends MouseAdapter {
	public void mouseClicked(MouseEvent e) {
		System.exit(0);
	}
}
/**
 * Generate the frame of smile
 * @author tian0
 *
 */
public class Smile {

	/**
	 * Constructor of smile
	 * @param img BufferedImage
	 */
	public Smile(BufferedImage img) {
		JFrame frame = new JFrame("Output");
		JPanel companel = new JPanel();
		JPanel gamepanel = new JPanel();
		JPanel exitpanel = new JPanel();
		JLabel label = new JLabel("Rouds: ");
		JButton start = new JButton("Start");
		JButton exit = new JButton("Exit");
		JTextField text = new JTextField(10);
		// close the frame to end the program
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		frame.setBounds(0, 0, 500, 610);
		companel.setBounds(0, 0, 500, 35);
		gamepanel.setBounds(0, 35, 500, 500);
		exitpanel.setBounds(0, 535, 500, 35);
		
		// add all panels to the frame, all buttons to corresponding panels
		frame.add(companel);
		frame.add(gamepanel);
		frame.add(exitpanel);
		companel.add(label);
		companel.add(text);
		companel.add(start);
		exitpanel.add(exit);

		//create a new buffered image to draw the new image
		// the length, width are same as the gamepanel
		BufferedImage changedImg = new BufferedImage(gamepanel.getWidth(), gamepanel.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		//use jlabel to draw the smile
		JLabel drawLabel = new JLabel(new ImageIcon(changedImg));
		//initialize the original smile through pattern class
		Pattern p = new Pattern(img, changedImg, drawLabel, gamepanel.getHeight(), gamepanel.getWidth());
		gamepanel.add(p.getDrawLabel());

		//click start button to realize cell machine function
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// initialize the original smile every time you click
				Pattern p = new Pattern(img, changedImg, drawLabel, gamepanel.getHeight(), gamepanel.getWidth());
				gamepanel.add(p.getDrawLabel());

				try {
					// get text from text field
					String str = text.getText();
					//string to integer
					int rounds = Integer.parseInt(str);
					//call cell machine
					new CellMachine(gamepanel.getHeight(), gamepanel.getWidth(), p.getField_old(), p.getField_new(),
							rounds, p.getChangedImg(), p.getDrawLabel());
				} catch (Exception o) {
					System.out.println("Rounds cannot be null! You should input a number.");
					return;
				}
			}

		});

		// add exit function to exit button
		ExitMouseAdapter e = new ExitMouseAdapter();
		exit.addMouseListener(e);

		frame.setVisible(true);
	}
	
	/**
	 * This method demonstrates cell machine of smile.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		// get the path of the smile image
		String path = args[0];
		BufferedImage img;
		try {
			// read the smile image
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// if the path of image is wrong
			String workingDir = System.getProperty("user.dir");
			System.out.println(path + " isn't found in the directory: " + workingDir);
			return;
		}
		new Smile(img);
	}

}
