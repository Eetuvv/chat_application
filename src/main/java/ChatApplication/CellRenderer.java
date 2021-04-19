package ChatApplication;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CellRenderer extends JLabel implements ListCellRenderer<ChatMessage> {
    
    public CellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ChatMessage> list, ChatMessage msg, int index, boolean isSelected, boolean cellHasFocus) {
        setText(msg.toString());
        setFont(new java.awt.Font("Whitney", 1, 17));
        setForeground(Color.WHITE);
        Color lightBackground = new java.awt.Color(112, 117, 123);
        Color darkerBackground = new java.awt.Color(106, 111, 117);
        
        if (index % 2 == 0) setBackground(darkerBackground);
        else setBackground(lightBackground);
        
        return this;
    }
}
