package model.audioRelated;

import java.util.Calendar;

public abstract class AudioModel implements Comparable <AudioModel>
{
    private long audioID;
    private String audioName;
    private String nameOfArtist;
    private long playAmount;
    private long likeAmount;
    private Calendar releaseDate;
    private Genre genre;
    private String link;
    private String cover;
    private static long audioAmount;
    public AudioModel(String audioName, String nameOfArtist, Genre genre, String link, String cover)
    {
        this.audioName=audioName;
        this.nameOfArtist=nameOfArtist;
        this.genre=genre;
        this.link=link;
        this.cover=cover;
        this.releaseDate=Calendar.getInstance();
        audioAmount++;
    }
    public Calendar getReleaseDate()
    {
        return this.releaseDate;
    }
    public long getAudioID()
    {
        return this.audioID;
    }
    public Genre getGenre()
    {
        return this.genre;
    }
    public long getLikeAmount()
    {
        return this.likeAmount;
    }
    public String getAudioName()
    {
        return this.audioName;
    }
    public long getPlayAmount()
    {
        return this.playAmount;
    }
    public String getCover()
    {
        return this.cover;
    }
    public String getLink()
    {
        return this.link;
    }
    public String getNameOfArtist()
    {
        return this.nameOfArtist;
    }
    public static long getAudioAmount()
    {
        return audioAmount;
    }
    public void setAudioID(long audioID)
    {
        this.audioID = audioID;
    }
    public void setAudioName(String audioName)
    {
        this.audioName = audioName;
    }
    public void setReleaseDate(Calendar releaseDate)
    {
        this.releaseDate = releaseDate;
    }
    public void setPlayAmount(long playAmount)
    {
        this.playAmount = playAmount;
    }
    public void setCover(String cover)
    {
        this.cover = cover;
    }
    public void setGenre(Genre genre)
    {
        this.genre = genre;
    }
    public void setLikeAmount(long likeAmount)
    {
        this.likeAmount = likeAmount;
    }
    public void setLink(String link)
    {
        this.link = link;
    }
    public void setNameOfArtist(String nameOfArtist)
    {
        this.nameOfArtist = nameOfArtist;
    }
    public static void setAudioAmount(long audioAmount)
    {
        AudioModel.audioAmount = audioAmount;
    }
    public String toString()
    {
        return "Audio name: "+this.audioName+"\nAudio ID: "+this.audioID+"\nAudio's artist: "+this.nameOfArtist+"\nLikes: "+this.likeAmount+"\nPlays: "+this.playAmount+"\nRelease date: "+this.releaseDate.get(Calendar.YEAR)+"/"+(this.releaseDate.get(Calendar.MONTH)+1)+"/"+this.releaseDate.get(Calendar.DATE)+
                "\nGenre: "+this.genre+"\nLink: "+this.link+"\nCover: "+this.cover;
    }
    public int compareTo(AudioModel audio)
    {
        if(this.audioName.compareTo(audio.getAudioName())>0)
            return 1;
        else if(this.audioName.compareTo(audio.getAudioName())<0)
            return -1;
        else
        {
            if(this.getLikeAmount()>audio.getLikeAmount())
                return 1;
            else if(this.getLikeAmount()<audio.getLikeAmount())
                return -1;
            else
            {
                if(this instanceof MusicModel && audio instanceof PodcastModel)
                    return 1;
                else if(this instanceof PodcastModel && audio instanceof MusicModel)
                    return -1;
                else
                {
                    if(this.playAmount>audio.getPlayAmount())
                        return 1;
                    else if(this.playAmount<audio.getPlayAmount())
                        return -1;
                    else
                        return 0;
                }
            }
        }
    }
}
