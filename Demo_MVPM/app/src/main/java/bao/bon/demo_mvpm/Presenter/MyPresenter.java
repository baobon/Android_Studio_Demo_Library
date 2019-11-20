package bao.bon.demo_mvpm.Presenter;

import bao.bon.demo_mvpm.Manager.UserManager;

public class MyPresenter {

    public MyPresenter() {
        UserManager.getInstance().addUser("baobon","123456");
    }

    public boolean login(String userName , String password){
         return UserManager.getInstance().validInformation( userName,password);
    }
}
