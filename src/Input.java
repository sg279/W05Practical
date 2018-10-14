//This class describes a finite state machine input by what the input value is, what output this results in, and what state it changes the machine to
public class Input{
    //The input value
    private String value;
    //The output of the machine
    private String output;
    //The state the machine transitions to
    private String transition;

    /**
     * The constructor for an input object
     * @param value The value of the input
     * @param output The value for the machine to output
     * @param transition The state the machine will transition to
     */
    public Input( String value, String output, String transition){
        this.value = value;
        this.output = output;
        this.transition = transition;
    }

    public String getOutput() {
        return output;
    }

    public String getTransition() {
        return transition;
    }

    public String getValue() {
        return value;
    }
}