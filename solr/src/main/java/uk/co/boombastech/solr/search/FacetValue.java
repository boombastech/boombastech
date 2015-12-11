package uk.co.boombastech.solr.search;

public class FacetValue {
	private final String name;
	private final long count;

	public FacetValue(String name, long count) {
		this.name = name;
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public long getCount() {
		return count;
	}
}