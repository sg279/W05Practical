import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//This class is an abstraction of a finite state machine, that maintains a state, takes an input, and depending
//on the state produces an output and switches to another state
public class FSM {
    //Define an array list of State objects called states
    public ArrayList<State> states = new ArrayList<>();
    //Define a state property called currentState
    private State currentState;

    /**
     * This method takes an input, changes the state of the machine, and returns an output (which is null if the input
     * doesn't exist
     * @param inputValue The input for the machine
     * @return The output of the machine
     */
    public String processInput(String inputValue){
        //Create an input object from the state's input that matches that value
        Input input = currentState.getInput(inputValue);
        //If the input object isn't null do the following
        if (input!=null){
            //Define a string called output as the input object's output property
            String output=input.getOutput();
            //Define an integer called stateIndex as the getStateIndex method called on the name of the transition state
            //property of the input object
            int stateIndex = getStateIndex(input.getTransition());
            //Create a state object called transitionState as the state at the transition state's index in the state's array
            State transitionState = states.get(stateIndex);
            //Set the machine's current state to the transitionState object
            this.currentState = transitionState;
            //Return the output object
            return output;
        }
        //Otherwise, return null
        else{
            return null;
        }
    }

    /**
     * This method returns the index of the state in the states array with a name that matches the name
     * parameter and returns -1 if there is no matching state
     * @param name The name of the state to be returned
     * @return The index the state appears in the states property array or -1 if the state doesn't exist
     */
    public int getStateIndex(String name){
        //Define an integer called returnStateIndex as -1
        int returnStateIndex=-1;
        //For each state item in the states array, if the state's name matches the name parameter
        //set returnStateIndex to the state's index
        for (int i=0; i<states.size(); i++) {
            if(states.get(i).getName().equals(name)){
                returnStateIndex = i;
            }
        }
        //Return returnStateIndex
        return returnStateIndex;
    }

    public void addState(State state){
        this.states.add(state);
    }

    public void setInitialState(State initialState) {
        this.currentState = initialState;
    }

    /**
     * This method returns true if all of the transitions defined in the transition table are for existing states
     * @return Whether or not all of the transitions in the transition table are valid
     */
    public boolean testTransitions(){
        //Define an array list of strings called startingStates
        ArrayList<String> startingStates = new ArrayList<>();
        //For each state in the states array property, add the state's name to the startingStates array list
        for (State state: states
             ) {
            startingStates.add(state.getName());
        }
        //For each state in the states array do the following
        for (State state: states
             ) {
            //For each of the states inputs, if the inputs transition isn't in the starting states array, return false
            for (Input input:state.getInputs()
                 ) {
                if(!startingStates.contains(input.getTransition())){
                    return false;
                }
            }
        }
        //Return true
        return true;
    }

    /**
     * This method returns true if all states have he same inputs and no duplicates and returns false otherwise
     * @return Whether or not the state's inputs are valid
     */
    public boolean testInputs(){
        //Create an array list of strings called inputs as the getInputValues method called on the first state in the states array
        ArrayList<String> inputs = states.get(0).getInputValues();
        //Create a new hash set called inputSet
        Set<String> inputSet = new HashSet<>();
        //Add the contents of the inputs array list to the input set
        inputSet.addAll(inputs);
        //If the size of the set is different to the size of the array list return false (as this means the list will contain duplicates)
        if(inputSet.size()!=inputs.size()){
            return false;
        }
        //For each state after the first, return false if the getInputValues method returns a different array to the inputs list
        for (int i = 1; i<states.size(); i++){
            if(!states.get(i).getInputValues().equals(inputs)){
                return false;
            }
        }
        //Return true
        return true;

    }
}