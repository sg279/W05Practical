import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//This class represents a state the machine can take, what inputs are available, and what output and transition they cause
public class State{
    //The name of the state
    private String name;
    //The inputs the state can take
    private ArrayList<Input> inputs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public ArrayList<Input> getInputs() {
        return inputs;
    }

    /**
     * Gets the state's input object by the input value
     * @param value The value of the input to be fetched
     * @return The input object for the value parameter (null if no input with that value exists)
     */
    public Input getInput (String value){
        //Create a new null input object
        Input input = null;
        //For each input the state can take, if the input's value is the same as the parameter parsed, set the input
        //object to the state's input
        for (Input inputIterator: inputs
        ) {
            if (inputIterator.getValue().equals(value)){
                input = inputIterator;
            }
        }
        //Return the input object
        return input;
    }

    public void addInput (Input input){
        this.inputs.add(input);
    }

    /**
     * The constructor for a state object
     * @param name The state's name
     */
    public State (String name){
        this.name=name;
    }

    /**
     * This method returns all of the values that the state can take as inputs
     * @return A list of input values
     */
    public ArrayList<String> getInputValues(){
        //Create an empty arraylist of strings called inputValues
        ArrayList <String> inputValues = new ArrayList<>();
        //For each input the state can take, add the value of the input to the arraylist
        for (Input input: inputs
             ) {
            inputValues.add(input.getValue());
        }
        //Sort the array list
        Collections.sort(inputValues);
        //Return the array list
        return inputValues;
    }
}