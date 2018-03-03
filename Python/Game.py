import TicTak

if __name__=="__main__":
    again=True
    game=TicTak.TicTak();
    while again:
        while not game.End():
            game.Ask();
        inp=input("Do you wish to play another game ?")
        if inp in ["YES","Yes","y","Y"]:
            game.Nullify();
        else:
            again=False
