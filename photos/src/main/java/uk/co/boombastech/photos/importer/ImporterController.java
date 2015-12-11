package uk.co.boombastech.photos.importer;

import uk.co.boombastech.controllers.Controller;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;
import uk.co.boombastech.photos.models.Photo;

import javax.inject.Inject;
import java.util.Date;

public class ImporterController implements Controller {

	private final ImporterService importerService;

	@Inject
	public ImporterController(ImporterService importerService) {
		this.importerService = importerService;
	}

	@Override
	public void execute(Request request, Response response) {
		if (importerService.run()) {
			response.withValue("test", "importer found update");
		} else {
			response.withValue("test", "no update found");
			response.withValue("more", "importer found update");
			response.withValue("even", new Photo("filename.jpg", new Date(), null, null));
		}
	}
}