package rs.ac.uns.ftn.informatika.udd.vezbe01.lucene.model;

public final class ResultData {
	
	private String title;
    private String author;
	private String lang;
	private String keywords;
	private String location;
	private String highlight;
	
	public ResultData() {
		super();
	}

	public ResultData(String title, String author, String lang, String keywords, String location, String highlight) {
		super();
		this.title = title;
        this.author = author;
        this.lang = lang;
		this.keywords = keywords;
		this.location = location;
		this.highlight = highlight;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
