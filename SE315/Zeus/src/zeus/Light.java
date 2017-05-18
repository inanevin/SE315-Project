/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeus;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.util.Timer;

/**
 *
 * @author ROOT
 */
public class Light extends JPanel {

    private long l_LightRedTime;
    private long l_LightGreenTime;
    private long l_LightYellowTime;
    private long l_CurrentLightTime;
    private int i_LightIndex = -1;
    private Timer timer;

    public Light(Node attachedNode, long red, long yellow, long green) {
        l_LightRedTime = red;
        l_LightGreenTime = green;
        l_LightYellowTime = yellow;
        l_CurrentLightTime = l_LightRedTime;
        attachedNode._ParentPanel.add(this);
        this.setSize(10, 10);
        this.setLocation(attachedNode.GetX() + 15, attachedNode.GetY() - 5);
        this.setBackground(Color.RED);
        this.repaint();
        PVO_TimerFinished();

    }

    public void PVO_SetTimes(long red, long yellow, long green) {
        l_LightRedTime = red;
        l_LightYellowTime = yellow;
        l_LightGreenTime = green;
        timer.cancel();
        PVO_TimerFinished();
        i_LightIndex = -1;
    }

    public void PVO_TimerFinished() {
        i_LightIndex++;
        if (i_LightIndex == 3) {
            i_LightIndex = 0;
        }

        if (i_LightIndex == 0) {
            l_CurrentLightTime = l_LightRedTime;
            this.setBackground(Color.RED);
        } else if (i_LightIndex == 1) {
            l_CurrentLightTime = l_LightYellowTime;
            this.setBackground(Color.YELLOW);
        } else if (i_LightIndex == 2) {
            l_CurrentLightTime = l_LightGreenTime;
            this.setBackground(Color.GREEN);
        }
        timer = new Timer();
        timer.schedule(new LightTimer(this), l_CurrentLightTime);

    }

}
