package com.ocr;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/ocr")
@Api(value = "/ocr", description = "Manage ocr operations such as converting image to text etc.")
public class OCRRestFulWS {

	@POST
	@Path("/imageToText")
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Returns text extracted from an image", notes = "Returns text extracted from an image", response = String.class)
	public String getText(
			@ApiParam(value = "Image byte stream", required = true) InputStream incomingData) {

		String result = "<No results> !";

		Tesseract tesseract = Tesseract.getInstance(); // JNA Interface
														// Mapping
		try {
			BufferedImage buffImg = ImageIO.read(incomingData);
			result = tesseract.doOCR(buffImg);
		} catch (TesseractException e) {
			result = "<No results> !";
			e.printStackTrace();
		} catch (IOException e) {
			result = "<No results> !";
			e.printStackTrace();
		} catch (Exception e) {
			result = "<No results> !";
			e.printStackTrace();
		}

		System.out.println("OCR Image Text = " + result);

		return result;

	}

	@GET
	@Path("/ping")
	@Produces(MediaType.TEXT_HTML)
	@ApiOperation(value = "Returns status of the ocr service", notes = "Returns status of the ocr service", response = String.class)
	public String ping() {
		return "<h1>Tessaract OCR RESTFul Service good to go ! <h1>";
	}
}