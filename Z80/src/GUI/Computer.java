package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

//import Machine.Memory;

import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import z80.Memory;
import z80.Processor;

public class Computer extends JFrame {
	// 8 bits
	private final int A = 0, B = 1, C = 2, D = 3, E = 4, H = 5, L = 6;
	private final int Ac = 7, Bc = 8, Cc = 9, Dc = 10, Ec = 11, Hc = 12, Lc = 13;

	// 16 bits
	private final int PC = 0, SP = 1, IX = 2, IY = 3;

	// Registros
	private int[] reg_8bit;
	private int[] reg_16bit;

	private Processor processor;

	private JPanel contentPane;
	private JTable table;

//	private int size = 100;
	private int size = 65535;
	// private String datas[][] = new String[memory.getMemorySize()][2];
	private String datas[][] = new String[size][2];
	private String column[] = { "Posición", "Instrucción" };

//	public Computer(Memory memory) {
	public Computer(Memory memory) {
		processor = new Processor(memory);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1153, 532);
		contentPane = new JPanel();
		getContentPane().setBackground(new Color(177, 178, 176));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		for (int i = 0; i < size; i++) {
			datas[i][0] = Integer.toHexString(i).toUpperCase();
			datas[i][1] = memory.get(i) + "";
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(674, 46, 432, 417);
		contentPane.add(scrollPane);
		table = new JTable(datas, column);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		scrollPane.setViewportView(table);

		JLabel label = new JLabel("Memoria");
		label.setFont(new Font("Ubuntu", Font.BOLD, 19));
		label.setBounds(541, 0, 225, 34);
		contentPane.add(label);

		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(33, 51, 604, 339);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblRegistros = new JLabel("Registros 8 Bits");
		lblRegistros.setForeground(new Color(70, 107, 63));
		lblRegistros.setBounds(83, 41, 145, 23);
		lblRegistros.setFont(new Font("Ubuntu", Font.BOLD, 17));
		panel.add(lblRegistros);

		JLabel lblAf_1 = new JLabel("A (Acumulador)");
		lblAf_1.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblAf_1.setBounds(22, 209, 156, 23);
		panel.add(lblAf_1);

		JLabel label_4 = new JLabel("00000000");
		label_4.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_4.setBounds(158, 209, 102, 23);
		panel.add(label_4);

		JLabel label_8 = new JLabel("B");
		label_8.setFont(new Font("Ubuntu", Font.BOLD, 16));
		label_8.setBounds(55, 76, 39, 23);
		panel.add(label_8);

		JLabel label_10 = new JLabel("00000000");
		label_10.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_10.setBounds(22, 90, 102, 23);
		panel.add(label_10);

		JLabel lblAlu = new JLabel("F (Flags)\n");
		lblAlu.setForeground(new Color(70, 107, 63));
		lblAlu.setFont(new Font("Ubuntu", Font.BOLD, 17));
		lblAlu.setBounds(23, 267, 132, 23);
		panel.add(lblAlu);

		JLabel lblC = new JLabel("C");
		lblC.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblC.setBounds(191, 76, 39, 23);
		panel.add(lblC);

		JLabel label_2 = new JLabel("00000000");
		label_2.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_2.setBounds(158, 90, 102, 23);
		panel.add(label_2);

		JLabel lblD = new JLabel("D");
		lblD.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblD.setBounds(55, 111, 39, 23);
		panel.add(lblD);

		JLabel label_5 = new JLabel("00000000");
		label_5.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_5.setBounds(22, 125, 102, 23);
		panel.add(label_5);

		JLabel lblE = new JLabel("E");
		lblE.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblE.setBounds(191, 111, 39, 23);
		panel.add(lblE);

		JLabel label_7 = new JLabel("00000000");
		label_7.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_7.setBounds(158, 125, 102, 23);
		panel.add(label_7);

		JLabel lblH = new JLabel("H");
		lblH.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblH.setBounds(55, 146, 39, 23);
		panel.add(lblH);

		JLabel label_11 = new JLabel("00000000");
		label_11.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_11.setBounds(22, 160, 102, 23);
		panel.add(label_11);

		JLabel lblL = new JLabel("L");
		lblL.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblL.setBounds(191, 146, 39, 23);
		panel.add(lblL);

		JLabel label_13 = new JLabel("00000000");
		label_13.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_13.setBounds(158, 160, 102, 23);
		panel.add(label_13);

		JLabel lblRegistrosBits = new JLabel("Registros 16 Bits");
		lblRegistrosBits.setForeground(new Color(70, 107, 63));
		lblRegistrosBits.setFont(new Font("Ubuntu", Font.BOLD, 17));
		lblRegistrosBits.setBounds(350, 41, 145, 23);
		panel.add(lblRegistrosBits);

		JLabel lblIx = new JLabel("IX");
		lblIx.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblIx.setBounds(401, 76, 39, 23);
		panel.add(lblIx);

		JLabel label_6 = new JLabel("0000000000000000");
		label_6.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_6.setBounds(340, 90, 178, 23);
		panel.add(label_6);

		JLabel lblIy = new JLabel("IY");
		lblIy.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblIy.setBounds(401, 132, 39, 23);
		panel.add(lblIy);

		JLabel label_9 = new JLabel("0000000000000000");
		label_9.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_9.setBounds(340, 146, 178, 23);
		panel.add(label_9);

		JLabel lblSp = new JLabel("SP");
		lblSp.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblSp.setBounds(401, 183, 39, 23);
		panel.add(lblSp);

		JLabel label_14 = new JLabel("0000000000000000");
		label_14.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_14.setBounds(340, 197, 178, 23);
		panel.add(label_14);

		JLabel lblPc = new JLabel("PC");
		lblPc.setFont(new Font("Ubuntu", Font.BOLD, 16));
		lblPc.setBounds(401, 235, 39, 23);
		panel.add(lblPc);

		JLabel label_17 = new JLabel("0000000000000000");
		label_17.setFont(new Font("Ubuntu", Font.BOLD, 17));
		label_17.setBounds(340, 249, 178, 23);
		panel.add(label_17);

		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!processor.isEnd()) {
					int[] reg = processor.getReg_16bit();

					table.changeSelection(reg[0], 2, false, false);

					try {
						processor.fetch();
						processor.execute();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					setReg_16bit(processor.getReg_16bit());
					setReg_8bit(processor.getReg_8bit());

					label_10.setText(String.format("%8s", Integer.toBinaryString(reg_8bit[B])).replace(" ", "0"));
					label_2.setText(String.format("%8s", Integer.toBinaryString(reg_8bit[C])).replace(" ", "0"));
					label_5.setText(String.format("%8s", Integer.toBinaryString(reg_8bit[D])).replace(" ", "0"));
					label_7.setText(String.format("%8s", Integer.toBinaryString(reg_8bit[E])).replace(" ", "0"));
					label_11.setText(String.format("%8s", Integer.toBinaryString(reg_8bit[H])).replace(" ", "0"));
					label_13.setText(String.format("%8s", Integer.toBinaryString(reg_8bit[L])).replace(" ", "0"));
					label_4.setText(String.format("%8s", Integer.toBinaryString(reg_8bit[A])).replace(" ", "0"));

					label_6.setText(String.format("%16s", Integer.toBinaryString(reg_16bit[IX])).replace(" ", "0"));
					label_9.setText(String.format("%16s", Integer.toBinaryString(reg_16bit[IY])).replace(" ", "0"));
					label_14.setText(String.format("%16s", Integer.toBinaryString(reg_16bit[SP])).replace(" ", "0"));
					label_17.setText(String.format("%16s", Integer.toBinaryString(reg_16bit[PC])).replace(" ", "0"));
				}
			}
		});
		btnProcesar.setForeground(Color.WHITE);
		btnProcesar.setFont(new Font("Ubuntu", Font.BOLD, 14));
		btnProcesar.setBackground(new Color(166, 28, 49));
		btnProcesar.setBounds(107, 429, 202, 34);
		contentPane.add(btnProcesar);

		JButton btnProcesarTodo = new JButton("Procesar Todo");
		btnProcesarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] reg = processor.getReg_16bit();
				while (!processor.isEnd()) {
					try {
						table.changeSelection(reg[0], 2, false, false);
						processor.fetch();
						processor.execute();

						setReg_16bit(processor.getReg_16bit());
						setReg_8bit(processor.getReg_8bit());

						label_10.setText(reg_8bit[B] + "");
						label_2.setText(reg_8bit[C] + "");
						label_5.setText(reg_8bit[D] + "");
						label_7.setText(reg_8bit[E] + "");
						label_11.setText(reg_8bit[H] + "");
						label_13.setText(reg_8bit[L] + "");

						label_6.setText(reg_16bit[IX] + "");
						label_9.setText(reg_16bit[IY] + "");
						label_14.setText(reg_16bit[SP] + "");
						label_17.setText(reg_16bit[PC] + "");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnProcesarTodo.setForeground(Color.WHITE);
		btnProcesarTodo.setFont(new Font("Ubuntu", Font.BOLD, 14));
		btnProcesarTodo.setBackground(new Color(166, 28, 49));
		btnProcesarTodo.setBounds(364, 429, 202, 34);
		contentPane.add(btnProcesarTodo);
	}

	public int[] getReg_16bit() {
		return reg_16bit;
	}

	public void setReg_16bit(int[] reg_16bit) {
		this.reg_16bit = reg_16bit;
	}

	public int[] getReg_8bit() {
		return reg_8bit;
	}

	public void setReg_8bit(int[] reg_8bit) {
		this.reg_8bit = reg_8bit;
	}

}
