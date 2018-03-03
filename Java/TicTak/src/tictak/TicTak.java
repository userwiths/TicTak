/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictak;

import java.util.*;

public class TicTak {
    public String[][] Field;
    public String Turn;
    private Scanner in;
    public int[][] Policy;
    private int NPCPolicy;
    
    public TicTak(){
        this.in=new Scanner(System.in);
        this.Field=new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.Field[i][j]=" ";
            }
        }
        this.Turn="X";
        this.Policy=new int[][]{{1,5,9},
                                {3,5,7},
                                {1,4,7},
                                {2,5,8},
                                {3,6,9},
                                {1,2,3},
                                {4,5,6},
                                {7,8,9}
                                };
        this.NPCPolicy=new Random().nextInt(this.Policy.length);
    }
    public TicTak(String turn){
        this.in=new Scanner(System.in);
        this.Field=new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.Field[i][j]=" ";
            }
        }
        this.Turn=turn;
        this.Policy=new int[][]{{1,5,9},
                                {3,5,7},
                                {1,4,7},
                                {2,5,8},
                                {3,6,9},
                                {1,2,3},
                                {4,5,6},
                                {7,8,9}
                                };
        this.NPCPolicy=new Random().nextInt(this.Policy.length);;
    }
    
    //Main game Methods
    public void ChangePlayer(){
        if(this.Turn=="X"){
            this.Turn="O";
        }else{
            this.Turn="X";
        }
    }
    public boolean ValidMove(int move){
        int x=(move-1)%3;
        int y=(move-1)/3;
        if(move>9 || move<1){
            return false;
        }
        if(this.Field[y][x]==" "){
            return true;
        }
        return false;
    }
    public String GetPosition(int n){
        int x=(n-1)%3;
        int y=(n-1)/3;
        if(n>9 || n<1){
            return " ";
        }
        return this.Field[y][x];    
    }
    public boolean Draw(){
        if(this.Won()=="X" || this.Won()=="O"){
            return true;
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.Field[i][j]==" "){
                    return false;
                }
            }
        }
        if(this.Won()==" "){
            return true;
        }
        return true;
    }
    public String Won(){
        int count_x=0;
        int count_o=0;
        for(int i=0;i<this.Policy.length;i++){
            for(int j=0;j<3;j++){
                if(this.GetPosition(this.Policy[i][j])=="X"){
                    count_x++;
                }else if(this.GetPosition(this.Policy[i][j])=="O"){
                    count_o++;
                }
            }
            if(count_x==3){
                return "X";
            }else if(count_o==3){
                return "O";
            }
            count_x=0;
            count_o=0;
        }
        return " ";
    }
    public void PrintResult(){
        if(this.Won()==" "){
            System.out.println("The game ended in draw !");
        }else{
            System.out.println("Player '"+this.Turn+"' won this game !");
        }
    }
    public void PrintGround(){
        for(String[] str:this.Field){
            System.out.println(Arrays.toString(str));
        }
    }
    public void PlayTurn(){
        int position;
        System.out.print("Enter number [1..9]: ");
        position=this.in.nextInt();
        if(ValidMove(position)){
            this.Field[(position-1)/3][(position-1)%3]=this.Turn;
            ChangePlayer();
        }else{
            System.out.println("This Position is taken ...");
        }
    }
    public void Reset(){
        this.Field=new String[][]{{" "," "," "},{" "," "," "},{" "," "," "}};
    }
    
    //Game options
    public void TwoPlayer(){
        while(!this.Draw()){
            this.PlayTurn();
            this.PrintGround();
        }
        this.ChangePlayer();
        this.PrintResult();
    }
    
    //Display game options
    public void Options(){
        System.out.println("Game Options: ");
        System.out.println("\t1. Two Player's game.");
        System.out.println("\t2. NPC first game.");
        System.out.println("\t3. Player first game.");
        int choose=this.in.nextInt();
        NPC npc=new NPC();
        switch(choose){
            case 1:this.TwoPlayer();break;
            case 2:npc.setGame(this);
                    npc.NPCVsHuman();
                    break;
            case 3:npc.setGame(this);
                    npc.HumanVsNPC();
                    break;
            default:return;
        }
        this.Reset();
        Options();
    }
    
    public static void main(String[] args) {
        TicTak game=new TicTak();
        game.Options();
    }
    
}
