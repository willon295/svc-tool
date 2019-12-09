package util;

import bean.SystemData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * @Description:
 * @Author: Light
 * @CreateDate: 2019/8/14$ 14:35$
 * @Version: 1.0
 */
public final class ConfigManager {
    private static Properties prop;

    private static Properties getInstance() {
        if (prop == null) {
            try {
                prop = new Properties();
                String fileLogPath = SystemData.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                fileLogPath = fileLogPath.substring(0, fileLogPath.length() - 1);
                fileLogPath = fileLogPath.substring(0, fileLogPath.lastIndexOf("/"));
                fileLogPath = fileLogPath + File.separator;
                fileLogPath = URLDecoder.decode(fileLogPath, "UTF-8");
                InputStream in = new FileInputStream(new File(fileLogPath + "config.properties"));
                prop.load(in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }

    public static String getPropertiesByKey(String key) {
        String property = getInstance().getProperty(key);
        if (StringUtils.isNullOrEmpty(property)) {
            return "";
        } else {
            return property;
        }
    }

    public static String getWinWidth() {
        String property = getInstance().getProperty("winWidth");
        if (StringUtils.isNullOrEmpty(property)) {
            return "";
        } else {
            return property;
        }
    }

    public static String getCreatMode() {
        String property = getInstance().getProperty("createMode");
        if (StringUtils.isNullOrEmpty(property)) {
            return "default";
        } else {
            return property;
        }
    }
}
