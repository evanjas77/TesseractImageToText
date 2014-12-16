package com.ocr;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Path("/imageToText")
public class OCRRestFulWS {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getText(InputStream incomingData) {

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
}