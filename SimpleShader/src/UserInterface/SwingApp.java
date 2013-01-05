package UserInterface;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Functionality.ImagePanel;
import Listeners.BlueActionListener;
import Listeners.FileChooserActionListener;
import Listeners.GreenActionListener;
import Listeners.RedActionListener;

import java.awt.CardLayout;

public class SwingApp {

	public static File inputImage = null;

	public static void main(String[] args) throws IOException {
		SwingApp swingApp = new SwingApp();
		swingApp.createUI();
	}

	private void createUI() {
		JFrame frame = new JFrame();
		frame.setSize(750, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		Image frameBackgroundImage = null;
		ImagePanel imagePanel = null;

		try {
			frameBackgroundImage = ImageIO.read(new File(
					"resources/background.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		// If the background image is loaded properly create a new image panel
		// and add it to the main frame.
		if (frameBackgroundImage != null || imagePanel != null) {
			imagePanel = new ImagePanel(frameBackgroundImage);
			imagePanel.setLayout(new CardLayout(0, 0));
			frame.getContentPane().add(imagePanel);

		} else {
			throw new IllegalArgumentException(
					"Problem with loading bacground image.");
		}
		// Add a menu bar that can be manipulated with key shortcuts.
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// File Menu, F - Mnemonic
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);

		// File->New Image, N - Mnemonic
		JMenuItem newImageMenuItem = new JMenuItem("New Image", KeyEvent.VK_N);

		FileChooserActionListener fileChooserActionListener = new FileChooserActionListener(
				frame, imagePanel);
		newImageMenuItem.addActionListener(fileChooserActionListener);
		fileMenu.add(newImageMenuItem);
		
		addButton(new String(), imagePanel, true, false, false);

		frame.setVisible(true);
	}

	private void addButton(String path, ImagePanel imagePanel, boolean isRed,
			boolean isGreen, boolean isBlue) {
		JButton btnNewButton = new JButton();
		if(isRed){
			btnNewButton.setText("Red");
			btnNewButton.setBounds(10, 11, 344, 231);
			btnNewButton.addActionListener(new RedActionListener());
		}else if(isGreen){
			btnNewButton.setText("Green");
			btnNewButton.setBounds(10, 11, 344, 231);
			btnNewButton.addActionListener(new GreenActionListener());
		}else if(isBlue){
			btnNewButton.setText("Blue");
			btnNewButton.setBounds(10, 11, 344, 231);
			btnNewButton.addActionListener(new BlueActionListener());
		}
		
		btnNewButton.setIcon(new ImageIcon(path));
		imagePanel.add(btnNewButton);
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