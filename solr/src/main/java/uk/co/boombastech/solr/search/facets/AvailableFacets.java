package uk.co.boombastech.solr.search.facets;

import sun.security.x509.AVA;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class AvailableFacets implements Iterable<AvailableFacet> {

	private final List<AvailableFacet> availableFacets;

	public AvailableFacets(List<AvailableFacet> availableFacets) {
		this.availableFacets = availableFacets;
	}

	@Override
	public Iterator<AvailableFacet> iterator() {
		return availableFacets.iterator();
	}

	public boolean contains(String parameter) {
		return availableFacets.stream().anyMatch(facet -> facet.getName().equals(parameter));
	}

	public List<String> getPivots() {
		return availableFacets.stream().filter(AvailableFacet::isPivot).map(AvailableFacet::getName).collect(toList());
	}

	public List<String> getFacets() {
		Predicate<AvailableFacet> isFacet = (AvailableFacet availableFacet) -> !availableFacet.isPivot();
		return availableFacets.stream().filter(isFacet).map(AvailableFacet::getName).collect(toList());
	}
}