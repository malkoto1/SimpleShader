package Listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import UserInterface.UIBuilder;

public class WindowResizeListener implements WindowStateListener
{
	private UIBuilder uiBuilder;

	public WindowResizeListener(UIBuilder uiBuilder)
	{
		this.uiBuilder = uiBuilder;
	}

	@Override
	public void windowStateChanged(WindowEvent arg0)
	{
		uiBuilder.getFrame().repaint();
	}
}
