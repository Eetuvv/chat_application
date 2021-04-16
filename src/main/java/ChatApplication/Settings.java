package ChatApplication;

import java.awt.Color;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;


public class Settings extends JFrame {

    private JFrame settingsFrame = new JFrame("Login");
    private JPanel settingsPanel = new JPanel();


    public Settings(){
        initComponents();
    }

    private void centeredFrame(javax.swing.JFrame jFrame) {
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - jFrame.getWidth()) / 2;
        int iCoordY = (objDimension.height - jFrame.getHeight()) / 2;
        jFrame.setLocation(iCoordX, iCoordY);
    }

    private void initComponents(){
    Authentication authentication = Authentication.getInstance();

    }


}
