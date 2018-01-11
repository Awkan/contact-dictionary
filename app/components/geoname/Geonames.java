package components.geoname;

public class Geonames {

    private String countryId;

    private String adminCode1;

    private String countryName;

    private String fclName;

    private String countryCode;

    private String lng;

    private String fcodeName;

    private String toponymName;

    private String fcl;

    private String name;

    private String fcode;

    private String geonameId;

    private String lat;

    private String adminName1;

    private String population;

    public String getCountryId ()
    {
        return countryId;
    }

    public void setCountryId (String countryId)
    {
        this.countryId = countryId;
    }

    public String getAdminCode1 ()
    {
        return adminCode1;
    }

    public void setAdminCode1 (String adminCode1)
    {
        this.adminCode1 = adminCode1;
    }

    public String getCountryName ()
    {
        return countryName;
    }

    public void setCountryName (String countryName)
    {
        this.countryName = countryName;
    }

    public String getFclName ()
    {
        return fclName;
    }

    public void setFclName (String fclName)
    {
        this.fclName = fclName;
    }

    public String getCountryCode ()
    {
        return countryCode;
    }

    public void setCountryCode (String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getLng ()
    {
        return lng;
    }

    public void setLng (String lng)
    {
        this.lng = lng;
    }

    public String getFcodeName ()
    {
        return fcodeName;
    }

    public void setFcodeName (String fcodeName)
    {
        this.fcodeName = fcodeName;
    }

    public String getToponymName ()
    {
        return toponymName;
    }

    public void setToponymName (String toponymName)
    {
        this.toponymName = toponymName;
    }

    public String getFcl ()
    {
        return fcl;
    }

    public void setFcl (String fcl)
    {
        this.fcl = fcl;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getFcode ()
    {
        return fcode;
    }

    public void setFcode (String fcode)
    {
        this.fcode = fcode;
    }

    public String getGeonameId ()
    {
        return geonameId;
    }

    public void setGeonameId (String geonameId)
    {
        this.geonameId = geonameId;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    public String getAdminName1 ()
    {
        return adminName1;
    }

    public void setAdminName1 (String adminName1)
    {
        this.adminName1 = adminName1;
    }

    public String getPopulation ()
    {
        return population;
    }

    public void setPopulation (String population)
    {
        this.population = population;
    }

    @Override
    public String toString()
    {
        return "GeoNames [countryId = "+countryId+", adminCode1 = "+adminCode1+", countryName = "+countryName+", fclName = "+fclName+", countryCode = "+countryCode+", lng = "+lng+", fcodeName = "+fcodeName+", toponymName = "+toponymName+", fcl = "+fcl+", name = "+name+", fcode = "+fcode+", geonameId = "+geonameId+", lat = "+lat+", adminName1 = "+adminName1+", population = "+population+"]";
    }
}