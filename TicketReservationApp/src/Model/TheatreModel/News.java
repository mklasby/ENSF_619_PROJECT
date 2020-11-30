package Model.TheatreModel;

public class News {
	private String fname;
	private String content;
	
	
	public News(String fname, String content) {
		setFname(fname);
		setContent(content);
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	
}
