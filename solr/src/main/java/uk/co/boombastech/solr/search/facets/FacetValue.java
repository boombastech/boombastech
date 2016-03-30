package uk.co.boombastech.solr.search.facets;

import java.util.List;

public class FacetValue extends Facet {
	private final long count;
	private final boolean selected;

	public FacetValue(String name, List<FacetValue> facetValues, long count, boolean selected) {
		super(name, facetValues);
		this.count = count;
		this.selected = selected;
	}

	public long getCount() {
		return count;
	}

	public boolean isSelected() {
		return selected;
	}
}