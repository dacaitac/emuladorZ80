package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class MainGui extends JFrame {

	/**
	 * Create the applet.
	 */
	private String assemblerCode = "sssssss";


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
				setAssemblerCode( loadAssembler() );
				textPane.setText( getAssemblerCode() );

			}
		});
		btnSeleccionarArchivo.setBounds(137, 520, 202, 34);
		getContentPane().add(btnSeleccionarArchivo);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(537, 92, 367, 414);
		getContentPane().add(textPane_1);

		JButton button = new JButton("Ensamblar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				textPane_1.setText(getAssemblerCode());
				// TODO: Cargar el codigo ensamblador que quedó en getAssemblerCode()
				// Y traducirlo para luego cargarlo en textPane_1.setText( 
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
				Linker linker = new Linker( getAssemblerCode() );
				linker.setVisible(true);
				linker.setBounds(200,200,940,590);
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

		JLabel label = new JLabel("Código Binario");
		label.setFont(new Font("Ubuntu", Font.BOLD, 19));
		label.setBounds(537, 46, 225, 34);
		getContentPane().add(label);

	}

	public String loadAssembler() {
		String aux="";
		String texto="";
		  try
		  {
		  	JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(this);
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			System.out.println("Seleccionar archivo Ensamblador...");
			File file = fileChooser.getSelectedFile();

		   /**recorremos el archivo, lo leemos para plasmarlo
		   *en el area de texto*/
		   if(file!=null)
		   {
		      FileReader archivos=new FileReader(file);
		      BufferedReader lee = new BufferedReader(archivos);
		      while((aux = lee.readLine())!=null)
		      {
		         texto += aux+ "\n";
		      }
		         lee.close();
		    }
		   }
		   catch(IOException ex)
		   {
		     JOptionPane.showMessageDialog(null,ex+"" +
		           "\nNo se ha encontrado el archivo",
		                 "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
		    }
		  return texto;
	}

}
