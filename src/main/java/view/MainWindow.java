package view;

import repository.User;

import javax.swing.*;
import java.io.IOException;


public class MainWindow extends JFrame {
	private User loggedUser;

	public MainWindow() throws IOException {
		super("Bitcoin symulator 2077");
		setSize(350, 140);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLookAndFeel();
		setVisible(true);

		welcomeWindow();
		setSize(500, 350);
		mainWindow(loggedUser);

	}

	private void welcomeWindow() throws IOException {
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
