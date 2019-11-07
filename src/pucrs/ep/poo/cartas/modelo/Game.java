package pucrs.ep.poo.cartas.modelo;

import pucrs.ep.poo.cartas.gui.GameEvent;

import java.util.*;

public class Game extends Observable{
    private static Game game = new Game();
    private int ptsJ1,ptsJ2;
    private CardDeck deckJ1,deckJ2;
    private Table tableJ1, tableJ2;
    private int player;
    private int jogadas;

    public static Game getInstance(){
        return(game);
    }
    
    private Game(){
        ptsJ1 = 0;
        ptsJ2 = 0;
        deckJ1 = new CardDeck(1);
        deckJ2 = new CardDeck(2);
        tableJ1 = new Table(1);
        tableJ2 = new Table(2);
        player = 1;
        jogadas = CardDeck.NCARDS;
    }
    
    private void nextPlayer(){
        player++;
        if (player == 4){
            player = 1;
        }
    }
        
    public int getPtsJ1(){
        return(ptsJ1);
    }

    public int getPtsJ2(){
        return(ptsJ2);
    }
    
    public CardDeck getDeckJ1(){
        return(deckJ1);
    }
    
    public CardDeck getDeckJ2(){
        return(deckJ2);
    }

    public Table getTableJ1() {return tableJ1;}

    public Table getTableJ2() {return tableJ2;}
    
    public void play(CardDeck deckAcionado){
        GameEvent gameEvent = null;

        if (player == 3){
                player = 1;
        }        
        if (deckAcionado == deckJ1){
            if (player != 1){
                gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.INVPLAY,"2");
                setChanged();
                notifyObservers((Object)gameEvent);
            }else{
                //Adiona carta na mesa
                tableJ1.addToTable(deckJ1.getSelectedCard());
                //Remove a carta selecionada
                this.removeSelected();
                //Manda as mudanças
                setChanged();
                notifyObservers((Object)gameEvent);
                // Proximo jogador
                nextPlayer();
            }
        }else if (deckAcionado == deckJ2){
            if (player != 2){
                gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.INVPLAY,"2");
                setChanged();
                notifyObservers((Object)gameEvent);
            }else{
                //Adiona carta na mesa
                tableJ2.addToTable(deckJ2.getSelectedCard());
                //Remove a carta selecionada
                this.removeSelected();
                //Manda as mudanças
                setChanged();
                notifyObservers((Object)gameEvent);
                // Próximo jogador
                nextPlayer();
            }
        }          
    }

    // Acionada pelo botao de limpar    
    public void removeSelected(){
        GameEvent gameEvent = null;
        deckJ1.removeSel();
        deckJ2.removeSel();
    }
}
