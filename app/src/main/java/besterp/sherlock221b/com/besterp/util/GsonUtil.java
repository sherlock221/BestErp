package besterp.sherlock221b.com.besterp.util;

import com.google.gson.Gson;

/**
 * Created by sherlock on 15/12/1.
 */
public class GsonUtil {


    private GsonUtil(){}


    private static Gson gson = new Gson();
    public static Gson getInstance(){
        return gson;
    }



}
