/*
 * Copyright (C) https://github.com/mjwheatley/cordova-plugin-android-aes
 * Modifications copyright (C) 2016 Niklas Merz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package de.alvaro.cordova.aes;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.cordova.CordovaInterface;


public class Aes extends CordovaPlugin {


    public static String packageName;


    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    KeyguardManager mKeyguardManager;

    public static KeyStore mKeyStore;
    public static KeyGenerator mKeyGenerator;
    public static Cipher mCipher;


    public static CallbackContext mCallbackContext;
    public static PluginResult mPluginResult;

    /**
     * Alias for our key in the Android Key Store
     */
    private static String mClientId;


    /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/    /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/    /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/
    private static String login;
    AESEncrypter aesEn;
    /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/    /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/    /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/ /*ncryptttttt*/


    /**
     * Used to encrypt token
     */
    private static String mClientSecret;

    /**
     * Options
     */
    private static boolean mDisableBackup = false;

    /**
     * Constructor.
     */
    public Aes() {

    }


    /**
     * Executes the request and returns PluginResult.
     *
     * @param action          The action to execute.
     * @param args            JSONArry of arguments for the plugin.
     * @param callbackContext The callback id used when calling back into JavaScript.
     * @return A PluginResult object with a status and message.
     */
    @Override
    public boolean execute(final String action,JSONArray args, CallbackContext callbackContext) throws JSONException {

        mCallbackContext = callbackContext;
        final JSONObject arg_object = args.getJSONObject(0);

        if (action.equals("aesEncrypt")) {
            if (!arg_object.has("login")) {
                PluginResult mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                mCallbackContext.error("No hay parametros");
                mCallbackContext.sendPluginResult(mPluginResult);
            }
            else {
               String clientId = arg_object.getString("login");
               initAES(clientId);
            }
        }
        return false;

    }

    
    /* FUNCTION PERSONALIZADASSSSSSSS NCRYPTTTT */    /* FUNCTION PERSONALIZADASSSSSSSS NCRYPTTTT */    /* FUNCTION PERSONALIZADASSSSSSSS NCRYPTTTT */
    private void initAES(String clientId) {
        try {

            aesEn = new AESEncrypter();
            String callbacckk = aesEn.m4413a(clientId);
            //CallbackContext.sendPluginResult(callbacckk);
            PluginResult result = new PluginResult(PluginResult.Status.OK, callbacckk);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }catch (BadPaddingException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /* FUNCTION PERSONALIZADASSSSSSSS NCRYPTTTT */    /* FUNCTION PERSONALIZADASSSSSSSS NCRYPTTTT */    /* FUNCTION PERSONALIZADASSSSSSSS NCRYPTTTT */



}
