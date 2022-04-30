package ict.utils;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class Image {

    public static String covertBlobToImg(Blob img) throws SQLException {
        Blob blob = img;
        if (img != null) {
            byte[] ba = blob.getBytes(1, (int) blob.length());
            byte[] img64 = Base64.getEncoder().encode(ba);
            String image = new String(img64);
            return "data:image/png;base64," + image;
        }
        return "data:image/png;base64,";
    }
}
