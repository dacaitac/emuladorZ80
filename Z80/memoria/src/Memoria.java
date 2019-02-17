public class Memoria {
    private int[] MEM;
    private final int SIZE = 65535;

    public Memoria() {
        this.MEM = new int[SIZE];
    }

    public int get(int addr) {
        return this.MEM[addr];
    }

    public void set(int addr, int value) {
        this.MEM[addr] = value;
    }

}
