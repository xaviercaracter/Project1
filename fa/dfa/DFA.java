package fa.dfa;

import fa.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DFA implements DFAInterface {

    //Fields
    //F = Final state, set of DFAState
    Set<DFAState> F;
    //Q0 = Initial State
    DFAState q0;
    //Sigma = set containing language
    Set<Character> Sigma;
    //Q = set of all states
    Set<DFAState> Q;
    //delta = map containing transition function table
    Map<DFAState, Map<Character, DFAState>> Delta;

    //Constructor
    public DFA(){
        this.F = new HashSet<>();
        this.q0 = new DFAState();
        this.Sigma = new HashSet<>();
        this.Q = new HashSet<>();
        this.Delta = new HashMap<>();
    }





    //Methods
    /**
     * Adds the initial state to the DFA instance
     *
     * @param name is the label of the start state
     */
    @Override
    public void addStartState(String name) {
        q0.setName(name);
        Q.add(q0);
    }

    /**
     * Adds a non-final, not initial state to the DFA instance
     *
     * @param name is the label of the state
     */
    @Override
    public void addState(String name) {
        DFAState states = new DFAState();
        states.setName(name);
        Q.add(states);
    }

    /**
     * Adds a final state to the DFA
     *
     * @param name is the label of the state
     */
    @Override
    public void addFinalState(String name) {
        DFAState finalState = new DFAState();
        finalState.setName(name);
        F.add(finalState);
        Q.add(finalState);
    }

    /**
     * Adds the transition to the DFA's delta data structure
     *
     * @param fromState is the label of the state where the transition starts
     * @param onSymb    is the symbol from the DFA's alphabet.
     * @param toState   is the label of the state where the transition ends
     */
    @Override
    public void addTransition(String fromState, char onSymb, String toState) {
        Sigma.add(onSymb);
        DFAState from = new DFAState();
        DFAState to = new DFAState();
        from.setName(fromState);
        to.setName(toState);
        if(!Delta.containsKey(from)){
            Map<Character, DFAState> value = new HashMap<>();
            Delta.put(from, value);
            value.put(onSymb, to);
        } else {
            Map<Character, DFAState> value = Delta.get(from);
            value.put(onSymb, to);
        }
    }

    /**
     * Getter for Q
     *
     * @return a set of states that FA has
     */
    @Override
    public Set<? extends State> getStates() {
        return Q;
    }

    /**
     * Getter for F
     *
     * @return a set of final states that FA has
     */
    @Override
    public Set<? extends State> getFinalStates() {
        return F;
    }

    /**
     * Getter for q0
     *
     * @return the start state of FA
     */
    public State getStartState() {
        return q0;
    }

    /**
     * Getter for Sigma
     *
     * @return the alphabet of FA
     */
    @Override
    public Set<Character> getABC() {
        return Sigma;
    }

    /**
     * Simulates a DFA on input s to determine
     * whether the DFA accepts s.
     *
     * @param s - the input string
     * @return true if s in the language of the DFA and false otherwise
     */
    @Override
    public boolean accepts(String s) {
        return false;
    }

    /**
     * Uses transition function delta of FA
     *
     * @param from   the source state
     * @param onSymb the label of the transition
     * @return the sink state.
     */
    @Override
    public State getToState(DFAState from, char onSymb) {
        DFAState toState = Delta.get(from).get(onSymb);
        if(toState == null){
            throw new NullPointerException("Looks like the input data " + from.getName() + " " + onSymb + " didn't work.");
        } else
            return toState;
    }

    /**
     * Computes a copy of this DFA
     * which language is the complement
     * of this DFA's language.
     *
     * @return a copy of this DFA
     */
    @Override
    public DFA complement() {
        return null;
    }

    public String printQ(){
        String qElems="";
        qElems += "Q = { ";
        for(DFAState Qs : Q){
            qElems += Qs + " ";
        }
        qElems += "}";
        return qElems;
    }

    public String printFs(){
        String fElems="";
        fElems += "F = { ";
        for(DFAState Fs : F){
            fElems += Fs + " ";
        }
        fElems += "}";
        return fElems;
    }

    public String printSigma(){
        String sigElems="";
        sigElems += "Sigma = { ";
        for(Character sigmas : Sigma){
            sigElems += sigmas + " ";
        }
        sigElems += "}";
        return sigElems;
    }

    public String printDelta(){
        String deltaString = "";
        deltaString += "delta = \n";
        deltaString += "\t\t";
        for(Character sigmas : Sigma){
            deltaString += sigmas + "\t";
        }
        deltaString += "\n";
        for(DFAState Qs : Q){
            deltaString += "\t" +Qs;
            for(Character sigs : Sigma) {
                 deltaString += "\t" + getToState(Qs, sigs).getName();
            }
            deltaString += "\n";
        }

            //getToState().getName();

        return deltaString;
    }


    public String toString() {

        return "q0 = " + q0.getName() + "\n" + printFs() + "\n" + printQ() + "\n" + printSigma()+ "\n" + printDelta();
    }
}
