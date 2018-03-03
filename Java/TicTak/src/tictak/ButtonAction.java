/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictak;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ButtonAction implements ActionListener{
    private int ID;
    private TicTak game;
    public static JLabel msg;
    
    public ButtonAction(int id,JLabel msg,TicTak game){
        this.ID=id;
        this.game=game;
        this.msg=msg;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(!game.ValidMove(ID)){
            msg.setText("Please enter a valid move !");
            msg.setForeground(Color.red);
            return;
        }
        ((JButton)ae.getSource()).setText(game.Turn);
        game.Field[(ID-1)/3][(ID-1)%3]=game.Turn;
        game.ChangePlayer();
    }
}
