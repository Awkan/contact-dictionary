package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Contact extends Model{

    public static final String IMPORTER_TWITTER = "twitter";

    private String importer;
    private String name;
    private String location;
    private GeoPoint locationGeoNames;
    private String logoUrl;
    private String bannerUrl;

    public String toString() {
        return name;
    }

    public String getImporter() {
        return importer;
    }

    public void setImporter(String importer) {
        this.importer = importer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public GeoPoint getLocationGeoNames() {
        return locationGeoNames;
    }

    public void setLocationGeoNames(GeoPoint locationGeoNames) {
        this.locationGeoNames = locationGeoNames;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}
