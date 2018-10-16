import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//This class contains code to set up and process the inputs for the finite state machine extension objects
public class fsminterpreterExtension extends fsminterpreter{

    /**
     * This method sets up a finite state machine extension object and processes the input stream
     * @param args The program arguments, should contain the location of the transition table
     */
    public static void main(String[] args){
        //Create a new FSMExtension object by parsing the constructor a finite state machine
        //created with the setup method that's extended
        FSMExtension fsm = new FSMExtension(setup(args[0]));
        //Call the normaliseInputs method on the fsmObject
        fsm.normaliseInputs();
        //Set the fsm's state to the first state
        fsm.setInitialState(fsm.states.get(0));

        //The rest of this method is copied from the fsmInterpreter main method
        if(!(fsm.testTransitions()&&fsm.testInputs())){
            System.out.print("Bad description");
        }
        else {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String inputSequence = bufferedReader.readLine();
                String[] inputCharacters = inputSequence.split("");
                for (int i = 0; i < inputCharacters.length; i++) {
                    String output = fsm.processInput(inputCharacters[i]);
                    if (output != null) {
                        System.out.print(output);
                    }
                    else {
                        System.out.print("Bad input");
                        System.exit(0);
                    }
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
