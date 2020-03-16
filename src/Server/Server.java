package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server implements Runnable {
	private ServerSocket server;

	static final int PORT = 5000;

	public Server() throws IOException {
		server = new ServerSocket(PORT);
		System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			try {
				Thread conn = new Thread(new Connection(server.accept()));
				conn.start();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

}
