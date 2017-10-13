package com.fileservice.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;

public class FileServiceClientTwo {

	private static final String FILE_PATH = "/Users/Inampudi/Downloads/downloaded.png";

	public static void main(String[] args) throws IOException {

		WebClient client = WebClient.create("http://localhost:8080/webservices-restful-uploaddownload/services/fileService/download");
		client.accept("multipart/form-data");
		InputStream attachment =  client.get(InputStream.class);
		
		copyFile(attachment);

	}
	
	public static void copyFile(InputStream inputStream) throws FileNotFoundException, IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];

		out = new FileOutputStream(new File(FILE_PATH));
		while ((read = inputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}

}
