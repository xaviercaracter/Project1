package fa.dfa;

import fa.State;

public class DFAState extends State {
    //Fields
    private final String name;

    //Constructor
    public DFAState(String name) {
        this.name = name;
    }

    //Methods
    @Override
    public String getName(){
        return name;
    }
}
