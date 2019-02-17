import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Port {
    private int adressbus;
    private int databus;
    private boolean[] inputports;
    private boolean[] outputports;

    Port(){
        this.adressbus = (short) 0;
        this.databus = (byte) 0;
        this.inputports = new boolean[7];
        this.inputports = new boolean[4];

    }
    public void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("databus input");
        String input = br.readLine();
        this.databus = Integer.parseInt(input, 2);
        br.close();
    }
    public int getDatabus() throws IOException{
        this.input();
        return databus;
    }
    public void setDatabus(int data){
        this.databus = data;
    }

}
