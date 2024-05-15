package controller;

import exceptions.FreeAccountLimitException;
import exceptions.InvalidFormatException;
import model.Database;
import model.audioRelated.*;
import model.report.Report;
import model.users.AccountUserModel;
import model.users.Subscription;
import model.users.artists.ArtistModel;
import model.users.artists.PodcasterModel;
import model.users.artists.SingerModel;
import model.users.listeners.FreeListenerModel;
import model.users.listeners.ListenerModel;
import model.users.listeners.PremiumListenerModel;
import java.time.Duration;
import java.util.*;
import java.util.regex.Pattern;

public class ListenerController
{
    private static ListenerController listenerController;
    private ListenerModel listener;
    private ListenerController(){}
    public static ListenerController getListenerController()
    {
        if(listenerController==null)
            listenerController=new ListenerController();
        return listenerController;
    }
    public ListenerModel getListener()
    {
        return this.listener;
    }
    public void setListener(ListenerModel listener)
    {
        this.listener = listener;
    }
    public String makeNewListener(String userName,String password,String fullName,String email,String phoneNumber,String birthDate) throws Exception {
        String answer;
        answer=Controller.getController().makeNewAccount(userName,email,phoneNumber,birthDate);
        if(answer.compareTo("Signed up successfully")==0)
        {
            ListenerModel tempListener;
            tempListener=new FreeListenerModel(userName,password,fullName,email,phoneNumber,birthDate);
            tempListener.setListenerCredit(50);
            tempListener.setSubscriptionExpiration(null);
            setListener(tempListener);
            Database.getDatabase().getAllUsers().add(tempListener);
        }
        return answer;
    }
    public String getGenres()
    {
        StringBuilder temp=new StringBuilder();
        for(Genre genre:Genre.values())
            temp.append(genre).append(" ");
        return temp.toString();
    }
    public boolean addFavGenres(String genre)
    {
        if(genre.compareTo("Rock")==0)
        {
            getListener().getFavGenres().add(Genre.ROCK);
            return true;
        }
        else if(genre.compareTo("Pop")==0)
        {
            getListener().getFavGenres().add(Genre.POP);
            return true;
        }
        else if(genre.compareTo("Jazz")==0)
        {
            getListener().getFavGenres().add(Genre.JAZZ);
            return true;
        }
        else if(genre.compareTo("Country")==0)
        {
            getListener().getFavGenres().add(Genre.COUNTRY);
            return true;
        }
        else if(genre.compareTo("TrueCrime")==0)
        {
            getListener().getFavGenres().add(Genre.TRUE_CRIME);
            return true;
        }
        else if(genre.compareTo("HipHop")==0)
        {
            getListener().getFavGenres().add(Genre.HIPHOP);
            return true;
        }
        else if(genre.compareTo("History")==0)
        {
            getListener().getFavGenres().add(Genre.HISTORY);
            return true;
        }
        else if(genre.compareTo("InterView")==0)
        {
            getListener().getFavGenres().add(Genre.INTERVIEW);
            return true;
        }
        else if(genre.compareTo("Society")==0)
        {
            getListener().getFavGenres().add(Genre.SOCIETY);
            return true;
        }
        else
            return false;
    }
    public void changeToFree()
    {
        String birthDate=Integer.toString(getListener().getBirthDate().get(Calendar.YEAR))+"/"+Integer.toString((getListener().getBirthDate().get(Calendar.MONTH)+1))+"/"+Integer.toString(getListener().getBirthDate().get(Calendar.DATE));
        FreeListenerModel freeListener=new FreeListenerModel(getListener().getUserName(),getListener().getPassword(),getListener().getFullName(),getListener().getEmail(),getListener().getPhoneNumber(),birthDate);
        freeListener.setSubscriptionExpiration(null);
        freeListener.setListenerCredit(getListener().getListenerCredit());
        freeListener.setFollowings(getListener().getFollowings());
        freeListener.setFavGenres(getListener().getFavGenres());
        freeListener.setLikedAudios(getListener().getLikedAudios());
        freeListener.setPlayingAmount(getListener().getPlayingAmount());
        freeListener.setPlayLists(getListener().getPlayLists());
        Database.getDatabase().getAllUsers().remove(getListener());
        Database.getDatabase().getAllUsers().add(freeListener);
        setListener(freeListener);
    }
    public String addToPlayList(String playListName,String audioID) throws Exception
    {
        if (getListener() instanceof PremiumListenerModel && ((PremiumListenerModel)getListener()).getRemainingDays()<=0)
        {
            changeToFree();
            return addToPlayList(playListName,audioID);
        }
        else if((getListener() instanceof PremiumListenerModel && ((PremiumListenerModel)getListener()).getRemainingDays()>0) || (getListener() instanceof FreeListenerModel && (((FreeListenerModel) getListener()).getAddedAudios()<FreeListenerModel.getAddAudioLimit())))
        {
            boolean check=false;
            AudioModel chosenAudio =null;
            for(AudioModel temp:Database.getDatabase().getAllAudios())
                if(temp!=null && temp.getAudioID()==Long.parseLong(audioID))
                {
                    check=true;
                    chosenAudio=temp;
                    break;
                }
            if(!check)
                return "audio doesn't exist";
            check=false;
            for(PlayListModel temp:listener.getPlayLists())
                if(temp!=null && temp.getPlayListName().compareTo(playListName)==0)
                {
                    for(AudioModel audio:temp.getAudios())
                        if(audio!=null && audio.getAudioID()==Long.parseLong(audioID))
                            return "its already in the playlist";
                    temp.getAudios().add(chosenAudio);
                    check=true;
                    break;
                }
            if(!check)
                return "playlist doesn't exist";
            if(getListener() instanceof  FreeListenerModel)
                ((FreeListenerModel)getListener()).setAddedAudios(((FreeListenerModel) getListener()).getAddedAudios()+1);
            return "audio added to playlist successfully";
        }
        else
            throw new FreeAccountLimitException("you already added 10 audios buy premium for more");
    }
    public String showArtists()
    {
        StringBuilder answer=new StringBuilder("Artists usernames:\n");
        for(AccountUserModel temp:Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel)
                answer.append(temp.getUserName()).append("\n");
        return answer.toString();
    }
    public Object showArtist(String artistUserName)
    {
        for(AccountUserModel temp:Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel && temp.getUserName().compareTo(artistUserName)==0)
                return temp;
        return "artist doesn't exist";
    }
    public String followArtist(String artistUserName)
    {
        for(AccountUserModel temp:Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel && temp.getUserName().compareTo(artistUserName)==0)
            {
                ((ArtistModel) temp).getFollowers().add(getListener());
                getListener().getFollowings().add((ArtistModel)temp);
                return "added to followings";
            }
        return "artist doesn't exist";
    }
    public String search(String audioOrArtistName)
    {
        boolean firstRound=true;
        StringBuilder answer=new StringBuilder("");
        for(AudioModel temp:Database.getDatabase().getAllAudios())
        {
            if(temp!=null)
            {
                if(firstRound)
                    answer.append("Audios:\n");
                if(temp.getAudioName().compareTo(audioOrArtistName)==0)
                    answer.append("Audio name: ").append(temp.getAudioName()).append("\nAudio ID: ").append(temp.getAudioID()).append("\n");
            }
            firstRound=false;
        }
        firstRound=true;
        for(AccountUserModel temp :Database.getDatabase().getAllUsers())
        {
            if(temp!=null)
            {
                if(firstRound)
                    answer.append("Artists:\n");
                if(temp instanceof ArtistModel && temp.getFullName().compareTo(audioOrArtistName)==0)
                    answer.append("User name: ").append(temp.getUserName()).append("\nFull name: ").append(temp.getFullName()).append("\n");
            }
            firstRound=false;
        }
        if(answer.toString().compareTo("")!=0)
            return answer.toString();
        return "no result";
    }
    public String sort(String sortType)
    {
        StringBuilder answer=new StringBuilder();
        if(sortType.compareTo("L")==0)
        {
            AudioModel[] audios =Database.getDatabase().getAllAudios().toArray(new AudioModel[Database.getDatabase().getAllAudios().size()]);
            for(int i=0;i<audios.length-1;++i)
            {
                for(int j=i+1;j<audios.length;++j)
                    if(audios[i].getLikeAmount()<audios[j].getLikeAmount())
                    {
                        AudioModel temp=audios[i];
                        audios[i]=audios[j];
                        audios[j]=temp;
                    }
            }
            for(AudioModel temp:audios)
                if(temp!=null)
                    answer.append("AudioID: ").append(temp.getAudioID()).append("\nAudio name: ").append(temp.getAudioName()).append("\nlike amount: ").append(temp.getLikeAmount()).append("\n");
            return answer.toString();
        }
        else if(sortType.compareTo("P")==0)
        {
            AudioModel[] audios =Database.getDatabase().getAllAudios().toArray(new AudioModel[Database.getDatabase().getAllAudios().size()]);
            for(int i=0;i<audios.length-1;++i)
            {
                for(int j=i+1;j<audios.length;++j)
                    if(audios[i].getPlayAmount()<audios[j].getPlayAmount())
                    {
                        AudioModel temp=audios[i];
                        audios[i]=audios[j];
                        audios[j]=temp;
                    }
            }
            for(AudioModel temp:audios)
                if(temp!=null)
                    answer.append("AudioID: ").append(temp.getAudioID()).append("\nAudio name: ").append(temp.getAudioName()).append("\nplay amount: ").append(temp.getPlayAmount()).append("\n");
            return answer.toString();
        }
        else
            return "wrong order";
    }
    public Genre getGenre(String genre)
    {
        if(genre.compareTo("Rock")==0)
        {
            return Genre.ROCK;
        }
        else if(genre.compareTo("Pop")==0)
        {
            return Genre.POP;
        }
        else if(genre.compareTo("Jazz")==0)
        {
            return Genre.JAZZ;
        }
        else if(genre.compareTo("Country")==0)
        {
            return Genre.COUNTRY;
        }
        else if(genre.compareTo("TrueCrime")==0)
        {
            return Genre.TRUE_CRIME;
        }
        else if(genre.compareTo("HipHop")==0)
        {
            return Genre.HIPHOP;
        }
        else if(genre.compareTo("History")==0)
        {
            return Genre.HISTORY;
        }
        else if(genre.compareTo("InterView")==0)
        {
            return Genre.INTERVIEW;
        }
        else if(genre.compareTo("Society")==0)
        {
            return Genre.SOCIETY;
        }
        else
            return null;
    }
    public String doFilter(String filter,String filterBy)throws Exception
    {
        StringBuilder answer=new StringBuilder();
        if(filter.compareTo("A")==0)
        {
            for(AccountUserModel temp:Database.getDatabase().getAllUsers())
                if(temp!=null && temp.getFullName().compareTo(filterBy)==0)
                    answer.append("Artist name: ").append(temp.getFullName()).append("\nArtist username: ").append(temp.getUserName()).append("\n");
            return answer.toString();
        }
        else if(filter.compareTo("G")==0)
        {
            for(AudioModel temp:Database.getDatabase().getAllAudios())
                if(temp!= null && temp.getGenre().compareTo(getGenre(filterBy))==0 && getGenre(filterBy)!=null)
                    answer.append("AudioID: ").append(temp.getAudioID()).append("\nAudio name: ").append(temp.getAudioName()).append("\n");
            return answer.toString();
        }
        else if(filter.compareTo("D")==0)
        {
            String[] dates=filterBy.split(" -");
            String dateRegex="^\\d{4}/([1][0-2]|[1-9]|[0][1-9])/([1-2][0-9]|30|31|[0-9]|0[0-9])$";
            Pattern datePattern=Pattern.compile(dateRegex);
            if(!datePattern.matcher(dates[0]).matches())
                throw new InvalidFormatException("date isn't valid");
            if(!datePattern.matcher(dates[1]).matches())
                throw new InvalidFormatException("date isn't valid");
            Calendar startDate =Calendar.getInstance();
            Calendar endDate =Calendar.getInstance();
            startDate.setTime(new Date(dates[0]));
            endDate.setTime(new Date(dates[1]));
            if(startDate.after(endDate))
            {
                startDate.setTime(new Date(dates[1]));
                endDate.setTime(new Date(dates[0]));
            }
            for(AudioModel temp:Database.getDatabase().getAllAudios())
                if(temp!=null && startDate.before(temp.getReleaseDate()) && endDate.after(temp.getReleaseDate()))
                    answer.append("AudioID: ").append(temp.getAudioID()).append("\nAudio name: ").append(temp.getAudioName()).append("\n");
            return answer.toString();
        }
        else
            return "wrong order";
    }
    public Map.Entry <Genre,Long>[] getTopTenMostPlayedGenre()
    {
        Map.Entry <AudioModel,Long>[] mostPlayed=getListener().getPlayingAmount().entrySet().toArray(new Map.Entry[getListener().getPlayingAmount().size()]);
        for(int i=0;i<mostPlayed.length-1;++i)
            for(int j=i+1;j<mostPlayed.length;++j)
                if(mostPlayed[i].getValue()<mostPlayed[j].getValue())
                {
                    Map.Entry <AudioModel,Long>temp=mostPlayed[j];
                    mostPlayed[j]=mostPlayed[i];
                    mostPlayed[i]=temp;
                }
        Map <Genre,Long> mostPlayedGenre=new HashMap<>();
        for(int i=0;i<mostPlayed.length;++i)
            if(mostPlayed[i].getValue()>0 && i<10)         //top ten most played
            {
                if(!mostPlayedGenre.containsKey(mostPlayed[i].getKey().getGenre()))
                    mostPlayedGenre.put(mostPlayed[i].getKey().getGenre(), 1L);
                else
                    mostPlayedGenre.replace(mostPlayed[i].getKey().getGenre(),mostPlayedGenre.get(mostPlayed[i].getKey().getGenre())+1);
            }
        Map.Entry <Genre,Long>[] favGenres=mostPlayedGenre.entrySet().toArray(new Map.Entry[mostPlayedGenre.size()]);
        for(int i=0;i<favGenres.length-1;++i)
            for(int j=i+1;j<favGenres.length;++j)
                if(favGenres[i].getValue()<favGenres[j].getValue())
                {
                    Map.Entry <Genre,Long>temp=favGenres[i];
                    favGenres[i]=favGenres[j];
                    favGenres[j]=temp;
                }
        return favGenres;
    }
    public Map.Entry <Genre,Long>[] getGenreOfLikedAudios()
    {
        Map <Genre,Long> genreOfLikedAudios=new HashMap<>();
        for(AudioModel temp: getListener().getLikedAudios())
            if(temp!=null)
            {
                if(!genreOfLikedAudios.containsKey(temp.getGenre()))
                    genreOfLikedAudios.put(temp.getGenre(),1L);
                else
                    genreOfLikedAudios.replace(temp.getGenre(),genreOfLikedAudios.get(temp.getGenre())+1);
            }
        Map.Entry <Genre,Long>[] genres=genreOfLikedAudios.entrySet().toArray(new Map.Entry[genreOfLikedAudios.size()]);
        for(int i=0;i<genres.length-1;++i)
            for(int j=i+1;j<genres.length;++j)
                if(genres[i].getValue()<genres[j].getValue())
                {
                    Map.Entry <Genre,Long>temp=genres[i];
                    genres[i]=genres[j];
                    genres[j]=temp;
                }
        return genres;
    }
    public ArrayList <AudioModel> getMostPlayedAudios()
    {
        AudioModel[] audios;
        audios=Database.getDatabase().getAllAudios().toArray(new AudioModel[Database.getDatabase().getAllAudios().size()]);
        for(int i=0;i<audios.length-1;++i)
            for(int j=i+1;j<audios.length;++j)
                if(audios[i].getPlayAmount()<audios[j].getPlayAmount())
                {
                    AudioModel temp=audios[i];
                    audios[i]=audios[j];
                    audios[j]=temp;
                }
        return new ArrayList<>(Arrays.asList(audios));
    }
    public String getSuggestions()
    {
        ArrayList <AudioModel> suggestions=new ArrayList<>();
        StringBuilder answer=new StringBuilder();
        int audioAmount=0;
        Map.Entry <Genre,Long>[] mostPlayedGenre=getTopTenMostPlayedGenre();
        Map.Entry <Genre,Long>[] likedGenres=getGenreOfLikedAudios();
        ArrayList <Genre> genres=new ArrayList<>();
        if(mostPlayedGenre[0].getKey()!=null)
            genres.add(mostPlayedGenre[0].getKey());
        if(mostPlayedGenre[1].getKey()!=null && !genres.contains(mostPlayedGenre[1].getKey()))
            genres.add(mostPlayedGenre[1].getKey());
        if(likedGenres[0].getKey()!=null && !genres.contains(likedGenres[0].getKey()))
            genres.add(likedGenres[0].getKey());
        if(likedGenres[1].getKey()!=null && !genres.contains(likedGenres[1].getKey()))
            genres.add(likedGenres[1].getKey());
        if(getListener().getFavGenres().get(0)!=null && !genres.contains(getListener().getFavGenres().get(0)))
            genres.add(getListener().getFavGenres().get(0));
        if(getListener().getFavGenres().get(1)!=null && !genres.contains(getListener().getFavGenres().get(1)))
            genres.add(getListener().getFavGenres().get(1));
        for(ArtistModel temp:getListener().getFollowings())
        {
            if(audioAmount==10)
                break;
            if(temp instanceof PodcasterModel)
            {
                for(PodcastModel audioTemp:((PodcasterModel)temp).getPodcasts())
                {
                    if(audioTemp!=null)
                        for(Genre genreTemp:genres)
                        {
                            if(genreTemp!=null && audioTemp.getGenre().compareTo(genreTemp)==0)
                            {
                                suggestions.add(audioTemp);
                                answer.append("AudioID: ").append(audioTemp.getAudioID()).append("\nAudio name: ").append(audioTemp.getAudioName()).append("\n");
                                audioAmount++;
                                break;
                            }
                        }
                }
            }
            else
            {
                for(AlbumModel albumTemp:((SingerModel)temp).getAlbums())
                    if(albumTemp!=null)
                        for(MusicModel musicTemp:albumTemp.getMusics())
                            if(musicTemp!=null)
                                for(Genre genreTemp:genres)
                                {
                                    if(genreTemp!=null && musicTemp.getGenre().compareTo(genreTemp)==0)
                                    {
                                        suggestions.add(musicTemp);
                                        answer.append("AudioID: ").append(musicTemp.getAudioID()).append("\nAudio name: ").append(musicTemp.getAudioName()).append("\n");
                                        audioAmount++;
                                        break;
                                    }
                                }
            }
        }
        if(audioAmount==10)
            return answer.toString();
        else
        {
            int counter=0;
            boolean check=false;
            ArrayList <AudioModel>audios=getMostPlayedAudios();
            for(int i=0;counter<10-audioAmount && i<audios.size();++i)
                if(audios.get(i)!=null)
                {
                    if(audioAmount!=0)
                    {
                        for(AudioModel audio:suggestions)
                            if(audio!=null && audio.toString().compareTo(audios.get(i).toString())==0)
                            {
                                check=true;
                                break;
                            }
                        if(!check)
                        {
                            answer.append("AudioID: ").append(audios.get(i).getAudioID()).append("\nAudio name: ").append(audios.get(i).getAudioName()).append("\n");
                            counter++;
                        }
                        check=false;
                    }
                    else
                        answer.append("AudioID: ").append(audios.get(i).getAudioID()).append("\nAudio name: ").append(audios.get(i).getAudioName()).append("\n");
                }
            return answer.toString();
        }
    }
    public String makePlayList(String playlistName)throws Exception
    {
        if (getListener() instanceof PremiumListenerModel && ((PremiumListenerModel)getListener()).getRemainingDays()<=0)
        {
            changeToFree();
            return makePlayList(playlistName);
        }
        else if((getListener() instanceof PremiumListenerModel && ((PremiumListenerModel)getListener()).getRemainingDays()>0) || (getListener() instanceof FreeListenerModel && ((FreeListenerModel) getListener()).getCreatedPlayLists()<FreeListenerModel.getPlayListLimit()))
        {
            for(PlayListModel playList: getListener().getPlayLists())
                if(playList!=null && playList.getPlayListName().compareTo(playlistName)==0)
                    return "playlist already exist";
            PlayListModel temp=new PlayListModel(playlistName,getListener().getUserName());
            long playListID=0;
            char[] userName=getListener().getUserName().toCharArray();
            for(int i=0;i<userName.length;++i)
                playListID+=userName[i];
            String fullID=Long.toString(playListID)+Long.toString(PlayListModel.getAmountOfPlaylists());
            temp.setPlayListID(Long.parseLong(fullID));
            getListener().getPlayLists().add(temp);
            if (getListener() instanceof FreeListenerModel)
                ((FreeListenerModel)getListener()).setCreatedPlayLists(((FreeListenerModel) getListener()).getCreatedPlayLists()+1);
            return "playList made successfully";
        }
        else
            throw new FreeAccountLimitException("you already created 3 playlists");
    }
    public ArrayList<PlayListModel> showPlayLists()
    {
        return getListener().getPlayLists();
    }
    public PlayListModel showPlayList(String playListName)
    {
        for(PlayListModel temp: getListener().getPlayLists())
            if(temp!=null && temp.getPlayListName().compareTo(playListName)==0)
                return temp;
        return null;
    }
    public String playAudio(String audioID)
    {
        for(AudioModel temp:Database.getDatabase().getAllAudios())
            if(temp!=null && temp.getAudioID()==Long.parseLong(audioID))
            {
                temp.setPlayAmount(temp.getPlayAmount()+1);
                if(!getListener().getPlayingAmount().containsKey(temp))
                    getListener().getPlayingAmount().put(temp,1L);
                else
                    getListener().getPlayingAmount().replace(temp,getListener().getPlayingAmount().get(temp)+1);
                return temp.toString();
            }
        return "Audio not found";
    }
    public String likeAudio(String audioID)
    {
        for(AudioModel temp:Database.getDatabase().getAllAudios())
            if(temp!=null && temp.getAudioID()==Long.parseLong(audioID))
            {
                temp.setLikeAmount(temp.getLikeAmount()+1);
                getListener().getLikedAudios().add(temp);
                return "liked audio";
            }
        return "Audio not found";
    }
    public String showLyricOrCaption(String audioID)
    {
        for(AudioModel temp:Database.getDatabase().getAllAudios())
            if(temp!=null && temp.getAudioID()==Long.parseLong(audioID))
            {
                if(temp instanceof MusicModel)
                    return ((MusicModel)temp).getLyric();
                else
                    return ((PodcastModel)temp).getCaption();
            }
        return "Audio not found";
    }
    public String showFollowings()
    {
        StringBuilder answer=new StringBuilder("Following usernames:\n");
        for(ArtistModel temp:getListener().getFollowings())
            if(temp!=null)
                answer.append(temp.getUserName()).append("\n");
        return answer.toString();
    }
    public String report(String artistUserName,String explanation)
    {
        for(AccountUserModel temp :Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel && temp.getUserName().compareTo(artistUserName)==0)
            {
                Report report=new Report((ArtistModel)temp,getListener(),explanation);
                Database.getDatabase().getReports().add(report);
                return "reported successfully";
            }
        return "Artist not found";
    }
    public void increaseCredit(String credit)
    {
        getListener().setListenerCredit(getListener().getListenerCredit()+Double.parseDouble(credit));
    }
    public String buyPremium(String pack)
    {
        Subscription subscription;
        if(pack.compareTo("30")==0)
        {
            subscription=Subscription.THIRTY_DAYS;
            if(getListener().getListenerCredit()<subscription.getPrice())
                return "your credit isn't enough";
            getListener().setListenerCredit(getListener().getListenerCredit()-subscription.getPrice());
            if(getListener() instanceof FreeListenerModel || (getListener() instanceof PremiumListenerModel && ((PremiumListenerModel) getListener()).getRemainingDays()<=0))
            {
                getListener().setSubscriptionExpiration(Calendar.getInstance());
                getListener().getSubscriptionExpiration().add(Calendar.DATE,30);
            }
            else
            {
                int addDays=30+((PremiumListenerModel)getListener()).getRemainingDays();
                getListener().setSubscriptionExpiration(Calendar.getInstance());
                getListener().getSubscriptionExpiration().add(Calendar.DATE,addDays);
            }
            if(getListener() instanceof FreeListenerModel)
            {
                String birthDate=Integer.toString(getListener().getBirthDate().get(Calendar.YEAR))+"/"+Integer.toString((getListener().getBirthDate().get(Calendar.MONTH)+1))+"/"+Integer.toString(getListener().getBirthDate().get(Calendar.DATE));
                PremiumListenerModel premiumListener=new PremiumListenerModel(getListener().getUserName(),getListener().getPassword(),getListener().getFullName(),getListener().getEmail(),getListener().getPhoneNumber(),birthDate);
                premiumListener.setListenerCredit(getListener().getListenerCredit());
                premiumListener.setFollowings(getListener().getFollowings());
                premiumListener.setFavGenres(getListener().getFavGenres());
                premiumListener.setLikedAudios(getListener().getLikedAudios());
                premiumListener.setPlayingAmount(getListener().getPlayingAmount());
                premiumListener.setPlayLists(getListener().getPlayLists());
                premiumListener.setSubscriptionExpiration(getListener().getSubscriptionExpiration());
                premiumListener.setRemainingDays((int)Duration.between(Calendar.getInstance().toInstant(),premiumListener.getSubscriptionExpiration().toInstant()).toDays());
                Database.getDatabase().getAllUsers().remove(getListener());
                Database.getDatabase().getAllUsers().add(premiumListener);
                setListener(premiumListener);
                return "package bought successfully";
            }
            ((PremiumListenerModel)getListener()).setRemainingDays((int)Duration.between(Calendar.getInstance().toInstant(),getListener().getSubscriptionExpiration().toInstant()).toDays());
            return "package bought successfully";
        }
        else if(pack.compareTo("60")==0)
        {
            subscription=Subscription.SIXTY_DAYS;
            if(getListener().getListenerCredit()<subscription.getPrice())
                return "your credit isn't enough";
            getListener().setListenerCredit(getListener().getListenerCredit()-subscription.getPrice());
            if(getListener() instanceof FreeListenerModel || (getListener() instanceof PremiumListenerModel && ((PremiumListenerModel) getListener()).getRemainingDays()<=0))
            {
                getListener().setSubscriptionExpiration(Calendar.getInstance());
                getListener().getSubscriptionExpiration().add(Calendar.DATE,60);
            }
            else
            {
                int addDays=60+((PremiumListenerModel)getListener()).getRemainingDays();
                getListener().setSubscriptionExpiration(Calendar.getInstance());
                getListener().getSubscriptionExpiration().add(Calendar.DATE,addDays);
            }
            if(getListener() instanceof FreeListenerModel)
            {
                String birthDate=Integer.toString(getListener().getBirthDate().get(Calendar.YEAR))+"/"+Integer.toString((getListener().getBirthDate().get(Calendar.MONTH)+1))+"/"+Integer.toString(getListener().getBirthDate().get(Calendar.DATE));
                PremiumListenerModel premiumListener=new PremiumListenerModel(getListener().getUserName(),getListener().getPassword(),getListener().getFullName(),getListener().getEmail(),getListener().getPhoneNumber(),birthDate);
                premiumListener.setListenerCredit(getListener().getListenerCredit());
                premiumListener.setFollowings(getListener().getFollowings());
                premiumListener.setFavGenres(getListener().getFavGenres());
                premiumListener.setLikedAudios(getListener().getLikedAudios());
                premiumListener.setPlayingAmount(getListener().getPlayingAmount());
                premiumListener.setPlayLists(getListener().getPlayLists());
                premiumListener.setSubscriptionExpiration(getListener().getSubscriptionExpiration());
                premiumListener.setRemainingDays((int)Duration.between(Calendar.getInstance().toInstant(),premiumListener.getSubscriptionExpiration().toInstant()).toDays());
                Database.getDatabase().getAllUsers().remove(getListener());
                Database.getDatabase().getAllUsers().add(premiumListener);
                setListener(premiumListener);
                return "package bought successfully";
            }
            ((PremiumListenerModel)getListener()).setRemainingDays((int)Duration.between(Calendar.getInstance().toInstant(),getListener().getSubscriptionExpiration().toInstant()).toDays());
            return "package bought successfully";
        }
        else if(pack.compareTo("180")==0)
        {
            subscription=Subscription.ONEHEIGHTY_DAYS;
            if(getListener().getListenerCredit()<subscription.getPrice())
                return "your credit isn't enough";
            getListener().setListenerCredit(getListener().getListenerCredit()-subscription.getPrice());
            if(getListener() instanceof FreeListenerModel || (getListener() instanceof PremiumListenerModel && ((PremiumListenerModel) getListener()).getRemainingDays()<=0))
            {
                getListener().setSubscriptionExpiration(Calendar.getInstance());
                getListener().getSubscriptionExpiration().add(Calendar.DATE,180);
            }
            else
            {
                int addDays=180+((PremiumListenerModel)getListener()).getRemainingDays();
                getListener().setSubscriptionExpiration(Calendar.getInstance());
                getListener().getSubscriptionExpiration().add(Calendar.DATE,addDays);
            }
            if(getListener() instanceof FreeListenerModel)
            {
                String birthDate=Integer.toString(getListener().getBirthDate().get(Calendar.YEAR))+"/"+Integer.toString((getListener().getBirthDate().get(Calendar.MONTH)+1))+"/"+Integer.toString(getListener().getBirthDate().get(Calendar.DATE));
                PremiumListenerModel premiumListener=new PremiumListenerModel(getListener().getUserName(),getListener().getPassword(),getListener().getFullName(),getListener().getEmail(),getListener().getPhoneNumber(),birthDate);
                premiumListener.setListenerCredit(getListener().getListenerCredit());
                premiumListener.setFollowings(getListener().getFollowings());
                premiumListener.setFavGenres(getListener().getFavGenres());
                premiumListener.setLikedAudios(getListener().getLikedAudios());
                premiumListener.setPlayingAmount(getListener().getPlayingAmount());
                premiumListener.setPlayLists(getListener().getPlayLists());
                premiumListener.setSubscriptionExpiration(getListener().getSubscriptionExpiration());
                premiumListener.setRemainingDays((int)Duration.between(Calendar.getInstance().toInstant(),premiumListener.getSubscriptionExpiration().toInstant()).toDays());
                Database.getDatabase().getAllUsers().remove(getListener());
                Database.getDatabase().getAllUsers().add(premiumListener);
                setListener(premiumListener);
                return "package bought successfully";
            }
            ((PremiumListenerModel)getListener()).setRemainingDays((int)Duration.between(Calendar.getInstance().toInstant(),getListener().getSubscriptionExpiration().toInstant()).toDays());
            return "package bought successfully";
        }
        else
            return "package not found";
    }
    public String getAccInfo()
    {
        if(getListener() instanceof PremiumListenerModel)
            getListener().getSubscriptionExpiration().add(Calendar.DATE,-1);
        return getListener().toString();
    }
    public String sortBasedOnCompareTo()
    {
        Collections.sort(Database.getDatabase().getAllAudios());
        StringBuilder answer=new StringBuilder();
        for(AudioModel temp: Database.getDatabase().getAllAudios())
            if(temp!=null)
                answer.append("audio ID: ").append(temp.getAudioID()).append("\n").append("audio name: ").append(temp.getAudioName()).append("\n").append("likes: ").append(temp.getLikeAmount()).append(" plays: ").append(temp.getPlayAmount()).append("\n");
        return answer.toString();
    }
}
