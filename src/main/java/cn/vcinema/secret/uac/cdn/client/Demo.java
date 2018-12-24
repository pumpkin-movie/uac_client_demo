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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Demo
 * Auth: Xulin Zhuang
 * Date: 2018-12-24 12:50 PM
 */
public class Demo {

    public static void main(String[] args) throws IOException {

        // 获取测试地址
        String url = "http://dev.uac.openapi.vcinema.cn:6668/get_demo_play_url?want_to_response_status=200";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            System.out.println("获取测试地址失败。");
            return;
        }
        RequestModel requestModel = JSON.parseObject(EntityUtils.toString(response.getEntity()), new TypeReference<RequestModel>(){});
        if (requestModel == null){
            System.out.println("解析测试地址的信息出错。");
            return;
        }
        String cdnKey = requestModel.getCdkKey();
        String remoteAddr = requestModel.getRemoteAddr();
        String httpVia = requestModel.getHttpVia();
        String httpClientIp = requestModel.getHttpClientIp();
        String httpXForwardedFor = requestModel.getHttpXForwardedFor();
        String httpReferer = requestModel.getHttpReferer();
        String httpUserAgent = requestModel.getHttpUserAgent();
        String uri = requestModel.getUri();

        // demo 所需要的数据
        String playUrl = requestModel.getPlayUrl();
        System.out.println("获取到的播放地址:"+playUrl);
        // 验证播放地址是否合法
        if (playUrl == null || !playUrl.contains("auth_key")){
            System.out.println("播放地址不合法。");
            return ;
        }
        String antiTheftChain = playUrl.substring(playUrl.indexOf("auth_key=")+9,playUrl.indexOf("&"));
        System.out.println("CDN防盗链地址:"+antiTheftChain);

        String[] antiTheftChainInfo = antiTheftChain.split("-");
        if (antiTheftChainInfo.length != 4){
            System.out.println("播放地址中的防盗链信息不合法");
            return;
        }
        // 时间戳是否合法
        long timestamp = Long.parseLong(antiTheftChainInfo[0]);
        if (timestamp < System.currentTimeMillis()/1000){
            System.out.println("时间戳异常。");
            return;
        }
        // 加密方式是否合法
        String uriInfo = playUrl.substring(playUrl.indexOf("cn")+2,playUrl.indexOf("?"));
        String privateKey = requestModel.getPrivateKey();
        if (!MD5.verify(uriInfo+"-"+timestamp+"-"+antiTheftChainInfo[1]+"-"+antiTheftChainInfo[2]+"-"+privateKey,antiTheftChainInfo[3])){
            System.out.println("CDN防盗链解密失败。");
            return;
        }
        // CDN防盗链校验通过，提交数据到UAC服务
        StringBuilder uacUrl = new StringBuilder();
        uacUrl.append("http://dev.uac.openapi.vcinema.cn:6668/cdn_auth");
        uacUrl.append("?cdn_key=").append(cdnKey);
        uacUrl.append("&remote_addr=").append(remoteAddr);
        uacUrl.append("&http_via=").append(httpVia);
        uacUrl.append("&http_client_ip=").append(httpClientIp);
        uacUrl.append("&http_x_forwarded_for=").append(httpXForwardedFor);
        uacUrl.append("&http_referer=").append(httpReferer);
        uacUrl.append("&http_user_agent=").append(httpUserAgent.replace(" ","_"));
        uacUrl.append("&").append(uri);

        HttpClient uacClient = new DefaultHttpClient();
        HttpGet uacRequest = new HttpGet(uacUrl.toString());
        HttpResponse uacResponse = uacClient.execute(uacRequest);
        int uacStatus = uacResponse.getStatusLine().getStatusCode();
        System.out.println("uac status:"+uacStatus);
        // 返回的header内容
        List<Header> httpHeaders = Arrays.asList(uacResponse.getAllHeaders());
        System.out.println("--------- Result for header ---------");
        for (Header header : httpHeaders){
            System.out.println(header.getName()+":"+header.getValue());
        }
        // 返回的JSON内容
        String uacResult =  EntityUtils.toString(uacResponse.getEntity());
        UacModel uacModel = JSON.parseObject(uacResult, new TypeReference<UacModel>(){});
        System.out.println("---------- Result for Json ----------");
        System.out.println("cost time:"+uacModel.getCostTime());
        System.out.println("location:"+uacModel.getLocation());
        System.out.println("status:"+uacModel.getStatus());
        if (uacStatus == 200){
            // 处理m3u8 文件


        }

    }

}
