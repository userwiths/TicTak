package tictak;

import java.awt.Color;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import static java.lang.Thread.sleep;
import java.util.*;

public class GUIBoard{
    private JFrame Board;
    private List<JButton> Buttons;
    public JLabel msg=new JLabel();
    public TicTak game;
    private NPC npc;
    public static Object lock=new Object();
    
    public GUIBoard(){
        this.Board=new JFrame("Game");
        this.Board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.game=new TicTak();
        this.npc=new NPC(this.game);      
        this.Buttons=new ArrayList<JButton>();
    }
    public GUIBoard(TicTak game){
        this.Board=new JFrame("Game");
        this.Board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.game=game;
        this.npc=new NPC(this.game);
        
        this.Buttons=new ArrayList<JButton>();
    }

    public void DrawGround(){
        msg.setSize(100, 70);
        for(JButton but:this.Buttons){
            this.Board.remove(but);
        }
        this.Board.remove(msg);
        
        GridLayout grid=new GridLayout(0,3);
        grid.setHgap(4);
        grid.setVgap(4);
        this.Board.setLayout(grid);
        JButton button=new JButton();
        
        for(int i=1;i<10;i++){
            button=new JButton(this.game.Field[(i-1)/3][(i-1)%3]);
            button.addActionListener(new ButtonAction(i,msg,this.game));
            button.setBorderPainted(true);
            this.Buttons.add(button);
            this.Board.add(button);
        }
        this.Board.add(msg);
        this.Board.setSize(350, 350);
        this.Board.setVisible(true);
    }

    public void NPC(){
        this.npc.NPCPlay();
    }
    
    public static void main(String[] args) throws InterruptedException{
        GUIBoard board=new GUIBoard();
        while(!board.game.Draw() && board.game.Won()==" "){
            board.DrawGround();
            if(board.game.Turn=="X"){
                sleep(1000);
            }else{
                board.NPC();
            }
        }
        if(!board.game.Draw()){
            board.msg.setText(board.game.Won()+" won!");
        }else{
            board.msg.setText("Draw !");
        }
        board.msg.setForeground(Color.green);
    }
}
