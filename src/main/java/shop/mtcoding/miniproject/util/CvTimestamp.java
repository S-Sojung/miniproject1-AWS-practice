package shop.mtcoding.miniproject.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CvTimestamp {
    public static Timestamp convertStringToTimestamp(String strDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(strDate);
            return new Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            return null;
        }
    }
}
