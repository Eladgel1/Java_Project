package Graphic;

public class Originator {
    private MainMenuFrame state;
    public void setState(MainMenuFrame state) { this.state = state; }
    public MainMenuFrame getState() { return state; }
    public Memento createMemento() {
        return new Memento(state);
    }
    public void setMemento(Memento memento) {
        state = memento.getState();
    }
}
