package model.report;

import model.users.AccountUserModel;
import model.users.artists.ArtistModel;

public class Report
{
    private ArtistModel reportedArtist;
    private AccountUserModel reporterUser;
    private String explanation;
    public Report(ArtistModel reportedArtist, AccountUserModel reporterUser, String explanation)
    {
        this.reportedArtist=reportedArtist;
        this.reporterUser=reporterUser;
        this.explanation=explanation;
    }
    public void setReportedArtist(ArtistModel reportedArtist)
    {
        this.reportedArtist = reportedArtist;
    }
    public void setReporterUser(AccountUserModel reporterUser)
    {
        this.reporterUser = reporterUser;
    }
    public void setExplanation(String explanation)
    {
        this.explanation = explanation;
    }
    public AccountUserModel getReporterUser()
    {
        return reporterUser;
    }
    public ArtistModel getReportedArtist()
    {
        return reportedArtist;
    }
    public String getExplanation()
    {
        return explanation;
    }
    public String toString()
    {
        return "Reported Artist: "+this.reportedArtist+"\nReporter user: "+this.reporterUser+"\nExplanation: "+this.explanation;
    }
}
