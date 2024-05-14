package model.users.artists;

import model.audioRelated.PodcastModel;

import java.util.ArrayList;

public class PodcasterModel extends ArtistModel
{
    private ArrayList <PodcastModel>podcasts=new ArrayList<>();
    public PodcasterModel(String userName, String password, String fullName, String email, String phoneNumber, String birthDate, String bio)
    {
        super(userName,password,fullName,email,phoneNumber,birthDate,bio);
    }
    public void setPodcasts(ArrayList<PodcastModel> podcasts)
    {
        this.podcasts = podcasts;
    }
    public ArrayList<PodcastModel> getPodcasts()
    {
        return this.podcasts;
    }
    public String toString()
    {
        StringBuilder temp=new StringBuilder("\nPodcasts:\n");
        for(PodcastModel podcastTemp:this.podcasts)
            if(podcastTemp!=null)
                temp.append(podcastTemp).append("\n");
        return super.toString()+temp;
    }
}
