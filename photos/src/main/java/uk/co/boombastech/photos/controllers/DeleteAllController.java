package uk.co.boombastech.photos.controllers;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import uk.co.boombastech.controllers.Controller;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;

import javax.inject.Inject;
import java.io.IOException;

public class DeleteAllController implements Controller {

	private final SolrClient solrClient;

	@Inject
	public DeleteAllController(SolrClient solrClient) {
		this.solrClient = solrClient;
	}

	@Override
	public void execute(Request request, Response response) {
		try {
			solrClient.deleteByQuery("*:*");
			solrClient.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
}