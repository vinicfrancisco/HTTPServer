package Methods;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostMethodStrategy extends MethodStrategy {
	private List<String> dataKey;
	private List<String> data;

	public PostMethodStrategy(BufferedReader input) {
		super(input);
	}

	@Override
	public void sendResponse(String path) {
		System.out.println("CHEEEGUEEEI");
		if (this.data == null && this.dataKey == null) {
			this.buscarData();
		}
	}

	private void buscarData() {
		this.dataKey = new ArrayList<>();
		this.data = new ArrayList<>();

		try {
			String query = getPostDataFromBuffer();
			String[] array = query.split("&");

			System.out.println(query);

			for (String str : array) {
				String[] dados = str.split("=");
				this.dataKey.add(dados[0]);
				this.data.add(dados[1]);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private String getPostDataFromBuffer() throws IOException {
		String headerLine = null;

		while ((headerLine = getInput().readLine()).length() != 0) {

			StringBuilder payload = new StringBuilder();

			while (getInput().ready()) {
				payload.append((char) getInput().read());
			}

			return payload.toString();
		}

		return "";

	}

}
