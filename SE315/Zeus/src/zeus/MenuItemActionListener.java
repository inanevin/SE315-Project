/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeus;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenuItem;
import java.awt.Color;

/**
 *
 * @author InanEvin-ROOT
 */
public class MenuItemActionListener implements ActionListener {

    private Node _AssociatedNode;

    public enum PopupType {
        Transition, Options, Delete
    };
    private PopupType _PopupType;

    public MenuItemActionListener(Node node, PopupType type) {
        _AssociatedNode = node;
        _PopupType = type;
    }

    public void actionPerformed(ActionEvent e) {
        if (_PopupType == PopupType.Transition) {
            _AssociatedNode.main.PVO_Transition(_AssociatedNode);
            _AssociatedNode.PVO_SetColor(Color.RED);

        } else if (_PopupType == PopupType.Options) {
            _AssociatedNode.main._CurrentOptionedNode = _AssociatedNode;
            _AssociatedNode.main.PVO_EnableNodeOptions();
        } else if (_PopupType == PopupType.Delete) {

        }
    }

}
