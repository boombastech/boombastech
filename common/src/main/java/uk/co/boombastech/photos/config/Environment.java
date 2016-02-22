package uk.co.boombastech.photos.config;

public enum Environment {
    dev;

    public String getPropertiesFileName() {
        return this.name() + ".properties";
    }
}