package showWindow;

import java.io.File;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import procMembers.ProcessAll;

public class EntryWindow extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private JButton fileSelectButton;
    private JButton processButton;
    private JButton exitButton;

    private JTextArea fileNameArea;
    private JFileChooser fChooser;
    private File enrolleeFile;
    private String filePath = "Names.txt";

    public EntryWindow()  {
        super(new BorderLayout());
       
        fileNameArea = new JTextArea(5,20);
        fileNameArea.setMargin(new Insets(5,5,5,5));
        fileNameArea.setEditable(false);
        fileNameArea.setRows( 1 );
        fileNameArea.append(filePath);

        //Create a file chooser
        fChooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fChooser.setCurrentDirectory(workingDirectory);

        fileSelectButton = new JButton("Select File:");
        fileSelectButton.addActionListener(this);

        JPanel fileInfoPanel = new JPanel(); 
        fileInfoPanel.add(fileSelectButton);
        fileInfoPanel.add(fileNameArea);

        processButton = new JButton("Process");
        processButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(processButton);
        buttonPanel.add(exitButton);

        add(fileInfoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == fileSelectButton) {
            int returnVal = fChooser.showOpenDialog(EntryWindow.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                enrolleeFile = fChooser.getSelectedFile();
                fileNameArea.selectAll();
                fileNameArea.replaceSelection("");
                filePath = enrolleeFile.getName();
                fileNameArea.append(filePath);
            } 
            fileNameArea.setCaretPosition(fileNameArea.getDocument().getLength());
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == processButton) {
            if (enrolleeFile != null)
                filePath = enrolleeFile.getAbsolutePath();
            ProcessAll procAll = new ProcessAll( filePath );
            procAll.process();
        }
    }
}
