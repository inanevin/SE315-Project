/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeus;

import java.awt.Color;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author ROOT
 */
public class LightTimer extends TimerTask {

    private Light _AttachedLight;

    public LightTimer(Light attachedLight) {
        _AttachedLight = attachedLight;
    }

    public void run() {
        _AttachedLight.PVO_TimerFinished();
        this.cancel();
    }
}
