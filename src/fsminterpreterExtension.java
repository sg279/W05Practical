import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

//This class contains code to set up and process the inputs for the finite state machine extension objects
public class fsminterpreterExtension extends fsminterpreter{

    /**
     * This method sets up a finite state machine extension object and processes the input stream
     * @param args The program arguments, should contain the location of the transition table
     */
    public static void main(String[] args){

        //Create an array list of FSMExtension objects called machineChain
        ArrayList<FSMExtension> machineChain = new ArrayList<>();
        //For each argument do the following
        for (String arg: args
             ) {
            //Create a new FSMExtension object from the argument
            FSMExtension fsm = new FSMExtension(setup(arg));
            //Call the normaliseInputs method on the fsm object
            fsm.normaliseInputs();
            //Set the fsm's state to the first state
            fsm.setInitialState(fsm.states.get(0));
            //If the testTransitions and testInputs methods aren't both true print 'Bad description'and exit
            if(!(fsm.testTransitions()&&fsm.testInputs())){
                System.out.print("Bad description");
                System.exit(0);
            }
            //Add the fsm to the machine chain
            machineChain.add(fsm);
        }

        //Working backwards through the machine chain, add each machine as a second machine to the machine before
        for (int i = machineChain.size()-1; i>0; i--){
            machineChain.get(i-1).setSecondMachine(machineChain.get(i));
        }
        //Create a FSMExtension object from the first item in the chain
        FSMExtension fsm = machineChain.get(0);

        //The rest of this code was taken from the fsminterpereter class
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
