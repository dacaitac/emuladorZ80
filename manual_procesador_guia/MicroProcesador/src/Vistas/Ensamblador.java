package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class Ensamblador {

	private JFrame frame;
	String texto1;
	String texto2;
	String texto3;
	String texto;
	String textoE;
	String salida;
	String DatosIn;
	String DatosAux;
	String Instrucciones;
	String etiquetas;

	/**
	 * Create the application.
	 */
	public Ensamblador() {
		this.texto1 = "";
		this.texto2 = "";
		this.texto3 = "";
		this.texto = "";
		this.salida = "";
		this.DatosIn = "";
		this.Instrucciones = "";
		this.textoE = "";
		this.etiquetas = "";
		this.DatosAux = "";
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
		
		JLabel name = new JLabel("ENSAMBLADOR");
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
				Init.setVisible1(true,salida,DatosIn);
				
			}
		});
		btnEnsamblador.setFocusable(false);
		btnEnsamblador.setBounds(1116, 305, 200, 45);
		frame.getContentPane().add(btnEnsamblador);
		
		JLabel labelPro = new JLabel("ENSAMBLADOR");
		labelPro.setForeground(Color.WHITE);
		labelPro.setFont(new Font("Dialog", Font.BOLD, 18));
		labelPro.setHorizontalAlignment(SwingConstants.CENTER);
		labelPro.setBounds(1116, 300, 200, 200);
		frame.getContentPane().add(labelPro);
		
		JTextPane txtpnHolaComoEstas = new JTextPane();
		txtpnHolaComoEstas.setFont(new Font("Dialog", Font.BOLD, 14));
		txtpnHolaComoEstas.setForeground(new Color(88,89,91));
		txtpnHolaComoEstas.setText("Esta parte del programa es la que se encarga de traducir un "
				+ "fichero fuente escrito en un lenguaje ensamblador, a un fichero objeto "
				+ "que contiene código máquina, ejecutable directamente por el microprocesador. Se puede obsevar en esta simulacion");
		txtpnHolaComoEstas.setBounds(1116, 430, 200, 230);
		txtpnHolaComoEstas.setEditable(false);
		frame.getContentPane().add(txtpnHolaComoEstas);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(88,89,91));
		panel.setBounds(1088, 25, 260, 655);
		frame.getContentPane().add(panel);
		
		JLabel labelEn = new JLabel("Insertar codigo en lenguaje ensamblador.");
		labelEn.setFont(new Font("Dialog", Font.PLAIN, 18));
		labelEn.setHorizontalAlignment(SwingConstants.LEFT);
		labelEn.setBounds(70, 130, 450, 20);
		frame.getContentPane().add(labelEn);
		
		JTextArea txtrEnsambladorE = new JTextArea();
		txtrEnsambladorE.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtrEnsambladorE.setForeground(Color.DARK_GRAY);
		txtrEnsambladorE.setBackground(Color.WHITE);
		txtrEnsambladorE.setText(texto1);
		txtrEnsambladorE.setBounds(85, 230, 120, 372);
		frame.getContentPane().add(txtrEnsambladorE);
		
		JTextArea txtrEnsambladorOPE = new JTextArea();
		txtrEnsambladorOPE.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtrEnsambladorOPE.setForeground(Color.DARK_GRAY);
		txtrEnsambladorOPE.setBackground(Color.WHITE);
		txtrEnsambladorOPE.setText(texto2);
		txtrEnsambladorOPE.setBounds(225, 230, 120, 372);
		frame.getContentPane().add(txtrEnsambladorOPE);
		
		JTextArea txtrEnsambladorOPC = new JTextArea();
		txtrEnsambladorOPC.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtrEnsambladorOPC.setForeground(Color.DARK_GRAY);
		txtrEnsambladorOPC.setBackground(Color.WHITE);
		txtrEnsambladorOPC.setText(texto3);
		txtrEnsambladorOPC.setBounds(365, 230, 160, 372);
		frame.getContentPane().add(txtrEnsambladorOPC);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(80, 225, 130, 380);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(220, 225, 130, 380);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(360, 225, 170, 380);
		frame.getContentPane().add(panel_3);
		
		JLabel labelEn1 = new JLabel("Codigo Ensamblado.");
		labelEn1.setFont(new Font("Dialog", Font.PLAIN, 18));
		labelEn1.setHorizontalAlignment(SwingConstants.LEFT);
		labelEn1.setBounds(580, 130, 450, 20);
		frame.getContentPane().add(labelEn1);
		
		JTextArea TextAreaInstruccion = new JTextArea();
		TextAreaInstruccion.setText("");
		TextAreaInstruccion.setForeground(Color.DARK_GRAY);
		TextAreaInstruccion.setFont(new Font("Dialog", Font.PLAIN, 15));
		TextAreaInstruccion.setEditable(false);
		TextAreaInstruccion.setBackground(Color.WHITE);
		TextAreaInstruccion.setBounds(595, 230, 150, 372);
		frame.getContentPane().add(TextAreaInstruccion);
		
		JTextArea txtrEnsamblado = new JTextArea();
		txtrEnsamblado.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtrEnsamblado.setForeground(Color.DARK_GRAY);
		txtrEnsamblado.setBackground(Color.WHITE);
		txtrEnsamblado.setText(salida);
		txtrEnsamblado.setBounds(765, 230, 120, 372);
		txtrEnsamblado.setEditable(false);
		frame.getContentPane().add(txtrEnsamblado);
		
		JTextArea textAreaDatos = new JTextArea();
		textAreaDatos.setText("");
		textAreaDatos.setForeground(Color.DARK_GRAY);
		textAreaDatos.setFont(new Font("Dialog", Font.PLAIN, 15));
		textAreaDatos.setEditable(false);
		textAreaDatos.setBackground(Color.WHITE);
		textAreaDatos.setBounds(905, 230, 130, 372);
		frame.getContentPane().add(textAreaDatos);
		
		JButton btnNewButton = new JButton("ENSAMBLAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salida = "";
				texto1 = txtrEnsambladorE.getText();
				texto2 = txtrEnsambladorOPE.getText();
				texto3 = txtrEnsambladorOPC.getText();
				edicion();
				code();
				txtrEnsamblado.setText(null);
				txtrEnsamblado.setText(salida);
				TextAreaInstruccion.setText(null);
				TextAreaInstruccion.setText(Instrucciones);
				textAreaDatos.setText(null);
				textAreaDatos.setText(DatosAux);
			}
		});
	
		
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(500, 625, 120, 30);
		btnNewButton.setBackground(new Color(88,89,91));
		btnNewButton.setFocusable(false);
		btnNewButton.setBorder(null);
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(760, 225, 130, 380);
		frame.getContentPane().add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(590, 225, 160, 380);
		frame.getContentPane().add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(900, 225, 140, 380);
		frame.getContentPane().add(panel_6);
		
		JLabel lblOpcode = new JLabel("INSTRUCCION");
		lblOpcode.setFont(new Font("Dialog", Font.BOLD, 15));
		lblOpcode.setForeground(Color.WHITE);
		lblOpcode.setBounds(227, 200, 120, 15);
		frame.getContentPane().add(lblOpcode);
		
		JLabel label = new JLabel("INSTRUCCION");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 15));
		label.setBounds(610, 200, 120, 15);
		frame.getContentPane().add(label);
		
		JLabel lblCodigo = new JLabel("CODIGO");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCodigo.setBounds(785, 200, 120, 15);
		frame.getContentPane().add(lblCodigo);
		
		JLabel lblEtiqueta = new JLabel("ETIQUETA");
		lblEtiqueta.setForeground(Color.WHITE);
		lblEtiqueta.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEtiqueta.setBounds(97, 200, 100, 15);
		frame.getContentPane().add(lblEtiqueta);
		
		JLabel lblDatos = new JLabel("DATOS");
		lblDatos.setForeground(Color.WHITE);
		lblDatos.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDatos.setBounds(935, 200, 120, 15);
		frame.getContentPane().add(lblDatos);
		
		JLabel Instruccion = new JLabel("PARAMETROS");
		Instruccion.setForeground(Color.WHITE);
		Instruccion.setFont(new Font("Dialog", Font.BOLD, 15));
		Instruccion.setBounds(385, 200, 130, 15);
		frame.getContentPane().add(Instruccion);
		
		JPanel panelF1 = new JPanel();
		panelF1.setBackground(Color.DARK_GRAY);
		panelF1.setBounds(70, 170, 470, 440);
		frame.getContentPane().add(panelF1);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("");
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Dialog", Font.PLAIN, 15));
		textArea.setBackground(Color.DARK_GRAY);
		panelF1.add(textArea);
		
		JPanel panelF2 = new JPanel();
		panelF2.setBackground(Color.DARK_GRAY);
		panelF2.setBounds(580, 170, 470, 440);
		frame.getContentPane().add(panelF2);
		
		JPanel panelBlanco = new JPanel();
		panelBlanco.setBackground(Color.white);
		panelBlanco.setBounds(30, 110, 1060, 560);
		frame.getContentPane().add(panelBlanco);
		
		JPanel panelReg = new JPanel();
		panelReg.setBackground(new Color(88,89,91));
		panelReg.setBounds(20, 100, 1200, 580);
		frame.getContentPane().add(panelReg);
		
	}

	public static void setVisible(boolean b) {
		// TODO Auto-generated method stub
		Ensamblador window = new Ensamblador();
		window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.frame.setVisible(true);
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////CODIGO ENSAMBLADOR//////////////////////
	///////////////////////////////////////////////////////////
	public void edicion() {
		String[] lineasE = texto1.split("\n");
		String[] lineasI = texto2.split("\n");
		String[] lineasP = texto3.split("\n");
		
		for(int i=0;i<lineasI.length;i++) {
			if(lineasE[i].trim().equals("")) {
				textoE += "-1n\n";
			}else {
				textoE += lineasE[i]+"\n";
				etiquetas += lineasE[i]+"\n";
			}
			if(i<lineasP.length) {
				texto += lineasI[i] + " " + lineasP[i] + "\n";
			}else {
				texto += lineasI[i] + " N\n";
			}
			
		}
	}
	public void code() {
		String[] etiquetasLista = etiquetas.split("\n");
		String[] etiquetaIndex = new String[etiquetasLista.length];
		String[] Etiquetas = textoE.split("\n");
		boolean esta;
		boolean datosI;
		int index = 0;
		int contador = 0;
		for(int i=0;i<etiquetaIndex.length;i++) {
			etiquetaIndex[i] = "-1";
		}
		String[] lineas = texto.split("\n");
		String[] words = new String[3];
		String binario;
		for(int i=0; i<lineas.length;i++) {
			datosI = false;
			if(!Etiquetas[i].equals("-1n")) {
				etiquetaIndex[contador] = Integer.toHexString(index).toUpperCase() + "";
				contador += 1;
			}

			if(lineas[i].equals("HALT N")) {
				Instrucciones += "HALT" + "\n";
			}else {
				Instrucciones += lineas[i] + "\n";
			}
			binario = "";
			String[] aux = lineas[i].split(" ");
			words[0] = aux[0];
			aux = aux[1].split(",");
			words[1] = aux[0];
			if(aux.length == 2) {
				words[2] = aux[1];
				if(num(words[2])) {
					if(!regular("(|))",words[2])) {
						if(words[2].length()==4) {
							DatosIn += words[2].substring(2,4);
							DatosIn += words[2].substring(0,2)+"\n";
							DatosAux += PBinario(words[2].substring(2,4)) + " " + PBinario(words[2].substring(0,2)) +"\n";
							index+=2;
							words[2] = "**";
							datosI = true;
						}else if(words[2].length()==2) {
							DatosIn += words[2]+"\n";
							DatosAux += PBinario(words[2])+"\n";
							index+=1;
							words[2] = "*";
							datosI = true;
						}
					}else if(regular("(|))",words[2])) {
						if(words[2].length()==4) {
							DatosIn += words[2].substring(2,4);
							DatosIn += words[2].substring(0,2)+"\n";
							DatosAux += PBinario(words[2].substring(2,4)) + " " + PBinario(words[2].substring(0,2))+"\n";
							words[2] = "(**)";
							datosI = true;
							index+=2;
						}else {
							//error
						}
					}
				}
			}
			if(words[0].equals("LD")) {
			    if(regular("A,B,C,D,E,L,H",words[1])) {
			    	if(regular("A,B,C,D,E,L,H",words[2])) {
			    		binario = "01";
						binario += LD1(words[1]);
						binario += LD1(words[2]);
						index+=1;
			    	}else if(words[2].equals("*")) {
			    		binario = "00";
			    		binario += LD1(words[1]);
			    		binario += "110";
			    		index+=1;
			    	}else if(words[2].equals("(**)")) {
			    		binario = "00";
			    		binario += LD1(words[1]);
			    		binario += "010";
			    		index+=1;
			    	}else if(words[2].equals("(HL)")) {
			    		binario = "01";
			    		binario += LD1(words[1]);
			    		binario += "110";
			    		index+=1;
			    	}else if(words[2].equals("(BC)")) {
			    		binario = "00001010";
			    		index+=1;
			    	}else if(words[2].equals("(DE)")) {
			    		binario = "00001100";
			    		index+=1;
			    	}
			    }else if(regular("DE,BC,HL,SP",words[1])) {
			    	binario = "00";
			    	binario += LD1(words[1].charAt(0)+"");
			    	binario += "001";
			    	index+=1;
			    }else if(regular("(DE),(BC),(HL)",words[1])) {
			    	if(words[2].equals("*")) {
			    		binario = "00110110";
			    		index+=1;
			    	}else if(regular("A,B,C,D,E,L,H",words[2])) {
			    		if(words[1].equals("(HL)")) {
			    			binario = "01";
			    			binario += "110";
			    			binario += LD1(words[2]);
			    			index+=1;
			    		}else if(words[1].equals("(BC)")) {
			    			binario = "00000010";
			    			index+=1;
			    		}else if(words[1].equals("(DE)")) {
			    			binario = "00010010";
			    			index+=1;
			    		}else {
			    			//error
			    		}			    		
			    	}else {
			    		//error
			    	}
			    }else if(words[1].equals("HL") && words[2].equals("(**)")) {
			    	binario = "00101010";
			    	index+=1;
			    }else if(words[1].equals("(**)")) {
			    	if(words[2].equals("HL")) {
			    		binario = "00100010";
			    		index+=1;
			    	}
			    	if(words[2].equals("A")) {
			    		binario = "00100011";
			    		index+=1;
			    	}
			    }
			}else if(words[0].equals("ADD")) {
				if(regular("A,B,C,D,E,L,H",words[1])) {
					if(regular("A,B,C,D,E,L,H",words[2])) {
						binario = "10000";
						binario += LD1(words[2]);
						index+=1;
					}else if(words[2].equals("*")) {
						binario = "11000110";
						index+=1;
					}else if(words[2].equals("(HL)")) {
						binario = "10000110";
						index+=1;
					}else {
						//error
					}
				}else if(words[1].equals("HL")) {
					if(words[2].equals("BC")) {
						binario = "00001001";
						index+=1;
					}else if(words[2].equals("DE")) {
						binario = "00011001";
						index+=1;
					}else if(words[2].equals("HL")) {
						binario = "00101001";
						index+=1;
					}else if(words[1].equals("SP")) {
						binario = "00111001";
						index+=1;
					}else {
						//error
					}
				}else {
					//error
				}
			}else if(words[0].equals("ADC")) {
				if(regular("A,B,C,D,E,L,H",words[1])) {
					if(regular("A,B,C,D,E,L,H",words[2])) {
						binario = "10001";
						binario += LD1(words[2]);
						index+=1;
					}else if(words[2].equals("*")){
						binario = "11001110";
						index+=1;
					}else {
						//error
					}
				}else if(words[1].equals("(HL)")) {
					if(regular("A,B,C,D,E,L,H",words[2])) {
						binario = "10001110";
						index+=1;
					}else {
						//error
					}					
				}else {
					//error
				}
			}else if(words[0].equals("SUB")) {
				if(regular("A,B,C,D,E,L,H",words[1])) {
					binario = "10010";
					binario += LD1(words[1]);
					index+=1;
				}else if(words[1].equals("(HL)")) {
					binario = "10010110";
					index+=1;
				}else if(words[1].equals("*")){
					DatosIn += words[1]+"\n";
					binario = "11010110";
					index+=1;
				}else {
					//error
				}
			}else if(words[0].equals("SBC")) {
				if(regular("A,B,C,D,E,L,H",words[1])) {
					if(regular("A,B,C,D,E,L,H",words[2])) {
						binario = "10011";
						binario += LD1(words[2]);
						index+=1;
					}else if(words[2].equals("*")){
						binario = "11011110";
						index+=1;
					}else {
						//error
					}
				}else if(words[1].equals("(HL)")) {
					if(regular("A,B,C,D,E,L,H",words[2])) {
						binario = "10011110";
						index+=1;
					}else {
						//error
					}					
				}else {
					//error
				}
			}else if(words[0].equals("AND")) {
				if(regular("A,B,C,D,E,L,H",words[1])) {
					binario = "10100";
					binario += LD1(words[1]);
					index+=1;
				}else if(words[1].equals("(HL)")){
					binario = "10100110";
					index+=1;
				}else if(words[1].equals("*")) {
					DatosIn += words[1]+"\n";
					binario = "11100110";
					index+=1;
				}else {
					//error
				}
			}else if(words[0].equals("OR")) {
				if(regular("A,B,C,D,E,L,H",words[1])) {
					binario = "10110";
					binario += LD1(words[1]);
					index+=1;
				}else if(words[1].equals("(HL)")){
					binario = "10110110";
					index+=1;
				}else if(words[1].equals("*")) {
					DatosIn += words[1]+"\n";
					binario = "11110110";
					index+=1;
				}else {
					//error
				}
			}else if(words[0].equals("XOR")) {
				if(regular("A,B,C,D,E,L,H",words[1])) {
					binario = "10101";
					binario += LD1(words[1]);
					index+=1;
				}else if(words[1].equals("(HL)")){
					binario = "10101110";
					index+=1;
				}else if(words[1].equals("*")) {
					DatosIn += words[1]+"\n";
					binario = "11101110";
					index+=1;
				}else {
					//error
				}
			}else if(words[0].equals("CP")) {
				if(regular("A,B,C,D,E,L,H",words[1])) {
					binario = "10111";
					binario += LD1(words[1]);
					index+=1;
				}else if(words[1].equals("(HL)")){
					binario = "10111110";
					index+=1;
				}else if(words[1].equals("*")) {
					binario = "11111110";
					index+=1;
				}else {
					//error
				}
			}else if(words[0].equals("INC")) {
				if(regular("A,B,C,D,E,L,H",words[1])) {
					binario = "00";
					binario += LD1(words[1]);
					binario += "100";
					index+=1;
				}else if(regular("BC,DE,HL,SP",words[1])) {
					binario = "00";
					binario += LD1(words[1].charAt(0)+"");
					binario += "011";
					index+=1;
				}else if(words[1].equals("(HL")) {
					binario = "00110100";
					index+=1;
				}else {
					//error
				}
			}else if(words[0].equals("DEC")) {
				if(regular("A,B,C,D,E,L,H",words[1])) {
					binario = "00";
					binario += LD1(words[1]);
					binario += "101";
					index+=1;
				}else if(regular("BC,DE,HL",words[1])) {
					binario = "00";
					binario += LD1(words[1].charAt(1)+"");
					binario += "011";
					index+=1;
				}else if(words[1].equals("SP")){
					binario = "00111011";
					index+=1;
				}else if(words[1].equals("(HL")) {
					binario = "00110101";
					index+=1;
				}else {
					//error
				}
			}else if(words[0].equals("RLCA")) {
				binario = "00000111";
				index+=1;
			}else if(words[0].equals("RRCA")) {
				binario = "00001111";
				index+=1;
			}else if(words[0].equals("CPL")) {
				binario = "00101111";
				index+=1;
			}else if(words[0].equals("RLA")) {
				binario = "00010111";
				index+=1;
			}else if(words[0].equals("RRA")) {
				binario = "00011111";
				index+=1;
			}else if(words[0].equals("PUSH")) {
				if(regular("BC,DE,HL",words[1])) {
					binario = "11";
					binario += LD1(words[1].charAt(0)+"");
					binario += "101";
					index+=1;
				}else if(words[1].equals("AF")) {
					binario = "11110101";
					index+=1;
				}else {
					//error
				}
			}else if(words[0].equals("POP")) {
				if(regular("BC,DE,HL",words[1])) {
					binario = "11";
					binario += LD1(words[1].charAt(0)+"");
					binario += "001";
					index+=1;
				}else if(words[1].equals("AF")) {
					binario = "11110001";
					index+=1;
				}else {
					//error
				}
			}else if(words[0].equals("JR")){
				String datoIn = "nada";
				if(words[1].equals("Z")) {
					binario = "00101000";
					datoIn = words[2];
				}else if(words[1].equals("NZ")) {
					binario = "00100000";
					datoIn = words[2];
				}else if(words[1].equals("C")) {
					binario = "00111000";
					datoIn = words[2];
				}else if(words[1].equals("NC")) {
					binario = "00110000";
					datoIn = words[2];
				}else if(num(words[1])) {
					binario = "00011000";
					DatosAux += PBinario(words[1]) + "\n";
					DatosIn += words[1] + "\n";
				}else {
					datoIn = words[1];
					binario = "00011000";
					//error
				}
				esta = false;
				int ayuda = 0;
				for(int k = 0; k < etiquetasLista.length;k++) {
					if(etiquetasLista[k].equals(datoIn)) {
						ayuda = k;
						esta = true;
						break;
					}
				}
				if(esta) {
					if(etiquetaIndex[ayuda].equals("-1")) {
						DatosAux +=datoIn + "\n";
						DatosIn += datoIn + "\n";
						datosI = true;
					}else {
						DatosAux += PBinario(etiquetaIndex[ayuda]) + "\n";
						DatosIn += etiquetaIndex[ayuda] + "\n";
						datosI = true;
					}
					index+=2;
				}else {
					//ya fue guardado
				}
			}else if(words[0].equals("HALT")){
				binario = "01110110";
				index+=2;
			}else if(words[0].equals("IN")){
				binario = "11011011";
				index+=2;
				DatosIn += words[2].substring(1, 3) +"\n";
				DatosAux += PBinario(words[2].substring(1, 3));
			}else if(words[0].equals("OUT")){
				binario = "11010011";
				index+=1;
				DatosIn += words[1].substring(1, 3) +"\n";
				DatosAux += PBinario(words[1].substring(1, 3))+"\n";
			}else{
				//error o falta instruccion por ensamblar
			}
			salida += binario + "\n";
			if(!datosI) {
				DatosAux += "\n";
			}
		}
		
		String[] corregir = DatosIn.split("\n");
		for(int j=0;j<corregir.length;j++) {
			for(int i=0;i<etiquetasLista.length;i++) {
				if(corregir[j].equals(etiquetasLista[i])) {
					corregir[j] = etiquetaIndex[i];
				}
			}
		}
		DatosIn = "";
		for(int i=0;i<corregir.length;i++) {
			DatosIn += corregir[i] + "\n";
		}
		
		
		
		corregir = DatosAux.split("\n");
		for(int j=0;j<corregir.length;j++) {
			for(int i=0;i<etiquetasLista.length;i++) {
				if(corregir[j].equals(etiquetasLista[i])) {
					corregir[j] = PBinarioN(Integer.parseInt(etiquetaIndex[i],16)+"");
				}
			}
		}
		
		DatosAux= "";
		for(int i=0;i<corregir.length;i++) {
			DatosAux += corregir[i] + "\n";
		}
	}
	
	public static boolean num(String comparar) {
		boolean encontro = false;
		boolean paso = true;
		String[] A = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		String[] B = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		String aux;
		String aux2 = "";
		if(comparar.length()==2) {
			aux2 = comparar;
		}else if(comparar.length()==4) {
			if(comparar.charAt(0) == '(') {
				aux2 = comparar.substring(1,3);
			}else {
				aux2 = comparar.substring(0,2);
			}
		}else {
			aux2 = "";
			paso = false;
		}
		if(paso) {
			for(int i = 0; i< A.length;i++) {
				for(int j = 0; j<B.length;j++) {
					aux = A[i] + B[j];
					if(aux2.equals(aux)) {
						encontro = true;
						break;
					}
				}
			}
		}
		return encontro;
	}
	
	public static boolean regular(String reg, String comparar) {
		String[] expresiones = reg.split(",");
		boolean encontro = false;
		for(int i=0;i<expresiones.length;i++) {
			if(expresiones[i].equals(comparar)) {
				encontro = true;
			}
		}
		return encontro;		
	}
	
	public static String PBinarioN(String num) {
		String binario = Integer.toBinaryString(Integer.parseInt(num));
		String binarioC = "";
		if(binario.length()<8) {
			for(int i=0;i<(8-binario.length());i++) {
				binarioC +="0";
			}
		}
		return binarioC + binario;
	}
	
	public static String PBinario(String num) {
		int numero = Integer.parseInt(num,16);
		String binario = Integer.toBinaryString(numero);
		String binarioC = "";
		if(binario.length()<8) {
			for(int i=0;i<(8-binario.length());i++) {
				binarioC +="0";
			}
		}
		return binarioC + binario;
	}
	public static String LD1(String Operando) {
		String binario = "";
		if(Operando.equals("A"))binario += "111";
		if(Operando.equals("B"))binario += "000";
		if(Operando.equals("C"))binario += "001";		
		if(Operando.equals("D"))binario += "010";		
		if(Operando.equals("E"))binario += "011";		
		if(Operando.equals("L"))binario += "101";		
		if(Operando.equals("H"))binario += "100";		
		if(Operando.equals("S"))binario += "110";
		return binario;
	}
}
