package UserInterface;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class SwingApp {
	public static File inputImage;
	public static JFrame frame;

	public static ImagePanel imagePanel;

	public static void main(String[] args) throws IOException {
		final Image image = ImageIO.read(new File("resources/background.png"));
		frame = new JFrame();
		frame.setResizable(false);
		imagePanel = new ImagePanel(image);
		frame.getContentPane().add(imagePanel);
		imagePanel.setLayout(new CardLayout(0, 0));
		frame.setSize(750, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// File Menu, F - Mnemonic
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);

		// File->New, N - Mnemonic
		JMenuItem newImageMenuItem = new JMenuItem("New Image", KeyEvent.VK_N);
		newImageMenuItem.addActionListener(new FileChooserActionListener());
		fileMenu.add(newImageMenuItem);

		frame.setVisible(true);

	}

	public static void visualiseImage(JFrame frame, ImagePanel imagePanel)
			throws IOException {

		if (inputImage != null) {
			Image loadedImage = ImageIO.read(inputImage);
			ImagePanel panel = new ImagePanel(loadedImage);
			panel.setSize(frame.getHeight() - 100, frame.getWidth() - 100);
			panel.setBounds(34, 63, 680, 480);
			imagePanel.add(panel);
			imagePanel.repaint();
			frame.repaint();
		} else {

		}
	}

}

@SuppressWarnings("serial")
class ImagePanel extends JPanel {
	private Image image;
	private boolean tile;

	ImagePanel(Image image) {
		this.image = image;
		this.tile = false;
	};

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (tile) {
			int iw = image.getWidth(this);
			int ih = image.getHeight(this);
			if (iw > 0 && ih > 0) {
				for (int x = 0; x < getWidth(); x += iw) {
					for (int y = 0; y < getHeight(); y += ih) {
						g.drawImage(image, x, y, iw, ih, this);
					}
				}
			}
		} else {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}