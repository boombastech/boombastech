package uk.co.boombastech.solr.search;

import uk.co.boombastech.utils.Builder;

public class FacetValueBuilder implements Builder<FacetValue> {
	private String facetOptionName;
	private long facetOptionCount;
	private boolean selected;

	public FacetValueBuilder withFacetOptionName(String facetOptionName) {
		this.facetOptionName = facetOptionName;
		return this;
	}

	public FacetValueBuilder withFacetOptionCount(long facetOptionCount) {
		this.facetOptionCount = facetOptionCount;
		return this;
	}

	public FacetValueBuilder withSelected(boolean selected) {
		this.selected = selected;
		return this;
	}

	@Override
	public FacetValue build() {
		return new FacetValue(facetOptionName, facetOptionCount, selected);
	}
}