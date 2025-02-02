import javax.swing.SwingUtilities;

import ui.GUI;

public class App {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            GUI.getInstance().showLoginScreen();
        });
    }
}