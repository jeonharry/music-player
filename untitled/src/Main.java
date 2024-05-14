import model.users.AdminModel;
import view.View;

public class Main {
    public static void main(String[] args) {
        AdminModel admin=AdminModel.getAdmin("MK","jk1997","Marzieh Karami","marzieh666@gmail.com","09132082206","2005/2/1");
        View.getView().showFirstMenu();
    }
}