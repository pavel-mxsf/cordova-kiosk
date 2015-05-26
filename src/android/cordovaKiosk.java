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
                String message=args.getString(0);
                this.showBars(message,callbackContext);
                return true;
        }
        if(action.equals("showBars")){
                String message=args.getString(0);
                this.showBars(message,callbackContext);
                return true;
        }
        return false;
        }

private void hideBars(String message,CallbackContext callbackContext){

        try
        {
        Process proc=Runtime.getRuntime().exec(new String[]{"su","-c","service call activity 42 s16 com.android.systemui"});
        proc.waitFor();
        callbackContext.success();
        }
        catch(Exception ex)
        {

        Log.e("ROOTERROR",ex.getMessage());
        callbackContext.error();
        }
        }

private void showBars(String message,CallbackContext callbackContext){
        try
                {
               String command;
                      command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib am startservice -n com.android.systemui/.SystemUIService";
                      String[] envp = null;
                      Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", command }, envp);
                      callbackContext.success();
                }
                catch(Exception ex)
                {

                Log.e("ROOTERROR",ex.getMessage());
                callbackContext.error();
                }
        }
}
