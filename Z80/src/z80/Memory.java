package z80;

import java.util.Arrays;

public class Memory {

	private int[] M;
	private final int SIZE = 65535;
	private int org;

	public int getOrg() {
		return org;
	}

	public void setOrg(int org) {
		this.org = org;
	}

	public Memory() {
		this.M = new int[SIZE];
	}

	public int get(int addr) {
		return this.M[addr];
	}

	public void set(int addr, int value) {
		this.M[addr] = value;
	}

	public void setMemory(int[] data) {
		this.M = data;
	}

	@Override
	public String toString() {
		return "Memory [M=" + Arrays.toString(M) + "]";
	}
}
