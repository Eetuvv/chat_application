package ChatApplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class Chat extends JFrame {

    private final JFrame chatFrame = new JFrame("Chat");
    //private final JPanel chatPanel = new JPanel();
    /*ArrayList<String> channels;
    ArrayList<ChatMessage> messages;*/
    ChatChannel chatChannel = new ChatChannel();
    String currentChannel;

    public Chat() {
        currentChannel = chatChannel.getCurrentChannel();
        initComponents();
        centeredFrame(chatFrame);
    }

    // Center window
    private void centeredFrame(javax.swing.JFrame jFrame) {
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - jFrame.getWidth()) / 2;
        int iCoordY = (objDimension.height - jFrame.getHeight()) / 2;
        jFrame.setLocation(iCoordX, iCoordY);
    }

    private void initComponents() {
        // Get an instance of authentication class
        Authentication authentication = Authentication.getInstance();

        // Add default channels and description message to each channel
        chatChannel.addDefaultChannels();

        // Initialize messages and add some example messages
        /*this.messages = new ArrayList<>();
        messages.add(new ChatMessage("First message", "22:20:00"));
        messages.add(new ChatMessage("Second message", "22:20:10"));
        messages.add(new ChatMessage("Third message", "22:20:30"));*/
        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatFrame.setSize(1350, 950);
        chatFrame.setResizable(false);
        chatFrame.setBackground(new java.awt.Color(60, 63, 65));

        // Create JPanel with gradient background
        JPanel chatPanel;
        chatPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    final int R = 104;
                    final int G = 106;
                    final int B = 116;
                    Paint p
                            = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
                                    getWidth(), getHeight(), new Color(R, G, B, 255), true);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };

        // Add JPanel to JFrame
        chatFrame.add(chatPanel);
        chatPanel.setLayout(null);
        chatPanel.setBackground(new java.awt.Color(60, 63, 65));

        Color textColor = new java.awt.Color(187, 187, 187);
        Color buttonColor = new java.awt.Color(60, 60, 60);

        // Create all components for chat window
        JLabel channelLabel = new JLabel("# " + currentChannel);
        channelLabel.setFont(new java.awt.Font("Dialog", 1, 34));
        channelLabel.setBounds(72, 30, 225, 50);
        channelLabel.setForeground(textColor);

        // Create JList to show chat messages
        DefaultListModel<ChatMessage> model = new DefaultListModel<>();

        // Get messages from current channel and append them to chat area
        chatChannel.getMessagesFromChannel(currentChannel).forEach(msg -> {
            model.addElement(msg);
        });

        JList<ChatMessage> chatArea = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        chatArea.setBackground(new java.awt.Color(106, 111, 117));
        chatArea.setCellRenderer(new CellRenderer());
        chatArea.setFixedCellHeight(60);

        scrollPane.setBounds(300, 2, 1035, 866);

        JTextField messageField = new RoundedTextField(5);
        messageField.setBounds(300, 869, 932, 40);
        messageField.setFont(new java.awt.Font("Whitney", 1, 17));

        JButton sendMessageButton = new JButton("Lähetä");
        sendMessageButton.setBounds(1232, 869, 102, 40);
        sendMessageButton.setBackground(new java.awt.Color(62, 62, 68));
        sendMessageButton.setForeground(textColor);
        sendMessageButton.setBorder(new RoundedBorder(5));
        sendMessageButton.setFocusable(false);

        JButton chooseChannelButton = new JButton("Vaihda kanava");
        chooseChannelButton.setBounds(45, 125, 200, 65);
        chooseChannelButton.setBackground(buttonColor);
        chooseChannelButton.setForeground(textColor);
        chooseChannelButton.setBorder(new RoundedBorder(15));
        chooseChannelButton.setToolTipText("Vaihda chat-kanava");

        // TODO fix icons
        //chooseChannelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swap_channel_icon.png")));
        //jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settings.png")));
        JButton createChannelButton = new JButton("Luo kanava");
        createChannelButton.setBounds(45, 225, 200, 65);
        createChannelButton.setBackground(buttonColor);
        createChannelButton.setForeground(textColor);
        createChannelButton.setBorder(new RoundedBorder(15));
        createChannelButton.setToolTipText("Luo uusi kanava haluamallesi aiheelle");

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 775, 300, 1);
        separator.setForeground(new java.awt.Color(45, 45, 45));

        // Text area for nickname to wrap text if name is very long
        /*JTextArea nicknameText = new JTextArea();

        nicknameText.setWrapStyleWord(true);
        nicknameText.setLineWrap(true);
        nicknameText.setOpaque(false);
        nicknameText.setEditable(false);
        nicknameText.setFocusable(false);
        nicknameText.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 21));
        nicknameText.setForeground(textColor);
        nicknameText.setBounds(52, 700, 225, 175);*/
        JLabel nicknameText = new JLabel("Nickname nickname");
        nicknameText.setFocusable(false);
        nicknameText.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 21));
        nicknameText.setForeground(textColor);
        nicknameText.setBounds(52, 650, 225, 175);
        // Set text placement based on text length
        if (!authentication.getLoggedUser().isEmpty()) {
            if (authentication.getLoggedUser().length() < 10) {
                nicknameText.setBounds(125, 650, 225, 175);
            } else if (authentication.getLoggedUser().length() > 10
                    && authentication.getLoggedUser().length() <= 15) {
                nicknameText.setBounds(78, 650, 225, 175);
            } else if (authentication.getLoggedUser().length() > 15
                    && authentication.getLoggedUser().length() < 20) {
                nicknameText.setBounds(65, 650, 225, 175);
            }
            nicknameText.setText(authentication.getLoggedUser());
        }

        JButton openSettingsButton = new JButton("Asetukset");
        openSettingsButton.setBounds(55, 775, 180, 55);
        openSettingsButton.setBackground(buttonColor);
        openSettingsButton.setForeground(textColor);
        openSettingsButton.setBorder(new RoundedBorder(15));
        openSettingsButton.setToolTipText("Avaa asetukset-valikko");
        openSettingsButton.setFocusable(false);

        JButton logoutButton = new JButton("Kirjaudu ulos");
        logoutButton.setBounds(55, 845, 180, 55);
        logoutButton.setBackground(new java.awt.Color(158, 63, 65));
        logoutButton.setForeground(textColor);
        logoutButton.setBorder(new RoundedBorder(15));
        logoutButton.setToolTipText("Kirjaudu ulos ja palaa kirjautumisnäkymään");

        // Set tooltip text color and background
        UIManager.put("ToolTip.background", Color.white);
        UIManager.put("ToolTip.border", new LineBorder(Color.BLACK, 1));

        // Add components to JPanel
        //chatPanel.add(chatArea);
        chatPanel.add(scrollPane);
        chatPanel.add(messageField);
        chatPanel.add(sendMessageButton);
        chatPanel.add(channelLabel);
        chatPanel.add(chooseChannelButton);
        chatPanel.add(createChannelButton);
        chatPanel.add(openSettingsButton);
        // chatPanel.add(separator);
        chatPanel.add(nicknameText);
        chatPanel.add(logoutButton);

        // Set funcionality to buttons
        chooseChannelButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            var selected = JOptionPane.showInputDialog(null, "Valitse kanava", "Kanava-asetukset", JOptionPane.DEFAULT_OPTION, null, chatChannel.listChannels().toArray(), "Yleinen");
            if (selected != null) {//null if the user cancels. 
                // Set channel text to chosen channel
                String selectedString = selected.toString().substring(0, 1).toUpperCase() + selected.toString().substring(1);;
                channelLabel.setText("# " + selectedString);
                // Repaint frame to not mess up gradient
                this.chatFrame.repaint();

                chatChannel.setCurrentChannel(selectedString);
                currentChannel = selectedString;

                // Clear chat area
                model.removeAllElements();
                // Get messages from current channel and append them to chat area
                chatChannel.getMessagesFromChannel(currentChannel).forEach(msg -> {
                    model.addElement(msg);
                });
            }
        });

        createChannelButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            UIManager.put("OptionPane.cancelButtonText", "Peruuta");
            UIManager.put("OptionPane.okButtonText", "Valmis");
            UIManager.put("OptionPane.yesButtonText", "Siirry");
            UIManager.put("OptionPane.noButtonText", "Peruuta");

            var selected = JOptionPane.showInputDialog(null, "Syötä kanavan nimi", "Luo uusi kanava", JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (selected != null) {
                // Add new channel to channels if it doesn't yet exist
                String channelString = selected.toString();
                
                // Capitalize first letter
                String capitalizedChannel = channelString.substring(0, 1).toUpperCase() + channelString.toString().substring(1);;
                ArrayList<String> channels = chatChannel.listChannels();
                
                // If channel doesn't exist yet, add a new channel
                if (!channels.stream().anyMatch(capitalizedChannel::equalsIgnoreCase)) {
                    chatChannel.addChannel(capitalizedChannel);
                    // Set new channel to current channel
                    currentChannel = capitalizedChannel;
                    channelLabel.setText("# " + capitalizedChannel);
                    this.chatFrame.repaint();

                    // Clear chat area
                    model.removeAllElements();
                    // Get messages from current channel and append them to chat area
                    chatChannel.getMessagesFromChannel(currentChannel).forEach(msg -> {
                        model.addElement(msg);
                    });
                } else {
                    // Get channel name from channels list to make sure that capitalization is the same
                    for (int i = 0; i < channels.size(); i++) {
                        if (channels.get(i).equalsIgnoreCase(capitalizedChannel)) {
                            capitalizedChannel = channels.get(i);
                        }
                    }
                    var selection = JOptionPane.showConfirmDialog(null, "Kanava on jo olemassa, siirry kanavalle '" + capitalizedChannel + "'?", "Valitse toiminto...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    // If user chooses to switch channel, set current channel to new channel
                    if (selection == JOptionPane.YES_OPTION) {
                        chatChannel.setCurrentChannel(capitalizedChannel);
                        currentChannel = capitalizedChannel;
                        channelLabel.setText("# " + capitalizedChannel);
                        // Repaint frame to not mess up gradient
                        this.chatFrame.repaint();
                        // Clear chat area
                        model.removeAllElements();
                        // Get messages from current channel and append them to chat area
                        chatChannel.getMessagesFromChannel(currentChannel).forEach(msg -> {
                            model.addElement(msg);
                        });
                    }
                }
            }
        });

        logoutButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.setVisible(false);
            Login login = new Login();
            login.setVisible(true);
            // Set logged user to empty string when user logs out
            authentication.setLoggedUser("");
        });

        sendMessageButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            String message = messageField.getText();
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timestamp = time.format(formatter);

            ChatMessage msg = new ChatMessage(message, timestamp);
            // Don't send a new message if message is empty
            if (!message.isEmpty()) {
                model.addElement(msg);
                chatChannel.addMessageToChannel(currentChannel, msg);
                messageField.setText("");
            }
        });

        messageField.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // If user presses enter and messagefield has focus, send new message
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    // Check if message field has focus and message is not empty
                    if (messageField.hasFocus() && !messageField.getText().isEmpty()) {
                        String message = messageField.getText();
                        LocalDateTime time = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                        String timestamp = time.format(formatter);

                        ChatMessage msg = new ChatMessage(message, timestamp);
                        model.addElement(msg);
                        chatChannel.addMessageToChannel(currentChannel, msg);
                        messageField.setText("");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        // Set hover actions to buttons
        chooseChannelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chooseChannelButton.setBackground(new java.awt.Color(70, 70, 70));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chooseChannelButton.setBackground(new java.awt.Color(60, 60, 60));
            }
        });

        createChannelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createChannelButton.setBackground(new java.awt.Color(70, 70, 70));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createChannelButton.setBackground(new java.awt.Color(60, 60, 60));
            }
        });

        openSettingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                openSettingsButton.setBackground(new java.awt.Color(70, 70, 70));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                openSettingsButton.setBackground(new java.awt.Color(60, 60, 60));
            }
        });

        openSettingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                openSettingsButton.setBackground(new java.awt.Color(70, 70, 70));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                openSettingsButton.setBackground(new java.awt.Color(60, 60, 60));
            }
        });

        sendMessageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sendMessageButton.setBackground(new java.awt.Color(72, 72, 81));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sendMessageButton.setBackground(new java.awt.Color(62, 62, 71));
            }
        });

        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(new java.awt.Color(168, 73, 75));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(new java.awt.Color(158, 63, 65));
            }
        });
    }

    @Override
    public void setVisible(boolean visible) {
        chatFrame.setVisible(visible);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Chat chat = new Chat();
            chat.setVisible(true);
        });
    }
}
