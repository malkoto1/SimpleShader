package Listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Functionality.ImagePanel;
import UserInterface.UIBuilder;

public class BlueButtonActionListener implements ActionListener
{
	private ImagePanel imagePanel;

	private JFrame frame;

	public BlueButtonActionListener(JFrame frame, ImagePanel imagePanel)
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
					int greenColor = originalColor.getGreen();
					int blueColor = 255;

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
