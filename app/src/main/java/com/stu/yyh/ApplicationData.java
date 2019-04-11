package com.stu.yyh;

/**
 * Created by Administrator on 2019/3/28.
 */

public class ApplicationData {


    private static ApplicationData mInitData;
    private static User mUser;
    private static boolean isReceived = false;


    public static ApplicationData getInstance() {
        if (mInitData == null) {
            mInitData = new ApplicationData();
        }
        if(mUser == null)
        {
            mUser = new User();
        }
        return mInitData;
    }

    private ApplicationData() {

    }

    public static boolean isReceived() {
        return isReceived;
    }

    public void setIsReceived(boolean isReceived) {
        ApplicationData.isReceived = isReceived;
    }


    public User getUserInfo() {
        return mUser;
    }

    public void setmUser(User user){
        mUser.setId(user.getId());
        mUser.setAccount(user.getAccount());
        mUser.setGender(user.getGender());
        setIsReceived(true);
    }
}
