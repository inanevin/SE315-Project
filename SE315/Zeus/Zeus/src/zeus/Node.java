/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeus;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author ROOT
 */
public class Node {

    private ArrayList<Node> _ConnectedNodes = new ArrayList<Node>();
    private int i_LaneProperty;
    private float f_XCor;
    private float f_YCor;
    private JPanel jp_This;

    public Node(float x, float y, int laneP, JPanel ownPanel) {
        f_XCor = x;
        f_YCor = y;
        i_LaneProperty = laneP;
        jp_This = ownPanel;
    }

    public Node GetClosestOrFurthestNode(boolean closest) {
        float distance = 999.0f;
        float currentDistance = -1.0f;
        int foundIndex = -1;

        if (!closest) {
            distance = 0.0f;
        }

        for (int i = 0; i < _ConnectedNodes.size(); i++) {
            currentDistance = (float) Math.sqrt(Math.pow(_ConnectedNodes.get(i).GetX() - this.GetX(), 2)
                    + Math.pow(_ConnectedNodes.get(i).GetY() - this.GetY(), 2));

            if (closest) {
                if (currentDistance < distance) {
                    distance = currentDistance;
                    foundIndex = i;
                }
            } else {
                if (currentDistance > distance) {
                    distance = currentDistance;
                    foundIndex = i;
                }
            }
        }
        return _ConnectedNodes.get(foundIndex);
    }

    public void NodeConnected(Node n) {
        _ConnectedNodes.add(n);
    }

    public void NodeDisconnected(Node n) {
        if (_ConnectedNodes.contains(n)) {
            _ConnectedNodes.remove(n);
        }
    }

    public void SetPanel(JPanel jp) {
        jp_This = jp;
    }

    public JPanel GetPanel() {
        return jp_This;
    }

    public float GetX() {
        return f_XCor;
    }

    public void SetX(float v) {
        f_XCor = v;
    }

    public float GetY() {
        return f_YCor;
    }

    public void SetY(float v) {
        f_YCor = v;
    }

    public int GetLaneP() {
        return i_LaneProperty;
    }

    public void SetLaneP(int v) {
        i_LaneProperty = v;
    }

}
