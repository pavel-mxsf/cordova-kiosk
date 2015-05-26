package eu.codepoint.kiosk;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class cordovaKiosk extends CordovaPlugin {

@Override
public boolean execute(String action,JSONArray args,CallbackContext callbackContext)throws JSONException{
        if(action.equals("hideBars")){

                this.showBars(callbackContext);
                return true;
        }
        if(action.equals("showBars")){

                this.showBars(callbackContext);
                return true;
        }
        return false;
        }

private void hideBars(CallbackContext callbackContext){

        try
        {
        String command;
        String[] envp = null;
               command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib service call activity 42 s16 com.android.systemui";
               Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", command }, envp);
               proc.waitFor();
        callbackContext.success("ok");
        }
        catch(Exception ex)
        {

        //Log.e("ROOTERROR",ex.getMessage());
        callbackContext.error("root needed");
        }
        }

private void showBars(CallbackContext callbackContext){
        try
                {
               String command;
               String[] envp = null;
                       command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib am startservice -n com.android.systemui/.SystemUIService";
                       Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", command }, envp);
                       proc.waitFor();
                      callbackContext.success("ok");
                }
                catch(Exception ex)
                {

                //Log.e("ROOTERROR",ex.getMessage());
                callbackContext.error("root needed");
                }
        }
}
