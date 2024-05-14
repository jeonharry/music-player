package model.audioRelated;

public class PodcastModel extends AudioModel
{
    private String caption;
    public PodcastModel(String audioName, String nameOfArtist, Genre genre, String link, String cover, String caption)
    {
        super(audioName,nameOfArtist,genre,link,cover);
        this.caption=caption;
    }
    public void setCaption(String caption)
    {
        this.caption=caption;
    }
    public String getCaption()
    {
        return this.caption;
    }
    public String toString()
    {
        return super.toString()+"\nCaption: "+this.caption;
    }
}
