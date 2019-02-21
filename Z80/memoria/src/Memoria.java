public class Memoria {
    private static  int[] MEM;
    private final int SIZE = 65535;

    public Memoria() {
        this.MEM = new int[SIZE];
    }

    public static  int get(int addr) {
        return MEM[addr];
    }

    public void set(int addr, int value) {
        this.MEM[addr] = value;
    }