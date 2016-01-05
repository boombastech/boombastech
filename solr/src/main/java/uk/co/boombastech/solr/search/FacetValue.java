package uk.co.boombastech.solr.search;

public class FacetValue {
	private final String name;
	private final long count;
	private final boolean selected;

	public FacetValue(String name, long count, boolean selected) {
		this.name = name;
		this.count = count;
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public long getCount() {
		return count;
	}

	public boolean isSelected() {
		return selected;
	}
}