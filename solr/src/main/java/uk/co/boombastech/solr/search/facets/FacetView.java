package uk.co.boombastech.solr.search.facets;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import uk.co.boombastech.solr.search.SearchResult;

import java.util.Collection;
import java.util.Map;

public class FacetView {

	private final Map<String, Collection<String>> facets;

	public FacetView(SearchResult searchResult) {
		Multimap<String, String> multimap = ArrayListMultimap.create();


//		Map<String, Collection<FacetValue>> facets = searchResult.getFacets();

//		for (String facetName : facets.keySet()) {
//			for (FacetValue facetValue : facets.get(facetName)) {
//				multimap.put(facetName, facetValue.getName());
//			}
//
//		}

		this.facets = multimap.asMap();
	}
}