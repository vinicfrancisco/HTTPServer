package main;

import java.io.IOException;

import Server.Server;

public class HTTPServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
        Server server = new Server();
        Thread thread = new Thread(server);
        while(true){
            thread.run();
        }
	}

}
