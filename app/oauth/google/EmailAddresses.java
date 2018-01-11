package oauth.google;

public class EmailAddresses
{
    private String formattedType;

    private String value;

    private String type;

    public String getFormattedType ()
    {
        return formattedType;
    }

    public void setFormattedType (String formattedType)
    {
        this.formattedType = formattedType;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "EmailAddresses [formattedType = "+formattedType+", value = "+value+", type = "+type+"]";
    }
}
