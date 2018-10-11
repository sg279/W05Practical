import java.util.ArrayList;

public class State{
    private String name;
    private ArrayList<Input> inputs;

    public String getName() {
        return name;
    }

    public ArrayList<Input> getInputs() {
        return inputs;
    }

    public Input getInput (String value){
        Input input = null;
        for (Input inputIterator: inputs
        ) {
            if (inputIterator.getValue().equals(value)){
                input = inputIterator;
            }
        }
        return input;
    }

    public void addInput (Input input){
        inputs.add(input);
    }

    public State (String name){
        this.name=name;
    }
}