package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import Machine.Memory;

import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class Computer extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private int size = 100;
	//private String datas[][] = new String[memory.getMemorySize()][2];
	private String datas[][] = new String[size][2];
	private String column[] = {"Posición", "Instrucción"};
	
	public Computer(Memory memory) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1032, 516);
		contentPane = new JPanel();
		getContentPane().setBackground(new Color(177, 178, 176));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		for (int i = 0 ; i < size; i++) {			
			datas[i][0] = Integer.toHexString(i).toUpperCase();
			datas[i][1] = memory.getInstr(Integer.toHexString(i).toUpperCase()); 		
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 81, 380, 361);
		contentPane.add(scrollPane);
		table = new JTable(datas, column);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("Memoria");
		label.setFont(new Font("Ubuntu", Font.BOLD, 19));
		label.setBounds(54, 35, 225, 34);
		contentPane.add(label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(454, 81, 504, 181);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRegistros = new JLabel("Registros");
		lblRegistros.setForeground(new Color(70, 107, 63));
		lblRegistros.setBounds(23, 26, 132, 23);
		lblRegistros.setFont(new Font("Ubuntu", Font.BOLD, 17));
		panel.add(lblRegistros);
		
		JLabel lblAf = new JLabel("HL");
		lblAf.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblAf.setBounds(23, 78, 39, 23);
		panel.add(lblAf);
		
		JLabel label_1 = new JLabel("00000000");
		label_1.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_1.setBounds(74, 78, 102, 23);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("00000000");
		label_2.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_2.setBounds(167, 78, 102, 23);
		panel.add(label_2);
		
		JLabel lblAf_1 = new JLabel("AF");
		lblAf_1.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblAf_1.setBounds(23, 61, 39, 23);
		panel.add(lblAf_1);
		
		JLabel label_4 = new JLabel("00000000");
		label_4.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_4.setBounds(74, 61, 102, 23);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("00000000");
		label_5.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_5.setBounds(167, 61, 102, 23);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("00000000");
		label_6.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_6.setBounds(167, 96, 102, 23);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("00000000");
		label_7.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_7.setBounds(74, 96, 102, 23);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("DE");
		label_8.setFont(new Font("Ubuntu", Font.BOLD, 16));
		label_8.setBounds(23, 96, 39, 23);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("BC");
		label_9.setFont(new Font("Ubuntu", Font.BOLD, 16));
		label_9.setBounds(23, 113, 39, 23);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("00000000");
		label_10.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_10.setBounds(74, 113, 102, 23);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("00000000");
		label_11.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_11.setBounds(167, 113, 102, 23);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("00000000");
		label_12.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_12.setBounds(167, 131, 102, 23);
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("00000000");
		label_13.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_13.setBounds(74, 131, 102, 23);
		panel.add(label_13);
		
		JLabel label_14 = new JLabel("IX");
		label_14.setFont(new Font("Ubuntu", Font.BOLD, 16));
		label_14.setBounds(23, 131, 39, 23);
		panel.add(label_14);
		
		JLabel lblIr = new JLabel("IR");
		lblIr.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblIr.setBounds(23, 148, 39, 23);
		panel.add(lblIr);
		
		JLabel label_16 = new JLabel("00000000");
		label_16.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_16.setBounds(74, 148, 102, 23);
		panel.add(label_16);
		
		JLabel label_17 = new JLabel("00000000");
		label_17.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_17.setBounds(167, 148, 102, 23);
		panel.add(label_17);
		
		JLabel lblAlu = new JLabel("Flags\n");
		lblAlu.setForeground(new Color(70, 107, 63));
		lblAlu.setFont(new Font("Ubuntu", Font.BOLD, 17));
		lblAlu.setBounds(288, 26, 132, 23);
		panel.add(lblAlu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(454, 274, 504, 168);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_3 = new JLabel("Instrucción en Curso");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Ubuntu", Font.BOLD, 16));
		label_3.setBounds(12, 59, 480, 23);
		panel_1.add(label_3);
		
		JLabel label_15 = new JLabel("00000000");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_15.setBounds(12, 82, 480, 23);
		panel_1.add(label_15);
		
		JLabel label_18 = new JLabel("Siguiente Instrucción");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Ubuntu", Font.BOLD, 16));
		label_18.setBounds(12, 113, 480, 23);
		panel_1.add(label_18);
		
		JLabel label_19 = new JLabel("00000000");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_19.setBounds(12, 136, 480, 23);
		panel_1.add(label_19);
		
		JLabel lblUnidadDeControl = new JLabel("Unidad de Control");
		lblUnidadDeControl.setFont(new Font("Ubuntu", Font.BOLD, 17));
		lblUnidadDeControl.setBounds(12, 12, 239, 23);
		panel_1.add(lblUnidadDeControl);
	}
}
