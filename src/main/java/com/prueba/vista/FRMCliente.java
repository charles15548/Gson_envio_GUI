package com.prueba.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.prueba.model.Persona;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FRMCliente extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblEdad;
	private JButton btnEnviar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMCliente frame = new FRMCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FRMCliente() {
		setAlwaysOnTop(true);
		setBackground(new Color(255, 215, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(189, 183, 107));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Datos: ");
		lblNewLabel.setBounds(10, 11, 76, 27);
		contentPane.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(118, 56, 110, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(118, 87, 110, 20);
		contentPane.add(txtNombre);
		
		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(118, 118, 110, 20);
		contentPane.add(txtEdad);
		
		lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 59, 56, 14);
		contentPane.add(lblCodigo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 90, 76, 14);
		contentPane.add(lblNombre);
		
		lblEdad = new JLabel("Edad");
		lblEdad.setBounds(10, 121, 76, 14);
		contentPane.add(lblEdad);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(this);
		btnEnviar.setBounds(20, 182, 127, 34);
		contentPane.add(btnEnviar);
	}
	
	void enviarDatos() {
		int edad = Integer.parseInt(txtEdad.getText());
		Persona persona = new Persona(txtCodigo.getText(),txtNombre.getText(), edad);
		Gson gson = new Gson();
		String json = gson.toJson(persona);
		System.out.println("Json enviado: "+ json);
		
		try(Socket socket = new Socket("localhost", 12345);
			OutputStream outStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outStream, true)){
			writer.print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEnviar) {
			actionPerformedBtnEnviar(e);
		}
	}
	protected void actionPerformedBtnEnviar(ActionEvent e) {
		enviarDatos();
	}
}
