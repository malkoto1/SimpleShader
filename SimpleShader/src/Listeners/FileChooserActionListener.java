package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Functionality.ImagePanel;
import UserInterface.SwingApp;

public class FileChooserActionListener implements ActionListener {

	private JFrame frame;
	private ImagePanel imagePanel;

	public FileChooserActionListener(JFrame frame, ImagePanel imagePanel) {
		this.frame = frame;
		this.imagePanel = imagePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(fc);
		SwingApp.inputImage = fc.getSelectedFile();
		try {
			SwingApp.visualiseImage(frame, imagePanel);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}