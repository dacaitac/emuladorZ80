import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;

public class Procesador {
    static final int A =0;
    static final int B =1;
    static final int C =2;
    static final int D =3;
    static final int E =4;
    static final int H =5;
    static final int L =6;
    //complementarios
    static final int AC =0;
    static final int BC =1;
    static final int CC =2;
    static final int DC =3;
    static final int EC =4;
    static final int HC =5;
    static final int LC =6;
    // 16 bits
    public final int PC =0;
    public final int IX =1;
    public final int IY =2;
    //registros
    public  int[] reg_8,reg_16,reg_esp;
    //flags
    public final int  Ca=0;//carry
    public final int  N=1;//add/substract
    public final int  PV=2;//parity/overflow
    public final int  Ha=4;//Half carry
    public final int  Z=6;//Zero
    public final int  S=7;//sign
    public int[] F;


    public int desicion;
    public int target;
    public int target2;

    //fin de ejecucion
    public boolean halt=false;
    //memoria
    public ArrayList<Integer> mem;
    public Port port;

    public Procesador(int[]data){
        for (int x:data) {
            mem.add(x);
        }
        this.reg_8 = new int[14];
        this.reg_16 =  new int[4];
        this.F = new int[7];
        this.port= new Port();

    }

    public void run(){
        while(halt=!true){
            this.execute(desicion, target,target2);
        }

    }

    public void execute(int desicion, int target,int target2){
        //TODO fetch
        int output;
        switch (desicion){// fin del programa
            case -1:
                halt=true;
                break;
            //add
                //memoria
            case 24:
                output= ALU.suma(reg_8[A],mem.get(target));
                if(output<0){F[S]=0;};
                break;
                //registro
            case 25:
                output= ALU.suma(reg_8[A],reg_8[target]);
                if(output<0){F[S]=0;};
                break;
                //numero
            case 26:
                output= ALU.suma(reg_8[A],target);
                if(output<0){F[S]=0;};
                break;

            //sub
                //memoria
            case 27:
                output= ALU.resta(reg_8[A],mem.get(target));
                if(output<0){F[S]=0;};
                break;
                //registro
            case 28:
                output= ALU.resta(reg_8[A],reg_8[target]);
                if(output<0){F[S]=0;};
                break;
                //numero
            case 29:
                output= ALU.resta(reg_8[A],target);
                if(output<0){F[S]=0;};
                break;
            //and
            case 3:
                output= ALU.and(this.reg_8[A],mem.get(target));
                break;
            //registros
            case 4:
                output= ALU.and(reg_8[A], reg_8[target]);
                break;
            //or
                //memoria
            case 1:
                output= ALU.or(this.reg_8[A],mem.get(target));
                break;
                //registros
            case 2:
                output= ALU.or(reg_8[A], reg_8[target]);
                break;
            //xor
            //memoria
            case 5:
                output= ALU.xor(this.reg_8[A],mem.get(target));
                break;
            //registros
            case 6:
                output= ALU.xor(reg_8[A], reg_8[target]);
                break;
            //cp
                //registros
            case 7:
                if(reg_8[A]<reg_8[target]){F[S]=1;F[Z]=0;F[Ha]=0;F[N]=1;F[PV]=1;F[Ca]=1;}
                else if (reg_8[A]==reg_8[target]){F[Z]=1;F[S]=0;F[Ha]=0;F[N]=1;F[PV]=0;F[Ca]=0;}
                else{F[S]=0;F[Z]=0;F[Ha]=0;F[N]=1;F[PV]=0;F[Ca]=0;}
                break;
                //memoria
            case 8:
                if(reg_8[A]<mem.get(target)){F[S]=1;F[Z]=0;F[Ha]=0;F[N]=1;F[PV]=1;F[Ca]=1;}
                else if (reg_8[A]==mem.get(target)){F[Z]=1;F[S]=0;F[Ha]=0;F[N]=1;F[PV]=0;F[Ca]=0;}
                else{F[S]=0;F[Z]=0;F[Ha]=0;F[N]=1;F[PV]=0;F[Ca]=0;}
                break;
            //inc
                //registro
            case 9:
                F[N]=0;
                if(reg_8[target]<0){output=reg_8[target]++;F[S]=1;}
                else if(reg_8[target]==127){output=reg_8[target]++;F[PV]=1;}
                break;
               //memoria
            case 10:
                F[N]=0;
                if(mem.get(target)<0){output=mem.get(target)+1;F[S]=1;}
                else if(mem.get(target)==127){output=mem.get(target)+1;F[PV]=1;}
                break;
            //dec
                //registro
            case 11:
                F[N]=0;
                if(reg_8[target]<0){output=reg_8[target]--;F[S]=1;}
                else if(reg_8[target]==127){output=reg_8[target]++;F[PV]=1;}
                break;
                //memoria
            case 12:
                F[N]=0;
                if(mem.get(target)<0){output=mem.get(target)-1;F[S]=1;}
                else if(mem.get(target)==127){output=mem.get(target)+1;F[PV]=1;}
                break;
            //cpl
            case 13:
                F[Ha]=1;F[N]=1;
                output= ALU.c1(reg_8[A]);
                break;
            //neg
            case 14:
                F[N]=1;
                if(reg_8[A]==0){F[Ca]=1;}
                if(reg_8[A]==128){F[PV]=1;}
                output=ALU.c2(reg_8[A]);
                if(output<0){F[S]=1;}
                if(output==0){F[Z]=1;}
                break;

            // ccf
            case 15:
                F[Ha]=F[Ca];
                F[Ca]=~F[Ca];
                break;
            //scf
            case 16:
                F[Ca]=1;
                break;


            //ex
//TODO ex
            //rlca
            case 17:
                output=ALU.sr(reg_8[A]);
                reg_8[A]=output;
                F[Ca]=Character.getNumericValue(ALU.getCarry());
                break;
            //rla
            case 18:{
                //char[] auxc= Integer.toBinaryString(reg_8[A]).toCharArray();
                String aux =Integer.toBinaryString(reg_8[A]);
                char c  = aux.charAt(aux.length()-1);
                String rotado= c+aux.substring(0,aux.length()-1);
                reg_8[A]=output=Integer.parseInt(rotado,2);}
                break;
             //rrca
            case 19:
                output=ALU.sl(reg_8[A]);
                reg_8[A]=output;
                F[Ca]=Character.getNumericValue(ALU.getCarry());
                break;
            //rra
            case 20:{
                String aux =Integer.toBinaryString(reg_8[A]);
                char c = aux.charAt(0);
                String rotado= aux.substring(1)+c;
                reg_8[A]=output=Integer.parseInt(rotado,2);}
                break;
            //rlc
            case 21:
                output=ALU.sr(reg_8[target]);
                reg_8[target]=output;
                F[Ca]=Character.getNumericValue(ALU.getCarry());
                break;
            //rrc
            case 22:
                output=ALU.sl(reg_8[target]);
                reg_8[target]=output;
                F[Ca]=Character.getNumericValue(ALU.getCarry());
                break;

            //bit
            case 23:
                //TODO definir como va a ser la entrada de datos
                break;
            //set
            //reset

            //jp
            case 30:
                if (reg_8[A] == 0){
                    reg_16[PC] = target;
                }
                break;
            case 31:
                if (reg_8[A] > 0){
                    reg_16[PC] = target;}
                    break;

            //ld
                //registro numero
            case 33:
                reg_8[target]=reg_8[target2];
                break;
                //registro memoria
            case 34:
                reg_8[target]=mem.get(target2);
                break;
                //registro registro
            case 32:

            //push
            //pop





        }

    }

}
