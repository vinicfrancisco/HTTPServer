package Methods;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import Server.Headers;
import Server.Path;
import Server.Status;
import utils.FileManipulator;

public class GetMethodStrategy extends MethodStrategy {

	public GetMethodStrategy(DataOutputStream out) {
		super(out);
	}

	@Override
	public void sendResponse(String path) {
		try {
			int status = 200;

			if (path.equals("not_found")) {
				status = 404;
			}

			FileManipulator fileManipulator = new FileManipulator();
			Path filePath = new Path(path);

			File page = new File(filePath.getPath());
			MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();

			getResponse(status, new String((fileManipulator.fileReader(page))),
					fileTypeMap.getContentType(page.getName()));
		} catch (Exception e) {
			sendResponse("not_found");
		}

	}

	public void getResponse(int statusCode, String responseString, String contentType) {
		String NEW_LINE = "\r\n";

		String statusLine = Status.getStatus(statusCode);
		String contentLengthLine = Headers.getContentLength(responseString.length());
		String contentTypeLine = Headers.getContentType(contentType);

		try {
			getOut().writeBytes(statusLine);
			getOut().writeBytes(contentTypeLine);
			getOut().writeBytes(contentLengthLine);

			getOut().writeBytes(NEW_LINE);

			getOut().writeBytes(responseString);

			getOut().close();
		} catch (Exception e) {
			System.out.println("Error on g response..." + e.getMessage());
		}
	}

}
