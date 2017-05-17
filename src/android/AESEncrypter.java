
/*
 * Copyright (C) 2015 The Android Open Source Project
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

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncrypter {
    byte[] f2631a;
    private Cipher f2632b;

    public AESEncrypter() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        
        this.f2631a = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        String c = "0add2dabb18204cca874ab56ed373cbdf1595bf1092dc3e574ab251e8ec4b56c";
        this.f2632b = Cipher.getInstance("AES/CBC/PKCS5Padding");
        this.f2632b.init(1, new SecretKeySpec(AESEncrypter.m4412c(c), "AES"), new IvParameterSpec(this.f2631a));
    }

    public String m4413a(String str) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return AESEncrypter.m4411b(AESEncrypter.m4410a(this.f2632b.doFinal(str.getBytes("UTF-8"))));
    }

    public static String m4411b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char toHexString : str.toCharArray()) {
            stringBuilder.append(Integer.toHexString(toHexString));
        }
        return stringBuilder.toString();
    }

    public static String m4410a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            int i = b & 255;
            if (Integer.toHexString(i).length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(i));
        }
        return stringBuilder.toString();
    }

    public static byte[] m4412c(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}

