import java.util.ArrayList;

public class fsm{
    public ArrayList<State> states = new ArrayList<>();
    private State currentState;
    private State initialState;

    public String processInput(String input){
        String output=currentState.getInput(input).getOutput();
        State transitionState = states.get((getStateIndex(currentState.getInput(input).getTransition())));
        this.currentState = transitionState;
        return output;
    }

    public int getStateIndex(String name){
        int returnStateIndex=-1;
        for (int i=0; i<states.size(); i++) {
            if(states.get(i).getName().equals(name)){
                returnStateIndex = i;
            }
        }
        return returnStateIndex;
    }

    public void addState(State state){
        this.states.add(state);
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }
}