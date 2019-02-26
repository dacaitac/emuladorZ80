public class Main {
  public static void main(String[] args) {
    Assembler a = new Assembler();
    LinkerLoader ll = new LinkerLoader();
    Memory m = new Memory();
    Processor p = null;
    try {
      a.assemble("programs/fibo.txt");
      ll.chargeProgram("relocatableCode.txt", m);
      p.runProgram();
    } catch(Exception e) {}
  }
}
