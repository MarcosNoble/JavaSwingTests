import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MyFrame implements ActionListener {
    private final JList<String> list;
    private JButton selectButton, createNewRoomButton, refreshListButton,joinRoomButton,leaveRoomButton;
    private static ArrayList<String> options;
    private JFrame frame;
    private JTextPane selectedOptionTextPane;


    public MyFrame() {
        list = new JList<String>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(list);
        // Create the Select button
        selectButton = new JButton("Select");
        selectButton.addActionListener(this);
        // Create the Create New Room button
        createNewRoomButton = new JButton("Create New Room");
        createNewRoomButton.addActionListener(this);
        // Create the Refresh List button
        refreshListButton = new JButton("Refresh List");
        refreshListButton.addActionListener(this);

        joinRoomButton = new JButton(("Join"));
        joinRoomButton.addActionListener(this);

        leaveRoomButton = new JButton("Leave");
        leaveRoomButton.addActionListener(this);
        // Add the components to the panel
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.add(selectButton);
        buttonPanel.add(createNewRoomButton);
        buttonPanel.add(refreshListButton);
        buttonPanel.add(joinRoomButton);
        buttonPanel.add(leaveRoomButton);

        JPanel optionsPanel = new JPanel(new BorderLayout());
        optionsPanel.add(listScrollPane, BorderLayout.CENTER);
        optionsPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(optionsPanel, BorderLayout.WEST);

        // Create the text window that displays the selected option
        selectedOptionTextPane = new JTextPane();
        selectedOptionTextPane.setEditable(false);
        selectedOptionTextPane.setPreferredSize(new Dimension(300, 200));
        JScrollPane textScrollPane = new JScrollPane(selectedOptionTextPane);

        mainPanel.add(textScrollPane, BorderLayout.CENTER);

        // Set up the frame
        frame = new JFrame();
        frame.setTitle("Options");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectButton) {
            // Get the selected item from the list
            String selectedOption = list.getSelectedValue();
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(frame, "Please select an option.");
            } else {
                // Do something with the selected option
                selectedOptionTextPane.setText(selectedOption);
                StyledDocument doc = selectedOptionTextPane.getStyledDocument();
                Style style = selectedOptionTextPane.addStyle("Color Style", null);
                StyleConstants.setForeground(style, java.awt.Color.BLUE);
                try {
                    doc.insertString(doc.getLength(), " and styled text", style);
                } catch (javax.swing.text.BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == createNewRoomButton) {
            // Do something when Create New Room button is clicked
            JOptionPane.showMessageDialog(frame, "Create New Room button clicked.");
        } else if (e.getSource() == refreshListButton) {
            // Add a new option to the options list
            options.add("sala " + (options.size() + 1));

            // Update the list and reflect the changes in the interface
            list.setListData(options.toArray(new String[0]));
            list.setSelectedIndex(0);
            selectedOptionTextPane.setText("");

            // Display a message to confirm the change
            //JOptionPane.showMessageDialog(frame, "The list has been updated.");
        }
    }

    public static void main(String[] args) {
        ArrayList<String> opt = new ArrayList<String>();
        options= opt;
        options.add("sala 1");
        options.add("sala 2");
        options.add("sala 3");
        new MyFrame();
    }
}
