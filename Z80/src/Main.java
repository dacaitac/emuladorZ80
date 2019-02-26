

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import GUI.MainGui;
import z80.Assembler;
import z80.LinkerLoader;
import z80.Memory;
import z80.Processor;

public class Main {
	public static Memory memory;
	static LinkerLoader linkerLoader;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("Main");
		MainGui main = new MainGui();
		main.setVisible(true);
		main.setBounds(200,200,940,650);
		
	}
}
