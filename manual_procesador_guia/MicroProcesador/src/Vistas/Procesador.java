package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Procesador {
	private JFrame frame;
	private String Memoria[] = null;
	private String[] MemoriaRam;
	boolean halt = false;
	boolean IN = true;
	boolean OUT = true;
	boolean InTexto;
	boolean OutTexto;
	int AddressIN;
	int AddressOUT;
	static int pcIn; //private int Time;
	//Program Counter
	int PC;
	//Stack Pointer
	String SP;	
	//Registers
	//General purpose records
	//Main Bank 8 bits
	String A, B, C, D, E, H, L;
	//Alternative Bank 8 bits
	String Bp, Cp, Dp, Ep, Hp, Lp;
	//Registers of indexes 16 bits
	String IX, IY;
	//Interrupt registers 8 bits
	String I;
	//Memory refresh record 8 bits
	String R;
	//Flags Sign, Zero, Auxiliary and carry, carry 8 bits
	String F;
	//State flags
	//Alternative accumulator and alternative flags 8 bits
	String Ap;
	String Fp;
	//Carry flag
	static char carry,S,Z; //Banderas
	static String Aux;
	static String Aux1;
	static String Aux2;
	Procesador Interfaz;
	public static boolean carryFlag;
	public boolean bit8;
	
	static String[] mem;
	private JTextField txtHello;
	private JTextField TextosS;

	public Procesador() {
		Memoria = mem;
		PC = pcIn;
		SP  = "0000";
		A = "00";
		B = "00";
		C = "00";
		D = "00";
		E = "00";
		H = "00";
		L = "00";
		Ap = "00";
		Bp = "00";
		Fp = "00";
		Cp = "00";
		Dp = "00";
		Ep = "00";
		Hp = "00";
		Lp = "00";
		IX = "0000";
		IY = "0000";
		F = "00";
		R = "00";
		I = "00";
		carryFlag = false;
		carry = '0';
		S = '0';
		Z = '0';				
		bit8=false;
		IN = true;
		OUT = true;
		halt = false;
		OutTexto = false;
		InTexto = false;
		AddressIN = 0;
		AddressOUT = 0;
		MemoriaRam = new String[256];
		for(int i=0;i<MemoriaRam.length;i++) {
			MemoriaRam[i] = "00";
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("SansSerif", Font.BOLD, 15));
		frame.setBounds(100, 100, 1350, 700);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("EMULACIÓN DEL PROCESADOR Z80");
		title.setFont(new Font("Dialog", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.BOTTOM);
		title.setBounds(12, 12, frame.getWidth()-220, 45);
		frame.getContentPane().add(title);
		
		JLabel name = new JLabel("PROCESADOR");
		name.setFont(new Font("Dialog", Font.BOLD, 25));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setVerticalAlignment(SwingConstants.BOTTOM);
		name.setBounds(12, 60, frame.getWidth()-220, 30);
		frame.getContentPane().add(name);
		
		ImageIcon image = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/EScudoUnal.png"); 	
		
		JLabel img = new JLabel(" "); 
		frame.getContentPane().add(img);
		
		img.setIcon(image); 
		img.setSize(260,260); 
		img.setLocation(1088,20); 
		
		JButton btnEnsamblador = new JButton("Inicio");
		btnEnsamblador.setBackground(Color.WHITE);
		btnEnsamblador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Init.setVisible(true);
				
			}
		});
		btnEnsamblador.setBounds(1116, 305, 200, 45);
		btnEnsamblador.setFocusable(false);
		frame.getContentPane().add(btnEnsamblador);
		
		JLabel labelPro = new JLabel("PROCESADOR");
		labelPro.setForeground(Color.WHITE);
		labelPro.setFont(new Font("Dialog", Font.BOLD, 18));
		labelPro.setHorizontalAlignment(SwingConstants.CENTER);
		labelPro.setBounds(1116, 300, 200, 200);
		frame.getContentPane().add(labelPro);
		
		JTextPane txtpnHolaComoEstas = new JTextPane();
		txtpnHolaComoEstas.setFont(new Font("Dialog", Font.BOLD, 14));
		txtpnHolaComoEstas.setForeground(new Color(88,89,91));
		txtpnHolaComoEstas.setText("Esta parte del programa se encarga de procesar el programa, cargando datos en memoria y registros, "
				+ " permitiendole realizar varias operaciones como las aritmeticas, logicas entre otras mas, como se ve en esta simulacion.");
		txtpnHolaComoEstas.setBounds(1116, 430, 200, 210);
		txtpnHolaComoEstas.setEditable(false);
		frame.getContentPane().add(txtpnHolaComoEstas);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(88,89,91));
		panel.setBounds(1088, 25, 260, 655);
		frame.getContentPane().add(panel);
		
		JLabel lblBank = new JLabel("Banco de Registros");
		lblBank.setFont(new Font("Dialog", Font.BOLD, 16));
		lblBank.setBounds(35, 125, 200, 17);
		lblBank.setForeground(Color.WHITE);
		frame.getContentPane().add(lblBank);
		
		//////////////////////////Flags////////////////////////////////
		JLabel lblFlags = new JLabel("Carry");
		lblFlags.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFlags.setBounds(40, 162, 70, 17);
		lblFlags.setForeground(Color.WHITE);
		frame.getContentPane().add(lblFlags);
		
		JTextPane FlagCarry = new JTextPane();
		FlagCarry.setText("0");
		FlagCarry.setEditable(false);
		FlagCarry.setBounds(90, 160, 20, 21);
		frame.getContentPane().add(FlagCarry);
		
		JLabel labelZ = new JLabel("Z");
		labelZ.setForeground(Color.WHITE);
		labelZ.setFont(new Font("Dialog", Font.BOLD, 16));
		labelZ.setBounds(120, 162, 70, 17);
		frame.getContentPane().add(labelZ);
		
		JTextPane FlagZ = new JTextPane();
		FlagZ.setText("0");
		FlagZ.setEditable(false);
		FlagZ.setBounds(135, 160, 20, 21);
		frame.getContentPane().add(FlagZ);
		
		JLabel lblS = new JLabel("S");
		lblS.setForeground(Color.WHITE);
		lblS.setFont(new Font("Dialog", Font.BOLD, 16));
		lblS.setBounds(165, 162, 70, 17);
		frame.getContentPane().add(lblS);
		
		JTextPane FlagS = new JTextPane();
		FlagS.setText("0");
		FlagS.setBounds(180, 160, 20, 21);
		FlagS.setEditable(false);
		frame.getContentPane().add(FlagS);
		
		/////////////////////Registers//////////////////////////
		
		JLabel lblAF = new JLabel("AF");
		lblAF.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAF.setBounds(40, 202, 70, 16);
		lblAF.setForeground(Color.WHITE);
		frame.getContentPane().add(lblAF);
		
		JTextPane textA = new JTextPane();
		textA.setText("00");
		textA.setBounds(70, 198, 20, 21);
		textA.setEditable(false);
		frame.getContentPane().add(textA);
		
		JTextPane textF = new JTextPane();
		textF.setText("00");
		textF.setBounds(90, 198, 20, 21);
		textF.setEditable(false);
		frame.getContentPane().add(textF);
		
		JLabel lblAf = new JLabel("AF'");
		lblAf.setBounds(130, 202, 70, 16);
		lblAf.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAf.setForeground(Color.WHITE);
		frame.getContentPane().add(lblAf);
		
		JTextPane textAp = new JTextPane();
		textAp.setText("00");
		textAp.setBounds(160, 198, 20, 21);
		textAp.setEditable(false);
		frame.getContentPane().add(textAp);
		
		JTextPane textFp = new JTextPane();
		textFp.setText("00");
		textFp.setBounds(180, 198, 20, 21);
		textFp.setEditable(false);
		frame.getContentPane().add(textFp);
		
		JLabel lblHL = new JLabel("HL");
		lblHL.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHL.setBounds(40, 232, 70, 16);
		lblHL.setForeground(Color.WHITE);
		frame.getContentPane().add(lblHL);
		
		JTextPane textH = new JTextPane();
		textH.setText("00");
		textH.setBounds(70, 228, 20, 21);
		textH.setEditable(false);
		frame.getContentPane().add(textH);
		
		JTextPane textL = new JTextPane();
		textL.setText("00");
		textL.setBounds(90, 228, 20, 21);
		textL.setEditable(false);
		frame.getContentPane().add(textL);
		
		JLabel lblHl = new JLabel("HL'");
		lblHl.setBounds(130, 232, 70, 16);
		lblHl.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHl.setForeground(Color.WHITE);
		frame.getContentPane().add(lblHl);
		
		JTextPane textHp = new JTextPane();
		textHp.setText("00");
		textHp.setBounds(160, 228, 20, 21);
		textHp.setEditable(false);
		frame.getContentPane().add(textHp);
		
		JTextPane textLp = new JTextPane();
		textLp.setText("00");
		textLp.setBounds(180, 228, 20, 21);
		textLp.setEditable(false);
		frame.getContentPane().add(textLp);
		
		JLabel lblDE = new JLabel("DE");
		lblDE.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDE.setBounds(40, 262, 70, 16);
		lblDE.setForeground(Color.WHITE);
		frame.getContentPane().add(lblDE);
		
		JTextPane textD = new JTextPane();
		textD.setText("00");
		textD.setBounds(70, 258, 20, 21);
		textD.setEditable(false);
		frame.getContentPane().add(textD);
		
		JTextPane textE = new JTextPane();
		textE.setText("00");
		textE.setBounds(90, 258, 20, 21);
		textE.setEditable(false);
		frame.getContentPane().add(textE);
		
		JLabel lblDe = new JLabel("DE'");
		lblDe.setBounds(130, 262, 70, 16);
		lblDe.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDe.setForeground(Color.WHITE);
		frame.getContentPane().add(lblDe);
		
		JTextPane textDp = new JTextPane();
		textDp.setText("00");
		textDp.setBounds(160, 258, 20, 21);
		textDp.setEditable(false);
		frame.getContentPane().add(textDp);
		
		JTextPane textEp = new JTextPane();
		textEp.setText("00");
		textEp.setBounds(180, 258, 20, 21);
		textEp.setEditable(false);
		frame.getContentPane().add(textEp);
		
		JLabel lblBC = new JLabel("BC");
		lblBC.setFont(new Font("Dialog", Font.BOLD, 16));
		lblBC.setBounds(40, 292, 70, 16);
		lblBC.setForeground(Color.WHITE);
		frame.getContentPane().add(lblBC);
		
		JTextPane textB = new JTextPane();
		textB.setText("00");
		textB.setBounds(70, 288, 20, 21);
		textB.setEditable(false);
		frame.getContentPane().add(textB);
		
		JTextPane textC = new JTextPane();
		textC.setText("00");
		textC.setBounds(90, 288, 20, 21);
		textC.setEditable(false);
		frame.getContentPane().add(textC);
		
		JLabel lblBc = new JLabel("BC'");
		lblBc.setBounds(130, 292, 70, 16);
		lblBc.setFont(new Font("Dialog", Font.BOLD, 16));
		lblBc.setForeground(Color.WHITE);
		frame.getContentPane().add(lblBc);
		
		JTextPane textBp = new JTextPane();
		textBp.setText("00");
		textBp.setBounds(160, 288, 20, 21);
		textBp.setEditable(false);
		frame.getContentPane().add(textBp);
		
		JTextPane textCp = new JTextPane();
		textCp.setText("00");
		textCp.setBounds(180, 288, 20, 21);
		textCp.setEditable(false);
		frame.getContentPane().add(textCp);
		
		JLabel lblIX = new JLabel("IX");
		lblIX.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIX.setBounds(40, 322, 70, 16);
		lblIX.setForeground(Color.WHITE);
		frame.getContentPane().add(lblIX);
		
		JTextPane textIX = new JTextPane();
		textIX.setText("0000");
		textIX.setBounds(70, 318, 40, 21);
		textIX.setEditable(false);
		frame.getContentPane().add(textIX);
				
		JLabel lblIY = new JLabel("IY");
		lblIY.setBounds(130, 322, 70, 16);
		lblIY.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIY.setForeground(Color.WHITE);
		frame.getContentPane().add(lblIY);
		
		JTextPane textIY = new JTextPane();
		textIY.setText("0000");
		textIY.setBounds(160, 318, 40, 21);
		textIY.setEditable(false);
		frame.getContentPane().add(textIY);
		
		JLabel lblSP = new JLabel("SP");
		lblSP.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSP.setBounds(40, 352, 70, 16);
		lblSP.setForeground(Color.WHITE);
		frame.getContentPane().add(lblSP);
		
		JTextPane textSP = new JTextPane();
		textSP.setText("0000");
		textSP.setBounds(70, 348, 40, 21);
		textSP.setEditable(false);
		frame.getContentPane().add(textSP);
		
		JLabel lblPC = new JLabel("PC");
		lblPC.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPC.setBounds(40, 382, 70, 16);
		lblPC.setForeground(Color.WHITE);
		frame.getContentPane().add(lblPC);
		
		JTextPane textPC = new JTextPane();
		String pcAux = PC + "";
		pcAux = "";
		for(int i=0;i<(4-pcAux.length());i++) {
			pcAux += "0";
		}
		pcAux += PC +"";
		textPC.setText(pcAux);
		textPC.setBounds(70, 378, 40, 21);
		textPC.setEditable(false);
		frame.getContentPane().add(textPC);
		
		JLabel lblI = new JLabel("  I");
		lblI.setBounds(130, 352, 70, 16);
		lblI.setFont(new Font("Dialog", Font.BOLD, 16));
		lblI.setForeground(Color.WHITE);
		frame.getContentPane().add(lblI);
		
		JTextPane textI = new JTextPane();
		textI.setText("00");
		textI.setBounds(160, 348, 20, 21);
		textI.setEditable(false);
		frame.getContentPane().add(textI);
		
		JLabel lblR = new JLabel(" R");
		lblR.setBounds(130, 382, 70, 16);
		lblR.setFont(new Font("Dialog", Font.BOLD, 16));
		lblR.setForeground(Color.WHITE);
		frame.getContentPane().add(lblR);
		
		JTextPane textR = new JTextPane();
		textR.setText("00");
		textR.setBounds(160, 378, 20, 21);
		textR.setEditable(false);
		frame.getContentPane().add(textR);
		
		JLabel img15 = new JLabel(" "); 
		frame.getContentPane().add(img15);
		ImageIcon image15 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaUp.png"); 
		img15.setIcon(image15); 
		img15.setSize(62,96); 
		img15.setLocation(849,325);
		
		JLabel img14 = new JLabel(" "); 
		frame.getContentPane().add(img14);
		ImageIcon image14 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaUp.png"); 
		img14.setIcon(image14); 
		img14.setSize(62,96); 
		img14.setLocation(898,325);
		
		JLabel img13 = new JLabel(" "); 
		frame.getContentPane().add(img13);
		ImageIcon image13 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaUp.png"); 
		img13.setIcon(image13); 
		img13.setSize(62,96); 
		img13.setLocation(798,325);
		
		JLabel img12 = new JLabel(" "); 
		frame.getContentPane().add(img12);
		ImageIcon image12 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaI.png"); 
		img12.setIcon(image12); 
		img12.setSize(62,96); 
		img12.setLocation(516,380);
		
		JLabel img11 = new JLabel(" "); 
		frame.getContentPane().add(img11);
		ImageIcon image11 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaI.png"); 
		img11.setIcon(image11); 
		img11.setSize(62,96); 
		img11.setLocation(516,456);
		
		JLabel img4 = new JLabel(" "); 
		frame.getContentPane().add(img4);
		ImageIcon image4 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaI.png"); 
		img4.setIcon(image4); 
		img4.setSize(62,96); 
		img4.setLocation(206,457);
		
		JLabel img5 = new JLabel(" "); 
		frame.getContentPane().add(img5);
		ImageIcon image5 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaI.png"); 
		img5.setIcon(image5); 
		img5.setSize(62,96); 
		img5.setLocation(206,487);
		
		JLabel img6 = new JLabel(" "); 
		frame.getContentPane().add(img6);
		ImageIcon image6 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaI.png"); 
		img6.setIcon(image6); 
		img6.setSize(62,96); 
		img6.setLocation(206,517);
		
		JLabel img7 = new JLabel(" "); 
		frame.getContentPane().add(img7);
		ImageIcon image7 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaD.png"); 
		img7.setIcon(image7); 
		img7.setSize(62,96); 
		img7.setLocation(500,456);
		
		JLabel img10 = new JLabel(" "); 
		frame.getContentPane().add(img10);
		ImageIcon image10 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaD.png"); 
		img10.setIcon(image10); 
		img10.setSize(62,96); 
		img10.setLocation(860,516);
		
		JLabel img9 = new JLabel(" "); 
		frame.getContentPane().add(img9);
		ImageIcon image9 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaD.png"); 
		img9.setIcon(image9); 
		img9.setSize(62,96); 
		img9.setLocation(860,486);
		
		JLabel img8 = new JLabel(" "); 
		frame.getContentPane().add(img8);
		ImageIcon image8 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/FlechaD.png"); 
		img8.setIcon(image8); 
		img8.setSize(62,96); 
		img8.setLocation(860,456);
		
		/////////////////////////////MEMORIA/////////////////////
		JLabel lblMemoria = new JLabel("Memoria");
		lblMemoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemoria.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMemoria.setBounds(20, 420, 205, 17);
		lblMemoria.setForeground(Color.WHITE);
		frame.getContentPane().add(lblMemoria);
		
		JPanel panelList = new JPanel(); 
		panelList.setBackground(new Color(88,89,91));
		panelList.setBounds(20, 449, 200, 200);
		panelList.setLayout(null); 
		DefaultListModel<String> modelA = new DefaultListModel<String>();
		String modelo = "";
		int pc1 = 0;
		String indexM = "";
		String Aux = "";
		for(int i = 0; i<13107; i++){
			Aux = "";
			indexM = i*5 +"";
			for(int j = 0;j<(5-indexM.length());j++) {
				Aux += "0";
			}
			indexM = Aux + indexM + " ";
			modelo = "";
			modelo += indexM;
			for (int j = 0; j<5;j++) {
				modelo+= " " + Memoria[pc1];
				pc1++;
			}
			modelA.addElement(modelo);
		}
		JList<String> list = new JList<String>(modelA);
		list.setVisibleRowCount(20);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
		JScrollPane barraDesplazamiento = new JScrollPane(list); 
		
		barraDesplazamiento.setBackground(Color.white);
		barraDesplazamiento.setBounds(17,0,166,190); 
		panelList.add(barraDesplazamiento); 
		frame.getContentPane().add(panelList);
		frame.getContentPane().add(panelList);
		
		TextosS = new JTextField();
		TextosS.setBounds(754, 169, 283, 30);
		frame.getContentPane().add(TextosS);
		TextosS.setColumns(10);
		
		txtHello = new JTextField();
		txtHello.setHorizontalAlignment(SwingConstants.CENTER);
		txtHello.setFont(new Font("Dialog", Font.BOLD, 13));
		txtHello.setBounds(753, 168, 284, 196);
		frame.getContentPane().add(txtHello);
		txtHello.setColumns(10);
		
		JPanel panel_RAM = new JPanel();
		panel_RAM.setBackground(Color.DARK_GRAY);
		panel_RAM.setBounds(870, 470, 200, 122);
		panel_RAM.setLayout(null); 
		DefaultListModel<String> modelR = new DefaultListModel<String>();
		int Ram = 0;
		for(int i=0;i<42;i++) {
			Aux = "";
			indexM = i*6 +"";
			
			for(int j = 0;j<(3-indexM.length());j++) {
				Aux += "0";
			}
			indexM = Aux + "" + indexM;
			Aux = "";
			
			for(int j = 0;j<6;j++) {
				Aux += " " + MemoriaRam[Ram];
				Ram+=1;
			}
			modelR.addElement(indexM + " " + Aux);
		}
		
		Aux = "252 00 00 00 00";
		modelR.addElement(Aux);
		
		JList<String> listR = new JList<String>(modelR);
		listR.setVisibleRowCount(20);
		listR.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
		JScrollPane barraDesplazamientoR = new JScrollPane(listR); 
		barraDesplazamientoR.setBackground(Color.white);
		barraDesplazamientoR.setBounds(8,8,185,107); 
		panel_RAM.add(barraDesplazamientoR); 
		frame.getContentPane().add(panel_RAM);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(88,89,91));
		textArea.setBounds(500, 628, 300, 25);
		textArea.setFont(new Font("Dialog", Font.BOLD, 15));
		textArea.setForeground(Color.WHITE);
		textArea.setEditable(false);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Instruccion a Procesar");
		lblNewLabel.setBounds(240, 630, 300, 15);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(88,89,91));
		panel_1.setBounds(230, 620, 580, 35);
		frame.getContentPane().add(panel_1);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IN) {
					String aux = "";
					String Aux = "";
					String indexM = "";
					if(Memoria[PC].length()==1)aux+= "0" + Memoria[PC];
					else aux+= Memoria[PC];
					textArea.setText(aux);
					decode(aux);
					if(InTexto) {
						TextosS.setText("Ingrese Dato");
						InTexto = false;
					}
					if(OutTexto) {
						TextosS.setText("Respuesta");
						txtHello.setText("");
						txtHello.setEditable(false);
						txtHello.setText(Integer.parseInt(MemoriaRam[AddressOUT],16)+"");
						txtHello.setEditable(true);
						OutTexto = false;
					}
					aux = "";
					if(A.length()==1)aux+= "0" + A;
					else aux+= A;
					textA.setText(aux);
					aux = "";
					if(B.length()==1)aux+= "0" + B;
					else aux+= B;
					textB.setText(aux);
					aux = "";
					if(C.length()==1)aux+= "0" + C;
					else aux+= C;
					textC.setText(aux);
					aux = "";
					if(D.length()==1)aux+= "0" + D;
					else aux+= D;
					textD.setText(aux);
					aux = "";
					if(E.length()==1)aux+= "0" + E;
					else aux+= E;
					textE.setText(aux);
					aux = "";
					if(F.length()==1)aux+= "0" + F;
					else aux+= F;
					textF.setText(aux);
					aux = "";
					if(H.length()==1)aux+= "0" + H;
					else aux+= H;
					textH.setText(aux);
					aux = "";
					if(L.length()==1)aux+= "0" + L;
					else aux+= L;
					textH.setText(aux);
					aux = "";
					if(Ap.length()==1)aux+= "0" + Ap;
					else aux+= Ap;
					textAp.setText(aux);
					aux = "";
					if(Bp.length()==1)aux+= "0" + Bp;
					else aux+= Bp;
					textBp.setText(aux);
					aux = "";
					if(Cp.length()==1)aux+= "0" + Cp;
					else aux+= Cp;
					textCp.setText(aux);
					aux = "";
					if(Dp.length()==1)aux+= "0" + Dp;
					else aux+= Dp;
					textDp.setText(aux);
					aux = "";
					if(Ep.length()==1)aux+= "0" + Ep;
					else aux+= Ep;
					textEp.setText(aux);
					aux = "";
					if(Fp.length()==1)aux+= "0" + Fp;
					else aux+= Fp;
					textFp.setText(aux);
					aux = "";
					if(Hp.length()==1)aux+= "0" + Hp;
					else aux+= Hp;
					textHp.setText(aux);
					aux = "";
					if(Lp.length()==1)aux+= "0" + Lp;
					else aux+= Lp;
					textLp.setText(aux);
					aux = "";
					if(I.length()==1)aux+= "0" + I;
					else aux+= I;
					textI.setText(aux);
					aux = "";
					if(R.length()==1)aux+= "0" + R;
					else aux+= R;
					textR.setText(aux);
					aux = "";
					if(IX.length()==1)aux+= "000" + IX;
					if(IX.length()==2)aux+= "00" + IX;
					if(IX.length()==3)aux+= "0" + IX;
					if(IX.length()==4)aux+= "" + IX;
					textIX.setText(aux);
					aux = "";
					if(IY.length()==1)aux+= "000" + IY;
					if(IY.length()==2)aux+= "00" + IY;
					if(IY.length()==3)aux+= "0" + IY;
					if(IY.length()==4)aux+= "" + IY;
					textIX.setText(aux);
					aux = "";
					if(SP.length()==1)aux+= "000" + SP;
					if(SP.length()==2)aux+= "00" + SP;
					if(SP.length()==3)aux+= "0" + SP;
					if(SP.length()==4)aux+= "" + SP;
					textSP.setText(aux);
					aux = "";
					if(PC>999)aux= "" + PC;
					if(PC<1000)aux= "0" + PC;
					if(PC<100)aux= "00" + PC;
					if(PC<10)aux= "000" + PC;
					textPC.setText(aux);
					FlagCarry.setText("");
					FlagZ.setText("");
					FlagS.setText("");
					FlagZ.setText(Z+"");
					FlagS.setText(S+"");
					FlagCarry.setText(carry+"");
					DefaultListModel<String> modelR3 = new DefaultListModel<String>();
					int Ram = 0;
					for(int i=0;i<42;i++) {
						Aux = "";
						indexM = i*6 +"";
						
						for(int j = 0;j<(3-indexM.length());j++) {
							Aux += "0";
						}
						indexM = Aux + "" + indexM;
						Aux = "";
						
						for(int j = 0;j<6;j++) {
							Aux += " " + MemoriaRam[Ram];
							Ram+=1;
						}
						modelR3.addElement(indexM + " " + Aux);
					}
					listR.setModel(modelR3);
					PC+=1;
				}				
			}
		});
		btnProcesar.setBackground(new Color(88,89,91));
		btnProcesar.setForeground(Color.WHITE);
		btnProcesar.setBorder(null);
		btnProcesar.setFocusable(false);
		btnProcesar.setBounds(820, 620, 100, 35);
		frame.getContentPane().add(btnProcesar);
		
		
		JButton btnProcesarTodo = new JButton("Procesar Todo");
		btnProcesarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = "";
				String Aux = "";
				String indexM = "";
				if(IN) {
					while(halt==false) {
						aux = "";
						if(Memoria[PC].length()==1)aux+= "0" + Memoria[PC];
						else aux+= Memoria[PC];
						textArea.setText(aux);
						decode(aux);
						if(InTexto) {
							TextosS.setText("Ingrese Dato");
							InTexto = false;
						}
						if(OutTexto) {
							TextosS.setText("Respuesta");
							txtHello.setText("");
							txtHello.setEditable(false);
							txtHello.setText(Integer.parseInt(MemoriaRam[AddressOUT],16)+"");
							txtHello.setEditable(true);
							OutTexto = false;
						}
						PC+=1;
						if(!IN)break;
					}
					aux = "";
					if(A.length()==1)aux+= "0" + A;
					else aux+= A;
					textA.setText(aux);
					aux = "";
					if(B.length()==1)aux+= "0" + B;
					else aux+= B;
					textB.setText(aux);
					aux = "";
					if(C.length()==1)aux+= "0" + C;
					else aux+= C;
					textC.setText(aux);
					aux = "";
					if(D.length()==1)aux+= "0" + D;
					else aux+= D;
					textD.setText(aux);
					aux = "";
					if(E.length()==1)aux+= "0" + E;
					else aux+= E;
					textE.setText(aux);
					aux = "";
					if(F.length()==1)aux+= "0" + F;
					else aux+= F;
					textF.setText(aux);
					aux = "";
					if(H.length()==1)aux+= "0" + H;
					else aux+= H;
					textH.setText(aux);
					aux = "";
					if(L.length()==1)aux+= "0" + L;
					else aux+= L;
					textH.setText(aux);
					aux = "";
					if(Ap.length()==1)aux+= "0" + Ap;
					else aux+= Ap;
					textAp.setText(aux);
					aux = "";
					if(Bp.length()==1)aux+= "0" + Bp;
					else aux+= Bp;
					textBp.setText(aux);
					aux = "";
					if(Cp.length()==1)aux+= "0" + Cp;
					else aux+= Cp;
					textCp.setText(aux);
					aux = "";
					if(Dp.length()==1)aux+= "0" + Dp;
					else aux+= Dp;
					textDp.setText(aux);
					aux = "";
					if(Ep.length()==1)aux+= "0" + Ep;
					else aux+= Ep;
					textEp.setText(aux);
					aux = "";
					if(Fp.length()==1)aux+= "0" + Fp;
					else aux+= Fp;
					textFp.setText(aux);
					aux = "";
					if(Hp.length()==1)aux+= "0" + Hp;
					else aux+= Hp;
					textHp.setText(aux);
					aux = "";
					if(Lp.length()==1)aux+= "0" + Lp;
					else aux+= Lp;
					textLp.setText(aux);
					aux = "";
					if(I.length()==1)aux+= "0" + I;
					else aux+= I;
					textI.setText(aux);
					aux = "";
					if(R.length()==1)aux+= "0" + R;
					else aux+= R;
					textR.setText(aux);
					aux = "";
					if(IX.length()==1)aux+= "000" + IX;
					if(IX.length()==2)aux+= "00" + IX;
					if(IX.length()==3)aux+= "0" + IX;
					if(IX.length()==4)aux+= "" + IX;
					textIX.setText(aux);
					aux = "";
					if(IY.length()==1)aux+= "000" + IY;
					if(IY.length()==2)aux+= "00" + IY;
					if(IY.length()==3)aux+= "0" + IY;
					if(IY.length()==4)aux+= "" + IY;
					textIX.setText(aux);
					aux = "";
					if(SP.length()==1)aux+= "000" + SP;
					if(SP.length()==2)aux+= "00" + SP;
					if(SP.length()==3)aux+= "0" + SP;
					if(SP.length()==4)aux+= "" + SP;
					textSP.setText(aux);
					aux = "";
					if(PC>999)aux= "" + PC;
					if(PC<1000)aux= "0" + PC;
					if(PC<100)aux= "00" + PC;
					if(PC<10)aux= "000" + PC;
					textPC.setText(aux);
					FlagCarry.setText("");
					FlagZ.setText("");
					FlagS.setText("");
					FlagZ.setText(Z+"");
					FlagS.setText(S+"");
					FlagCarry.setText(carry+"");	
					DefaultListModel<String> modelR4 = new DefaultListModel<String>();
					int Ram = 0;
					for(int i=0;i<42;i++) {
						Aux = "";
						indexM = i*6 +"";
						
						for(int j = 0;j<(3-indexM.length());j++) {
							Aux += "0";
						}
						indexM = Aux + "" + indexM;
						Aux = "";
						
						for(int j = 0;j<6;j++) {
							Aux += " " + MemoriaRam[Ram];
							Ram+=1;
						}
						modelR4.addElement(indexM + " " + Aux);
					}
					listR.setModel(modelR4);
				}
			}
		});
		
		
		btnProcesarTodo.setForeground(Color.WHITE);
		btnProcesarTodo.setFocusable(false);
		btnProcesarTodo.setBorder(null);
		btnProcesarTodo.setBackground(new Color(88, 89, 91));
		btnProcesarTodo.setBounds(930, 620, 145, 35);
		frame.getContentPane().add(btnProcesarTodo);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Input = txtHello.getText();
				if(!IN) {
					if(Input.length()!=0) {
						A = Integer.toHexString(Integer.parseInt(Input)).toUpperCase();
						String aux = "";
						String Aux = "";
						String indexM = "";
						if(A.length()==1)aux+= "0" + A;
						else aux+= A;
						textA.setText(aux);
						MemoriaRam[AddressIN] = Integer.toHexString(Integer.parseInt(Input)).toUpperCase();
						DefaultListModel<String> modelR2 = new DefaultListModel<String>();
						int Ram = 0;
						for(int i=0;i<42;i++) {
							Aux = "";
							indexM = i*6 +"";
							
							for(int j = 0;j<(3-indexM.length());j++) {
								Aux += "0";
							}
							indexM = Aux + "" + indexM;
							Aux = "";
							
							for(int j = 0;j<6;j++) {
								Aux += " " + MemoriaRam[Ram];
								Ram+=1;
							}
							modelR2.addElement(indexM + " " + Aux);
						}
						listR.setModel(modelR2);
						TextosS.setText("Gracias");
						txtHello.setText("");
						IN = true;
					}
				}
			}
		});
		btnEnter.setForeground(Color.WHITE);
		btnEnter.setFocusable(false);
		btnEnter.setBorder(null);
		btnEnter.setBackground(new Color(88, 89, 91));
		btnEnter.setBounds(945, 380, 100, 35);
		frame.getContentPane().add(btnEnter);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.BLACK);
		panel_5.setForeground(Color.BLACK);
		panel_5.setBounds(220, 501, 280, 8);
		frame.getContentPane().add(panel_5);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBackground(Color.BLACK);
		panel_17.setForeground(Color.BLACK);
		panel_17.setBounds(802, 380, 8, 15);
		frame.getContentPane().add(panel_17);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(Color.BLACK);
		panel_16.setForeground(Color.BLACK);
		panel_16.setBounds(852, 380, 8, 45);
		frame.getContentPane().add(panel_16);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.BLACK);
		panel_15.setForeground(Color.BLACK);
		panel_15.setBounds(902, 380, 8, 75);
		frame.getContentPane().add(panel_15);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.BLACK);
		panel_14.setForeground(Color.BLACK);
		panel_14.setBounds(620, 450, 290, 8);
		frame.getContentPane().add(panel_14);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.BLACK);
		panel_13.setForeground(Color.BLACK);
		panel_13.setBounds(530, 425, 330, 8);
		frame.getContentPane().add(panel_13);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.BLACK);
		panel_12.setForeground(Color.BLACK);
		panel_12.setBounds(420, 395, 390, 8);
		frame.getContentPane().add(panel_12);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.BLACK);
		panel_10.setForeground(Color.BLACK);
		panel_10.setBounds(530, 501, 330, 8);
		frame.getContentPane().add(panel_10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.BLACK);
		panel_6.setForeground(Color.BLACK);
		panel_6.setBounds(220, 531, 390, 8);
		frame.getContentPane().add(panel_6);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.BLACK);
		panel_9.setForeground(Color.BLACK);
		panel_9.setBounds(620, 531, 240, 8);
		frame.getContentPane().add(panel_9);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.BLACK);
		panel_7.setForeground(Color.BLACK);
		panel_7.setBounds(220, 561, 190, 8);
		frame.getContentPane().add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.BLACK);
		panel_8.setForeground(Color.BLACK);
		panel_8.setBounds(420, 561, 440, 8);
		frame.getContentPane().add(panel_8);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(400, 372, 30, 220);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(500, 372, 30, 220);
		frame.getContentPane().add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.DARK_GRAY);
		panel_4.setBounds(600, 372, 30, 220);
		frame.getContentPane().add(panel_4);
		
		JLabel Z80Nombre = new JLabel("CPU Z80");
		Z80Nombre.setFont(new Font("Dialog", Font.BOLD, 25));
		Z80Nombre.setBounds(450, 230, 200, 40);
		frame.getContentPane().add(Z80Nombre);
		
		
		JLabel img1 = new JLabel(" "); 
		frame.getContentPane().add(img1);
		ImageIcon image1 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/Micro2.png"); 
		img1.setIcon(image1); 
		img1.setSize(746,292); 
		img1.setLocation(314,120); 
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.DARK_GRAY);
		panel_11.setBounds(745, 160, 300, 215);
		frame.getContentPane().add(panel_11);
		
		JLabel lblVccv = new JLabel("VCC 5V.");
		lblVccv.setForeground(Color.RED);
		lblVccv.setBounds(240, 140, 70, 15);
		frame.getContentPane().add(lblVccv);
		
		JLabel lblGND = new JLabel("GND");
		lblGND.setForeground(new Color(0, 102, 0));
		lblGND.setBounds(250, 375, 70, 15);
		frame.getContentPane().add(lblGND);
		
		JLabel labelBA = new JLabel("B.A.");
		labelBA.setForeground(Color.DARK_GRAY);
		labelBA.setBounds(403, 600, 70, 15);
		frame.getContentPane().add(labelBA);
		
		JLabel labelBD = new JLabel("B.D.");
		labelBD.setForeground(Color.DARK_GRAY);
		labelBD.setBounds(503, 600, 70, 15);
		frame.getContentPane().add(labelBD);
		
		JLabel labelBC = new JLabel("B.C.");
		labelBC.setForeground(Color.DARK_GRAY);
		labelBC.setBounds(603, 600, 70, 15);
		frame.getContentPane().add(labelBC);
		
		JLabel labelRAM = new JLabel("PUERTOS");
		labelRAM.setForeground(Color.DARK_GRAY);
		labelRAM.setBounds(945, 592, 70, 15);
		frame.getContentPane().add(labelRAM);
		
		JLabel img2 = new JLabel(" "); 
		frame.getContentPane().add(img2);
		ImageIcon image2 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/PVCC.png"); 
		img2.setIcon(image2); 
		img2.setSize(111,106); 
		img2.setLocation(264,142); 
		
		JLabel img3 = new JLabel(" "); 
		frame.getContentPane().add(img3);
		ImageIcon image3 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/GND2.png"); 
		img3.setIcon(image3); 
		img3.setSize(111,106); 
		img3.setLocation(257,280); 

		////////////////////////////////////////////////////
		JPanel panelBlanco = new JPanel();
		panelBlanco.setBackground(Color.white);
		panelBlanco.setBounds(220, 110, 880, 560);
		frame.getContentPane().add(panelBlanco);
		
		JPanel panelReg = new JPanel();
		panelReg.setBackground(new Color(88,89,91));
		panelReg.setBounds(20, 100, 1200, 580);
		frame.getContentPane().add(panelReg);
	}
	
	public static void setVisible(boolean b, String[] memoria, int pc) {
		mem = memoria;
		pcIn = pc;
		Procesador window = new Procesador();
		window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.frame.setVisible(true);
	}
	public static void setVisible1(boolean b) {
		Procesador window = new Procesador();
		window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.frame.setVisible(true);
	}
	
	////////////////////////////////////////////////////////
	///////////////////////PROCESADOR///////////////////////
	////////////////////////////////////////////////////////
	
    public String incremento(String word) {
    	int value = Integer.parseInt(word, 16);
    	value++;
    	return Integer.toHexString(value).toUpperCase();
    } 
    public String decremento(String hexa) {
    	int value = Integer.parseInt(hexa, 16);
    	value--;
    	return Integer.toHexString(value).toUpperCase();
    } 
    public static String rlc(String hexa) {
    	int aux = Integer.parseInt(hexa,16);
    	String bin = Integer.toBinaryString(aux);
    	carry = bin.charAt(0);
    	bin = bin.substring(1, bin.length());
    	bin += carry;
    	aux = Integer.valueOf(bin, 2);
    	return Integer.toHexString(aux).toUpperCase();
    }
    public static String rrc(String hexa) {
    	int aux = Integer.parseInt(hexa,16);
    	String bin = Integer.toBinaryString(aux);
    	carry = bin.charAt(7);
    	bin = bin.substring(0, bin.length()-1);
    	bin = carry + bin;
    	aux = Integer.valueOf(bin, 2);
    	return Integer.toHexString(aux).toUpperCase();
    }
    public static String rl(String hexa) {
    	char carryOld = carry;
    	int aux = Integer.parseInt(hexa,16);
    	String bin = Integer.toBinaryString(aux);
    	carry = bin.charAt(0);
    	bin = bin.substring(1, bin.length());
    	bin += carryOld;
    	aux = Integer.valueOf(bin, 2);
    	return Integer.toHexString(aux).toUpperCase();
    }
    public static String rr(String hexa) {
    	char carryOld = carry;
    	int aux = Integer.parseInt(hexa,16);
    	String bin = Integer.toBinaryString(aux);
    	carry = bin.charAt(7);
    	bin = bin.substring(0, bin.length()-1);
    	bin = carryOld + bin;
    	aux = Integer.valueOf(bin, 2);
    	return Integer.toHexString(aux).toUpperCase();
    }
    
    //Sumar la entrada con a
    public static String add(String base,String sumar) {
    	int aux1 = Integer.parseInt(base,16);
    	int aux2 = Integer.parseInt(sumar,16);
    	aux1 += aux2;
        if(aux1>255)carryFlag = true;
        carry = '1';
    	return Integer.toHexString(aux1).toUpperCase();
    }
    
    //Sumar la entrada y el carry y guardar en a "Base"
    public static String adc(String base, String sumar) {
    	int aux1 = Integer.parseInt(base,16);
    	int aux2 = Integer.parseInt(sumar,16);
    	String auxR = Sum(Integer.toBinaryString(aux1),Integer.toBinaryString(aux2));
    	
    	return Integer.toHexString(Integer.parseInt(auxR)).toUpperCase();
    }
    
    public static String Sum(String num1,String num2) {
    	String caso = "";
    	String salida = "";
    	String numero1="";
    	String numero2 = "";
    	if(num1.length()<8) {
    		for(int i=0;i<8-num1.length();i++) {
    			numero1 += "0";
    		}
    	}
    	numero1+=num1;
    	if(num2.length()<8) {
    		for(int i=0;i<8-num2.length();i++) {
    			numero2 += "0";
    		}
    	}
    	numero2+=num2;
    	for(int i=7;i>=0;i--) {
    		caso = carry + "" + numero1.charAt(i) + "" + numero2.charAt(i);
    		switch (caso){
            case "000":{
                salida = '0' + salida;
                carry= '0';
                break;
            }
            case "010":{
            	salida = '1' + salida;
                carry= '0';
                break;
            }
            case "001":{
            	salida = '1' + salida;
                carry= '0';
                break;
            }
            case "100":{
            	salida = '1' + salida;
                carry= '0';
                break;
            }
            case "011":{
            	salida = '0' + salida;
                carry= '1';
                break;
            }
            case "101":{
            	salida = '0' + salida;
                carry= '1';
                break;
            }
            case "110":{
            	salida = '0' + salida;
                carry= '1';
                break;
            }
            case "111":{
            	salida = '1' + salida;
                carry= '1';
                break;
            }
        }
    	}
    	if(carry=='1') {
    		carryFlag = true;
    		carry = '1';
    	}
    	return salida;
    }
    public static String sub(String A, String subO) {
    	int aux1 = Integer.parseInt(A,16);
    	int aux2 = Integer.parseInt(subO,16);
    	aux1 -= aux2;
        if(aux1 < 0) {
        	carryFlag = true;
        	carry = '1';
        	S = '1';
        	Z = '1';
        }else if(aux1 == 0) {
        	carry = '0';
        	S = '0';
        	Z = '0';
        }else {
        	Z = '1';
        	S = '0';
        }
        return Integer.toHexString(aux1).toUpperCase();
    }
    public static String sbc(String A, String sbcO) {
    	int aux1 = Integer.parseInt(A,16);
    	int aux2 = Integer.parseInt(sbcO,16);
    	String auxR = resta(Integer.toBinaryString(aux1),Integer.toBinaryString(aux2));
    	int numeroR = Integer.parseInt(auxR);
    	if(numeroR == 0) {
    		Z = '0';
    		S = '0';
    	}else if(carry == '0') {
    		S = '0';
    		Z = '1';
    	}
    	
    	return Integer.toHexString(numeroR).toUpperCase();
    }  
    public static String resta(String num1, String num2) {
    	String caso = "";
    	String salida = "";
    	String numero1="";
    	String numero2 = "";
    	if(num1.length()<8) {
    		for(int i=0;i<8-num1.length();i++) {
    			numero1 += "0";
    		}
    	}
    	numero1+=num1;
    	if(num2.length()<8) {
    		for(int i=0;i<8-num2.length();i++) {
    			numero2 += "0";
    		}
    	}
    	numero2+=num2;
    	for(int i=7;i>=0;i--) {
    		caso = numero1.charAt(i) + "" + carry + "" + numero1.charAt(i) + "" + numero2.charAt(i);
    		switch (caso){
            case "000":{
                salida = '0' + salida;
                carry= '0';
                break;
            }
            case "010":{
            	salida = '1' + salida;
                carry= '1';
                break;
            }
            case "001":{
            	salida = '1' + salida;
                carry= '1';
                break;
            }
            case "100":{
            	salida = '1' + salida;
                carry= '0';
                break;
            }
            case "011":{
            	salida = '0' + salida;
                carry= '1';
                break;
            }
            case "101":{
            	salida = '0' + salida;
                carry= '0';
                break;
            }
            case "110":{
            	salida = '0' + salida;
                carry= '0';
                break;
            }
            case "111":{
            	salida = '1' + salida;
                carry= '1';
                break;
            }
        }
    	}
    	if(carry=='1') {
    		carryFlag = true;
    		carry = '1';
    		S = '1';
    		Z = '1';
    	}
    	return salida;
    }
    public static String and(String operar, String A) {
    	int aux1 = Integer.parseInt(operar,16);
    	int aux2 = Integer.parseInt(A,16);
    	aux2 &= aux1;
        carryFlag = false;
    	return Integer.toHexString(aux2).toUpperCase();
    }
    public static String xor(String operar, String A) {
    	int aux1 = Integer.parseInt(operar,16);
    	int aux2 = Integer.parseInt(A,16);
    	aux2 = (aux2 ^ aux1);
        carryFlag = false;
    	return Integer.toHexString(aux2).toUpperCase();
    }
    public static String or(String operar, String A) {
    	int aux1 = Integer.parseInt(operar,16);
    	int aux2 = Integer.parseInt(A,16);
    	aux2 = (aux2 | aux1);
        carryFlag = false;
    	return Integer.toHexString(aux2).toUpperCase();
    }
    public static void cp(String operar, String A) {
    	int aux1 = Integer.parseInt(operar,16);
    	int aux2 = Integer.parseInt(A,16);
    	int res = aux2 - aux1;
        carryFlag = res < 0;
        if(res == 0) {
        	carry = '0';
        	Z = '0';
        	S = '0';
        }else if(res > 0) {
        	carry = '0';
        	Z = '1';
        	S = '0';
        }else {
        	carry = '1';
        	Z = '1';
        	S = '1';
        }
    }
    public static String address(String hexa) {
    	int aux = Integer.parseInt((hexa),16);
    	return Integer.toHexString(aux).toUpperCase();
    }
    
    public static String cpl(String hexa) {
    	int aux = Integer.parseInt((hexa),16);
    	String bin = Integer.toBinaryString(aux);
    	Aux="";
    	if(bin.length()<8) {
    		for(int i=0;i<8-bin.length();i++) {
    			Aux += "0";
    		}
    	}
    	bin = Aux + bin;
    	Aux="";
    	for(int i=0;i<bin.length();i++) {
    		if(bin.charAt(i)=='1')Aux += "0";
    		else Aux += "1";
    	}
    	aux = Integer.valueOf(Aux, 2);
    	return Integer.toHexString(aux).toUpperCase();
    }

    public String pop(String SP) {
        String hexa = Memoria[Integer.parseInt((SP),16)+1] + Memoria[Integer.parseInt((SP),16)];
        int word = Integer.parseInt((hexa),16);
        SP = Integer.toHexString(Integer.parseInt((SP),16)+ 2).toUpperCase();
        return Integer.toHexString(word).toUpperCase();
    }

    public void push(int word) {
        SP = Integer.toHexString(Integer.parseInt((SP),16) - 1).toUpperCase();
        Memoria[Integer.parseInt((SP),16)] = Integer.toHexString(word >>> 8).toUpperCase();
        SP = Integer.toHexString(Integer.parseInt((SP),16) - 1).toUpperCase();
        Memoria[Integer.parseInt((SP),16)] = Integer.toHexString(word).toUpperCase();
    }
	public void decode(String opCode) {
		
		switch (opCode) {
		
		case "01":     /* LD BC,nn */
			C = Memoria[PC+1];
			B = Memoria[PC+2];
            PC +=2;
            break;
        
		case "02":  	/* LD (BC),A */
			Memoria[Integer.parseInt((B+C),16)] = A;
            break;
		
		case "03":     /* INC BC */
			if(C.equals("FF")) {
				C="00";
				B = incremento(B);
			}else {
				C = incremento(C);
			}
			break;
		
		case "04":     /* INC B */
			B = incremento(B);
			break;
		
		case "05":     /* DEC B */
            B = decremento(B);
            break;
        
		case "06":     /* LD B,n */
        	B = Memoria[PC+1];
        	PC +=1;
        	break;
        
		case "07":      /* RLCA */
			A = rlc(A);
			break;
		
		case "08":		/* EX AF,AF' */
			Aux = A;
			A = Ap;
			Ap = A;
			Aux = F;
			F = Fp;
			Fp = F;
			break;
		
		case "09":      /* ADD HL,BC */
			Aux = add(H+L,B+C);
			H = Aux.substring(0, 2);
			L = Aux.substring(2, 4);
			break;
		
		case "0A": 		/* LD A,(BC) */
			B = "A1";
			C = "C5";
			A = Memoria[Integer.parseInt((B+C),16)];
			break;
			
		case "0B":		/* DEC BC */
			if(C.equals("00")) {
				C = "FF";
				B = decremento(B);
			}else {
				C = decremento(C);
			}
			break;
			
		case "0C":		/* INC C */
			C = incremento(C);
			break;
			
		case "0D":		/* DEC C */ 
			C = decremento(C);
			break;
			
		case "0E":		/* LD C,n */
			C = Memoria[PC+1];
			PC++;
			break;
			
		case "0F":		/* RRCA */
			A = rrc(A);
			break;
			
		case "10":		/* DJNZ e */
			break;
			
		case "11":		/* LD DE,nn */
			E = Memoria[PC+1];
			D = Memoria[PC+2];
            PC +=2;
			break;
			
		case "12":		/* LD (DE),A */
			Memoria[Integer.parseInt((D+E),16)] = A;
            break;
            
		case "13":		/* INC DE */
			if(E.equals("FF")) {
				E="00";
				D = incremento(B);
			}else {
				E = incremento(E);
			}
			break;
		
		case "14":		/* INC D */
			D = incremento(D);
			break;
			
		case "15":		/* DEC D */
			D = decremento(D);
			break;
			
		case "16":		/* LD D,n */
			D = Memoria[PC+1];
        	PC +=1;
        	break;
        	
		case "17":		/* RLA */
			A = rl(A);
			break;
			
		case "18":		/* JR e */
			PC = Integer.parseInt(Memoria[PC+1],16);
			PC-=1;
			break;
			
		case "19":		/* ADD HL,DE */
			Aux = add(H+L,D+E);
			H = Aux.substring(0, 2);
			L = Aux.substring(2, 4);
			break;
			
		case "1A":		/* LD A,(DE) */
			A = address(D+E);
			break;
			
		case "1B":		/* DEC DE */
			if(E.equals("00")) {
				E = "FF";
				D = decremento(D);
			}else {
				E = decremento(E);
			}
			break;
			
		case "1C":		/* INC E */
			E = incremento(E);
			break;
			
		case "1D":		/* DEC E */
			E = decremento(E);
			break;
			
		case "1E":		/* LD E,n */
			E = Memoria[PC+1];
			PC++;
			break;
			
		case "1F":		/* RRA */
			A = rr(A);
			break;
			
		case "20":		/* JR NZ,e */
			if(Z=='1') {
				PC = Integer.parseInt(Memoria[PC+1],16);
				PC-=1;
			}else {
				PC +=1;
			}
			break;
			
		case "21":		/* LD HL,nn */
			L = Memoria[PC+1];
			H = Memoria[PC+2];
            PC += 2;
			break;
		case "22":		/* LD (nn),HL */
			String dir = address(Memoria[PC+2] + Memoria[PC+1]);
			Memoria[Integer.parseInt((dir),16)] = L;
			Memoria[Integer.parseInt((dir),16)+1] = H;
			PC += 2;
			break;
			
		case "23":		/* INC HL */
			if(L.equals("FF")) {
				L="00";
				H = incremento(H);
			}else {
				L = incremento(L);
			}
			break;
			
		case "24":		/* INC H */
			H = incremento(H);
			break;
			
		case "25":		/* DEC H */
			H = decremento(H);
			break;
		
		case "26":		/* LD H,n */
			H = Memoria[PC+1];
        	PC +=1;
        	break;
        	
		case "27":		/* DAA */
			break;
		case "28":		/* JR Z,e */
			if(Z=='0') {
				PC = Integer.parseInt(Memoria[PC+1],16);
				PC-=1;
			}else {
				PC +=1;
			}
			break;
			
		case "29":		/* ADD HL,HL */
			Aux = add(H+L,H+L);
			H = Aux.substring(0, 2);
			L = Aux.substring(2, 4);
			break;
		
		case "2A":		/* LD HL,(nn) */
			Aux = address(Memoria[PC+2]+Memoria[PC+1]);
			H = Aux.substring(0, 2);
			L = Aux.substring(2, 4);
			PC +=2;
			break;
			
		case "2B":		/* DEC HL */
			if(L.equals("00")) {
				L = "FF";
				H	 = decremento(H);
			}else {
				L = decremento(L);
			}
			break;
			
		case "2C":		/* INC L */
			L = incremento(L);
			break;
			
		case "2D":		/* DEC L */
			L = decremento(L);
			break;
			
		case "2E":		/* LD L,n */
			L = Memoria[PC+1];
			PC++;
			break;	
		
		case "2F":		/* CPL */
			A = cpl(A);
			break;
			
		case "30":		/* JR NC,e */
			if(S=='0') {
				PC = Integer.parseInt(Memoria[PC+1],16);
				PC-=1;
			}else {
				PC+=1;
			}
			break;
			
		case "31":		/* LD SP,nn */
			SP = Memoria[PC+2] + Memoria[PC+1];
            PC += 2;
			break;
		case "32":		/* LD (nn),A */
			dir = address(Memoria[PC+2] + Memoria[PC+1]);
			Memoria[Integer.parseInt((dir),16)] = A;
			PC += 2;
			break;
			
		case "33":		/* INC SP */
			int aux	= Integer.parseInt((SP),16);
			aux+=1;
			SP = Integer.toHexString(aux).toUpperCase();
			break;
			
		case "34":		/* INC (HL) */ //No estoy seguro puesto que HL es un registro
			Memoria[Integer.parseInt(address(H+L))] = incremento(Memoria[Integer.parseInt(address(H+L))]);
			break;
		
		case "35":		/* DEC (HL) */
			Memoria[Integer.parseInt(address(H+L))] = decremento(Memoria[Integer.parseInt(address(H+L))]);
			break;
			
		case "36":	/* LD (HL),n */
			Memoria[Integer.parseInt(address(H+L))] = Memoria[PC+1];
			PC +=1;
			break;
			
		case "38": /* JR C,**/
			if(S=='1') {
				PC = Integer.parseInt(Memoria[PC+1],16);
				PC -=1;
			}else {
				PC+=1;
			}
			break;
		case "39":		/* ADD HL,SP */
			Aux = add(H+L,SP);
			H = Aux.substring(0, 2);
			L = Aux.substring(2, 4);
			break;
		
		case "3A":		/* LD A,(nn) */
			break;
			
		case "3B":		/* DEC SP */
			Aux1 = SP.substring(0, 2);
			Aux2 = SP.substring(2,4);
			if(Aux2.equals("00")) {
				Aux2 = "FF";
				Aux1 = decremento(Aux1);
			}else {
				Aux2 = decremento(L);
			}
			break;
			
		case "3C":		/* INC A */
			A = incremento(A);
			break;
			
		case "3D":		/* DEC A */
			A = decremento(A);
			break;
			
		case "3E":		/* LD A,n */
			A = Memoria[PC+1];
			PC++;
			break;
		case "3F":
			if(carry=='0')carry = '1';
			else carry = '0';
			break;
		case "40":		/* LD B,B */
			break;
			
		case "41":		/* LD B,C */
			B = C;
			break;
		case "42":      /* LD B,D */
            B = D;
            break;
            
        case "43":      /* LD B,E */
            B = E;
            break;

        case "44":      /* LD B,H */
            B = H;
            break;

        case "45":      /* LD B,L */
            B = L;
            break;
        case "46":      /* LD B,(HL) */
            B = Memoria[Integer.parseInt(address(H+L))];
            break;
        
        case "47":      /* LD B,A */
            B = A;
            break;
        
        case "48":      /* LD C,B */
            C = B;
            break;
        
        case "49":      /* LD C,C */
            break;
        
        case "4A":      /* LD C,D */
            C = D;
            break;
        
        case "4B":      /* LD C,E */
            C = E;
            break;
        
        case "4C":      /* LD C,H */
            C = H;
            break;
        
        case "4D":      /* LD C,L */
            C = L;
            break;
        
        case "4E":      /* LD C,(HL) */
            C = Memoria[Integer.parseInt(address(H+L))];
            break;
        
        case "4F":      /* LD C,A */
            C = A;
            break;
        
        case "50":      /* LD D,B */
            D = B;
            break;
        
        case "51":      /* LD D,C */
            D = C;
            break;
        
        case "52":      /* LD D,D */
            break;
        
        case "53":      /* LD D,E */
            D = E;
            break;
        
        case "54":      /* LD D,H */
            D = H;
            break;
        
        case "55":      /* LD D,L */
            D = L;
            break;
        
        case "56":      /* LD D,(HL) */
            D = Memoria[Integer.parseInt(address(H+L))];
            break;
        
        case "57":      /* LD D,A */
            D = A;
            break;
        
        case "58":      /* LD E,B */
            E = B;
            break;
        
        case "59":      /* LD E,C */
            E = C;
            break;
        
        case "5A":      /* LD E,D */
            E = D;
            break;
        
        case "5B":      /* LD E,E */
        	break;
        
        case "5C":      /* LD E,H */
            E = H;
            break;
        
        case "5D":      /* LD E,L */
            E = L;
            break;
        
        case "5E":      /* LD E,(HL) */
            E = Memoria[Integer.parseInt(address(H+L))];
            break;
        
        case "5F":      /* LD E,A */
            E = A;
            break;
        case "60":      /* LD H,B */
            H = B;
            break;
        
        case "61":      /* LD H,C */
            H = C;
            break;
        
        case "62":      /* LD H,D */
            H = D;
            break;
        
        case "63":      /* LD H,E */
            H = E;
            break;
        
        case "64":      /* LD H,H */
            break;
        
        case "65":      /* LD H,L */
            H = L;
            break;
        
        case "66":      /* LD H,(HL) */
            H = Memoria[Integer.parseInt(address(H+L))];
            break;
        
        case "67":      /* LD H,A */
            H = A;
            break;
        
        case "68":      /* LD L,B */
            L = B;
            break;
        
        case "69":     /* LD L,C */
            L = C;
            break;
        
        case "6A":      /* LD L,D */
            L = D;
            break;
        
        case "6B":      /* LD L,E */
            L = E;
            break;
        
        case "6C":      /* LD L,H */
            L = H;
            break;
        
        case "6D":     /* LD L,L */
            break;
       
        case "6E":      /* LD L,(HL) */
            L = Memoria[Integer.parseInt(address(H+L))];
            break;
        
        case "6F":      /* LD L,A */
            L = A;
            break;
        
        case "70":      /* LD (HL),B */
        	Memoria[Integer.parseInt(address(H+L))] = B; 
            break;
        
        case "71":      /* LD (HL),C */
        	Memoria[Integer.parseInt(address(H+L))] = C;
            break;
        
        case "72":      /* LD (HL),D */
        	Memoria[Integer.parseInt(address(H+L))] = D;
            break;
        
        case "73":      /* LD (HL),E */
        	Memoria[Integer.parseInt(address(H+L))] = E;
            break;
        
        case "74":      /* LD (HL),H */
        	Memoria[Integer.parseInt(address(H+L))] = H;
            break;
        
        case "75":      /* LD (HL),L */
        	Memoria[Integer.parseInt(address(H+L))] = L;
            break;
        case "76":
        	halt = true;
        	break;
        
        case "77":      /* LD (HL),A */
        	Memoria[Integer.parseInt(address(H+L))] = A;
            break;
        
        case "78":      /* LD A,B */
            A = B;
            break;
        
        case "79":      /* LD A,C */
            A = C;
            break;
        
        case "7A":      /* LD A,D */
            A = D;
            break;
        
        case "7B":      /* LD A,E */
            A = E;
            break;
        
        case "7C":      /* LD A,H */
            A = H;
            break;
        
        case "7D":      /* LD A,L */
            A = L;
            break;
        
        case "7E":      /* LD A,(HL) */
            A = Memoria[Integer.parseInt(address(H+L))];
            break;
        
        case "7F":      /* LD A,A */
            break;
        case "80":      /* ADD A,B */
            A = add(A,B);
            bit8 = true;
            break;
        
        case "81":      /* ADD A,C */
        	A = add(A,C);
        	bit8 = true;
            break;
        
        case "82": {     /* ADD A,D */
        	A = add(A,D);
        	bit8 = true;
            break;
        }
        case "83":      /* ADD A,E */
        	A = add(A,E);
        	bit8 = true;
            break;
        
        case "84":      /* ADD A,H */
        	A = add(A,H);
        	bit8 = true;
            break;
        
        case "85":      /* ADD A,L */
        	A = add(A,L);
        	bit8 = true;
            break;
        
        case "86":      /* ADD A,(HL) */
        	A = add(A,Memoria[Integer.parseInt(address(H+L))]);
            break;
        
        case "87":      /* ADD A,A */
        	A = add(A,A);
        	bit8 = true;
            break;
        
        case "88":      /* ADC A,B */
        	A = adc(A,B);
            break;
        
        case "89":      /* ADC A,C */
        	A = adc(A,C);
            break;
        
        case "8A":      /* ADC A,D */
        	A = adc(A,D);
            break;
        
        case "8B":      /* ADC A,E */
        	A = adc(A,E);
            break;
        
        case "8C":      /* ADC A,H */
        	A = adc(A,H);
            break;
        
        case "8D":      /* ADC A,L */
        	A = adc(A,L);
            break;
        
        case "8E":      /* ADC A,(HL) */
        	A = adc(A,Memoria[Integer.parseInt(address(H+L))]);
            break;
        
        case "8F":      /* ADC A,A */
        	A = adc(A,A);
            break;
            
        case "90":      /* SUB B */
        	A = sub(A,B);
            break;
        
        case "91":      /* SUB C */
        	A = sub(A,C);
            break;
        
        case "92":      /* SUB D */
        	A = sub(A,D);
            break;
        
        case "93":      /* SUB E */
        	A = sub(A,E);
            break;
        
        case "94":      /* SUB H */
        	A = sub(A,H);
            break;
        
        case "95":      /* SUB L */
        	A = sub(A,L);
            break;
        
        case "96":      /* SUB (HL) */
        	A = sub(A,Memoria[Integer.parseInt(address(H+L))]);
            break;
        
        case "97":      /* SUB A */
        	A = sub(A,A);
            break;
        
        case "98":      /* SBC A,B */
        	A = sbc(A,B);
            break;
        
        case "99":      /* SBC A,C */
        	A = sbc(A,C);
            break;
        
        case "9A":      /* SBC A,D */
        	A = sbc(A,D);
            break;
        
        case "9B":      /* SBC A,E */
        	A = sbc(A,E);
            break;
        
        case "9C":      /* SBC A,H */
        	A = sbc(A,H);
            break;
        
        case "9D":      /* SBC A,L */
        	A = sbc(A,L);
            break;
        
        case "9E":      /* SBC A,(HL) */
            A = sbc(A,Memoria[Integer.parseInt(address(H+L))]);
            break;
        
        case "9F":      /* SBC A,A */
            A = sbc(A,A);
            break;
        
        case "A0":      /* AND B */
            A = and(B,A);
            break;
        
        case "A1":      /* AND C */
            A = and(C,A);
            break;
        
        case "A2":      /* AND D */
            A = and(D,A);
            break;
        
        case "A3":      /* AND E */
            A = and(E,A);
            break;
        
        case "A4":      /* AND H */
            A = and(H,A);
            break;
        
        case "A5":      /* AND L */
            A = and(L,A);
            break;
        
        case "A6":      /* AND (HL) */
            A = and(Memoria[Integer.parseInt(address(H+L))],A);
            break;
        
        case "A7":      /* AND A */
            A = and(A,A);
            break;
        
        case "A8":      /* XOR B */
            A = xor(B,A);
            break;
        
        case "A9":      /* XOR C */
            A = xor(C,A);
            break;
        
        case "AA":      /* XOR D */
            A = xor(D,A);
            break;
        
        case "AB":      /* XOR E */
            A = xor(E,A);
            break;
        
        case "AC":      /* XOR H */
            A = xor(H,A);
            break;
        
        case "AD":      /* XOR L */
            A = xor(L,A);
            break;
        
        case "AE":      /* XOR (HL) */
            A = xor(Memoria[Integer.parseInt(address(H+L))],A);
            break;
        
        case "AF":      /* XOR A */
            A = xor(A,A);
            break;
        
        case "B0":      /* OR B */
            A = or(B,A);
            break;
        
        case "B1":      /* OR C */
            A = or(C,A);
            break;
        
        case "B2":      /* OR D */
            A = or(D,A);
            break;
        
        case "B3":      /* OR E */
            A = or(E,A);
            break;
        
        case "B4":      /* OR H */
            A = or(H,A);
            break;
        
        case "B5":     /* OR L */
            A = or(L,A);
            break;
        
        case "B6":      /* OR (HL) */
            A = or(Memoria[Integer.parseInt(address(H+L))],A);
            break;
        
        case "B7":      /* OR A */
            A = or(A,A);
            break;
        case "B8":      /* CP B */
            cp(B,A);
            break;
        
        case "B9":      /* CP C */
            cp(C,A);
            break;
        
        case "BA":      /* CP D */
            cp(D,A);
            break;
        
        case "BB":      /* CP E */
            cp(E,A);
            break;
        
        case "BC":      /* CP H */
            cp(H,A);
            break;
        
        case "BD":      /* CP L */
            cp(L,A);
            break;
        
        case "BE":      /* CP (HL) */
            cp(Memoria[Integer.parseInt(address(H+L))],A);
            break;
        
        case "BF":      /* CP A */
            cp(A,A);
            break;
        
        case "C0": 
            break;
        
        case "C1":      /* POP BC */
            Aux = pop(SP);
            B = Aux.substring(0,2);
            C = Aux.substring(2,4);
            break;
        
        case "C2":      /* JP NZ,nn */
        	break;
        
        case "C3":      /* JP nn */
            PC = Integer.parseInt((Memoria[PC+1] + Memoria[PC]),16);
            break;
        
        case "C4":      /* CALL NZ,nn */
            
            break;
        
        case "C5":      /* PUSH BC */
            push(Integer.parseInt((B+C),16));
            break;
        
        case "C6":      /* ADD A,n */
            A = add(A,Memoria[PC+1]);
            bit8 = true;
            PC +=1;
            break;
        
        case "C7":      /* RST 00H */
            break;
        
        case "C8":      /* RET Z */

            break;
        
        case "C9":      /* RET */
            break;
        
        case "CA":      /* JP Z,nn */
            break;
        
        case "CB":    
            break;
        
        case "CC":      /* CALL Z,nn */
            break;
        
        case "CD":      /* CALL nn */
            break;
        
        case "CE":      /* ADC A,n */
            A = adc(Memoria[PC+1],A);
            PC +=1;
            break;
        
        case "CF":      /* RST 08H */
            break;
        
        case "D0":      /* RET NC */
            break;
        
        case "D1":      /* POP DE */
        	Aux = pop(SP);
            D = Aux.substring(0,2);
            E = Aux.substring(2,4);
            break;
        
        case "D2":      /* JP NC,nn */
            break;
        
        case "D3":      /* OUT (n),A */
        	OUT = false;
        	OutTexto = true;
        	AddressOUT = Integer.parseInt(Memoria[PC+1],16);
        	MemoriaRam[AddressOUT] = A;
        	PC+=1;
            break;
        
        case "D4":      /* CALL NC,nn */
            break;
        
        case "D5":      /* PUSH DE */
            push(Integer.parseInt((D+E),16));
            break;
        
        case "D6":      /* SUB n */
            A = sub(A,Memoria[PC+1]);
            PC +=1;
            break;
        
        case "D7":      /* RST 10H */
            break;
        
        case "D8":      /* RET C */
            break;
        
        case "D9":      /* EXX */
            String work8 = B;
            B = Bp;
            Bp = work8;

            work8 = C;
            C = Cp;
            Cp = work8;

            work8 = D;
            D = Dp;
            Dp = work8;

            work8 = E;
            E = Ep;
            Ep = work8;

            work8 = H;
            H = Hp;
            Hp = work8;

            work8 = L;
            L = Lp;
            Lp = work8;
            break;
        
        case "DA":      /* JP C,nn */
            break;
        
        case "DB":      /* IN A,(n) */
        	IN = false;
        	InTexto = true;
        	AddressIN = Integer.parseInt(Memoria[PC+1],16);
        	PC+=1;
            break;
        
        case "DC":      /* CALL C,nn */
            break;
        
        case "DD":      /* Subconjunto de instrucciones */
            break;
        
        case "DE":      /* SBC A,n */
        	A = sbc(A,Memoria[PC+1]);
            PC +=1;
            break;
        
        case "DF":      /* RST 18H */
            break;
        case "E0":       /* RET PO */
            break;
        case "E1":       /* POP HL */
        	Aux = pop(SP);
            H = Aux.substring(0,2);
            L = Aux.substring(2,4);
            break;
        case "E2":       /* JP PO,nn */

            break;
        case "E3": 	    /* EX (SP),HL */
            break;
        
        case "E4":       /* CALL PO,nn */
            break;
        case "E5":       /* PUSH HL */
        	push(Integer.parseInt((H+L),16));
            break;
        case "E6":       /* AND n */
            A = and(Memoria[PC+1],A);
            PC+=1;
            break;
        case "E7":       /* RST 20H */
            break;
        case "E8":       /* RET PE */
            break;
        case "E9":       /* JP (HL) */
            break;
        case "EA":       /* JP PE,nn */
            break;
        case "EB":     /* EX DE,HL */
            work8 = H;
            H = D;
            D = work8;

            work8 = L;
            L = E;
            E = work8;
            break;
        
        case "EC":       /* CALL PE,nn */
            break;
        case "ED":    
            break;
        case "EE":       /* XOR n */
            A = xor(Memoria[PC], A);
            PC+=1;
            break;
        case "EF":       /* RST 28H */
            break;
        case "F0":       /* RET P */
            break;
        case "F1":       /* POP AF */
        	Aux = pop(SP);
            A = Aux.substring(0,2);
            F = Aux.substring(2,4);
            break;
        case "F2":       /* JP P,nn */
            break;
        case "F3":       /* DI */
            break;
        case "F4":       /* CALL P,nn */
            break;
        case "F5":       /* PUSH AF */
        	push(Integer.parseInt((A+F),16));
            break;
        case "F6":       /* OR n */
            A = or(Memoria[PC+1],A);
            PC+=1;
            break;
        case "F7":       /* RST 30H */
            break;
        case "F8":       /* RET M */
            break;
        case "F9":       /* LD SP,HL */
            SP = H+L;
            break;
        case "FA":       /* JP M,nn */
            break;
        case "FB":       /* EI */
            break;
        case "FC":       /* CALL M,nn */
            break;
        case "FD":    
            break;
        case "FE":       /* PC n */
            cp(Memoria[PC+1],A);
            PC+=1;
            break;
        case "FF":       /* RST 38H */
        	break;
		}
	}
}
