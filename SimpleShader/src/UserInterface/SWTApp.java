package UserInterface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * ZetCode Java SWT tutorial
 *
 * This program creates a submenu
 *
 * @author jan bodnar
 * website zetcode.com
 * last modified June 2009
 */

public class SWTApp {

    private Shell shell;

    public SWTApp(Display display) {

        shell = new Shell(display);

        shell.setText("Submenu");

        initUI();

        shell.setSize(250, 200);
        shell.setLocation(300, 300);

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    public void initUI() {

        Menu menuBar = new Menu(shell, SWT.BAR);
        MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
        cascadeFileMenu.setText("&File");

        Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        cascadeFileMenu.setMenu(fileMenu);

        MenuItem subMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
        subMenuItem.setText("New");

        Menu submenu = new Menu(shell, SWT.DROP_DOWN);
        subMenuItem.setMenu(submenu);

        MenuItem imageSubMenuItem = new MenuItem(submenu, SWT.PUSH);
        imageSubMenuItem.setText("Image...");
        
        imageSubMenuItem.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);

                String[] filterNames = new String[] 
                    {"Java sources", "All Files (*)"};

                String[] filterExtensions = new String[] 
                    {"*.java", "*"};

                dialog.setFilterNames(filterNames);
                dialog.setFilterExtensions(filterExtensions);

                String path = dialog.open();
                if (path != null) {
//                    label.setText(path);
//                    label.pack();
                }
			}
        	
        });

        shell.setMenuBar(menuBar);

    }

    public static void main(String[] args) {
        Display display = new Display();
        new SWTApp(display);
        display.dispose();
    }
}