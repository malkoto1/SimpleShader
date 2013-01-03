package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

public class MyActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		final JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(fc);
		
	}

}
