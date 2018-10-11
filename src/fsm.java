import java.util.ArrayList;

public class fsm{
    private ArrayList<State> states;
    private State currentState;
    private State initialState;

    public String processInput(String input){
        String output=currentState.getInput(input).getOutput();
        State transitionState = getState(currentState.getInput(input).getTransition());
        this.currentState = transitionState;
        return output;
    }

    private State getState(String name){
        State returnState = null;
        for (State state: states
             ) {
            if(state.getName().equals(name)){
                returnState = state;
            }
        }
        return returnState;
    }

    public void addState(State state){
        this.states.add(state);
    }

    public fsm(State initial){
        this.initialState=initial;
    }
}