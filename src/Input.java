public class Input{
    private String value;
    private String output;
    private String transition;

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