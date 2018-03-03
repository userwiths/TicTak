/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictak;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author guest
 */
public class NPC {
    private int NPCPolicy;
    private TicTak game;
    
    public NPC(){
        this.NPCPolicy=new Random().nextInt(8);
        this.game=new TicTak();
    }
    public NPC(TicTak game){
        this.NPCPolicy=new Random().nextInt(8);
        this.game=game;
    }

    public TicTak getGame() {
        return game;
    }
    public void setGame(TicTak game) {
        this.game = game;
    }
    
    
    public void HumanVsNPC(){
        while(!game.Draw() && game.Won()==" "){
            game.PlayTurn();
            if(game.Draw() || game.Won()!=" "){
                break;
            }
            this.NPCPlay();
            game.PrintGround();
        }
        game.ChangePlayer();
        game.PrintResult();
    }
    public void NPCVsHuman(){
        while(!game.Draw() && game.Won()==" "){
            this.NPCPlay();
            game.PrintGround();
            if(game.Draw() || game.Won()!=" "){
                break;
            }
            game.PlayTurn();
        }
        game.ChangePlayer();
        game.PrintResult();
    }
    
    //NPC's Functions
    public int SingleMove(){
        int counter=0;
        int index=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
               if(game.Field[i][j]!=" "){
                   counter++;
               }else{
                   index=(i*3+1);
               }
            }
        }
        if(counter>=3){
            return -1;
        }
        return index;
    }
    public int PredictPossition(){
        int policy=0;
        int count_enemy=0,me=0;
        if(game.Draw()){
            return -1;
        }
        for(int i=0;i<game.Policy.length;i++){
            for(int pos=0;pos<game.Policy[i].length;pos++){
                if(game.GetPosition(game.Policy[i][pos])!=game.Turn && game.GetPosition(game.Policy[i][pos])!=" "){
                    count_enemy++;
                }else if(game.GetPosition(game.Policy[i][pos])==game.Turn){
                    me++;
                }else if(game.GetPosition(game.Policy[i][pos])==" "){
                    policy=game.Policy[i][pos];
                }
            }
            if(me==0 && count_enemy==2){
                return policy;
            }
            count_enemy=0;
            me=0;
        }
        return -1;
    }
    public int CounterEnemy(int position){
        List<Integer> allow=new ArrayList<Integer>();
        int policy=0;
        for(int i=0;i<game.Policy.length;i++){
            for(int item:game.Policy[i]){
                if(item==position){
                    allow.add(i);
                }
            }
        }
        for(int item:allow){
            for(int i:game.Policy[item]){
                if(game.GetPosition(i)==game.Turn){
                    policy=i;
                    return i;
                }
            }
        }
        return policy;
    }
    public boolean FreePlan(int pol){
        int counter=0;
        for(int item:game.Policy[pol]){
            if(!game.ValidMove(item)){
                counter++;
            }
        }
        if(counter<=2){
            return true;
        }
        return false;
    }
    public void NPCPlaySimple(){
        int action=0,index=0;
        if(this.SingleMove()!=-1){
            this.NPCPlaySimple(this.SingleMove());
            return;
        }
        while(!this.FreePlan(this.NPCPolicy)){
            this.NPCPolicy=new Random().nextInt(game.Policy.length);
        }
        do{
            action=new Random().nextInt(3);
            index=game.Policy[this.NPCPolicy][action];
        }while(!game.ValidMove(index));
                
        game.Field[(index-1)/3][(index-1)%3]=game.Turn;
        game.ChangePlayer();
    }
    public void NPCPlaySimple(int pos){
        game.Field[(pos-1)/3][(pos-1)%3]=game.Turn;
        game.ChangePlayer();
    }   
    public void NPCPlay(){
        int position=this.PredictPossition();
        int prediction=0;
        if(position==-1){
            this.NPCPlaySimple();
        }else{
            prediction=CounterEnemy(position);
            this.NPCPolicy=prediction;
            this.NPCPlaySimple(position);
        }
    }
}
