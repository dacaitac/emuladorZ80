public class Main {
  public static void main(String[] args) {
    Assembler a = new Assembler();
    LinkerLoader ll = new LinkerLoader();
    Memory m = new Memory();
    // Processor p = null;
    try {
      a.assemble("programs/suma.txt");
      ll.chargeProgram("relocatableCode.txt", m);
    } catch(Exception e) {}
  }
}
