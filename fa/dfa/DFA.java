package fa.dfa;

import fa.State;

import java.util.*;

/**
 *  An implementation of DFAs using java collection data structures
 *  Every DFA contains a starting state represented by q0, a set of final states represented by F,
 *  a set of all states in the DFA represented by Q, and a set of characters that represents the
 *  alphabet of the DFA called Sigma.
 *
 * @author Xavier Caracter
 */

public class DFA implements DFAInterface {

    //Fields
    //F = Final state, set of DFAStates
    private Set<DFAState> F;
    //Q0 = Initial State
    private DFAState q0;
    //Sigma = set containing language
    private Set<Character> Sigma;
    //Q = set of all states
    private Set<DFAState> Q;

    //Constructor
    public DFA(){
        this.F = new LinkedHashSet<>();
        this.q0 = new DFAState("dummy");
        this.Sigma = new LinkedHashSet<>();
        this.Q = new LinkedHashSet<>();
    }

    //Copy Constructor
    public DFA(DFA dfa){
        this.F = dfa.F;
        this.q0 = dfa.q0;
        this.Sigma = dfa.Sigma;
        this.Q = dfa.Q;
    }



    //Methods
    /**
     * Adds the initial state to the DFA instance
     *
     * @param name is the label of the start state
     */
    @Override
    public void addStartState(String name) {
        /* Check to see if start state is final state, then return */
        for(DFAState s : Q){
            if(s.getName().equals(name)){
                q0 = s;
                return;
            }
        }
        /* Create new starting DFAState q0, and add it to set of states Q */
        q0 = new DFAState(name);
        Q.add(q0);
    }

    /**
     * Adds a non-final, not initial state to the DFA instance
     *
     * @param name is the label of the state
     */
    @Override
    public void addState(String name) {
        /* Create a new DFAState and add it to the set of states Q */
        Q.add(new DFAState(name));
    }

    /**
     * Adds a final state to the DFA
     *
     * @param name is the label of the state
     */
    @Override
    public void addFinalState(String name) {
        /* Create a new DFAState for the fina state and it to both the final state set F and set of total states Q */
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
        /* Add the onSymb to the alphabet set Sigma */
        Sigma.add(onSymb);

        /* Loop through set of states Q to find fromState */
        for(DFAState current : Q){
            if(current.getName().equals(fromState)){
                /* Add transition to states transition map */
                current.toState(onSymb, getDFAState(toState));
            }
        }

    }

    /**
     * When given the string representation of a state, return
     * the corresponding DFAState
     * @param nameOfState
     * @return
     */
    public DFAState getDFAState(String nameOfState){
        /* Loop through set of states Q */
        for(DFAState d: Q){
            if(d.getName().equals(nameOfState)){
                /* Return corresponding DFAState if the current iteration is equal to the name of the state passed in */
                return d;
            }
        }
        return null;
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
     * Setter for Q
     * @param newQ
     */
    public void setQ(Set<DFAState> newQ){
        this.Q = newQ;
    }

    /**
     * Setter for F
     * @param newF
     */
    public void setF(Set<DFAState> newF){
        this.F = newF;
    }

    /**
     * Setter for Sigma
     * @param newSig
     */
    public void setSigma(Set<Character> newSig){
        this.Sigma = newSig;
    }

    /**
     * Setter for q0
     * @param newQ0
     */
    public void setQ0(DFAState newQ0){
        this.q0 = newQ0;
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
        DFAState current = q0;
        /* Loop over length of input string */
        for(int i = 0; i < s.length();i++){
            /* Get the appropriate state transitioned from correct string */
            current = (DFAState) getToState(current, s.charAt(i));

        }
        /* If the current state is in set of final states F, return true ,else return false */
        if(F.contains(current)){
            return true;
        } else {
            return false;
        }
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
        return from.getToState(onSymb);
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
        /* Create Deep Copy of DFA object using Copy constructor */
        DFA dfa = new DFA();
        DFA dfaC = new DFA(dfa);
        /* Get starting state and create vars for final and total state sets */
        DFAState newStartingState = (DFAState) getStartState();
        Set<DFAState> newFinalStates = new HashSet<>();
        Set<DFAState> newStates = new HashSet<>();

        /* Iterate over Q and perform complimentary operation */
        for(DFAState d : Q){
            if(F.contains(d)){
                newStates.add(d);
            } else {
                newFinalStates.add(d);
                newStates.add(d);
            }
        }
        /* Use setters for DFA Compliment's attributes */
        dfaC.setQ(newStates);
        dfaC.setF(newFinalStates);
        dfaC.setSigma(Sigma);
        dfaC.setQ0(newStartingState);
        //for each element not in dfa final states, put into dfaC final states
        return dfaC;
    }


    /**
     * Gives string representation of DFA in proper format a seen in the 
     * Assignment specifications using String Builder
     * @return dfaString
     */
    public String toString() {
        StringBuilder dfaString = new StringBuilder();

        /* Create Q portion of string */
        dfaString.append("Q =  { ");
        for (DFAState s : Q) {
            dfaString.append(s.getName() + " ");
        }
        dfaString.append("}\n");

        /* Create Sigma portion of string */
        dfaString.append("Sigma = { ");
        for (Character a : Sigma ) {
            dfaString.append(a + " ");
        }
        dfaString.append("}\n" );

        /* Create delta portion of string */
        dfaString.append("delta =  \n" );
        dfaString.append("\t\t");
        //Set of symbols row
        for (Character a : Sigma ) {
            dfaString.append(a.toString() + "\t");
        }
        dfaString.append("\n");
        /* Set of states colum */
        for(DFAState s : Q) {
            dfaString.append("\t" + s.getName());
            /* States on symbol in between */
            for(Character a : Sigma) {
                dfaString.append("\t" + s.getToState(a));
            }
            dfaString.append("\n");
        }

        /* Start state portion of string */
        dfaString.append("q0 = { ");
        dfaString.append(q0.getName() + " }\n");

        /* Final states portion of string */
        dfaString.append("F = { ");
        for (DFAState f : F ) {
            dfaString.append(f + " ");
        }
        dfaString.append("}\n");

        /* Return fully built DFA string */
        return dfaString.toString();

    }
}
