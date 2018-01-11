package oauth.google;

public class Names
{
    private String familyName;

    private String displayNameLastFirst;

    private String givenName;

    private String displayName;

    public String getFamilyName ()
    {
        return familyName;
    }

    public void setFamilyName (String familyName)
    {
        this.familyName = familyName;
    }

    public String getDisplayNameLastFirst ()
    {
        return displayNameLastFirst;
    }

    public void setDisplayNameLastFirst (String displayNameLastFirst)
    {
        this.displayNameLastFirst = displayNameLastFirst;
    }

    public String getGivenName ()
    {
        return givenName;
    }

    public void setGivenName (String givenName)
    {
        this.givenName = givenName;
    }

    public String getDisplayName ()
    {
        return displayName;
    }

    public void setDisplayName (String displayName)
    {
        this.displayName = displayName;
    }

    @Override
    public String toString()
    {
        return "Names [familyName = "+familyName+", displayNameLastFirst = "+displayNameLastFirst+", givenName = "+givenName+", displayName = "+displayName+"]";
    }
}
