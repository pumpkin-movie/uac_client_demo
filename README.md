# uac_client_demo
CDN鉴权DEMO

## 1:获取测试url地址及相关参数
### (1)认证返回200 url demo
```
curl http://dev.uac.openapi.vcinema.cn:6668/get_demo_play_url\?want_to_response_status\=200
```

### (2)认证返回401 url demo
```
curl http://dev.uac.openapi.vcinema.cn:6668/get_demo_play_url\?want_to_response_status\=401
```

### (3)认证返回403 url demo
```
curl http://dev.uac.openapi.vcinema.cn:6668/get_demo_play_url\?want_to_response_status\=403
```

## 2:测试uac系统功能
### (1)认证通过，返回200
+ 通过参数传递，GET方式
```
curl -w %{http_code} http://dev.uac.openapi.vcinema.cn:6668/cdn_auth\?remote_addr\=1.1.1.1\&cdn_key\=xxxxxxxxx\&uri=/201812/demo.m3u8&auth_key=1545482419-0-0-2c0fc3f68315cd50875e453a329d48e1&secret=d1d4f3ceaad2700e27db3507df220ffe
```

+ 通过参数传递，POST方式


+ 通过header传递
```
curl -w %{http_code} -H "remote_addr: 1.1.1.1" -H "cdn_key: xxxxxxxxx" -H "uri: uri=/201812/demo.m3u8&auth_key=1545482419-0-0-2c0fc3f68315cd50875e453a329d48e1&secret=d1d4f3ceaad2700e27db3507df220ffe" http://dev.uac.openapi.vcinema.cn:6668/cdn_auth
```


### (2)认证不通过，返回401
将auth_key中的timestamp修改成为少于当前时间，则返回401（此功能是为了模式测试使用，正式环境不会固定成此模式）
+ 通过参数传递

```
curl -w %{http_code} http://dev.uac.openapi.vcinema.cn:6668/cdn_auth\?remote_addr\=1.1.1.1\&cdn_key\=xxxxxxxxx\&uri=/201812/demo.m3u8&auth_key=1545482419-0-0-2c0fc3f68315cd50875e453a329d48e1&secret=xxxxxxx
```
+ 通过header传递

### (3)认证不通过，返回403
+ 通过参数传递
```
curl -w %{http_code} http://dev.uac.openapi.vcinema.cn:6668/cdn_auth\?remote_addr\=1.1.1.1\&cdn_key\=xxxxxxxxx\&uri=/201812/demo.m3u8&auth_key=1545482419-0-0-2c0fc3f68315cd50875e453a329d48e1&secret=xxxxx
```

+ 通过header传递

