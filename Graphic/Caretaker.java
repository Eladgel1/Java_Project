package Graphic;

import java.util.*;

public class Caretaker {
    private List statesList = new ArrayList();
    public void addMemento(Memento m) {
        statesList.add(m);
    }
    public void removeMemento(){
        statesList.remove(0);
    }
    public Memento getMemento(int index) {
        return (Memento) statesList.get(index);
    }
    public int get_list_size(){return statesList.size();}
}