package gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Machine.Memory;
import controller.LinkerLoader;
import controller.Util;

public class Linker extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	
	//int size = (int)Math.pow(2, 16);
	int size = 100;
	private Memory memory = new Memory();
	private Util util = new Util();	
	//private String datas[][] = new String[memory.getMemorySize()][2];
	private String datas[][] = new String[size][2];
	private String column[] = {"Posición", "Instrucción"};
	
	public Linker(String binaryCode) {
		getContentPane().setBackground(new Color(177, 178, 176));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Memoria");
		label.setFont(new Font("Ubuntu", Font.BOLD, 19));
		label.setBounds(498, 12, 225, 34);
		contentPane.add(label);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Ubuntu", Font.PLAIN, 17));
		textPane.setText(binaryCode);
		textPane.setEditable(false);
		textPane.setBounds(35, 59, 446, 377);
		contentPane.add(textPane);
		
		for (int i = 0 ; i < size; i++) {			
			datas[i][0] = Integer.toHexString(i).toUpperCase();
			datas[i][1] = memory.getInstr(Integer.toHexString(i).toUpperCase()); 		
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(498, 59, 415, 469);
		contentPane.add(scrollPane);
		
		table = new JTable(datas, column);
		scrollPane.setViewportView(table);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		
		JLabel label_1 = new JLabel("Código Binario");
		label_1.setFont(new Font("Ubuntu", Font.BOLD, 19));
		label_1.setBounds(35, 12, 225, 34);
		contentPane.add(label_1);
		
		JLabel lblCargarEnPosicin = new JLabel("Cargar en posición (Entero): ");
		lblCargarEnPosicin.setFont(new Font("Ubuntu", Font.BOLD, 19));
		lblCargarEnPosicin.setBounds(45, 448, 280, 34);
		contentPane.add(lblCargarEnPosicin);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
			      if (!((c >= '0') && (c <= '9') ||
			         (c == KeyEvent.VK_BACK_SPACE) ||
			         (c == KeyEvent.VK_DELETE))) {
			        getToolkit().beep();
			        e.consume();
			      }
			}
		});
		textField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);		
		textField.setText("00000");
		textField.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		textField.setBounds(323, 448, 92, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("Cargar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {							
				LinkerLoader linker = new LinkerLoader();
				int position = Integer.parseInt(textField.getText());				
				memory = linker.loadCode(binaryCode, position);				
				for (int i = 0 ; i < size; i++) {			
					datas[i][0] = Integer.toHexString(i).toUpperCase();
					datas[i][1] = memory.getInstr(Integer.toHexString(i).toUpperCase()); 		
				}			
				
				table = new JTable(datas, column);		
				table = new JTable(datas, column);
				scrollPane.setViewportView(table);
				table.setShowVerticalLines(false);
				table.setShowHorizontalLines(false);
				table.getColumnModel().getColumn(0).setMaxWidth(50);;				
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Ubuntu", Font.BOLD, 14));
		button.setBackground(new Color(166, 28, 49));
		button.setBounds(35, 494, 202, 34);
		contentPane.add(button);
		
		JButton btnComputar = new JButton("Computar");
		btnComputar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Computer processor = new Computer(memory);
				processor.setVisible(true);
				processor.setBounds(200,200,1000,610);
			}
		});
		btnComputar.setForeground(Color.WHITE);
		btnComputar.setFont(new Font("Ubuntu", Font.BOLD, 14));
		btnComputar.setBackground(new Color(166, 28, 49));
		btnComputar.setBounds(279, 494, 202, 34);
		contentPane.add(btnComputar);
	}
}
