package components.geoname;

import java.util.List;

public class Geoname {

    private String totalResultsCount;

    private List<Geonames> geonames;

    public String getTotalResultsCount ()
    {
        return totalResultsCount;
    }

    public void setTotalResultsCount (String totalResultsCount)
    {
        this.totalResultsCount = totalResultsCount;
    }

    public List<Geonames> getGeonames ()
    {
        return geonames;
    }

    public void setGeonames (List<Geonames> geonames)
    {
        this.geonames = geonames;
    }

    @Override
    public String toString()
    {
        return "GeoName [totalResultsCount = "+totalResultsCount+", geonames = "+geonames+"]";
    }
}
