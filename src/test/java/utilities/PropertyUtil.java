package utilities;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static utilities.Constants.CONFIG_FILE_PATH;

public final class PropertyUtil {

  private transient final Properties properties;
  private static volatile PropertyUtil singletonObject;

  private PropertyUtil(Properties properties) {
    this.properties = properties;
  }

  public static PropertyUtil getInstance() {
    if (singletonObject != null) {
      return singletonObject;
    }
    Properties properties = new Properties();
    try (InputStream inputStream = Files.newInputStream(Path.of(CONFIG_FILE_PATH))) {
      properties.load(inputStream);
    } catch (IOException exception) {
      System.out.println("Error occurred in fetching the property from config file"+ exception);
    }
    singletonObject = new PropertyUtil(properties);
    return singletonObject;
  }

  public String getConfigProperty(String propertyName) {
    return properties.getProperty(propertyName);
  }

}