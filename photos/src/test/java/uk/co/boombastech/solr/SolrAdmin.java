package uk.co.boombastech.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import uk.co.boombastech.photos.PhotoBuilder;
import uk.co.boombastech.photos.importer.PhotoSolrDocumentConverter;
import uk.co.boombastech.photos.models.Photo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static uk.co.boombastech.photos.PhotoBuilder.newPhoto;

public class SolrAdmin {

    public static void main(String[] args) {
        HttpSolrClient solr = new HttpSolrClient("http://localhost:8983/solr/photos");
        try {
            solr.deleteByQuery("*:*");
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

        PhotoSolrDocumentConverter converter = new PhotoSolrDocumentConverter();

        List<SolrInputDocument> solrInputDocuments = newArrayList();
        for (Photo photo : photosToAdd()) {
            solrInputDocuments.add(converter.convertTo(photo));
        }

        try {
            solr.add(solrInputDocuments);
            solr.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Photo> photosToAdd() {
        List<Photo> photos = newArrayList();

        photos.add(newPhoto()
                .withId("photo1")
                .withFilename("photo1.png")
                .withDate(new Date())
                .withYear(2015)
                .withMonth(9)
                .withDay(24)
                .withAlbum("Wales Summer 2015")
                .withCategory("Holiday")
                .build());

        photos.add(newPhoto()
                .withId("photo2")
                .withFilename("photo2.jpg")
                .withDate(new Date())
                .withYear(2015)
                .withMonth(9)
                .withDay(23)
                .withAlbum("Wales Summer 2015")
                .withCategory("Holiday")
                .build());

        photos.add(newPhoto()
                .withId("photo3")
                .withFilename("photo3.jpg")
                .withDate(new Date())
                .withYear(2015)
                .withMonth(8)
                .withDay(24)
                .withAlbum("Thomas Land")
                .withCategory("Trains")
                .build());

        photos.add(newPhoto()
                .withId("photo4")
                .withFilename("photo4.jpg")
                .withDate(new Date())
                .withYear(2014)
                .withMonth(5)
                .withDay(2)
                .withAlbum("Thomas Land")
                .withCategory("Trains")
                .build());

        photos.add(newPhoto()
                .withId("photo5")
                .withFilename("photo5.jpg")
                .withDate(new Date())
                .withAlbum("Thomas Land")
                .withCategory("Trains")
                .build());

        return photos;
    }
}