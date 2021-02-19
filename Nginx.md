# Nginx

## 概念

### Nginx 是什么，能做什么

Nginx（engine x）是一个高性能的 HTTP 和反向代理服务器，占用内存少，并发能力强。

### 反向代理

- 正向代理：作为客户端的代理，客户端向代理发送一个访问目标服务器的请求，然后代理向目标服务器转交请求并将获得的内容返回给客户端。
- 反向代理：作为服务器的代理，客户端向代理发送一个请求，然后代理将请求转发给服务器集群中的某一台服务器。

### 负载均衡

将大量的请求通过代理服务器分发到不同的服务器上。

主要有以下几种分发算法：
1. 随机（Random）
2. 加权随机（Weighted Random） 
3. 轮询（Round Robin）
4. 加权轮询（Weighted Round Robin）
5. 最少连接（Least Connection）
6. 源地址哈希（Hash）

### 动静分离

把动态页面和静态页面交给不同的服务器来解析，加快解析速度，降低单服务器压力。

## 常用命令

- 查看版本：`nginx -v`
- 启动：`nginx`
- 关闭：`nginx -s stop`
- 重新加载 Nginx：`nginx -s reload`

## 配置文件

配置文件部分参考：[张龙豪的博客 - Nginx配置详解](https://www.cnblogs.com/knowledgesea/p/5175711.html)

### 文件结构

- 全局块：配置影响 nginx 全局的指令。一般有运行 nginx 服务器的用户组，nginx 进程 pid 存放路径，日志存放路径，配置文件引入，允许生成 worker process 数等。
- Events 块：配置影响 nginx 服务器或与用户的网络连接。有每个进程的最大连接数，选取哪种事件驱动模型处理连接请求，是否允许同时接受多个网路连接，开启多个网络连接序列化等。
- Http 块：可以嵌套多个 server，配置代理，缓存，日志定义等绝大多数功能和第三方模块的配置。如文件引入，mime-type 定义，日志自定义，是否使用 sendfile 传输文件，连接超时时间，单连接请求数等。
- Server 块：配置虚拟主机的相关参数，一个 http 中可以有多个 server。
- Location 块：配置请求的路由，以及各种页面的处理情况。

### 配置文件示例

```nginx
########### 每个指令必须有分号结束。#################
#user administrator administrators;  #配置用户或者组，默认为nobody nobody。
#worker_processes 2;  #允许生成的进程数，默认为1
#pid /nginx/pid/nginx.pid;   #指定nginx进程运行文件存放地址
error_log log/error.log debug;  #制定日志路径，级别。这个设置可以放入全局块，http块，server块，级别以此为：debug|info|notice|warn|error|crit|alert|emerg
events {
    accept_mutex on;   #设置网路连接序列化，防止惊群现象发生，默认为on
    multi_accept on;  #设置一个进程是否同时接受多个网络连接，默认为off
    #use epoll;      #事件驱动模型，select|poll|kqueue|epoll|resig|/dev/poll|eventport
    worker_connections  1024;    #最大连接数，默认为512
}
http {
    include       mime.types;   #文件扩展名与文件类型映射表
    default_type  application/octet-stream; #默认文件类型，默认为text/plain
    #access_log off; #取消服务日志    
    log_format myFormat '$remote_addr–$remote_user [$time_local] $request $status $body_bytes_sent $http_referer $http_user_agent $http_x_forwarded_for'; #自定义格式
    access_log log/access.log myFormat;  #combined为日志格式的默认值
    sendfile on;   #允许sendfile方式传输文件，默认为off，可以在http块，server块，location块。
    sendfile_max_chunk 100k;  #每个进程每次调用传输数量不能大于设定的值，默认为0，即不设上限。
    keepalive_timeout 65;  #连接超时时间，默认为75s，可以在http，server，location块。

    upstream mysvr {   
      server 127.0.0.1:7878;
      server 192.168.10.121:3333 backup;  #热备
    }
    error_page 404 https://www.baidu.com; #错误页
    server {
        keepalive_requests 120; #单连接请求上限次数。
        listen       4545;   #监听端口
        server_name  127.0.0.1;   #监听地址       
        location  ~*^.+$ {       #请求的url过滤，正则匹配，~为区分大小写，~*为不区分大小写。
           #root path;  #根目录
           #index vv.txt;  #设置默认页
           proxy_pass  http://mysvr;  #请求转向mysvr 定义的服务器列表
           deny 127.0.0.1;  #拒绝的ip
           allow 172.18.5.54; #允许的ip           
        } 
    }
}
```