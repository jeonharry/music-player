package model.users;

import java.util.Calendar;
import java.util.Date;

public abstract class AccountUserModel
{
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Calendar birthDate;
    public AccountUserModel(String userName, String password, String fullName, String email, String phoneNumber, String birthDate)
    {
        this.userName=userName;
        this.password=password;
        this.fullName=fullName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.birthDate=Calendar.getInstance();
        this.birthDate.setTime(new Date(birthDate));
    }
    public Calendar getBirthDate()
    {
        return this.birthDate;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getFullName()
    {
        return this.fullName;
    }
    public String getPassword()
    {
        return this.password;
    }
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    public String getUserName()
    {
        return this.userName;
    }
    public void setBirthDate(Calendar birthDate)
    {
        this.birthDate = birthDate;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String toString()
    {
        return "User name: "+this.userName+"\nFull name: "+this.fullName+"\nEmail: "+this.email+"\nPhone number: "+this.phoneNumber+"\nBirth date: "+this.birthDate.get(Calendar.YEAR)+"/"+(this.birthDate.get(Calendar.MONTH)+1)+"/"+this.birthDate.get(Calendar.DATE);
    }
}
