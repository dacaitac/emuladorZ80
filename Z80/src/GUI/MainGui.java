package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import z80.Assembler;
import z80.LinkerLoader;
import z80.Memory;

public class MainGui extends JFrame {

	/**
	 * Create the applet.
	 */
	private String assemblerCode = "";
	Assembler assembler = new Assembler();
	int[] as;
	static LinkerLoader linkerLoader = new LinkerLoader();
	static Memory memory;
	private String[] strInstr;
	static ArrayList<int[]> instr;
	public static int org;

	public String getAssemblerCode() {
		return assemblerCode;
	}

	public void setAssemblerCode(String assemblerCode) {
		this.assemblerCode = assemblerCode;
	}

	public MainGui() {
		getContentPane().setBackground(new Color(177, 178, 176));
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 92, 367, 414);
		getContentPane().add(scrollPane);

		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);

		JButton btnSeleccionarArchivo = new JButton("Seleccionar Archivo");
		btnSeleccionarArchivo.setFont(new Font("Ubuntu", Font.BOLD, 14));
		btnSeleccionarArchivo.setForeground(Color.WHITE);
		btnSeleccionarArchivo.setBackground(new Color(166, 28, 49));
		btnSeleccionarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAssemblerCode(loadAssembler());
				textPane.setText(getAssemblerCode());
			}
		});
		btnSeleccionarArchivo.setBounds(137, 520, 202, 34);
		getContentPane().add(btnSeleccionarArchivo);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(537, 92, 367, 414);
		getContentPane().add(scrollPane_1);

		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);

		JButton button = new JButton("Ensamblar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strInstr = getAssemblerCode().split("\n");
				try {
					instr = assembler.assemble(strInstr);
					org = assembler.getOrgPointer();
					System.out.println("LA DIRECCION DE ARRANQUE ES: " + org);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String str = "";
				for (int[] is : instr)
					str += linkerLoader.binaryInstruction(is) + "\n";

				setAssemblerCode(str);
				textPane_1.setText(str);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Ubuntu", Font.BOLD, 14));
		button.setBackground(new Color(166, 28, 49));
		button.setBounds(617, 520, 202, 34);
		getContentPane().add(button);

		JButton button_1 = new JButton("Cargar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memory = new Memory();
				memory.setOrg(org);
				try {
					linkerLoader.chargeProgram(instr, memory);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				Computer computer = new Computer(memory);
				computer.setVisible(true);
				computer.setBounds(200, 200, 900, 530);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Ubuntu", Font.BOLD, 14));
		button_1.setBackground(new Color(166, 28, 49));
		button_1.setBounds(375, 567, 202, 34);
		getContentPane().add(button_1);

		JLabel lblCdigoEnsamblador = new JLabel("Código Ensamblador");
		lblCdigoEnsamblador.setFont(new Font("Ubuntu", Font.BOLD, 19));
		lblCdigoEnsamblador.setBounds(57, 46, 225, 34);
		getContentPane().add(lblCdigoEnsamblador);

		JLabel lblCdigoRealocable = new JLabel("Código Realocable");
		lblCdigoRealocable.setFont(new Font("Ubuntu", Font.BOLD, 19));
		lblCdigoRealocable.setBounds(537, 46, 225, 34);
		getContentPane().add(lblCdigoRealocable);

	}

	public String loadAssembler() {
		String aux = "";
		String texto = "";
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(this);
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			System.out.println("Seleccionar archivo Ensamblador...");
			File file = fileChooser.getSelectedFile();

			/**
			 * recorremos el archivo, lo leemos para plasmarlo en el area de texto
			 */
			if (file != null) {
				FileReader archivos = new FileReader(file);
				BufferedReader lee = new BufferedReader(archivos);
				System.out.println(file.getAbsolutePath());

				while ((aux = lee.readLine()) != null)
					texto += aux + "\n";
				lee.close();
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex + "" + "\nNo se ha encontrado el archivo", "ADVERTENCIA!!!",
					JOptionPane.WARNING_MESSAGE);
		}
		return texto;
	}

}
