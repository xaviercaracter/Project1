package fa.dfa;

import fa.State;

public class DFAState extends State {

    //Constructor
    public DFAState() {
    }

    //Methods
    @Override
    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }
}
