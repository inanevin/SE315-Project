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

/**
 *
 * @author ROOT
 */
public class Node extends JPanel {

    private ArrayList<Node> _ConnectedNodes = new ArrayList<Node>();
    private int i_XCor;
    private int i_YCor;
    private int i_xSize;
    public int i_ySize;
    public JPanel _ParentPanel;
    private int i_Index;
    public MainTest main;
    private boolean b_HasLight;
    private Light _AttachedLight;
    private Road _AttachedRoad;
    private long redTime = 5000;
    private long yellowTime = 1000;
    private long greenTime = 5000;

    public Node(int xCor, int yCor, int xS, int yS, JPanel parent, int index, MainTest m) {
        i_XCor = xCor;
        i_YCor = yCor;
        i_xSize = xS;
        i_ySize = yS;
        _ParentPanel = parent;
        i_Index = index;
        main = m;
        VO_Draw();
    }

    void VO_Draw() {
        _ParentPanel.add(this);
        this.setSize(i_xSize, i_ySize);
        this.setLocation(i_XCor - i_xSize / 2, i_YCor - i_ySize / 2);
        this.createToolTip();
        this.setToolTipText("Node " + (MainTest._AllActiveNodes.size() + 1));
        this.setBackground(Color.BLUE);
        this.repaint();
        MainTest._AllActiveNodes.add(this);

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {

                    if (MainTest.b_TransitionOpen) {

                        if (this.hashCode() != MainTest._CurrentTransitionNode.hashCode()) {
                            // make transition
                            VO_MakeTransition();
                        } else {
                            MainTest._CurrentTransitionNode.PVO_SetColor(Color.blue);
                        }

                        main.PVO_Transition(null);

                    } else if (main._CurrentEditingTool == MainTest.CurrentEditingTool.Lights) {
                        VO_CreateNewLight();
                    }

                }
                repaint();
            }

        });

        JPopupMenu popup = new JPopupMenu("Sa");
        JMenuItem menuItemTransition = new JMenuItem("Make Transition");
        JMenuItem menuItemOptions = new JMenuItem("Specific Options");
        JMenuItem menuItemDelete = new JMenuItem("Delete");

        MenuItemActionListener transitionListener = new MenuItemActionListener(this, MenuItemActionListener.PopupType.Transition);
        MenuItemActionListener optionsListener = new MenuItemActionListener(this, MenuItemActionListener.PopupType.Options);
        MenuItemActionListener deleteListener = new MenuItemActionListener(this, MenuItemActionListener.PopupType.Delete);

        menuItemTransition.addActionListener(transitionListener);
        menuItemOptions.addActionListener(optionsListener);
        menuItemDelete.addActionListener(deleteListener);

        popup.add(menuItemTransition);
        popup.add(menuItemOptions);
        popup.add(menuItemDelete);
        this.setComponentPopupMenu(popup);

    }

    public Road GetRoad() {
        return _AttachedRoad;
    }

    public Light GetLight() {
        return _AttachedLight;
    }

    public void SetRoad(Road r) {
        _AttachedRoad = r;
    }
    
    public void SetLight(Light l)
    {
        _AttachedLight = l;
    }

    public void PVO_SetTimings(long red, long yellow, long green) {
        redTime = red;
        yellowTime = yellow;
        greenTime = green;

        if (_AttachedLight != null) {
            _AttachedLight.PVO_SetTimes(redTime, yellowTime, greenTime);
        }

    }

    private void VO_CreateNewLight() {
        if (b_HasLight) {
            return;
        }

        b_HasLight = true;
        _AttachedLight = new Light(this, redTime, yellowTime, greenTime);
    }

    private void VO_MakeTransition() {
        MainTest._CurrentTransitionNode.PVO_SetColor(Color.green);
        PVO_SetColor(Color.green);
        MainTest._CurrentTransitionNode.NodeConnected(this);
        NodeConnected(MainTest._CurrentTransitionNode);
        Road betweenRoad = new Road(MainTest._CurrentTransitionNode, this, _ParentPanel);
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

    public int GetIndex() {
        return i_Index;
    }

    public void NodeConnected(Node n) {
        _ConnectedNodes.add(n);
    }

    public void NodeDisconnected(Node n) {
        if (_ConnectedNodes.contains(n)) {
            _ConnectedNodes.remove(n);
        }
    }

    public void PVO_SetColor(Color col) {
        this.setBackground(col);
    }

    public JPanel GetParentPanel() {
        return _ParentPanel;
    }

    public int GetX() {
        return i_XCor;
    }

    public void SetX(int v) {
        i_XCor = v;
    }

    public int GetY() {
        return i_YCor;
    }

    public void SetY(int v) {
        i_YCor = v;
    }

}
