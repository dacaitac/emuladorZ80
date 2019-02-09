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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Enlazador {

	private JFrame frame;
	String[] Memoria;
	String object;
	String DatosIn;
	static String[] lineas;
	static String[] Datos;
	static int PC;
	private int PCinicial;
	static int DC;
	static int i;
	static int len;
	static boolean indiceInicial;
	private JTextField txtHola;

	public Enlazador() {
		this.object = "";
		this.DatosIn = "";
		Memoria = new String[65536];
		PC = 0;
		DC = 0;
		i = 0;
		PCinicial = 0;
		indiceInicial = false;
		for(int i=0;i<65536;i++) {
			Memoria[i] = "00";
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("EMULACIÓN DEL PROCESADOR Z80");
		title.setFont(new Font("Dialog", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.BOTTOM);
		title.setBounds(12, 12, frame.getWidth()-220, 45);
		frame.getContentPane().add(title);
		
		JLabel name = new JLabel("Enlazador");
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
		
		txtHola = new JTextField();
		txtHola.setText("00000");
		txtHola.setFont(new Font("Dialog", Font.BOLD, 30));
		txtHola.setBackground(Color.DARK_GRAY);
		txtHola.setForeground(Color.WHITE);
		txtHola.setBounds(135, 180, 114, 31);
		txtHola.setBorder(null);
		frame.getContentPane().add(txtHola);
		txtHola.setColumns(10);
		
		JButton btnEnsamblador = new JButton("Inicio");
		btnEnsamblador.setBackground(Color.WHITE);
		btnEnsamblador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Init.setVisible2(true,Memoria,Integer.parseInt(txtHola.getText()));
			}
		});
		btnEnsamblador.setBounds(1116, 305, 200, 45);
		btnEnsamblador.setFocusable(false);
		frame.getContentPane().add(btnEnsamblador);
		
		JLabel labelPro = new JLabel("ENLAZADOR");
		labelPro.setForeground(Color.WHITE);
		labelPro.setFont(new Font("Dialog", Font.BOLD, 18));
		labelPro.setHorizontalAlignment(SwingConstants.CENTER);
		labelPro.setBounds(1116, 300, 200, 200);
		frame.getContentPane().add(labelPro);
		
		JLabel lblIndex = new JLabel("INDEX");
		lblIndex.setBackground(Color.DARK_GRAY);
		lblIndex.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndex.setForeground(Color.DARK_GRAY);
		lblIndex.setFont(new Font("Dialog", Font.BOLD, 18));
		lblIndex.setBounds(40, 167, 100, 60);
		frame.getContentPane().add(lblIndex);
		
		JTextPane txtpnHolaComoEstas = new JTextPane();
		txtpnHolaComoEstas.setFont(new Font("Dialog", Font.BOLD, 14));
		txtpnHolaComoEstas.setForeground(new Color(88,89,91));
		txtpnHolaComoEstas.setText("Esta parte del programa es la que se encarga de cargar el programa en la memoria, como se puede ver en esta simulacion.");
		txtpnHolaComoEstas.setBounds(1116, 430, 200, 210);
		txtpnHolaComoEstas.setEditable(false);
		frame.getContentPane().add(txtpnHolaComoEstas);
				
		JPanel panel = new JPanel();
		panel.setBackground(new Color(88,89,91));
		panel.setBounds(1088, 25, 260, 655);
		frame.getContentPane().add(panel);
		
		JPanel panelList = new JPanel(); 
		panelList.setBackground(new Color(88,89,91));
		panelList.setBounds(59, 240, 1002, 410);
		panelList.setLayout(null); 
		DefaultListModel<String> model = new DefaultListModel<String>();
		String modelo = "";
		int pc1 = 0;
		String indexM = "";
		String Aux = "";
		for(int i = 0; i<2048; i++){
			Aux = "";
			indexM = i*32 +"";
			for(int j = 0;j<(5-indexM.length());j++) {
				Aux += "0";
			}
			indexM = Aux + indexM + " ";
			modelo = "";
			modelo += " " + indexM + " ";
			for (int j = 0; j<32;j++) {
				modelo+= Memoria[pc1] + " ";
				pc1++;
			}
			model.addElement(modelo);
		}
		JList<String> list = new JList<String>(model);
		list.setSelectedIndices(new int[] {2028});
		list.setSelectedIndex(0);
		list.setFont(new Font("Dialog", Font.BOLD, 16));
		list.setVisibleRowCount(20);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
		JScrollPane barraDesplazamiento = new JScrollPane(list); 
		barraDesplazamiento.setBackground(Color.white);
		barraDesplazamiento.setBounds(6,6,990,398); 
		panelList.add(barraDesplazamiento); 
		frame.getContentPane().add(panelList);
		
		
		JTextArea txtrInstruccion = new JTextArea();
		txtrInstruccion.setForeground(Color.WHITE);
		txtrInstruccion.setFont(new Font("Dialog", Font.BOLD, 30));
		txtrInstruccion.setBackground(Color.DARK_GRAY);
		txtrInstruccion.setText(lineas[0]);
		txtrInstruccion.setBounds(270, 180, 500, 30);
		frame.getContentPane().add(txtrInstruccion);
		
		JButton btnIniciar = new JButton("CARGAR");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!indiceInicial){
					String indiceInit = txtHola.getText();
					PC = Integer.parseInt(indiceInit);
					PCinicial = Integer.parseInt(indiceInit);
					txtHola.setEditable(false);
					indiceInicial = true;
				}
				if(i!=-1) {
					if(i<len-1) {
						txtrInstruccion.setText(lineas[i+1]);
						enlazar(lineas[i]);
						DefaultListModel<String> modelA = new DefaultListModel<String>();
						String modelo = "";
						int pc1 = 0;
						String indexM = "";
						String Aux = "";
						for(int i = 0; i<2048; i++){
							Aux = "";
							indexM = i*32 +"";
							for(int j = 0;j<(5-indexM.length());j++) {
								Aux += "0";
							}
							indexM = Aux + indexM + " ";
							modelo = "";
							modelo += " " + indexM + " ";
							for (int j = 0; j<32;j++) {
								modelo+= Memoria[pc1] + " ";
								pc1++;
							}
							modelA.addElement(modelo);
						}
						list.setModel(modelA);
						i++;
					}else {
						txtrInstruccion.setText("Fin");
						i=-1;
					}
				}
			}
		});
		
		JButton btnTodo = new JButton("CARGAR TODO");
		btnTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!indiceInicial){
					String indiceInit = txtHola.getText();
					PC = Integer.parseInt(indiceInit);
					PCinicial = Integer.parseInt(indiceInit);
					txtHola.setEditable(false);
					indiceInicial = true;
				}
				if(i!=-1) {
					while(i!=-1) {
						txtrInstruccion.setText("");
						if(i<len-1) {
							txtrInstruccion.setText(lineas[i+1]);
							enlazar(lineas[i]);
							
							i++;
						}else {
							txtrInstruccion.setText("Fin");
							i=-1;
							DefaultListModel<String> modelA = new DefaultListModel<String>();
							String modelo = "";
							int pc1 = 0;
							String indexM = "";
							String Aux = "";
							for(int i = 0; i<2048; i++){
								Aux = "";
								indexM = i*32 +"";
								for(int j = 0;j<(5-indexM.length());j++) {
									Aux += "0";
								}
								indexM = Aux + indexM + " ";
								modelo = "";
								modelo += " " + indexM + " ";
								for (int j = 0; j<32;j++) {
									modelo+= Memoria[pc1] + " ";
									pc1++;
								}
								modelA.addElement(modelo);
							}
							list.setModel(modelA);
						}
					}
				}				
			}
		});
		btnTodo.setForeground(Color.WHITE);
		btnTodo.setFocusable(false);
		btnTodo.setBorder(null);
		btnTodo.setBackground(Color.DARK_GRAY);
		btnTodo.setBounds(926, 180, 135, 30);
		frame.getContentPane().add(btnTodo);
		btnIniciar.setBackground(Color.DARK_GRAY);
		btnIniciar.setForeground(Color.WHITE);
		btnIniciar.setBorder(null);
		btnIniciar.setFocusable(false);
		btnIniciar.setBounds(790, 180, 120, 30);
		frame.getContentPane().add(btnIniciar);
		
		JPanel panelBlanco = new JPanel();
		panelBlanco.setBackground(Color.white);
		panelBlanco.setBounds(30, 110, 1060, 560);
		frame.getContentPane().add(panelBlanco);
		
		JPanel panelReg = new JPanel();
		panelReg.setBackground(new Color(88,89,91));
		panelReg.setBounds(20, 100, 1200, 580);
		frame.getContentPane().add(panelReg);
	}

	public static void setVisible(boolean b,String object,String DatosIn) {
		lineas = object.split("\n");
		len = lineas.length;
		Datos = DatosIn.split("\n");
		Enlazador window = new Enlazador();
		window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.frame.setVisible(true);
	}
	
	///////////////////////////////////////////////////////
	///////////////////////CODIGO ENLAZADOR////////////////
	///////////////////////////////////////////////////////
	public void enlazar(String dato) {
			if(codes(dato)) {
				Memoria[PC] = Corregir(Integer.toHexString(Integer.valueOf(dato, 2)).toUpperCase());
				Memoria[PC+1] = Corregir(Datos[DC]);
				PC+=2;
				DC++;
			}else if((dato.substring(0, 2).equals("00")) && (dato.substring(5, 8).equals("110"))) {
				if(codes1(dato.substring(2, 5))) {
					Memoria[PC] = Corregir(Integer.toHexString(Integer.valueOf(dato, 2)).toUpperCase());
					Memoria[PC+1] = Corregir(Datos[DC]);
					PC+=2;
					DC++;
				}else {
					Memoria[PC] = Corregir(Integer.toHexString(Integer.valueOf(dato, 2)).toUpperCase());
					PC++;
				}
			}else if((dato.substring(0, 2).equals("00")) && (dato.substring(5, 8).equals("010"))) {
				if(codes1(dato.substring(2, 5))) {
					Memoria[PC] = Corregir(Integer.toHexString(Integer.valueOf(dato, 2)).toUpperCase());
					if(Datos[DC].length()==4) {
						Memoria[PC+1] = Corregir(Datos[DC].substring(0, 2));
						Memoria[PC+2] = Corregir(Datos[DC].substring(2, 4));
						DC++;;
						PC+=2;
					}else {
						System.out.println("Error");
					}
					PC++;
				}else {
					Memoria[PC] = Corregir(Integer.toHexString(Integer.valueOf(dato, 2)).toUpperCase());
					PC++;
				}
			}else if((dato.substring(0, 2).equals("00")) && (dato.substring(5, 8).equals("001"))) {
				if(codes1(dato.substring(2, 5))) {
					Corregir(Memoria[PC] = Integer.toHexString(Integer.valueOf(dato, 2)).toUpperCase());
					if(Datos[DC].length()==4) {
						Memoria[PC+1] = Corregir(Datos[DC].substring(0, 2));
						Memoria[PC+2] = Corregir(Datos[DC].substring(2, 4));
						DC++;;
						PC+=2;
					}else {
						System.out.println("Error");
					}
					PC++;
				}else {
					Memoria[PC] = Corregir(Integer.toHexString(Integer.valueOf(dato, 2)).toUpperCase());
					PC++;
				}
			}else if(dato.equals("00110110")){
				Memoria[PC] = Corregir(Integer.toHexString(Integer.valueOf(dato, 2)).toUpperCase());
				Memoria[PC+1] = Corregir(Datos[DC].substring(0, 2));
				Memoria[PC+2] = Corregir(Datos[DC].substring(2, 4));
				PC+=3;
				DC++;
			}else if(codesJump(dato)){
				Memoria[PC] = Corregir(Integer.toHexString(Integer.valueOf(dato, 2)).toUpperCase());
				Memoria[PC+1] = Corregir(Integer.toHexString(Integer.parseInt(Datos[DC],16)+PCinicial).toUpperCase());
				PC+=2;
				DC++;
			}else if(dato.equals("11011011") || dato.equals("11010011")){
				Memoria[PC] = Corregir(Integer.toHexString(Integer.valueOf(dato, 2)).toUpperCase());
				Memoria[PC+1] = Corregir(Datos[DC]);
				DC++;
				PC+=2;
			}else {
				Memoria[PC] = Corregir(Integer.toHexString(Integer.valueOf(dato, 2)).toUpperCase());
				PC+=1;
			}
	}
	public static boolean codes(String code) {
		String[] codesBin = {"11000110","11001110","11010110","11011110",
				"11100110","11110110","11101110","11111110","00101010"};
		for(int i=0;i<codesBin.length;i++) {
			if(codesBin[i].equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	public static String Corregir(String dato) {
		if(dato.length()==0) {
			return "00";
		}else if(dato.length()==1) {
			return "0" + dato;
		}else {
			return dato;
		}
	}
	
	public static boolean codesJump(String code) {
		String[] codesBin = {"00011000","00101000","00100000","00111000","00110000"};
		for(int i=0;i<codesBin.length;i++) {
			if(codesBin[i].equals(code)) {
				return true;
			}
		}
		return false;
	}
	public static boolean codes1(String code) {
		String[] codesBin = {"111","000","001","010","011","101","100"};
		for(int i=0;i<codesBin.length;i++) {
			if(codesBin[i].equals(code)) {
				return true;
			}
		}
		return false;
	}
}
