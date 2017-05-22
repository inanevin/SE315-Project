/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeus;
import javax.swing.JPanel;
import java.awt.Graphics;
/**
 *
 * @author ROOT
 */
public class ScenePanel extends JPanel {
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(int i = 0; i < MainTest._Roads.size(); i++)
        {
            g.drawLine(MainTest._Roads.get(i).GetNode1().getX(), MainTest._Roads.get(i).GetNode1().getY(),
                    MainTest._Roads.get(i).GetNode2().getX(), MainTest._Roads.get(i).GetNode2().getY());
        }
        
    }
    
}
