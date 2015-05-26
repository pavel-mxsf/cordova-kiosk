package eu.codepoint.kiosk;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Process;
import java.lang.Runtime;

/**
 * This class echoes a string called from JavaScript.
 */
public class cordovaKiosk extends CordovaPlugin{

@Override
public boolean execute(String action,JSONArray args,CallbackContext callbackContext)throws JSONException{
        if(action.equals("hideBars")){
        String message=args.getString(0);
        this.hideMethod(message,callbackContext);
        return true;
        }
        if(action.equals("showBars")){
        String message=args.getString(0);
        this.showMethod(message,callbackContext);
        return true;
        }
        return false;
        }

private void hideBars(String message,CallbackContext callbackContext){

        try
        {
        Process proc=Runtime.getRuntime().exec(new String[]{"su","-c","service call activity 42 s16 com.android.systemui"});
        proc.waitFor();
        callbackContext.success("hiden successfuly");
        }
        catch(Exception ex)
        {
        Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        Log.e("ROOT ERROR",ex.getMessage());
        callbackContext.error("Root needed");
        }
        }

private void showBars(String message,CallbackContext callbackContext){
        if(message!=null&&message.length()>0){
        callbackContext.success(message);
        }else{
        callbackContext.error("Expected one non-empty string argument.");
        }
        }
        }
