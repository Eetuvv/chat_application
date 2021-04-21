package ChatApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChannelDialog {

    private JList list;
    private JLabel label;
    private JOptionPane optionPane;
    private JButton okButton, cancelButton;
    private ActionListener okEvent, cancelEvent;
    private JDialog dialog;

    public ChannelDialog(String message, JList listToDisplay) {
        list = listToDisplay;
        label = new JLabel(message);
        createAndDisplayOptionPane();
    }

    public ChannelDialog(String title, String message, JList listToDisplay) {
        this(message, listToDisplay);
        dialog.setTitle(title);
    }

    private void createAndDisplayOptionPane() {
        setupButtons();
        JPanel pane = layoutComponents();
        optionPane = new JOptionPane(pane);
        optionPane.setOptions(new Object[]{okButton, cancelButton});
        dialog = optionPane.createDialog("Kanavavalikko");
        dialog.setSize(300, 300);
    }

    private void setupButtons() {
        okButton = new JButton("Valmis");
        okButton.setBackground(new java.awt.Color(60, 151, 51));
        okButton.addActionListener(e -> handleOkButtonClick(e));

        cancelButton = new JButton("Peruuta");
        cancelButton.setBackground(new java.awt.Color(181, 60, 51));
        cancelButton.addActionListener(e -> handleCancelButtonClick(e));
    }

    private JPanel layoutComponents() {
        JPanel panel = new JPanel(new BorderLayout(2, 5));
        label.setFont(new java.awt.Font("Dialog", 1, 16));
        panel.add(label, BorderLayout.NORTH);
        list.setFont(new java.awt.Font("Minion", 1, 13));
        list.setFixedCellHeight(25);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(list);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    public void setOnOk(ActionListener event) {
        okEvent = event;
    }

    public void setOnClose(ActionListener event) {
        cancelEvent = event;
    }

    private void handleOkButtonClick(ActionEvent e) {
        if (okEvent != null) {
            okEvent.actionPerformed(e);
            // If user didn't select a channel, display error message
            if (list.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(null, "Et valinnut kanavaa. Valitse kanava.", "Kanavavirhe", JOptionPane.ERROR_MESSAGE);
            } else {
                hide();
            }
        }
    }

    private void handleCancelButtonClick(ActionEvent e) {
        if (cancelEvent != null) {
            cancelEvent.actionPerformed(e);
        }
        hide();
    }

    public void show() {
        dialog.setVisible(true);
    }

    private void hide() {
        dialog.setVisible(false);
    }

    public Object getSelectedItem() {
        return list.getSelectedValue();
    }
}
