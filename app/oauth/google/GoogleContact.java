package oauth.google;

import java.util.List;

public class GoogleContact {

    private String totalPeople;

    private String totalItems;

    private String nextSyncToken;

    private String nextPageToken;

    private List<Connections> connections;

    public String getTotalPeople ()
    {
        return totalPeople;
    }

    public void setTotalPeople (String totalPeople)
    {
        this.totalPeople = totalPeople;
    }

    public String getTotalItems ()
    {
        return totalItems;
    }

    public void setTotalItems (String totalItems)
    {
        this.totalItems = totalItems;
    }

    public String getNextSyncToken ()
    {
        return nextSyncToken;
    }

    public void setNextSyncToken (String nextSyncToken)
    {
        this.nextSyncToken = nextSyncToken;
    }

    public String getNextPageToken ()
    {
        return nextPageToken;
    }

    public void setNextPageToken (String nextPageToken)
    {
        this.nextPageToken = nextPageToken;
    }

    public List<Connections> getConnections ()
    {
        return connections;
    }

    public void setConnections (List<Connections> connections)
    {
        this.connections = connections;
    }

    @Override
    public String toString()
    {
        return "GoogleContact [totalPeople = "+totalPeople+", totalItems = "+totalItems+", nextSyncToken = "+nextSyncToken+", nextPageToken = "+nextPageToken+", connections = "+connections+"]";
    }

}
