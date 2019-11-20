package bao.bon.demo_mvpm.Manager;

import java.util.ArrayList;
import java.util.List;

import bao.bon.demo_mvpm.Model.User;

public class UserManager {
    private static UserManager instance;
    private  List<User> listUser;

    public UserManager(List<User> listUser) {
        this.listUser = listUser;
    }

    public UserManager() {

    }

    public static UserManager getInstance(){
        if(instance == null){
            instance = new UserManager();
        }
        return instance;
    }

    public void addUser(String userName,String password){
        if(listUser == null){
            listUser = new ArrayList<>();
        }
        listUser.add(new User(userName,password));
    }

    public boolean validInformation(String userName , String passWord){
        for(int i = 0;i<listUser.size();i++){
            if(listUser.get(i).getUserName().equals(userName)
            &&listUser.get(i).getPassword().equals(passWord)){
                return true;
            }
        }
        return false;
    }
}
