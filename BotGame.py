import TicTak
import AI

if __name__=="__main__":
    again=True
    game=TicTak.TicTak();
    bot=AI.SimpleBot(game);
    while again:
        while not game.End():
            game.Ask();
            if not game.End():
                bot.Play();
               # game.Turn+=1
        if not game.Won():
            print("The game ended in draw.")
        inp=input("Do you wish to play another game ?")
        if inp in ["YES","Yes","y","Y"]:
            game.Nullify();
        else:
            again=False
