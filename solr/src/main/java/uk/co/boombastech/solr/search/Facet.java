package uk.co.boombastech.solr.search;

import uk.co.boombastech.common.Identifiable;

public interface Facet extends Identifiable {
	@Override
	String name();
}