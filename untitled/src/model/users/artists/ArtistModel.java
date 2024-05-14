package model.users.artists;

import model.users.AccountUserModel;

import java.util.ArrayList;

public class ArtistModel extends AccountUserModel
{
    private double income;
    private ArrayList <AccountUserModel>followers=new ArrayList<>();
    private String bio;
    public ArtistModel(String userName, String password, String fullName, String email, String phoneNumber, String birthDate, String bio)
    {
        super(userName,password,fullName,email,phoneNumber,birthDate);
        this.bio=bio;
    }
    public void setBio(String bio)
    {
        this.bio = bio;
    }
    public void setIncome(double income)
    {
        this.income = income;
    }
    public void setFollowers(ArrayList<AccountUserModel> followers)
    {
        this.followers = followers;
    }
    public String getBio()
    {
        return this.bio;
    }
    public double getIncome()
    {
        return this.income;
    }
    public ArrayList<AccountUserModel> getFollowers()
    {
        return this.followers;
    }
    public String toString()
    {
        String string=super.toString()+"\nIncome: "+this.income+"\nBiography: "+this.bio;
        StringBuilder temp=new StringBuilder("\nFollowers:\n");
        for(AccountUserModel userTemp:this.followers)
            if(userTemp!=null)
                temp.append(userTemp).append("\n");
        return string+temp;
    }
}
