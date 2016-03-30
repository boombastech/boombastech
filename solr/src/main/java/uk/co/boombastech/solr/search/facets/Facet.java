package uk.co.boombastech.solr.search.facets;

import java.util.List;

public class Facet {
	private final String name;
	private final List<FacetValue> facetValues;

	public Facet(String name, List<FacetValue> facetValues) {
		this.name = name;
		this.facetValues = facetValues;
	}

	public String getName() {
		return name;
	}

	public List<FacetValue> getFacetValues() {
		return facetValues;
	}
}