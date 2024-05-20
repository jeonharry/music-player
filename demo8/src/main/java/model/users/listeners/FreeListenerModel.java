package model.users.listeners;

public class FreeListenerModel extends ListenerModel
{
    private final static int addAudioLimit=10;
    private final static int playListLimit=3;
    private int createdPlayLists;
    private int addedAudios;
    public FreeListenerModel(String userName, String password, String fullName, String email, String phoneNumber, String birthDate)
    {
        super(userName,password,fullName,email,phoneNumber,birthDate);
    }
    public void setAddedAudios(int addedAudios)
    {
        this.addedAudios = addedAudios;
    }
    public void setCreatedPlayLists(int createdPlayLists)
    {
        this.createdPlayLists = createdPlayLists;
    }
    public static int getAddAudioLimit()
    {
        return addAudioLimit;
    }
    public static int getPlayListLimit()
    {
        return playListLimit;
    }
    public int getCreatedPlayLists()
    {
        return this.createdPlayLists;
    }
    public int getAddedAudios()
    {
        return this.addedAudios;
    }
    public String toString()
    {
        return super.toString()+"Amount of created PlayLists: "+this.createdPlayLists+"\nAmount of added Audios: "+this.addedAudios;
    }
}
