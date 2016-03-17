package uk.co.boombastech.solr.search;

public class Page<T> {
	private final int pageNumber;
	private final SearchCriteria<T> searchCriteria;
	private final boolean active;

	public Page(int pageNumber, SearchCriteria searchCriteria, boolean active) {
		this.pageNumber = pageNumber;
		this.searchCriteria = searchCriteria;
		this.active = active;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public SearchCriteria<T> getSearchCriteria() {
		return searchCriteria;
	}

	public boolean isActive() {
		return active;
	}
}