package uk.co.boombastech.solr.search;

public class Page {
	private final int pageNumber;
	private final String link;

	public Page(int pageNumber, String link) {
		this.pageNumber = pageNumber;
		this.link = link;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public String getLink() {
		return link;
	}
}