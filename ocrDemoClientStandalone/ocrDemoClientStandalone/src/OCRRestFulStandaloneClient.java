import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 * A test class to stream across an image to the RESTFul OCR service and get
 * converted text in response.
 * 
 * @author Evangeline
 *
 */
public class OCRRestFulStandaloneClient {
	public static void main(String[] args) {
		try {
			URL url = new URL(
					"http://localhost:7001/ocrDemoProject/rest/ocr/imageToText");
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");

			ImageInputStream iis = ImageIO.createImageInputStream(new File(
					"samples/eurotext.gif"));
			ByteArrayOutputStream os = (ByteArrayOutputStream) connection
					.getOutputStream();
			byte[] buffer = new byte[0xFFFF];

			// System.out.println("Image stream:\n");

			for (int len; (len = iis.read(buffer)) != -1;) {
				os.write(buffer, 0, len);
				// System.out.println(buffer.toString());
			}

			os.flush();
			os.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String imgTxt = "";
			while ((imgTxt = in.readLine()) != null) {
				System.out.println(imgTxt);
			}
			System.out.println("\nREST Service Invoked Successfully..");
			in.close();
		} catch (Exception e) {
			System.out.println("\nError while calling REST Service");
			System.out.println(e);
		}
	}

}