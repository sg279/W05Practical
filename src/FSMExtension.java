import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//This class extends FSM and adds a method to add any inputs that are missing
public class FSMExtension extends FSM {

    /**
     * This method runs checks that all states have the same inputs and adds any missing inputs to the states that output nothing and transition
     * the state to the same state
     */
    public void normaliseInputs(){
        //Create a new hash set called inputSet
        Set<String> inputSet = new HashSet<>();
        //For each state the machine contains, add all of the input values to the set
        for (State state: states
             ) {
            inputSet.addAll(state.getInputValues());
        }
        //For each state in the machine do the following
        for (State state: states
             ) {
            //Copy the set to an array list called toAdd
            ArrayList<String> toAdd = new ArrayList<>(inputSet);
            //For each value of the state's inputs, remove the state from the toAdd array
            for (String input: state.getInputValues()
                 ) {
                toAdd.remove(input);
            }
            //For each value left in the toAdd array, create a new input that outputs nothing and transitions
            //to the same state and add it to the state
            for (String value: toAdd
                 ) {
                Input newInput = new Input(value, "", state.getName());
                state.addInput(newInput);
            }
        }

    }

    /**
     * This is the constructor for an FSMExtension object that takes a finite state machine as a parameter and sets
     * the FSMExtension's states array to the array of the finite state machine
     * @param fsm The machine that contains the array to be copied
     */
    public FSMExtension(FSM fsm){
        this.states=fsm.states;
    }
}