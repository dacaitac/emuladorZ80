public class ALU {
    static char carry;
    public static char getCarry(){return carry;}
    public static int and(int input, int input2){ return input&input2;}
    public static int or(int input, int input2){ return input|input2;}
    public static int xor(int input, int input2){ return input ^ input2;}
    public static int c1(int input){return ~input;}
    public static int sl(int input){
        String aux = Integer.toBinaryString(input);
        carry=aux.charAt(0);
        int aux2=Integer.parseInt(aux+carry,2);
        return aux2 <<1;
    }
    public static int sr(int input){
        String aux = Integer.toBinaryString(input);
        carry=aux.charAt(aux.length()-1);
        int aux2=Integer.parseInt(carry+aux,2);
        return aux2 >>1;
    }
    public static int c2(int input){
        int aux = c1(input);
        return aux+1;
    }

    public static int bit(int input, int pos){//carry =z
        String aux = Integer.toBinaryString(input);
        if(aux.charAt(pos)==1){return 1;}
        else return 0;
    }
    public static int set(int input, int pos){
        char[] aux = Integer.toBinaryString(input).toCharArray();
        aux[pos]=1;
        return Integer.parseInt(aux.toString());
    }
    public static int reset(int input, int pos){
        char[] aux = Integer.toBinaryString(input).toCharArray();
        aux[pos]=1;
        return Integer.parseInt(aux.toString());
    }
    public static int suma(int input,int pos){return input+pos;}
    public static int resta(int input,int pos){return input-pos;}


}
