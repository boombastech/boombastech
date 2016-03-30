package uk.co.boombastech.photos.search;

import uk.co.boombastech.solr.search.facets.AvailableFacet;

public enum PhotoFacet implements AvailableFacet {
	location,
	album,
	category,
	date("year,month,day", true);

	private final String solrName;
	private final boolean pivot;

	PhotoFacet(String solrName, boolean pivot) {
		this.solrName = solrName;
		this.pivot = pivot;
	}

	PhotoFacet() {
		this.solrName = name();
		this.pivot = false;
	}

	@Override
	public String getName() {
		return solrName;
	}

	@Override
	public boolean isPivot() {
		return pivot;
	}
}