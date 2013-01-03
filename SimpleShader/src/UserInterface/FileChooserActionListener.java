package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class FileChooserActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		final JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(fc);
		SwingApp.inputImage = fc.getSelectedFile();
		try {
			SwingApp.visualiseImage(SwingApp.frame, SwingApp.imagePanel);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
