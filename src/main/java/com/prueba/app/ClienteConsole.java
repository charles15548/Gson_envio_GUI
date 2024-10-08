package com.prueba.app;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;
import com.prueba.model.Persona;

public class ClienteConsole {

	public static void main(String[] args) {
		Persona persona = new Persona("J123", "Renzo", 30);
		Gson gson = new Gson();
		String json = gson.toJson(persona);
		System.out.println("JSON Enviado: " + json);
		try (Socket socket = new Socket("localhost", 12345);
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true)) {
		writer.println(json);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}

}
