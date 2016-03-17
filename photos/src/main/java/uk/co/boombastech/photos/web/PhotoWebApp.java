package uk.co.boombastech.photos.web;

import uk.co.boombastech.photos.injection.PhotosModule;
import uk.co.boombastech.solr.injection.SolrModule;

import static uk.co.boombastech.http.handlers.ResourceHandlerBuilder.resourceHandler;
import static uk.co.boombastech.http.handlers.ServletContextHandlerBuilder.servletContextHandler;
import static uk.co.boombastech.http.injection.ListenerBuilder.listener;
import static uk.co.boombastech.http.web.WebServerBuilder.webServer;

public class PhotoWebApp {
    public static void main(String[] args) throws Exception {
        webServer()
            .withPortNumber(8080)
            .withHandler(servletContextHandler()
                .withContextPath("/api")
                .withEventListener(listener()
                    .withModule(new PhotosModule())
                    .withModule(new SolrModule())))
            .withHandler(resourceHandler()
                .withContextPath("/")
                .withResourceBase("./photos/src/main/web"))
            .withHandler(resourceHandler()
                .withContextPath("/photos")
                .withShowDirectory(true)
                .withResourceBase("./photos/src/dev-photos"))
            .build()
            .start();
    }
}