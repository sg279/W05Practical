import java.io.*;

//This class handles setting up the finite state machine by adding states to it and checking it is a valid
//machine as well as processing the input sequence
public class fsminterpreter {

    /**
     * This method returns a finite state machine with states set based on a transition table
     * @param file The transition table location
     * @return The set up finite state machine
     */
    public static FSM setup(String file){
        //Create a new finite state machine as a null object
        FSM Fsm = null;
        //Try the following
        try{
            //Create a file object called transitionTable fom the file parameter
            File transitionTable = new File(file);
            //Create a new buffered reader from a file reader with the transition table file parsed to it
            BufferedReader bufferedReader = new BufferedReader(new FileReader(transitionTable));
            //Create a string called line as the first line in the file
            String line = bufferedReader.readLine();
            //Create an array of strings called row as the line string split by spaces
            String[] row = line.split(" ");
            //Set the finite state machine to a new finite state machine object
            Fsm = new FSM();
            //Create a new state object with the first item in the row parsed to the constructor
            State newState = new State(row[0]);
            //Add an input to the state with the properties from the rest of the row
            newState.addInput(new Input(row[1],row[2],row[3]));
            //Add the state to the finite state machine
            Fsm.addState(newState);
            //Set the line to the next line of the transition table
            //line = bufferedReader.readLine();
            //While the buffered reader has more to read do the following
            while (bufferedReader.ready()){
                //Set line to the next line in he transition table
                line = bufferedReader.readLine();
                //Set the row array to the line split by spaces again
                row = line.split(" ");
                if(row.length==4){
                    //If the getStateIndex method returns a negative value (the state doesn't exist) do the following
                    if (Fsm.getStateIndex(row[0])<0){
                        //Set newState to a new state from the first item in the row
                        newState = new State(row[0]);
                        //Add a new input to the state with the properties from the rest of the row
                        newState.addInput(new Input(row[1],row[2],row[3]));
                        //Add the state to the finite state machine
                        Fsm.addState(newState);
                    }
                    //Otherwise, add a new input to the state with the properties defined in the rest of the row
                    else{
                        Fsm.states.get((Fsm.getStateIndex(row[0]))).addInput(new Input(row[1],row[2],row[3]));
                    }
                }
            }
            //Set the initial state to the first state in the states array
            Fsm.setInitialState(Fsm.states.get(0));
        }
        //If exceptions are thrown print them
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        //Return the finite state machine
        return Fsm;

    }

    /**
     * This is the main method and calls for the finite state machine to be set up and processes the input sequence
     * @param args The arguments provided in the constructor that point to the transition table and input sequence
     */
    public static void main (String[] args){
        //Create a new finite state machine from the setup method called with the first
        //argument (the transition table) as the parameter
        FSM Fsm = setup(args[0]);
        //If the testTransitions and testInputs methods aren't both true print 'Bad description'
        if(!(Fsm.testTransitions()&&Fsm.testInputs())){
            System.out.print("Bad description");
        }
        //Otherwise do the following
        else {
            //Try the following
            try {
                //Create a new buffered reader out of a file reader with the second argument (the input sequence) parsed to it
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                //BufferedReader bufferedReader = new BufferedReader(new FileReader(args[1]));
                //Create a string called inputSequence from the input file
                String inputSequence = bufferedReader.readLine();
                //For each character in the input sequence do the following
                String[] inputCharacters = inputSequence.split("");
                for (int i = 0; i < inputCharacters.length; i++) {
                    //Create a string called output from the processInput method called on the finite state machine with
                    //the string character parsed as a parameter
                    String output = Fsm.processInput(inputCharacters[i]);
                    //If the output isn't null print it
                    if (output != null) {
                        System.out.print(output);
                    }
                    //Otherwise, print 'Bad input' and set the counter to the end of the string to break the loop
                    else {
                        System.out.print("Bad input");
                        System.exit(0);
                    }
                }
            }
            //If exceptions are thrown, print them
            catch (FileNotFoundException e) {
                System.out.println(e);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }

    }
}
