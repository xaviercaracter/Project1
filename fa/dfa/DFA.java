package fa.dfa;

import fa.State;

import java.util.HashSet;
import java.util.Set;

public class DFA implements DFAInterface {

    //Fields
    //F = Final state, set of DFAState
    Set<DFAState> F = new HashSet<DFAState>();
    //Q0 = Initial State, set of DFAstate
    Set<DFAState> q0 = new HashSet<DFAState>();
    //Sigma = set containing language
    Set<String> Sigma = new HashSet<String>();
    //Q = set of all states
    Set<DFAState> Q = new HashSet<DFAState>();
    //delta = map containing transition function table

    //Constructor






    //Methods
    /**
     * Adds the initial state to the DFA instance
     *
     * @param name is the label of the start state
     */
    @Override
    public void addStartState(String name) {
        DFAState startState = new DFAState(name);
        q0.add(startState);
        Q.add(startState);
    }

    /**
     * Adds a non-final, not initial state to the DFA instance
     *
     * @param name is the label of the state
     */
    @Override
    public void addState(String name) {
        DFAState states = new DFAState(name);
        Q.add(states);
    }

    /**
     * Adds a final state to the DFA
     *
     * @param name is the label of the state
     */
    @Override
    public void addFinalState(String name) {
        DFAState finalState = new DFAState(name);
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

    }

    /**
     * Getter for Q
     *
     * @return a set of states that FA has
     */
    @Override
    public Set<? extends State> getStates() {
        return null;
    }

    /**
     * Getter for F
     *
     * @return a set of final states that FA has
     */
    @Override
    public Set<? extends State> getFinalStates() {
        return null;
    }

    /**
     * Getter for q0
     *
     * @return the start state of FA
     */
    @Override
    public State getStartState() {
        return null;
    }

    /**
     * Getter for Sigma
     *
     * @return the alphabet of FA
     */
    @Override
    public Set<Character> getABC() {
        return null;
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
        return null;
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
}
