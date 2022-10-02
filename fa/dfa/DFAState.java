package fa.dfa;

import fa.State;

import java.util.HashMap;

/**
 * Class that represents a DFAState
 * Each State uses a hashmap for its transitions to other states
 * on a given transition symbol
 *
 * @author Xavier Caracter
 */

public class DFAState extends State {

    //Fields
    /** HashMap that stores the DFAState's transitions Character is the transition char
     * DFAState is the destination state
     */
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

    /**
     * Puts the correct target state in the hashmap of transition states
     * @param tranSymb char transition symbol
     * @param toState Destination DFAState
     */
    public void toState(char tranSymb, DFAState toState){
        trans.put(tranSymb, toState);
    }

    /**
     * Returns the correct DFAState according to the transition symbol
     * @param toSymb char transition symbol
     * @return DFAState we are looking for according to toSymb
     */
    public DFAState getToState(char toSymb){
        return trans.get(toSymb);
    }
}
