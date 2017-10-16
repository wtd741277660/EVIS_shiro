/**      
 * @ MD5.java Create on 2009-3-4 ����10:30:33      
 *      
 * Copyright (c) 2008 by xinlian.      
 */

/**      
* @author chris    
*/
package com.ly.util;

/**
 * MD5���ܷ�����
 * @author   sunfulin
 * @version  1.0
 * @Date     2009-4-11
 * @FileName MD5.java
 *
 */
public class MD5 {
    public static String getMD5(byte[] source){
        String s = null;
        char hexDigits[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try{
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++){
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(getMD5("123456".getBytes()));
    }
}
