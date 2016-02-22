package uk.co.boombastech.photos.config;

import uk.co.boombastech.config.ApplicationConfig;

import javax.inject.Singleton;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

@Singleton
public class PhotoWebConfig implements ApplicationConfig {

    private int portNumber;

    public PhotoWebConfig(Environment environment) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream("./main/" + environment.getPropertiesFileName()));
        Properties properties = new Properties();
        properties.load(inputStreamReader);
        portNumber = Integer.parseInt(properties.getProperty("portNumber"));
    }

    public int getPortNumber() {
        return portNumber;
    }

    public static void main(String[] args) throws IOException {
        PhotoWebConfig photoWebConfig = new PhotoWebConfig(Environment.dev);

    }
}