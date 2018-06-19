package view;

import repository.User;

import javax.swing.*;
import java.io.IOException;


public class MainWindow extends JFrame {
	private User loggedUser;

	public MainWindow() throws IOException {
		super("Bitcoin symulator 2077");
		setSize(350, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLookAndFeel();
		setVisible(true);

		welcomeWindow();
		mainWindow(loggedUser);

	}

	private void welcomeWindow() {
		WelcomePanel panel = new WelcomePanel();
		add(panel);
		setVisible(true);
		loggedUser = panel.getLoggedUser();
		remove(panel);

	}

	private void mainWindow(User loggedUser) throws IOException {
		MainPanel panel = new MainPanel(loggedUser);
		add(panel);
		setVisible(true);
	}

	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ignored) {

		}
	}

}
