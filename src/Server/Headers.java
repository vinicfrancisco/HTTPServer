package Server;

public class Headers {
	private static String newLine = "\r\n";

	private static final String CONTENT_LENGTH = "Content-Length";
	private static final String CONTENT_TYPE = "Content-Type";

	public static String getContentType(String contentType) {
		return CONTENT_TYPE + ": " + contentType + newLine;
	}

	public static String getContentLength(int length) {
		return CONTENT_LENGTH + length + newLine;
	}
}
