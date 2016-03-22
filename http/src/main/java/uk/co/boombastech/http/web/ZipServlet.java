package uk.co.boombastech.http.web;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Singleton
public class ZipServlet extends HttpServlet {
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	public static final String PATH = "./photos/src/dev-photos";


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			File directory = new File(PATH);
			String[] files = directory.list();

			if (files != null && files.length > 0) {

				byte[] zip = zipFiles(directory, files);

				ServletOutputStream servletOutputStream = response.getOutputStream();
				response.setContentType("application/zip");
				response.setHeader("Content-Disposition", "attachment; filename=\"DATA.ZIP\"");

				servletOutputStream.write(zip);
				servletOutputStream.flush();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private byte[] zipFiles(File directory, String[] files) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
		byte bytes[] = new byte[2048];

		for (String fileName : files) {
			zipFile(directory, zipOutputStream, bytes, fileName);
		}
		zipOutputStream.flush();
		byteArrayOutputStream.flush();
		zipOutputStream.close();
		byteArrayOutputStream.close();

		return byteArrayOutputStream.toByteArray();
	}

	private void zipFile(File directory, ZipOutputStream zipOutputStream, byte[] bytes, String fileName) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(directory.getPath() + FILE_SEPARATOR + fileName);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

		zipOutputStream.putNextEntry(new ZipEntry(fileName));

		int bytesRead;
		while ((bytesRead = bufferedInputStream.read(bytes)) != -1) {
			zipOutputStream.write(bytes, 0, bytesRead);
		}
		zipOutputStream.closeEntry();
		bufferedInputStream.close();
		fileInputStream.close();
	}
}