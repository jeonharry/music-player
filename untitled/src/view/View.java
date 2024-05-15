package view;

import controller.AdminController;
import controller.ArtistController;
import controller.Controller;
import controller.ListenerController;
import model.audioRelated.AudioModel;
import model.audioRelated.PlayListModel;

import java.util.Scanner;

public class View
{
    Scanner input=new Scanner(System.in);
    private static View view;
    private View(){}
    public static View getView()
    {
        if(view==null)
            view=new View();
        return view;
    }
    public void showFirstMenu()
    {
        while(true)
        {
            String order=input.nextLine();
            String[] orders=order.split(" -");
            try
            {
                if(orders[0].compareTo("Signup")==0)
                {
                    if(orders[1].compareTo("L")==0)
                    {
                        String validOrNot=ListenerController.getListenerController().makeNewListener(orders[2],orders[3],orders[4],orders[5],orders[6],orders[7]);
                        System.out.println(validOrNot);
                        if(validOrNot.compareTo("Signed up successfully")==0)
                        {
                            System.out.println("Choose maximum 4 favorite genres from genres below:\n"+ListenerController.getListenerController().getGenres());
                            String favInputGenre=input.nextLine();
                            String[] inputSplit=favInputGenre.split(" -");
                            String[] genres=inputSplit[1].split(",");
                            int counter=0;
                            while(counter<4 && counter<genres.length)
                            {
                                if(ListenerController.getListenerController().addFavGenres(genres[counter]))
                                    counter++;
                                else
                                {
                                    System.out.println("genre doesn't exist");
                                    break;
                                }
                            }
                        }
                    }
                    else if(orders[1].compareTo("S")==0 || orders[1].compareTo("P")==0)
                        System.out.println(ArtistController.getArtistController().makeNewArtist(orders[2],orders[3],orders[4],orders[5],orders[6],orders[7],orders[8],orders[1]));
                    else
                        System.out.println("wrong order\ntry again");
                }
                else if(orders[0].compareTo("Login")==0)
                {
                    System.out.println(Controller.getController().logIn(orders[1],orders[2]));
                    if(Controller.getController().getAccType()!=null)
                    {
                        String exitOrNot;
                        if(Controller.getController().getAccType().compareTo("L")==0)
                            exitOrNot=showListenerPanel();
                        else if(Controller.getController().getAccType().compareTo("A")==0)
                            exitOrNot=showAdminPanel();
                        else
                            exitOrNot=showArtistPanel();
                        if(exitOrNot.compareTo("Exit")==0)
                            break;
                    }
                }
                else if (orders[0].compareTo("Logout")==0)
                {
                    if(Controller.getController().getAccType().compareTo("L")==0)
                    {
                        ListenerController.getListenerController().setListener(null);
                        Controller.getController().setAccModel(null);
                    }
                    else if(Controller.getController().getAccType().compareTo("A")==0)
                        Controller.getController().setAccModel(null);
                    else
                    {
                        ArtistController.getArtistController().setArtist(null);
                        Controller.getController().setAccModel(null);
                    }
                }
                else if(orders[0].compareTo("AccountInfo")==0)
                {
                    if(Controller.getController().getAccModel()!=null)
                        System.out.println(Controller.getController().getAccModel().toString());
                    else
                        System.out.println("not logged in");
                }
                else if(orders[0].compareTo("Exit")==0)
                {
                    System.out.println("Have a good day");
                    break;
                }
                else if(orders[0].compareTo("help")==0)
                    System.out.println(Controller.getController().help());
                else
                    System.out.println("wrong order\ntry again");
            }catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }
    public String showListenerPanel()
    {
        while(true)
        {
            String order=input.nextLine();
            String[] orders=order.split(" -");
            try
            {
                if(orders[0].compareTo("Add")==0)
                    System.out.println(ListenerController.getListenerController().addToPlayList(orders[1],orders[2]));
                else if(orders[0].compareTo("Artists")==0)
                    System.out.println(ListenerController.getListenerController().showArtists());
                else if(orders[0].compareTo("Artist")==0)
                    System.out.println(ListenerController.getListenerController().showArtist(orders[1]));
                else if(orders[0].compareTo("Follow")==0)
                    System.out.println(ListenerController.getListenerController().followArtist(orders[1]));
                else if(orders[0].compareTo("Search")==0)
                    System.out.println(ListenerController.getListenerController().search(orders[1]));
                else if(orders[0].compareTo("Sort")==0)
                {
                    if(orders.length==1)
                        System.out.println(ListenerController.getListenerController().sortBasedOnCompareTo());
                    else
                        System.out.println(ListenerController.getListenerController().sort(orders[1]));
                }
                else if(orders[0].compareTo("Filter")==0)
                {
                    if(orders[1].compareTo("D")==0)
                    {
                        String dates=orders[2]+" -"+orders[3];
                        System.out.println(ListenerController.getListenerController().doFilter(orders[1],dates));
                    }
                    else
                        System.out.println(ListenerController.getListenerController().doFilter(orders[1],orders[2]));
                }
                else if(orders[0].compareTo("GetSuggestions")==0)
                    System.out.println(ListenerController.getListenerController().getSuggestions());
                else if(orders[0].compareTo("NewPlaylist")==0)
                    System.out.println(ListenerController.getListenerController().makePlayList(orders[1]));
                else if(orders[0].compareTo("ShowPlaylists")==0)
                {
                    System.out.println("Playlists name:\n");
                    for(PlayListModel playListTemp : ListenerController.getListenerController().showPlayLists())
                        if(playListTemp!=null)
                            for(AudioModel audioTemp:playListTemp)
                                if(audioTemp!=null)
                                    System.out.println(audioTemp);
                }
                else if(orders[0].compareTo("SelectPlaylist")==0)
                {
                    PlayListModel playList=ListenerController.getListenerController().showPlayList(orders[1]);
                    if(playList==null)
                        System.out.println("playlist not found");
                    else
                    {
                        System.out.println("Audios:\n");
                        for(AudioModel temp :playList)
                            if(temp!=null)
                                System.out.println(temp);
                    }
                }
                else if(orders[0].compareTo("Play")==0)
                    System.out.println(ListenerController.getListenerController().playAudio(orders[1]));
                else if(orders[0].compareTo("Like")==0)
                    System.out.println(ListenerController.getListenerController().likeAudio(orders[1]));
                else if(orders[0].compareTo("Lyric")==0)
                    System.out.println(ListenerController.getListenerController().showLyricOrCaption(orders[1]));
                else if(orders[0].compareTo("Followings")==0)
                    System.out.println(ListenerController.getListenerController().showFollowings());
                else if(orders[0].compareTo("Report")==0)
                    System.out.println(ListenerController.getListenerController().report(orders[1],orders[2]));
                else if(orders[0].compareTo("IncreaseCredit")==0)
                    ListenerController.getListenerController().increaseCredit(orders[1]);
                else if(orders[0].compareTo("GetPremium")==0)
                    System.out.println(ListenerController.getListenerController().buyPremium(orders[1]));
                else if (orders[0].compareTo("Logout")==0)
                {
                    ListenerController.getListenerController().setListener(null);
                    Controller.getController().setAccModel(null);
                    return "noExit";
                }
                else if(orders[0].compareTo("AccountInfo")==0)
                    System.out.println(ListenerController.getListenerController().getAccInfo());
                else if(orders[0].compareTo("help")==0)
                    System.out.println(Controller.getController().help());
                else if(orders[0].compareTo("Exit")==0)
                {
                    System.out.println("Have a good day");
                    return "Exit";
                }
                else
                    System.out.println("wrong order\ntry again");

            }catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }
    public String showArtistPanel()
    {
        while(true)
        {
            String order = input.nextLine();
            String[] orders = order.split(" -");
            try
            {
                if(orders[0].compareTo("Followers")==0)
                    System.out.println(ArtistController.getArtistController().showFollowers());
                else if (orders[0].compareTo("ViewsStatistics")==0)
                    System.out.println(ArtistController.getArtistController().showViewsStatistics());
                else if (orders[0].compareTo("NewAlbum")==0)
                    System.out.println(ArtistController.getArtistController().makeAlbum(orders[1]));
                else if (orders[0].compareTo("Publish")==0)
                {
                    if(orders[1].compareTo("M")==0)
                        System.out.println(ArtistController.getArtistController().publish(orders[2],orders[3],orders[4],orders[5],orders[6],orders[7]));
                    else if(orders[1].compareTo("P")==0)
                        System.out.println(ArtistController.getArtistController().publish(orders[2],orders[3],orders[4],orders[5],orders[6]));
                    else
                        System.out.println("wrong order\ntry again");
                }
                else if (orders[0].compareTo("CalculateEarnings")==0)
                    System.out.println(ArtistController.getArtistController().CalculateEarnings());
                else if (orders[0].compareTo("Logout")==0)
                {
                    ArtistController.getArtistController().setArtist(null);
                    Controller.getController().setAccModel(null);
                    return "noExit";
                }
                else if(orders[0].compareTo("AccountInfo")==0)
                    System.out.println(Controller.getController().getAccModel().toString());
                else if(orders[0].compareTo("help")==0)
                    System.out.println(Controller.getController().help());
                else if(orders[0].compareTo("Exit")==0)
                {
                    System.out.println("Have a good day");
                    return "Exit";
                }
                else
                    System.out.println("wrong order\ntry again");
            }catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }
    public String showAdminPanel()
    {
        while(true)
        {
            String order = input.nextLine();
            String[] orders = order.split(" -");
            try
            {
                if(orders[0].compareTo("Reports")==0)
                    System.out.println(AdminController.getAdminController().getReports());
                else if(orders[0].compareTo("Artists")==0)
                    System.out.println(AdminController.getAdminController().getArtists());
                else if(orders[0].compareTo("Artist")==0)
                    System.out.println(AdminController.getAdminController().getArtist(orders[1]));
                else if(orders[0].compareTo("Audios")==0)
                    System.out.println(AdminController.getAdminController().getAudios());
                else if(orders[0].compareTo("Audio")==0)
                    System.out.println(AdminController.getAdminController().getAudio(orders[1]));
                else if(orders[0].compareTo("Statistics")==0)
                    System.out.println(AdminController.getAdminController().showStatistics());
                else if (orders[0].compareTo("Logout")==0)
                {
                    Controller.getController().setAccModel(null);
                    return "noExit";
                }
                else if(orders[0].compareTo("AccountInfo")==0)
                    System.out.println(Controller.getController().getAccModel().toString());
                else if(orders[0].compareTo("help")==0)
                    System.out.println(Controller.getController().help());
                else if(orders[0].compareTo("Exit")==0)
                {
                    System.out.println("Have a good day");
                    return "Exit";
                }
                else
                    System.out.println("wrong order\ntry again");
            }catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }
}
