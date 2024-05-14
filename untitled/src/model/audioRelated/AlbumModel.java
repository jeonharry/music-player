package model.audioRelated;

import java.util.ArrayList;

public class AlbumModel
{
    private long albumID;
    private String albumName;
    private String nameOfArtist;
    private ArrayList <MusicModel> musics=new ArrayList<>();
    private static long albumAmount;
    public AlbumModel(String albumName, String nameOfArtist)
    {
        this.albumName=albumName;
        this.nameOfArtist=nameOfArtist;
        albumAmount++;
    }
    public static void setAlbumAmount(long albumAmount)
    {
        AlbumModel.albumAmount = albumAmount;
    }
    public void setAlbumID(long albumID)
    {
        this.albumID=albumID;
    }
    public void setAlbumName(String albumName)
    {
        this.albumName=albumName;
    }
    public void setNameOfArtist(String nameOfArtist)
    {
        this.nameOfArtist = nameOfArtist;
    }
    public void setMusics(ArrayList<MusicModel> musics)
    {
        this.musics = musics;
    }
    public long getAlbumID()
    {
        return this.albumID;
    }
    public static long getAlbumAmount()
    {
        return albumAmount;
    }
    public ArrayList<MusicModel> getMusics()
    {
        return musics;
    }
    public String getAlbumName()
    {
        return albumName;
    }
    public String getNameOfArtist()
    {
        return this.nameOfArtist;
    }
    public String toString()
    {
        String string="Album name: "+this.albumName+"\nAlbum ID: "+this.albumID+"\nAlbum's artist: "+this.nameOfArtist;
        StringBuilder temp=new StringBuilder("\nMusics:\n");
        for(MusicModel musicTemp:this.musics)
            if(musicTemp!=null)
                temp.append(musicTemp).append("\n");
        return string+temp;
    }
}
