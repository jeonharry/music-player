package model;

import model.audioRelated.AudioModel;
import model.report.Report;
import model.users.AccountUserModel;

import java.util.ArrayList;

public class Database
{
    private ArrayList <AccountUserModel> allUsers=new ArrayList<>();
    private ArrayList <AudioModel> allAudios=new ArrayList<>();
    private ArrayList <Report> reports=new ArrayList<>();
    private static Database database;
    private Database() {}
    public static Database getDatabase()
    {
        if(database==null)
            database=new Database();
        return database;
    }
    public ArrayList<AccountUserModel> getAllUsers()
    {
        return this.allUsers;
    }
    public ArrayList<Report> getReports()
    {
        return this.reports;
    }
    public ArrayList<AudioModel> getAllAudios()
    {
        return this.allAudios;
    }
    public void setAllAudios(ArrayList<AudioModel> allAudios)
    {
        this.allAudios = allAudios;
    }
    public void setAllUsers(ArrayList<AccountUserModel> allUsers)
    {
        this.allUsers = allUsers;
    }
    public void setReports(ArrayList<Report> reports)
    {
        this.reports = reports;
    }
}
