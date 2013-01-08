package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Functionality.ImagePanel;
import UserInterface.UIBuilder;

public class FileChooserActionListener implements ActionListener
{

	private JFrame frame;

	private ImagePanel imagePanel;

	private BufferedImage imageToVisualise;

	public FileChooserActionListener(JFrame frame, ImagePanel imagePanel)
	{
		this.frame = frame;
		this.imagePanel = imagePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		File fileImageToVisualise = fileChooser.getSelectedFile();

		if (fileImageToVisualise != null)
		{
			try
			{
				imageToVisualise = ImageIO.read(fileImageToVisualise);
				UIBuilder.visualiseImage(frame, imagePanel, imageToVisualise);
			}
			catch (IOException e2)
			{
				e2.printStackTrace();
				throw new IllegalArgumentException("Image could not be loaded.");
			}
		}
	}
}