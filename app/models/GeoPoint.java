package models;

import javax.persistence.Entity;

import play.db.jpa.Model;


@Entity
public final class GeoPoint extends Model{

    private double lat;
    private double lon;

    public GeoPoint() {
    }

    public GeoPoint(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public GeoPoint resetLat(double lat) {
        this.lat = lat;
        return this;
    }

    public GeoPoint resetLon(double lon) {
        this.lon = lon;
        return this;
    }

    public double lat() {
        return this.lat;
    }

    public double getLat() {
        return this.lat;
    }

    public double lon() {
        return this.lon;
    }

    public double getLon() {
        return this.lon;
    }

    @Override
    public String toString() {
        return lat + ", " + lon;
    }

}