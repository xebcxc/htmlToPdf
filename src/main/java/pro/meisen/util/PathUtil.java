package pro.meisen.util;

import java.io.File;
import java.io.IOException;

/**
 * @author meisen
 * 2017-07-06
 */
public class PathUtil {

    public static String getCurrentPath(){
        String path = "";
        File directory = new File("");// 参数为空
        try {
            path = directory.getCanonicalPath();
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}
