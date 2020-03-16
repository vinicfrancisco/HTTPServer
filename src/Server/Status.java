package Server;

public class Status {
	private static String newLine = "\r\n";
	public static final String VERSION = "HTTP/1.1";

	public static final String HTTP_200 = VERSION + "200 OK" + newLine;
	public static final String HTTP_400 = VERSION + "400 Bad Request" + newLine;
	public static final String HTTP_403 = VERSION + "403 Forbidden" + newLine;
	public static final String HTTP_404 = VERSION + "404 Not Found" + newLine;
	public static final String HTTP_418 = VERSION + "418 I'm a teapot" + newLine;
	public static final String HTTP_501 = VERSION + "501 Not Implemented" + newLine;
	public static final String HTTP_503 = VERSION + "503 Service Unvailable" + newLine;

	public static String getStatus(int status) {
		switch (status) {
		case 200:
			return HTTP_200;
		case 400:
			return HTTP_400;
		case 403:
			return HTTP_403;
		case 404:
			return HTTP_404;
		case 418:
			return HTTP_418;
		case 501:
			return HTTP_501;
		case 503:
			return HTTP_503;
		default:
			return HTTP_404;
		}
	}
}
