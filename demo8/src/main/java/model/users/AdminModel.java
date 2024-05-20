package model.users;

public class AdminModel extends AccountUserModel
{
    private static AdminModel admin;
    private AdminModel(String userName, String password, String fullName, String email, String phoneNumber, String birthDate)
    {
        super(userName,password,fullName,email,phoneNumber,birthDate);
    }
    public static AdminModel getAdmin(String userName,String password,String fullName,String email,String phoneNumber,String birthDate)
    {
        if(admin==null)
            admin=new AdminModel(userName,password,fullName,email,phoneNumber,birthDate);
        return admin;
    }
    public static AdminModel getAdmin()
    {
        if(admin==null)
            admin=new AdminModel("MK","jk1997","Marzieh Karami","marzieh666@gmail.com","09132082206","2005/2/1");
        return admin;
    }
}
