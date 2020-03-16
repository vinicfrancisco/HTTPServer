package Server;

public class Path {

	private String WEB_ROOT = "src/public_html/";
	private String DEFAULT_FILE = "index.html";
	private String NOT_FOUND = "not_found.html";
	private String FAVICON = "favicon.ico";

	private String path;

	public Path(String path) {
		if (path.equals("not_found")) {
			this.path = this.WEB_ROOT + this.NOT_FOUND;
		} else {
			if (path.equals("/")) {
				this.path = this.WEB_ROOT + this.DEFAULT_FILE;
			} else {
				if (path.equals("/favicon.ico")) {
					this.path = this.WEB_ROOT + this.FAVICON;
				} else {
					this.path = WEB_ROOT + path;
				}
			}
		}
	}

	public String getPath() {
		return this.path;
	}

}