package com.prueba.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;
import com.prueba.model.Persona;

public class ServerConsole {

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(12345)) {
			System.out.println("Servidor escuchando en el puerto 12345...");
			Gson gson = new Gson();
			while (true) {
				try (Socket socket = serverSocket.accept();
						BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

					String jsonRecibido = reader.readLine();
					System.out.println("JSON recibido: " + jsonRecibido );

					// convertir en Persona
					Persona persona = gson.fromJson(jsonRecibido, Persona.class);
					System.out.println(persona + "\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
