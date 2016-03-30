package uk.co.boombastech.solr.search.facets;

import uk.co.boombastech.utils.Builder;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class FacetValueBuilder implements Builder<FacetValue> {
	private String facetOptionName;
	private long facetOptionCount;
	private boolean selected;
	private List<FacetValue> facetValues = newArrayList();

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

	public FacetValueBuilder withPivotFacetValue(FacetValue facetValue) {
		this.facetValues.add(facetValue);
		return this;
	}

	@Override
	public FacetValue build() {
		return new FacetValue(facetOptionName, facetValues, facetOptionCount, selected);
	}
}