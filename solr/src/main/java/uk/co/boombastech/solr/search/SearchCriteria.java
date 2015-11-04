package uk.co.boombastech.solr.search;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SearchCriteria {
	private final Multimap<Facet,String> searchCriteria;


	public SearchCriteria() {
		searchCriteria = ArrayListMultimap.create();
	}

	public SearchCriteria withFacet(Facet facet, String value) {
		searchCriteria.put(facet, value);
		return this;
	}
}