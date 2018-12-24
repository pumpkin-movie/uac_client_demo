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

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Auth: Xulin Zhuang
 * Date: 2018-12-24 1:20 PM
 */
public class RequestModel {

    @JSONField(name="cdn_key")
    private String cdkKey;

    @JSONField(name="remote_addr")
    private String remoteAddr;

    @JSONField(name="http_via")
    private String httpVia;

    @JSONField(name="http_client_ip")
    private String httpClientIp;

    @JSONField(name="http_x_forwarded_for")
    private String httpXForwardedFor;

    @JSONField(name="http_referer")
    private String httpReferer;

    @JSONField(name="http_user_agent")
    private String httpUserAgent;

    @JSONField(name="uri")
    private String uri;

    // 测试时获取测试地址需要
    @JSONField(name="play_url")
    private String playUrl;

    @JSONField(name="private_key")
    private String privateKey;

    public String getCdkKey() {
        return cdkKey;
    }

    public void setCdkKey(String cdkKey) {
        this.cdkKey = cdkKey;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getHttpVia() {
        return httpVia;
    }

    public void setHttpVia(String httpVia) {
        this.httpVia = httpVia;
    }

    public String getHttpClientIp() {
        return httpClientIp;
    }

    public void setHttpClientIp(String httpClientIp) {
        this.httpClientIp = httpClientIp;
    }

    public String getHttpXForwardedFor() {
        return httpXForwardedFor;
    }

    public void setHttpXForwardedFor(String httpXForwardedFor) {
        this.httpXForwardedFor = httpXForwardedFor;
    }

    public String getHttpReferer() {
        return httpReferer;
    }

    public void setHttpReferer(String httpReferer) {
        this.httpReferer = httpReferer;
    }

    public String getHttpUserAgent() {
        return httpUserAgent;
    }

    public void setHttpUserAgent(String httpUserAgent) {
        this.httpUserAgent = httpUserAgent;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
