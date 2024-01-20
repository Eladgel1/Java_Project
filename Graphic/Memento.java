package Graphic;

public class Memento {
    private MainMenuFrame state;
    public Memento(MainMenuFrame state){
        this.state = state;
    }
    public MainMenuFrame getState() { return state; }
}

