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
				sendFile(httpQueryString);
			} else if (httpMethod.equals("POST")) {
				// POST METHOD
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

	public void sendFile(String path) {
		try {
			int status = 200;

			if (path.equals("not_found")) {
				status = 404;
			}

			FileManipulator fileManipulator = new FileManipulator();
			Path filePath = new Path(path);

			File page = new File(filePath.getPath());
			MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();

			sendResponse(status, new String((fileManipulator.fileReader(page))),
					fileTypeMap.getContentType(page.getName()));
		} catch (Exception e) {
			sendFile("not_found");
		}

	}

	public void sendResponse(int statusCode, String responseString, String contentType) throws Exception {
		String NEW_LINE = "\r\n";

		String statusLine = Status.getStatus(statusCode);
		String contentLengthLine = Headers.getContentLength(responseString.length());
		String contentTypeLine = Headers.getContentType(contentType);

		outClient.writeBytes(statusLine);
		outClient.writeBytes(contentTypeLine); 
		outClient.writeBytes(contentLengthLine);

		outClient.writeBytes(NEW_LINE);

		outClient.writeBytes(responseString);

		outClient.close();
	}
}
