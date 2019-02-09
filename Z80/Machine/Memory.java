package Machine;
import java.util.HashMap;
import controller.*;

public class Memory {
	private HashMap<String, String > memory;
	int memorySize = (int) Math.pow(2, 16);
	private Util util = new Util();
	
	public Memory() {
		String bin = "00000000000000000000000000000000";
		memory = new HashMap<String, String>();
		for (int i = 0; i < memorySize; i++) {
			memory.put(Integer.toHexString(i).toUpperCase(), bin);
		}
		
	}

	public int getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}

	public HashMap<String, String > getMemory() {
		return memory;
	}

	public void setMemory(HashMap<String, String > memory) {
		this.memory = memory;
	}	
	
	public String getInstr( String positionMemory ) {
		return memory.get(positionMemory);
	}
	
	public void loadMemory(String positionMemory, String instr ) {
		this.memory.put(positionMemory, instr);		
	}
	
}
