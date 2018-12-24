/*
 * Copyright (c) 2017-present, Pumpkin Movie, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 * copy, modify, and distribute this software in source code or binary form for use
 * in connection with the web services and APIs provided by Pumpkin Movie.
 *
 * As with any software that integrates with the pumpkin movie platform, your use of
 * this software is subject to the Pumpkin Movie Developer Principles and Policies
 * [http://developers.vcinema.com/policy/]. This copyright notice shall be
 * included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cn.vcinema.secret.uac.cdn.client;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5
 * Auth: Xulin Zhuang
 * Date: 2018-12-24 4:52 PM
 */
public class MD5 {
    /**
     * MD5方法
     *
     *  @param text
     * @return 密文
     * @throws Exception
     */
    public static String encrypt(String text) {
        //加密后的字符串
        return DigestUtils.md5Hex(text);
    }

    /**
     * MD5验证方法
     *
     * @param info 待加密内容
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String info, String md5) {
        //根据传入的密钥进行验证
        String md5Text = encrypt(info);
        if(md5Text.equalsIgnoreCase(md5)) {
            return true;
        }

        return false;
    }
}
