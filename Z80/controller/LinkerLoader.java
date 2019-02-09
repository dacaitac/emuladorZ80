package controller;

import Machine.Memory;

public class LinkerLoader {
	Memory memory;	
	Util util;	
	
	public Memory loadCode(String binaryCode, int position) {
		memory = new Memory();
		String[] n = binaryCode.split("\r\n|\r|\n");		
		String[] fields = new String[n.length];
		for(int i = 0; i < n.length; i++) fields[i] = Integer.toHexString(position++).toUpperCase();		
		for(int i = 0; i < n.length; i++) memory.loadMemory(fields[i], n[i]);
		
		return memory;		
	}
	
}
