package uk.co.boombastech.photos.web;

import com.google.common.collect.Lists;
import com.google.inject.Module;
import uk.co.boombastech.photos.injection.PhotosModule;
import uk.co.boombastech.routes.MutableRouteStore;
import uk.co.boombastech.solr.injection.SolrModule;
import uk.co.boombastech.web.WebServer;

import java.util.List;

public class PhotoWebServer extends WebServer {

	public PhotoWebServer() throws Exception {}

	@Override
	public List<Module> modules(MutableRouteStore mutableRouteStore) {
		return Lists.newArrayList(new SolrModule(), new PhotosModule(mutableRouteStore));
	}

	@Override
	public int portNumber() {
		return 8080;
	}

	public static void main(String[] args) throws Exception {
		new PhotoWebServer();
	}
}