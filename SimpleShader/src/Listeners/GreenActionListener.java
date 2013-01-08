package Listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import UserInterface.UIBuilder;

import Functionality.ImagePanel;

public class GreenActionListener implements ActionListener
{

	private JFrame frame;

	private ImagePanel imagePanel;

	public GreenActionListener(JFrame frame, ImagePanel imagePanel)
	{
		this.frame = frame;
		this.imagePanel = imagePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		BufferedImage loadedImage = UIBuilder.imageToVisualise;

		if (loadedImage != null)
		{
			int loadedImageWidth = loadedImage.getWidth();
			int loadedImageHeight = loadedImage.getHeight();

			for (int width = 0; width < loadedImageWidth; width++)
			{
				for (int height = 0; height < loadedImageHeight; height++)
				{
					Color originalColor = new Color(loadedImage.getRGB(width, height));
					int redColor = originalColor.getRed();
					int greenColor = 255;
					int blueColor = originalColor.getBlue();

					Color nColor = new Color(redColor, greenColor, blueColor);

					loadedImage.setRGB(width, height, nColor.getRGB());
				}
			}

			try
			{
				UIBuilder.visualiseImage(frame, imagePanel, loadedImage);
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "Please, first load an image.");
		}

	}

}
