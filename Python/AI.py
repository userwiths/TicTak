import random
import history

class SimpleBot:
    def __init__(self,game):
        self.policy=[
            #Horizontal
            [1,2,3],
            [4,5,6],
            [7,8,9],
            #Vertical
            [1,4,7],
            [2,5,8],
            [3,6,9],
            #Diagonals
            [1,5,9],
            [3,5,7]
            ];
        self.ai_policy=0;
        self.game=game;

    def Play(self):
        count_x=0;
        count_o=0;
        empthy=0;
        for i in self.policy:
            for j in i:
                y,x=self.game.Expand(j);
                if self.game.Table[y][x]==" ":
                    empthy=j;
                if self.game.Table[y][x]=="X":
                    count_x+=1;
                if self.game.Table[y][x]=="O":
                    count_o+=1
            if self.game.Turn%2==1 and count_o==2 and empthy!=0:
                #Play at empthy.
                self.game.Play(empthy);
                return
            if self.game.Turn%2==0 and count_x==2 and empthy!=0:
                self.game.Play(empthy);
                return
            empthy=0;
            count_x=0;
            count_o=0;
        self.playFromPolicy();

    def playFromPolicy(self):
        for i in self.policy[self.ai_policy]:
            if self.game.Table[self.game.Expand(i)[0]][self.game.Expand(i)[1]]==" ":
                y,x=self.game.Expand(i)
                if self.game.Turn%2==1:
                    self.game.Play(i);
                else:
                    self.game.Play(i);
                return;
        self.ai_policy=random.randrange(0,7,1)
        self.playFromPolicy()

class SmartBot:
    def __init__(self,game):
        self.x_action=[]
        self.o_action=[]
        self.memory=history.memory
        self.game=game

    def Play(self):
        if self.memory==[]:
            self.memory.append([])
            pos=1
            x=0;
            y=0;
            while self.game.Table[y][x]!=" ":
                pos=random.randrange(1,9,1)
                y,x=self.game.Expand(pos);
            self.game.Play(pos)
            self.memory[0].append(pos)
        else:
            pass;
