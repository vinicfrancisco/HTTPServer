package Methods;

import java.io.BufferedReader;
import java.io.DataOutputStream;

abstract public class MethodStrategy {

	private String path;
	private DataOutputStream out;
	private BufferedReader input;

	public MethodStrategy(DataOutputStream out) {
		this.out = out;
	}
	
	public MethodStrategy(BufferedReader input) {
		this.input = input;
	}

	public BufferedReader getInput() {
		return input;
	}

	public void setInput(BufferedReader input) {
		this.input = input;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public void setOut(DataOutputStream out) {
		this.out = out;
	}

	abstract public void sendResponse(String path);
}
