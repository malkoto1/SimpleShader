package UserInterface;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Functionality.ImagePanel;
import Listeners.BlueButtonActionListener;
import Listeners.ExitButtonActionListener;
import Listeners.FileChooserActionListener;
import Listeners.GreenButtonActionListener;
import Listeners.RedButtonActionListener;
import Listeners.SaveFileActionListener;
import Listeners.WindowResizeListener;

import java.awt.CardLayout;

public class UIBuilder
{
	private static final String FILE_MENU_EXIT_BUTTON = "Exit";

	private static final String FILE_MENU_NEW_IMAGE_BUTTON = "New Image";

	private static final String FILE_MENU_SAVE_IMAGE_BUTTON = "Save Image";

	private static final String BACKGROUND_IMAGE = "resources/background.PNG";

	private static final String FILE_MENU_FILE_BUTTON = "File";

	private static final String BLUE_BUTTON_ICON = "resources/blue.png";

	private static final String GREEN_BUTTON_ICON = "resources/green.png";

	private static final String RED_BUTTON_ICON = "resources/red.PNG";

	public static File inputImage = null;

	public static BufferedImage imageToVisualise;

	private JFrame frame;

	public static void main(String[] args) throws IOException
	{
		UIBuilder swingApp = new UIBuilder();
		swingApp.createUI();
	}

	private void createUI()
	{
		frame = new JFrame();
		frame.setSize(750, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, frame.getWidth(), frame.getHeight() - 25);
		frame.add(layeredPane, BorderLayout.CENTER);

		Image frameBackgroundImage = null;
		ImagePanel imagePanel = null;
		try
		{
			frameBackgroundImage = ImageIO.read(new File(BACKGROUND_IMAGE));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		// If the background image is loaded properly create a new image panel
		// and add it to the main frame.
		if (frameBackgroundImage != null)
		{
			imagePanel = new ImagePanel(frameBackgroundImage);
			imagePanel.setLayout(new CardLayout(0, 0));

			// We get the frames height and abstract 25 from it so the background picture fits in the frame.
			imagePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight() - 25);
			imagePanel.setOpaque(true);
		}
		else
		{
			throw new IllegalArgumentException("Problem with loading bacground image.");
		}

		if (imagePanel != null)
		{
			layeredPane.add(imagePanel, new Integer(0), 0);
		}
		// Add a menu bar that can be manipulated with key shortcuts.
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// File Menu, F - Mnemonic
		JMenu fileMenu = new JMenu(FILE_MENU_FILE_BUTTON);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);

		// File->New Image, N - Mnemonic
		JMenuItem newImageMenuItem = new JMenuItem(FILE_MENU_NEW_IMAGE_BUTTON, KeyEvent.VK_N);
		FileChooserActionListener fileChooserActionListener = new FileChooserActionListener(frame, imagePanel);
		newImageMenuItem.addActionListener(fileChooserActionListener);

		// File->Save Image, S - Mnemonic
		JMenuItem saveImageMenuItem = new JMenuItem(FILE_MENU_SAVE_IMAGE_BUTTON, KeyEvent.VK_S);
		saveImageMenuItem.addActionListener(new SaveFileActionListener());

		JMenuItem exitMenuItem = new JMenuItem(FILE_MENU_EXIT_BUTTON, KeyEvent.VK_E);
		exitMenuItem.addActionListener(new ExitButtonActionListener(frame));

		fileMenu.add(newImageMenuItem);
		fileMenu.add(saveImageMenuItem);
		fileMenu.add(exitMenuItem);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(100, 550, 550, 100);
		buttonsPanel.setLayout(null);
		buttonsPanel.setSize(500, 150);
		buttonsPanel.setOpaque(false);

		addButton(RED_BUTTON_ICON, buttonsPanel, imagePanel, frame, true, false, false);
		addButton(GREEN_BUTTON_ICON, buttonsPanel, imagePanel, frame, false, true, false);
		addButton(BLUE_BUTTON_ICON, buttonsPanel, imagePanel, frame, false, false, true);

		layeredPane.add(buttonsPanel, new Integer(1), 1);

		frame.addWindowStateListener(new WindowResizeListener(this));
		frame.setVisible(true);
	}

	private void addButton(	String path,
							JPanel buttonsPanel,
							ImagePanel imagePanel,
							JFrame frame,
							boolean isRed,
							boolean isGreen,
							boolean isBlue)
	{
		// FIXME Fix buttons size and make new smaller pictures for the buttons icons.
		JButton btnNewButton = new JButton();
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(path));
		Insets insets = buttonsPanel.getInsets();

		if (isRed)
		{
			// btnNewButton.setBounds(insets.left, 40 + insets.top, 150, 100);
			btnNewButton.setBounds(35 + insets.left, 50 + insets.top, 105, 103);
			btnNewButton.addActionListener(new RedButtonActionListener(frame, imagePanel));
			buttonsPanel.add(btnNewButton);
		}
		else if (isGreen)
		{
			btnNewButton.setBounds(210 + insets.left, 50 + insets.top, 105, 103);
			btnNewButton.addActionListener(new GreenButtonActionListener(frame, imagePanel));
			buttonsPanel.add(btnNewButton);
		}
		else if (isBlue)
		{
			btnNewButton.setBounds(385 + insets.left, 50 + insets.top, 105, 103);
			btnNewButton.addActionListener(new BlueButtonActionListener(frame, imagePanel));
			buttonsPanel.add(btnNewButton);
		}
	}

	public static void visualiseImage(JFrame frame, ImagePanel imagePanel, BufferedImage imageToVisualise)
		throws IOException
	{
		UIBuilder.imageToVisualise = imageToVisualise;
		if (imagePanel.getComponentCount() != 0)
		{
			imagePanel.remove(imagePanel.getComponent(0));

		}
		ImagePanel panel = new ImagePanel(UIBuilder.imageToVisualise);
		panel.setSize(frame.getHeight() - 100, frame.getWidth() - 100);
		panel.setBounds(34, 63, 680, 495);
		imagePanel.add(panel);
		imagePanel.repaint();
		frame.repaint();
	}

	public JFrame getFrame()
	{
		return frame;
	}
}