package Vistas;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Init {
	

	private JFrame frame;
	static String[] Mem;
	static int pcIn;
	int PC;
	String[] Memoria;
	String object;
	String DatosIn;
	static String binarios;
	static String DatosMem;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Init() {
		PC = pcIn;
		Memoria = Mem;
		object = binarios;
		DatosIn = DatosMem;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setFont(new Font("SansSerif", Font.BOLD, 15));
		frame.setBounds(100, 100, 1350, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("EMULACIÓN DEL PROCESADOR Z80");
		title.setFont(new Font("Dialog", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.BOTTOM);
		title.setBounds(12, 12, frame.getWidth()-220, 45);
		frame.getContentPane().add(title);
		
		JLabel labelby = new JLabel("by");
		labelby.setHorizontalAlignment(SwingConstants.CENTER);
		labelby.setBounds(12, 70, frame.getWidth()-220, 20);
		frame.getContentPane().add(labelby);
		

		JLabel name = new JLabel("BRIAN JULIAN MORENO NIAMPIRA");
		name.setFont(new Font("Dialog", Font.BOLD, 20));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setVerticalAlignment(SwingConstants.BOTTOM);
		name.setBounds(12, 100, frame.getWidth()-220, 25);
		frame.getContentPane().add(name);
		
		JLabel labelEst = new JLabel("Est. Ingenieria de Sistemas y computacion.");
		labelEst.setHorizontalAlignment(SwingConstants.CENTER);
		labelEst.setBounds(12, 130, frame.getWidth()-220, 20);
		frame.getContentPane().add(labelEst);
		
		JLabel labelEmail = new JLabel("bjmorenon@unal.edu.co");
		labelEmail.setHorizontalAlignment(SwingConstants.CENTER);
		labelEmail.setBounds(12, 160, frame.getWidth()-220, 20);
		frame.getContentPane().add(labelEmail);
		
		JLabel labelLen = new JLabel("Lenguajes de Programacion");
		labelLen.setHorizontalAlignment(SwingConstants.CENTER);
		labelLen.setBounds(12, 190, frame.getWidth()-220, 20);
		frame.getContentPane().add(labelLen);
		
		JLabel labelUnal = new JLabel("UNIVERSIDAD NACIONAL DE COLOMBIA");
		labelUnal.setHorizontalAlignment(SwingConstants.CENTER);
		labelUnal.setBounds(12, 220, frame.getWidth()-220, 25);
		frame.getContentPane().add(labelUnal);
		

		ImageIcon image = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/EScudoUnal.png"); 	
		
		JLabel img = new JLabel(" "); 
		frame.getContentPane().add(img);
		
		img.setIcon(image); 
		img.setSize(260,260); 
		img.setLocation(1088,20); 
		
		JLabel labelPro = new JLabel("Arquitectura MicroProcesador Z80");
		labelPro.setHorizontalAlignment(SwingConstants.CENTER);
		labelPro.setBounds(30, 300, 515, 20);
		frame.getContentPane().add(labelPro);
		
		ImageIcon image1 = new ImageIcon("/home/julian/eclipse (2)/WorkSpace/MicroProcesador/src/Vistas/AZ80.png"); 	
		
		JLabel img1 = new JLabel(" "); 
		frame.getContentPane().add(img1);
		
		img1.setIcon(image1); 
		img1.setSize(515,325); 
		img1.setLocation(30,325); 
		
		JLabel labelFun = new JLabel("FUNCIONAMIENTO");
		labelFun.setHorizontalAlignment(SwingConstants.CENTER);
		labelFun.setBounds(550, 300, 515, 20);
		frame.getContentPane().add(labelFun);
		
		JTextPane txtpn = new JTextPane();
		txtpn.setForeground(new Color(88,89,91));
		txtpn.setText("EL funcionamiento de este procesador se da debido a que las instrucciones "
				+ "están guardadas en memoria ROM, las cuales se van  consultando  de  forma  secuencial. Esta "
				+ " compuesto   de   registros  en   memoria  ram,  buses  de  datos,  acumuladores, banderas, registro "
				+ "de refresh y de interrupciones, la aritmética de las funciones, y las instrucciones lógicas "
				+ "de la CPU se ejecutan en la ALU, la cual realiza sumas, restas, funciones lógicas AND, OR, "
				+ "OR-EX, comparaciones, desplazamientos a la izquierda y a la derecha, incrementar y decrementar "
				+ "bits, poner bits en uno lógico, poner bits en zero lógico y comprobar el estado de los bits. "
				+ "Todo esto se puede realizar en la arquitectura del microprocesador que se muestra en la figura. "
				+ "Para lleva acabo el trabajo de este programa se pide ir realizando cada uno de los pasos de "
				+ "codificación, poniendo código en lenguaje ensamblador e ir ejecutando hasta llegar al procesador, "
				+ "cada una de estas simulaciones se verán visualmente. ");
		txtpn.setBounds(560, 340, 520, 300);
		txtpn.setEditable(false);
		

		txtpn.setFont(new Font("Dialog", Font.BOLD, 14));
		frame.getContentPane().add(txtpn);
		
		JButton btnEnsamblador = new JButton("ENSAMBLADOR");
		btnEnsamblador.setBackground(Color.WHITE);
		btnEnsamblador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ensamblador.setVisible(true);
				frame.dispose();				
			}
		});
		btnEnsamblador.setFocusable(false);
		btnEnsamblador.setBounds(1116, 305, 200, 45);
		frame.getContentPane().add(btnEnsamblador);
		
		JButton btnEnlazador = new JButton("ENLAZADOR CARGADOR");
		btnEnlazador.setBackground(Color.WHITE);
		btnEnlazador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enlazador.setVisible(true,object+"Fin",DatosIn);
				frame.setVisible(false);
				frame.dispose();
				
			}
		});
		btnEnlazador.setFocusable(false);
		btnEnlazador.setBounds(1116, 385, 200, 45);
		frame.getContentPane().add(btnEnlazador);
		
		JButton btnProcesador = new JButton("PROCESADOR");
		btnProcesador.setBackground(Color.WHITE);
		btnProcesador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procesador.setVisible(true,Memoria,PC);
				frame.setVisible(false);
				frame.dispose();
				
			}
		});
		btnProcesador.setBounds(1116, 465, 200, 45);
		btnProcesador.setFocusable(false);
		frame.getContentPane().add(btnProcesador);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(88,89,91));
		panel.setBounds(1088, 25, 260, 655);
		frame.getContentPane().add(panel);

	}

	public static void setVisible1(boolean b, String object, String Datos) {
		DatosMem = Datos;
		binarios = object;
	
		Init window = new Init();
		window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.frame.setVisible(true);
	}
	
	public static void setVisible2(boolean b, String[] mem, int PC) {
		Mem = mem;
		pcIn = PC;
		Init window = new Init();
		window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.frame.setVisible(true);
	}
	public static void setVisible(boolean b) {	
		Init window = new Init();
		window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.frame.setVisible(true);
	}
}
