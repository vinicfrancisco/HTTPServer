package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.activation.MimetypesFileTypeMap;

import Methods.GetMethodStrategy;
import Methods.MethodStrategy;
import Methods.PostMethodStrategy;
import utils.FileManipulator;

public class Connection implements Runnable {

	private Socket client = null;
	private BufferedReader inClient = null;
	private DataOutputStream outClient = null;

	public Connection(Socket socket) {
		this.client = socket;
	}

	@Override
	public void run() {
		try {
			inClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			outClient = new DataOutputStream(client.getOutputStream());
			MethodStrategy method = null;

			String requestString = inClient.readLine();
			String headerLine = requestString;

			if (headerLine == null)
				return;

			StringTokenizer tokenizer = new StringTokenizer(headerLine);
			String httpMethod = tokenizer.nextToken();
			String httpQueryString = tokenizer.nextToken();

			while (inClient.ready()) {
				System.out.println(requestString);
				requestString = inClient.readLine();
			}

			if (httpMethod.equals("GET")) {
				method = new GetMethodStrategy(outClient);
				method.sendResponse(httpQueryString);
			} else if (httpMethod.equals("POST")) {
				method = new PostMethodStrategy(inClient);
				method.sendResponse(httpQueryString);
			}
		} catch (IOException ioe) {
			System.err.println("Server error : " + ioe);
		} finally {
			try {
				inClient.close();
				outClient.close();
			} catch (Exception e) {
				System.err.println("Error closing stream : " + e.getMessage());
			}
		}
	}

}
