import java.io.*;
import java.util.Arrays;

/**
 * This class contains methods for reading and deleting messages in the directory
 */
public class fsminterpreter {

    public static fsm setup(String file){

        fsm Fsm = null;
        try{
            File transitionTable = new File(file);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(transitionTable));
            String line = bufferedReader.readLine();
            String[] row = line.split(" ");
            Fsm = new fsm();
            State newState = new State(row[0]);
            newState.addInput(new Input(row[1],row[2],row[3]));
            Fsm.addState(newState);
            line = bufferedReader.readLine();
            while (line!=null){
                row = line.split(" ");
                if (Fsm.getStateIndex(row[0])<0){
                    newState = new State(row[0]);
                    newState.addInput(new Input(row[1],row[2],row[3]));
                    Fsm.addState(newState);
                }
                else{
                    Fsm.states.get((Fsm.getStateIndex(row[0]))).addInput(new Input(row[1],row[2],row[3]));
                }
                line = bufferedReader.readLine();
            }
            Fsm.setInitialState(Fsm.states.get(0));
        }
        catch (FileNotFoundException e){

        }
        catch (IOException e){

        }
        return Fsm;

    }

    public static void main (String[] args){
        fsm Fsm = setup(args[0]);
        int a=90;
    }
}
