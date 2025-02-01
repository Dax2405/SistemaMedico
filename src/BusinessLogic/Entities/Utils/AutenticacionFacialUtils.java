package BusinessLogic.Entities.Utils;

import com.github.sarxos.webcam.Webcam;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AutenticacionFacialUtils {

    private static final String PHOTO_PATH = "media/images/photo.jpg";
    private static final String API_URL = "http://127.0.0.1:8000/autenticacion_facial/";

    public static void captureAndSavePhoto() throws IOException {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        BufferedImage image = webcam.getImage();
        ImageIO.write(image, "JPG", new File(PHOTO_PATH));
        webcam.close();
    }

    public static Integer autenticarFacialmente() throws IOException {
        captureAndSavePhoto();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost uploadFile = new HttpPost(API_URL + "validar/");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", new File(PHOTO_PATH), ContentType.APPLICATION_OCTET_STREAM,
                    "photo.jpg");

            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);

            HttpClientResponseHandler<String> responseHandler = response -> {
                int status = response.getCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new IOException("Unexpected response status: " + status);
                }
            };

            String response = httpClient.execute(uploadFile, responseHandler);
            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse.getInt("id_usuario");
        }
    }

    public static Boolean registrarFacialmente(Integer idUsuario) throws IOException {
        captureAndSavePhoto();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost uploadFile = new HttpPost(API_URL);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("id_usuario", String.valueOf(idUsuario), ContentType.TEXT_PLAIN);
            builder.addBinaryBody("file", new File(PHOTO_PATH), ContentType.APPLICATION_OCTET_STREAM, "photo.jpg");

            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);

            HttpClientResponseHandler<String> responseHandler = response -> {
                int status = response.getCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new IOException("Unexpected response status: " + status);
                }
            };

            String response = httpClient.execute(uploadFile, responseHandler);
            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse.has("id_usuario");
        }
    }

}