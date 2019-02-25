package z80;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Assembler {
	private int MEMORY_SIZE;
	private IR ir;
	private HashMap<String, Integer> reg_8bit;
	private HashMap<String, Integer> reg_16bit;
	private HashMap<String, Integer> labels;
	private HashSet<String> opcodes;
	private int memPointer, orgPointer;

	private ArrayList<int[]> programList = new ArrayList<int[]>();

	public Assembler() {
		this.ir = new IR();
		this.MEMORY_SIZE = (int) Math.pow(2, 16);
		this.reg_8bit = new HashMap();
		this.reg_16bit = new HashMap();
		this.labels = new HashMap();
		this.opcodes = new HashSet();
		this.opcodes.add("ADD");
		this.opcodes.add("SUB");
		this.opcodes.add("LD");
		this.opcodes.add("AND");
		this.opcodes.add("OR");
		this.opcodes.add("XOR");
		this.opcodes.add("OUT");
		this.opcodes.add("JP");
		this.opcodes.add("INC");
		this.opcodes.add("DEC");
		this.opcodes.add("CPL");
		this.opcodes.add("NEG");
		this.opcodes.add("CP");
		this.opcodes.add("RL");
		this.opcodes.add("RR");
		this.opcodes.add("BIT");
		this.opcodes.add("SET");
		this.opcodes.add("RESET");
		this.opcodes.add("CALL");
		this.opcodes.add("HALT");
		this.opcodes.add("END");
		this.opcodes.add("ORG");
		this.opcodes.add("EQU");
		this.orgPointer = 0;
		this.reg_8bit.put("A", 0);
		this.reg_8bit.put("B", 1);
		this.reg_8bit.put("C", 2);
		this.reg_8bit.put("D", 3);
		this.reg_8bit.put("E", 4);
		this.reg_8bit.put("H", 5);
		this.reg_8bit.put("L", 6);
		this.reg_8bit.put("Ac", 7);
		this.reg_8bit.put("Bc", 8);
		this.reg_8bit.put("Cc", 9);
		this.reg_8bit.put("Dc", 10);
		this.reg_8bit.put("Ec", 11);
		this.reg_8bit.put("Hc", 12);
		this.reg_8bit.put("Lc", 13);
		this.reg_16bit.put("PC", 0);
		this.reg_16bit.put("SP", 1);
		this.reg_16bit.put("IX", 2);
		this.reg_16bit.put("IY", 3);
	}

	private int toDec(String address) {
		int size = address.length();
		return Integer.parseInt(address.substring(0, size - 1), 16);
	}

	private void chargeLabels(String[] file) throws FileNotFoundException, IOException {
//    String input = null;
//    FileReader f = new FileReader(file);
//    BufferedReader b = new BufferedReader(f);    
		String[] line = null;
		int auxPointer = 0;

		for (String input : file) {
			if (input.charAt(0) == '#')
				continue;
			line = input.split(" ");
			if (line.length == 3 || !this.opcodes.contains(line[0])) {
				this.labels.put(line[0], auxPointer);
			}
			auxPointer += 5;
		}
	}

	public ArrayList<int[]> assemble(String[] data) throws FileNotFoundException, IOException {
		this.chargeLabels(data);

//    FileReader f = new FileReader(file);
//    BufferedReader b = new BufferedReader(f);
		String[] line = null, ops = null, check = null, check2 = null;
		int address = 0;
		int[] instruction = null, program = new int[MEMORY_SIZE];
		boolean end = false, no_op = false;

		for (String input : data) {
			no_op = false;
			if (input.charAt(0) == '#')
				continue;
			line = input.split(" ");

			if (line.length == 3) {
				line[0] = line[1];
				line[1] = line[2];
			}

			if (!this.opcodes.contains(line[0])) { // ENDWHILE HALT
				line[0] = line[1];
			}

			switch (line[0]) {

			case "LD":
				// Transferencia de bits
				ops = line[1].split(",");
				check = ops[0].split("[()]");
				check2 = ops[1].split("[()]");

				if (check[0].equalsIgnoreCase("") && check2[0].equalsIgnoreCase("")) {
					if (check[1].length() == 2 && check2[1].length() == 2) {
						// TODO :)
					} else if (check[1].length() == 2) {
						// TODO :)
					} else if (check2[1].length() == 2) {
						// TODO :)
					} else {
						this.ir.opcode = 20; // TODO subir :v
						this.ir.op1 = this.toDec(check[1]);
						this.ir.op2 = this.toDec(check2[1]);
					}
				} else if (check[0].equalsIgnoreCase("") && this.reg_8bit.containsKey(ops[1])) {
					if (check[1].length() == 2) {
						// TODO :)
					} else {
						this.ir.opcode = 17;
						this.ir.op1 = this.toDec(check[1]);
						this.ir.op2 = this.reg_8bit.get(ops[1]);
					}
				} else if (check[0].equalsIgnoreCase("")) {
					if (check[1].length() == 2) {
						// TODO :)
					} else {
						this.ir.opcode = 19;
						this.ir.op1 = this.toDec(check[1]);
						this.ir.op2 = this.toDec(ops[1]);
					}
				} else if (this.reg_8bit.containsKey(ops[0]) && this.reg_8bit.containsKey(ops[1])) {
					this.ir.opcode = 14;
					this.ir.op1 = this.reg_8bit.get(ops[0]);
					this.ir.op2 = this.reg_8bit.get(ops[1]);
				} else if (this.reg_8bit.containsKey(ops[0]) && check2[0].equalsIgnoreCase("")) {
					if (check2[1].length() == 2) {
						// TODO :)
					} else {
						this.ir.opcode = 16;
						this.ir.op1 = this.reg_8bit.get(ops[0]);
						this.ir.op2 = this.toDec(check2[1]);
					}
				} else if (this.reg_8bit.containsKey(ops[0])) {
					this.ir.opcode = 18;
					this.ir.op1 = this.reg_8bit.get(ops[0]);
					this.ir.op2 = this.toDec(ops[1]);
				} else {
					this.ir.opcode = 15;
					this.ir.op1 = this.reg_16bit.get(ops[0]);
					this.ir.op2 = this.reg_16bit.get(ops[1]);
				}

				break;
			case "ADD":
				// Suma
				// TODO reemplazar valores de otros registros
				// A, B ---- A,32H------ A,(HL) ----------- A, (2080H)
				ops = line[1].split(",");
				check = ops[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 2;
						this.ir.op1 = this.reg_8bit.get("A");
						this.ir.op2 = this.toDec(check[1]);
					}

				} else if (this.reg_8bit.containsKey(ops[1])) {
					// EL operando es un registro
					this.ir.opcode = 0;
					this.ir.op1 = this.reg_8bit.get("A");
					this.ir.op2 = this.reg_8bit.get(ops[1]);
				} else {
					// El opernado es un valor hexadecimal
					this.ir.opcode = 1;
					this.ir.op1 = this.reg_8bit.get("A");
					this.ir.op2 = this.toDec(ops[1]);
				}
				break;
			case "SUB":
				// Resta
				// TODO reemplazar valores de otros registros
				// A, B ---- A,32H------ A,(HL) ----------- A, (2080H)
				check = line[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 5;
						this.ir.op2 = this.toDec(check[1]);
					}

				} else if (this.reg_8bit.containsKey(line[1])) {
					// EL operando es un registro
					this.ir.opcode = 3;
					this.ir.op2 = this.reg_8bit.get(line[1]);
				} else {
					// El opernado es un valor hexadecimal
					this.ir.opcode = 4;
					this.ir.op1 = this.reg_8bit.get("A");
					this.ir.op2 = this.toDec(line[1]);
				}
				break;
			case "INC":
				// INC B------------ INC BC ??
				// Incremento
				// TODO reemplazar valores de otros registros
				// A, B ---- A,32H------ A,(HL) ----------- A, (2080H)
				check = line[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 8;
						this.ir.op2 = this.toDec(check[1]);
					}

				} else {
					if (this.reg_8bit.containsKey(line[1])) {
						this.ir.opcode = 6;
						this.ir.op2 = this.reg_8bit.get(line[1]);
					} else {
						this.ir.opcode = 7;
						this.ir.op2 = this.reg_16bit.get(line[1]);
					}
				}
				break;
			case "DEC":
				// decremento
				// TODO reemplazar valores de otros registros
				// A, B ---- A,32H------ A,(HL) ----------- A, (2080H)
				check = line[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 11;
						this.ir.op2 = this.toDec(check[1]);
					}

				} else {
					if (this.reg_8bit.containsKey(line[1])) {
						this.ir.opcode = 9;
						this.ir.op2 = this.reg_8bit.get(line[1]);
					} else {
						this.ir.opcode = 10;
						this.ir.op2 = this.reg_16bit.get(line[1]);
					}
				}
				break;
			case "CPL":
				// complemento
				this.ir.opcode = 12;
				break;
			case "NEG":
				// complement2
				this.ir.opcode = 13;
				break;
			case "AND":
				// and
				// TODO reemplazar valores de otros registros
				// AND A
				check = line[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 24;
						this.ir.op1 = this.reg_8bit.get("A");
						this.ir.op2 = this.toDec(check[1]);
					}

				} else if (this.reg_8bit.containsKey(line[1])) {
					// EL operando es un registro
					this.ir.opcode = 23;
					this.ir.op1 = this.reg_8bit.get("A");
					this.ir.op2 = this.reg_8bit.get(line[1]);
				} else {
					// El opernado es un valor hexadecimal
					this.ir.opcode = 25;
					this.ir.op1 = this.reg_8bit.get("A");
					this.ir.op2 = this.toDec(line[1]);
				}
				break;
			case "OR":
				// TODO reemplazar valores de otros registros
				// AND A
				check = line[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 27;
						this.ir.op1 = this.reg_8bit.get("A");
						this.ir.op2 = this.toDec(check[1]);
					}

				} else if (this.reg_8bit.containsKey(line[1])) {
					// EL operando es un registro
					this.ir.opcode = 26;
					this.ir.op1 = this.reg_8bit.get("A");
					this.ir.op2 = this.reg_8bit.get(line[1]);
				} else {
					// El opernado es un valor hexadecimal
					this.ir.opcode = 28;
					this.ir.op1 = this.reg_8bit.get("A");
					this.ir.op2 = this.toDec(line[1]);
				}
				break;
			case "XOR":
				// TODO reemplazar valores de otros registros
				// AND A
				check = line[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 30;
						this.ir.op1 = this.reg_8bit.get("A");
						this.ir.op2 = this.toDec(check[1]);
					}

				} else if (this.reg_8bit.containsKey(line[1])) {
					// EL operando es un registro
					this.ir.opcode = 29;
					this.ir.op1 = this.reg_8bit.get("A");
					this.ir.op2 = this.reg_8bit.get(line[1]);
				} else {
					// El opernado es un valor hexadecimal
					this.ir.opcode = 31;
					this.ir.op1 = this.reg_8bit.get("A");
					this.ir.op2 = this.toDec(line[1]);
				}
				break;
			case "CP":
				// comparar
				// TODO reemplazar valores de otros registros
				// AND A
				check = line[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 34;
						this.ir.op1 = this.reg_8bit.get("A");
						this.ir.op2 = this.toDec(check[1]);
					}

				} else if (this.reg_8bit.containsKey(line[1])) {
					// EL operando es un registro
					this.ir.opcode = 32;
					this.ir.op1 = this.reg_8bit.get("A");
					this.ir.op2 = this.reg_8bit.get(line[1]);
				} else {
					// El opernado es un valor hexadecimal
					this.ir.opcode = 33;
					this.ir.op1 = this.reg_8bit.get("A");
					this.ir.op2 = this.toDec(line[1]);
				}
				break;
			case "RL":
				// Rotacion izquierda
				// TODO reemplazar valores de otros registros
				// A, B ---- A,32H------ A,(HL) ----------- A, (2080H)
				check = line[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 40;
						this.ir.op2 = this.toDec(check[1]);
					}

				} else {
					if (this.reg_8bit.containsKey(line[1])) {
						this.ir.opcode = 38;
						this.ir.op2 = this.reg_8bit.get(line[1]);
					} else {
						this.ir.opcode = 39;
						this.ir.op2 = this.reg_16bit.get(line[1]);
					}
				}
				break;
			case "RR":
				// Roacion derecha
				// TODO reemplazar valores de otros registros
				// A, B ---- A,32H------ A,(HL) ----------- A, (2080H)
				check = line[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 37;
						this.ir.op2 = this.toDec(check[1]);
					}

				} else {
					if (this.reg_8bit.containsKey(line[1])) {
						this.ir.opcode = 35;
						this.ir.op2 = this.reg_8bit.get(line[1]);
					} else {
						this.ir.opcode = 36;
						this.ir.op2 = this.reg_16bit.get(line[1]);
					}
				}
				break;
			case "BIT":
				// TODO reemplazar valores de otros registros
				// A, B ---- A,32H------ A,(HL) ----------- A, (2080H)
				ops = line[1].split(",");
				check = ops[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 42;
						this.ir.op1 = this.reg_8bit.get("A");
						this.ir.op2 = this.toDec(check[1]);
					}

				} else {
					// EL operando es un registro
					this.ir.opcode = 41;
					this.ir.op2 = this.reg_8bit.get(ops[1]);
				}
				break;
			case "SET":
				// Poner bit 1
				// TODO reemplazar valores de otros registros
				// A, B ---- A,32H------ A,(HL) ----------- A, (2080H)
				ops = line[1].split(",");
				check = ops[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 44;
						this.ir.op1 = this.reg_8bit.get("A");
						this.ir.op2 = this.toDec(check[1]);
					}

				} else {
					// EL operando es un registro
					this.ir.opcode = 43;
					this.ir.op2 = this.reg_8bit.get(ops[1]);
				}
				break;
			case "RESET":
				// Poner bit en 0
				// TODO reemplazar valores de otros registros
				// A, B ---- A,32H------ A,(HL) ----------- A, (2080H)
				ops = line[1].split(",");
				check = ops[1].split("[()]");
				if (check[0].equalsIgnoreCase("")) {
					// El operando apunta a memoria check[1]
					if (check[1].length() == 2) {
						// this.ir.opcode = ???;
						// this.ir.op1 = ; H
						// this.ir.op2 = ; L
						// TODO :)
					} else {
						this.ir.opcode = 46;
						this.ir.op1 = this.reg_8bit.get("A");
						this.ir.op2 = this.toDec(check[1]);
					}

				} else {
					// EL operando es un registro
					this.ir.opcode = 45;
					this.ir.op2 = this.reg_8bit.get(ops[1]);
				}
				break;
			case "OUT":
				// Mostrar acumulador por puerto definid
				this.ir.opcode = 22;
				break;
			case "CALL":
				// llamada a subrutina
				no_op = true;
				break;
			case "HALT":
				// Fin de programa
				this.ir.opcode = -1;
				break;
			case "END":
				// Fin de ensamblado
				end = true;
				break;
			case "ORG":
				// Localidad en memoria de inicio de programa
				this.ir.opcode = 50;
				this.orgPointer = this.toDec(line[1]);
				this.ir.op2 = this.orgPointer;
				break;
			case "EQU":
				// Definir puerto de entrada
				no_op = true;
				break;
			case "JP":
				// 2 operandos JP C, dir JP Z, dir ------ JP dir
				ops = line[1].split(",");
				if (ops[0].equalsIgnoreCase("C")) {
					this.ir.opcode = 47;
					if (this.labels.containsKey(ops[1])) {
						this.ir.op2 = this.labels.get(ops[1]) + this.orgPointer;
					} else {
						this.ir.op2 = this.toDec(ops[1]);
					}
				} else if (ops[0].equalsIgnoreCase("Z")) {
					this.ir.opcode = 48;
					if (this.labels.containsKey(ops[1])) {
						this.ir.op2 = this.labels.get(ops[1]) + this.orgPointer;
					} else {
						this.ir.op2 = this.toDec(ops[1]);
					}
				} else {
					this.ir.opcode = 49;
					if (this.labels.containsKey(ops[0])) {
						this.ir.op2 = this.labels.get(ops[0]) + this.orgPointer;
					} else {
						this.ir.op2 = this.toDec(ops[0]);
					}
				}
				break;
			default:
				System.err.println("no opcode found: " + line[0]);
				// Terminar programa
				System.exit(0);
				no_op = true;
			}
			if (!end && !no_op) {
				instruction = ir.encodeInstruction();
				program[this.memPointer++] = instruction[0];
				program[this.memPointer++] = instruction[1];
				program[this.memPointer++] = instruction[2];
				program[this.memPointer++] = instruction[3];
				program[this.memPointer++] = instruction[4];
				if (this.ir.opcode == 50) {
					this.memPointer = this.ir.op2;
				}
			}
			System.out.println(this.ir + " " + input + " " + this.memPointer);
			setOrgPointer(this.orgPointer);
			programList.add(this.ir.encodeInstruction());
		}

		return programList;
	}

	public int getOrgPointer() {
		return orgPointer;
	}

	public void setOrgPointer(int orgPointer) {
		this.orgPointer = orgPointer;
	}
}
