package oauth.google;

import java.util.List;

public class Connections {

    private List<Photos> photos;

    private String etag;

    private List<Names> names;

    private String resourceName;

    private List<EmailAddresses> emailAddresses;

    private List<Addresses> addresses;



    public List<Photos> getPhotos ()
    {
        return photos;
    }

    public void setPhotos (List<Photos> photos)
    {
        this.photos = photos;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public List<Names> getNames ()
    {
        return names;
    }

    public void setNames (List<Names> names)
    {
        this.names = names;
    }

    public String getResourceName ()
    {
        return resourceName;
    }

    public void setResourceName (String resourceName)
    {
        this.resourceName = resourceName;
    }

    public List<EmailAddresses> getEmailAddresses ()
    {
        return emailAddresses;
    }

    public void setEmailAddresses (List<EmailAddresses> emailAddresses)
    {
        this.emailAddresses = emailAddresses;
    }

    public List<Addresses> getAddresses ()
    {
        return addresses;
    }

    public void setAddresses (List<Addresses> addresses)
    {
        this.addresses = addresses;
    }

    @Override
    public String toString()
    {
        return "Connections [photos = "+photos+", etag = "+etag+", names = "+names+", resourceName = "+resourceName+", emailAddresses = "+emailAddresses+"]";
    }

}