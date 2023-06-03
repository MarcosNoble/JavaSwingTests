import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.*;

public class MyFrame2 implements ActionListener {
    private final JList<String> list;
    private JButton selectButton, createNewRoomButton, refreshListButton, joinRoomButton, leaveRoomButton;
    private static ArrayList<String> options;
    private JFrame frame;
    private JTextPane messageArea;
    private StyledDocument doc;
    private Style style;

    public MyFrame2() {
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
        messageArea = new JTextPane();
        messageArea.setEditable(false);
        messageArea.setPreferredSize(new Dimension(300, 200));
        JScrollPane textScrollPane = new JScrollPane(messageArea);

        mainPanel.add(textScrollPane, BorderLayout.CENTER);

        // Set up the frame
        frame = new JFrame();
        frame.setTitle("Options");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Initialize the document and style
        doc = messageArea.getStyledDocument();
        style = messageArea.addStyle("Color Style", null);
    }

    private void appendToMessageArea(String message, Color color) {
        try {
            if (color != null) {
                StyleConstants.setForeground(style, Color.BLUE);
            }
            doc.insertString(doc.getLength(), message, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == joinRoomButton) {
            // Get the selected item from the list
            String selectedOption = list.getSelectedValue();
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(frame, "Please select an option.");
            } else {
                // Do something with the selected option
                appendToMessageArea("entrando na sala "+ selectedOption + "\n", Color.BLUE);
            }
        } else if (e.getSource() == createNewRoomButton) {
            // Do something when Create New Room button is clicked
            String roomName = JOptionPane.showInputDialog(frame, "Enter the room name:");
            if (!options.contains(roomName)) {
                JOptionPane.showMessageDialog(frame,"criando"+ roomName);
            }
        } else if (e.getSource() == refreshListButton) {
            // Add a new option to the options list
            options.add("sala " + (options.size() + 1));

            // Update the list and reflect the changes in the interface
            list.setListData(options.toArray(new String[0]));
            list.setSelectedIndex(0);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> opt = new ArrayList<String>();
        options = opt;
        options.add("sala 1");
        options.add("sala 2");
        options.add("sala 3");
        new MyFrame2();
    }
}
