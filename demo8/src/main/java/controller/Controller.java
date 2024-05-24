package controller;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import exceptions.InvalidFormatException;
import exceptions.UserNotFoundException;
import exceptions.WrongPasswordException;
import model.Database;
import model.audioRelated.AudioModel;
import model.users.AccountUserModel;
import model.users.AdminModel;
import model.users.artists.ArtistModel;
import model.users.artists.PodcasterModel;
import model.users.artists.SingerModel;
import model.users.listeners.ListenerModel;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Controller
{
    private String pageType="home";
    private static ArrayList <AudioModel>musics;
    private static Controller controller;
    private AccountUserModel accModel;
    private BorderPane basePage;
    private StackPane stackPane;
    private Node node;
    private Scene previousScene;
    private AudioModel currentAudio;
    private boolean sideBarIsVisible=false;
    private ArtistModel selectedArtist;
    private String previousPage="home";
    private Controller(){}
    public static Controller getController()
    {
        if(controller==null)
            controller=new Controller();
        return controller;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }

    public ArtistModel getSelectedArtist() {
        return selectedArtist;
    }

    public void setSelectedArtist(ArtistModel selectedArtist) {
        this.selectedArtist = selectedArtist;
    }

    public AudioModel getCurrentAudio() {
        return currentAudio;
    }

    public void setCurrentAudio(AudioModel currentAudio) {
        this.currentAudio = currentAudio;
    }

    public boolean isSideBarIsVisible() {
        return sideBarIsVisible;
    }

    public void setSideBarIsVisible(boolean sideBarIsVisible) {
        this.sideBarIsVisible = sideBarIsVisible;
    }

    public Scene getPreviousScene()
    {
        return this.previousScene;
    }
    public Node getNode()
    {
        return this.node;
    }
    public StackPane getStackPane()
    {
        return this.stackPane;
    }
    public String getPageType()
    {
        return this.pageType;
    }
    public BorderPane getBasePage()
    {
        return this.basePage;
    }
    public void setBasePage(BorderPane basePage)
    {
        this.basePage = basePage;
    }
    public void setAccModel(AccountUserModel accModel)
    {
        this.accModel = accModel;
    }
    public static void setMusics(ArrayList<AudioModel> musics)
    {
        Controller.musics = musics;
    }
    public AccountUserModel getAccModel()
    {
        return this.accModel;
    }
    public static ArrayList<AudioModel> getMusics()
    {
        return musics;
    }
    public void setPageType(String pageType)
    {
        this.pageType = pageType;
    }
    public void setStackPane(StackPane stackPane)
    {
        this.stackPane = stackPane;
    }
    public void setNode(Node node)
    {
        this.node = node;
    }
    public void setPreviousScene(Scene previousScene)
    {
        this.previousScene = previousScene;
    }
    public String getAccType()
    {
        if(this.accModel instanceof ListenerModel)
            return "L";
        else if(this.accModel instanceof SingerModel)
            return "S";
        else if(this.accModel instanceof PodcasterModel)
            return "P";
        else if(this.accModel instanceof AdminModel)
            return "A";
        else
            return null;
    }
    public String logIn(String userName,String password) throws Exception
    {
        if(AdminModel.getAdmin().getUserName().compareTo(userName)==0)
        {
            if(AdminModel.getAdmin().getPassword().compareTo(password)==0)
            {
                setAccModel(AdminModel.getAdmin());
                return "logged in";
            }
            else
                throw new WrongPasswordException();
        }
        boolean exist=false;
        for(AccountUserModel temp: Database.getDatabase().getAllUsers())
            if(temp!=null && temp.getUserName().compareTo(userName)==0)
            {
                exist=true;
                if (password.compareTo(temp.getPassword())!=0)
                {
                    throw new WrongPasswordException();
                }
                if(temp instanceof ListenerModel)
                {
                    ListenerController.getListenerController().setListener((ListenerModel)temp);
                    setAccModel(ListenerController.getListenerController().getListener());
                }
                else if(temp instanceof SingerModel)
                {
                    ArtistController.getArtistController().setArtist((SingerModel)temp);
                    setAccModel(ArtistController.getArtistController().getArtist());
                }
                else if(temp instanceof PodcasterModel)
                {
                    ArtistController.getArtistController().setArtist((PodcasterModel)temp);
                    setAccModel(ArtistController.getArtistController().getArtist());
                }
                break;
            }
        if(!exist)
            throw new UserNotFoundException();
        return "logged in";
    }
    public String makeNewAccount(String userName,String email, String phoneNumber, String birthDate)throws Exception
    {
        for(AccountUserModel temp: Database.getDatabase().getAllUsers())
            if(temp!=null && userName.compareTo(temp.getUserName())==0)
                return "This username already exists";
        String numRegex="^09\\d{9}$";
        String emailRegex="^[^(\\.|\\W)](?=\\d*[a-zA-Z])([a-zA-Z0-9]\\.?){1,25}[^(\\.|\\W)]@(?=\\d*[a-zA-Z])([a-zA-Z0-9]-?){2,28}[^\\W-]\\.[a-zA-Z]{2,20}$";
        String dateRegex="^\\d{4}/([1][0-2]|[1-9]|[0][1-9])/([1-2][0-9]|30|31|[0-9]|0[0-9])$";
        Pattern numPattern=Pattern.compile(numRegex);
        Pattern emailPattern=Pattern.compile(emailRegex);
        Pattern datePattern=Pattern.compile(dateRegex);
        if(!numPattern.matcher(phoneNumber).matches())
            throw new InvalidFormatException("phoneNumber isn't valid");
        else if(!emailPattern.matcher(email).matches())
            throw new InvalidFormatException("email isn't valid");
        else if(!datePattern.matcher(birthDate).matches())
            throw new InvalidFormatException("birth date isn't valid");
        return "Signed up successfully";
    }
    public String help()
    {
        StringBuilder help=new StringBuilder("inputs format should be like commands bellow:\n");
        help.append("Signup -L|S|P -[username] -[password] -[name] -[email] -[phone number] -[birth date] -[bio] (for artists)\nnew user: Listener or Singer or Podcaster\n");
        help.append("FavouriteGenres -[favourite genres separated with comma(,)] (only for listeners, after signup)\n");
        help.append("Login -[username] -[password]\n");
        help.append("Logout\n");
        help.append("AccountInfo\n");
        help.append("for listeners only:\n");
        help.append(" GetSuggestions\nArtists\nArtist -[username]\nFollow -[username]\nSearch -[artist name OR audio’s title]\nSort -L|P (based on Likes or Plays)\n");
        help.append("Filter -A|G|D -[filter by (Filter by Artist, Genre or Date)\nAdd -[playlist’s name] -[audio’s ID]\nShowPlaylists\nSelectPlaylist -[playlist’s name]\n");
        help.append("Play -[audio’s ID]\nLike -[audio’s ID]\nLyric -[audio’s ID]\nNewPlaylist -[playlist’s name]\nFollowings\nReport -[artist’s username] -[explanation]\nIncreaseCredit -[value]\nGetPremium -[package]\n");
        help.append("for artists only:\n");
        help.append("Followers\nViewsStatistics\nCalculateEarnings\n");
        help.append("for singers only:\nNewAlbum -[name]\nPublish -M -[title] -[genre] -[lyric] -[link] -[cover] -[album ID ]\n");
        help.append("for podcasters only:\nPublish -P -[title] -[genre] -[caption] -[link] -[cover]\n");
        help.append("for admin only:\n");
        help.append("Statistics\nAudios\nAudio -[audio’s ID]\nArtists\nArtist -[username]\nReports\n");
        return help.toString();
    }
    public ArrayList <AudioModel> getMostLikedAudios()
    {
        ArrayList <AudioModel> answer=new ArrayList<>();
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
                answer.add(temp);
        return answer;
    }
    public ArrayList[] search(String audioOrArtistName)
    {
        ArrayList[] answer=new ArrayList[2];
        answer[0]=new ArrayList<AudioModel>();
        answer[1]=new ArrayList<ArtistModel>();
        for(AudioModel temp:Database.getDatabase().getAllAudios())
        {
            if(temp!=null)
            {
                if(temp.getAudioName().compareTo(audioOrArtistName)==0)
                    answer[0].add(temp);
            }
        }
        for(AccountUserModel temp :Database.getDatabase().getAllUsers())
        {
            if(temp!=null)
            {
                if(temp instanceof ArtistModel && temp.getFullName().compareTo(audioOrArtistName)==0)
                    answer[1].add(temp);
            }
        }
        if(!(answer[0].isEmpty() && answer[1].isEmpty()))
            return answer;
        return null;
    }
    public ArrayList <ArtistModel> getArtists()
    {
        ArrayList <ArtistModel> answer=new ArrayList<>();
        for(AccountUserModel temp:Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel)
            {
                answer.add((ArtistModel) temp);
            }
        return answer;
    }
}
