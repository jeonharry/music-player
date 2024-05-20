package controller;

import model.Database;
import model.audioRelated.AudioModel;
import model.report.Report;
import model.users.AccountUserModel;
import model.users.artists.ArtistModel;

public class AdminController
{
    private static AdminController adminController;
    private AdminController(){}
    public static AdminController getAdminController()
    {
        if(adminController==null)
            adminController=new AdminController();
        return adminController;
    }
    public String getReports()
    {
        StringBuilder answer=new StringBuilder();
        for(Report temp: Database.getDatabase().getReports())
            if(temp!=null)
                answer.append("Reported Artist username: ").append(temp.getReportedArtist().getUserName()).append("\nreporter username: ").append(temp.getReporterUser().getUserName()).append("\nexplanation: ").append(temp.getExplanation()).append("\n");
        return answer.toString();
    }
    public String getArtists()
    {
        StringBuilder answer=new StringBuilder("Artists username:\n");
        for(AccountUserModel temp:Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel)
            {
                answer.append(temp.getUserName()).append("\n");
            }
        return answer.toString();
    }
    public String getArtist(String artistUserName)
    {
        for(AccountUserModel temp:Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel && temp.getUserName().compareTo(artistUserName)==0)
                return temp.toString();
        return "Artist not found";
    }
    public String getAudios()
    {
        StringBuilder answer=new StringBuilder("Audios ID:\n");
        for(AudioModel temp:Database.getDatabase().getAllAudios())
            if(temp!=null)
                answer.append(temp.getAudioID()).append("\n");
        return answer.toString();
    }
    public String getAudio(String audioID)
    {
        for(AudioModel temp:Database.getDatabase().getAllAudios())
            if(temp!=null && temp.getAudioID()==Long.parseLong(audioID))
                return temp.toString();
        return "Audio not found";
    }
    public String showStatistics()
    {
        StringBuilder answer=new StringBuilder();
        AudioModel[] audios=Database.getDatabase().getAllAudios().toArray(new AudioModel[Database.getDatabase().getAllAudios().size()]);
        for(int i=0;i<audios.length-1;++i)
            for(int j=i+1;j<audios.length;++j)
                if(audios[i].getLikeAmount()<audios[j].getLikeAmount())
                {
                    AudioModel temp=audios[i];
                    audios[i]=audios[j];
                    audios[j]=temp;
                }
        for(AudioModel temp:audios)
            if(temp!=null)
                answer.append("AudioID: ").append(temp.getAudioID()).append("\nAudio name: ").append(temp.getAudioName()).append("\nlike amount: ").append(temp.getLikeAmount()).append("\n");
        return answer.toString();
    }
}
