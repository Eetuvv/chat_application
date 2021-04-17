package ChatApplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class Chat extends JFrame {

    private final JFrame chatFrame = new JFrame("Chat");
    //private final JPanel chatPanel = new JPanel();

    public Chat() {
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

        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatFrame.setSize(1350, 1100);
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
        JLabel channelLabel = new JLabel("Kanava");
        channelLabel.setFont(new java.awt.Font("Dialog", 1, 34));
        channelLabel.setBounds(78, 30, 225, 50);
        channelLabel.setForeground(textColor);

        JTextArea chatArea = new RoundedTextArea();
        chatArea.setBounds(300, 2, 1034, 960);
        chatArea.setBackground(new java.awt.Color(106, 111, 117));
        chatArea.setWrapStyleWord(true);
        chatArea.setLineWrap(true);
        chatArea.setFont(new java.awt.Font("Segoe UI", 1, 14));

        JTextField messageField = new RoundedTextField(15);
        messageField.setBounds(300, 970, 872, 80);
        messageField.setFont(new java.awt.Font("Segoe UI", 1, 14));

        JButton sendMessageButton = new JButton("Lähetä");
        sendMessageButton.setBounds(1172, 970, 160, 80);
        sendMessageButton.setBackground(new java.awt.Color(62, 62, 68));
        sendMessageButton.setForeground(textColor);
        sendMessageButton.setBorder(new RoundedBorder(12));

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

        JButton openSettingsButton = new JButton("Asetukset");
        openSettingsButton.setBounds(55, 915, 175, 50);
        openSettingsButton.setBackground(buttonColor);
        openSettingsButton.setForeground(textColor);
        openSettingsButton.setBorder(new RoundedBorder(15));
        openSettingsButton.setToolTipText("Avaa asetukset-valikko");

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 810, 300, 1);
        separator.setForeground(new java.awt.Color(45, 45, 45));

        // Text area for nickname to wrap text if name is very long
        JTextArea nicknameText = new JTextArea();
        nicknameText.setText("Nickname nickname");
        nicknameText.setWrapStyleWord(true);
        nicknameText.setLineWrap(true);
        nicknameText.setOpaque(false);
        nicknameText.setEditable(false);
        nicknameText.setFocusable(false);
        nicknameText.setFont(new java.awt.Font("Segoe UI", 1, 22));
        nicknameText.setForeground(textColor);
        nicknameText.setBounds(85, 830, 200, 150);

        JButton logoutButton = new JButton("Kirjaudu ulos");
        logoutButton.setBounds(55, 995, 175, 50);
        logoutButton.setBackground(new java.awt.Color(158, 63, 65));
        logoutButton.setForeground(textColor);
        logoutButton.setBorder(new RoundedBorder(15));
        logoutButton.setToolTipText("Kirjaudu ulos ja palaa kirjautumisnäkymään");

        // Set tooltip text color and background
        UIManager.put("ToolTip.background", Color.white);
        UIManager.put("ToolTip.border", new LineBorder(Color.BLACK, 1));

        chatPanel.add(chatArea);
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
            String[] channels = {"Yleinen", "Jalkapallo", "Jääkiekko", "Jääpallo", "Tennis", "Hiihto"};

            var selected = JOptionPane.showInputDialog(null, "Valitse kanava", "Selection", JOptionPane.DEFAULT_OPTION, null, channels, "Yleinen");
            if (selected != null) {//null if the user cancels. 
                // Set channel text to chosen channel
                String selectedString = selected.toString();
                channelLabel.setText(selectedString);
                // Repaint frame to not mess up gradient
                this.chatFrame.repaint();
            } else {
                System.out.println("User cancelled");
            }

            /*final JPanel panel = new JPanel();
            final JLabel label = new JLabel("Valitse kanava");
            final JRadioButton button1 = new JRadioButton("Yleinen");
            final JRadioButton button2 = new JRadioButton("Jääkiekk");

            panel.add (label);
            panel.add(button1);
            panel.add(button2);

            JOptionPane.showMessageDialog(null, panel);*/
        });
        
        createChannelButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            String name1 = JOptionPane.showInputDialog(chatFrame,
                    "Syötä uuden kanvan nimi", null);
        });

        logoutButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.setVisible(false);
            Login login = new Login();
            login.setVisible(true);
            // Set logged user to empty string when user logs out
            authentication.setLoggedUser("");
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
