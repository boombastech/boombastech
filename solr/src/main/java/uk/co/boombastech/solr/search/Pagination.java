package uk.co.boombastech.solr.search;

public class Pagination {

	// currentpage
	private final int currentPage;
	// total pages
	private final int totalPages;
	// first page
	private String firstPage;
	// last page
	private String lastPage;
	// previous page
	private String previousPage;
	// next page
	private String nextPage;

	public Pagination(SearchCriteria searchCriteria, long totalNumberOfResults) {
		currentPage = searchCriteria.getPageNumber();

		totalPages = (int) Math.ceil(totalNumberOfResults * 1.0 / searchCriteria.getNumberOfResults());

		if (currentPage != 1) {
			previousPage = searchCriteria.createUrlFromSearchCriteriaForPageNumber(currentPage-1);
			firstPage = searchCriteria.createUrlFromSearchCriteriaForPageNumber(1);
		}

		if (currentPage != totalPages) {
			previousPage = searchCriteria.createUrlFromSearchCriteriaForPageNumber(currentPage+1);
			firstPage = searchCriteria.createUrlFromSearchCriteriaForPageNumber(totalPages);
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public String getFirstPage() {
		return firstPage;
	}

	public String getLastPage() {
		return lastPage;
	}

	public String getPreviousPage() {
		return previousPage;
	}

	public String getNextPage() {
		return nextPage;
	}
}