package fa.dfa;

import fa.State;

import java.util.HashMap;

public class DFAState extends State {

    //Fields
    private HashMap<Character, DFAState> trans;

    //Constructor
    public DFAState(String name) {
        this.name = name;
        trans = new HashMap<Character, DFAState>();
    }

    //Methods
    @Override
    public String getName(){
        return name;
    }

    public void toState(char tranSymb, DFAState toState){
        trans.put(tranSymb, toState);
    }

    public DFAState getToState(char toSymb){
        return trans.get(toSymb);
    }
}
