package model.users.artists;

import model.audioRelated.AlbumModel;

import java.util.ArrayList;

public class SingerModel extends ArtistModel
{
    private ArrayList <AlbumModel>albums=new ArrayList<>();
    public SingerModel(String userName, String password, String fullName, String email, String phoneNumber, String birthDate, String bio)
    {
        super(userName,password,fullName,email,phoneNumber,birthDate,bio);
    }
    public void setAlbums(ArrayList<AlbumModel> albums)
    {
        this.albums = albums;
    }
    public ArrayList<AlbumModel> getAlbums()
    {
        return this.albums;
    }
    public String toString()
    {
        StringBuilder temp=new StringBuilder("\nAlbums:\n");
        for(AlbumModel albumTemp:this.albums)
            if(albumTemp!=null)
                temp.append(albumTemp).append("\n");
        return super.toString()+temp;
    }
}
