package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import UserInterface.UIBuilder;

public class SaveFileActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showSaveDialog(null);
		File selectedFile = fileChooser.getSelectedFile();

		if (selectedFile != null)
		{
			String selectedFileName = selectedFile.getAbsoluteFile().toString();
			File outputFile = new File(selectedFileName + ".PNG");
			try
			{
				ImageIO.write(UIBuilder.imageToVisualise, "PNG", outputFile);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
