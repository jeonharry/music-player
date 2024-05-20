package model.audioRelated;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayListModel implements Iterable <AudioModel>
{
    private long playListID;
    private String playListName;
    private String creator;
    private static long amountOfPlaylists;
    private int audiosIndex=0;
    private ArrayList <AudioModel> audios=new ArrayList<>();
    public PlayListModel(String playListName, String creator)
    {
        this.playListName=playListName;
        this.creator=creator;
        amountOfPlaylists++;
    }
    public static void setAmountOfPlaylists(long amountOfPlaylists)
    {
        PlayListModel.amountOfPlaylists = amountOfPlaylists;
    }
    public static long getAmountOfPlaylists()
    {
        return amountOfPlaylists;
    }
    public void setPlayListID(long playListID)
    {
        this.playListID=playListID;
    }
    public void setPlayListName(String playListName)
    {
        this.playListName = playListName;
    }
    public void setCreator(String creator)
    {
        this.creator = creator;
    }
    public void setAudios(ArrayList<AudioModel> audios)
    {
        this.audios = audios;
    }
    public long getPlayListID()
    {
        return playListID;
    }
    public String getPlayListName()
    {
        return playListName;
    }
    public String getCreator()
    {
        return creator;
    }
    public ArrayList<AudioModel> getAudios()
    {
        return audios;
    }
    public String toString ()
    {
        String string="Playlist name: "+this.playListName+"\nPlaylist ID: "+this.playListID+"\nCreator's username: "+this.creator;
        StringBuilder temp=new StringBuilder("\nAudios:\n");
        for(AudioModel audioTemp:this.audios)
            if(audioTemp!=null)
                temp.append(audioTemp).append("\n");
        return string+temp;
    }
    public Iterator<AudioModel> iterator()
    {
        return new Iterator<AudioModel>() {
            @Override
            public boolean hasNext()
            {
                if(audiosIndex<audios.size())
                    return true;
                else
                    return false;
            }
            @Override
            public AudioModel next()
            {
                return audios.get(audiosIndex++);
            }
        };
    }
}