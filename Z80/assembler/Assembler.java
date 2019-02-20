package assembler;

import java.util.HashMap;
import java.util.HashSet;

public class Assembler {
    int programSize;


    private HashSet<String> opcodes;
    private HashMap<String, Integer> labels;
    private HashMap<String, Integer> reg_8bit;
    private HashMap<String, Integer> reg_16bit;

    public Assembler(String assemblyProgram) {


    }

    public String getProgramForGUI() {
        return "3e12 473e 184f 78b9 280b 3804 9147 18f6\n" + //De momento es el codigo de mcd2 desde la dirección 4000
                "7990 4f18 f1d3 b476 0000 0000 0000 0000\n";
    }

    public String getProgramForProcessor() {
        return "0000000000000000000000000000000000000000\n" +
                "0000000000000000000000000000000000000000\n" +
                "0000000000000000001100100000000000110010\n" +
                "0000000000000000101000100000000010100010\n" +
                "0000000000000000000000100000000000000010\n" +
                "0000000000000000000000010000000000000001\n" +
                "0000000000000000000000000000000000000000\n" +
                "0000000000000000000000000000000000000000\n" +
                "0000000000000000000000000000000000000000\n";
    }


}
