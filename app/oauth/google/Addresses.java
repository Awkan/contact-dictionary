package oauth.google;

public class Addresses {

    private String streetAddress;

    private String postalCode;

    private String formattedType;

    private String type;

    private String formattedValue;

    private String country;

    private String city;

    public String getStreetAddress ()
    {
        return streetAddress;
    }

    public void setStreetAddress (String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode ()
    {
        return postalCode;
    }

    public void setPostalCode (String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getFormattedType ()
    {
        return formattedType;
    }

    public void setFormattedType (String formattedType)
    {
        this.formattedType = formattedType;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getFormattedValue ()
    {
        return formattedValue;
    }

    public void setFormattedValue (String formattedValue)
    {
        this.formattedValue = formattedValue;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return streetAddress+" "+postalCode+" "+country+" "+city;
    }

}
