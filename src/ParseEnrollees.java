
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import showWindow.EntryWindow;

public class ParseEnrollees {

    private static void createDisplayGUI() {
        JFrame frame = new JFrame("Enrollee Parser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new EntryWindow());

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception 
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createDisplayGUI();
            }
        });
    }
}
