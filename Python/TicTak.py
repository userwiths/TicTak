#Python
class TicTak():
    def __init__(self):
        self.Table=[[" "," "," "],[" "," "," "],[" "," "," "]]
        self.Turn=0;

    def Expand(self,num):
        return (int((num-1)/3),int((num-1)%3))

    def Play(self,num):
        y,x=self.Expand(num)
        if self.Table[y][x]!=" ":
            print("This possition is occupied!\nPlease try with another place ...\n")
            self.Print()
            return False
        if self.Turn%2==1:
            self.Table[y][x]="X";
        else:
            self.Table[y][x]="O";
        self.Print()
        self.Turn+=1;
        return True;

    def Ask(self):
        if self.Turn%2==1:
            inp=input("Player 'X', choose youre possition (1..9) : ")
        else:
            inp=input("Player 'O', choose you're possition [1..9] : ")
        self.Play(int(inp))

    def Print(self):
        for i in self.Table:
            print(str(i)+"\n")
        print("\n"*10)

    def End(self):
        if self.Won():
            return True
        for i in self.Table:
            for e in i:
                if e==" ":
                    return False
        return True;

    def Won(self):
        count=0;
        checks=[
                #Diagonal
                [(0,0),(1,1),(2,2)],
                [(0,2),(1,1),(2,0)],
                #Horizontal
                [(0,0),(0,1),(0,2)],
                [(1,0),(1,1),(1,2)],
                [(2,0),(2,1),(2,2)],
                #Vertical
                [(0,0),(1,0),(2,0)],
                [(0,1),(1,1),(2,1)],
                [(0,2),(1,2),(2,2)]
                ]
        for i in checks:
            count=0
            for e in i:
                if self.Table[e[0]][e[1]]==" ":
                    count=-1;
                    break;
                if self.Table[e[0]][e[1]]=="X":
                    count+=1;
            if count==3:
                print("Player 'X' wins !!!")
                self.Print()
                return True
            if count==0:
                print("Player 'O' wins !!!")
                self.Print()
                return True
        return False


    def Nullify(self):
        self.Table=[[" "," "," "],[" "," "," "],[" "," "," "]]
        self.Turn=0;
