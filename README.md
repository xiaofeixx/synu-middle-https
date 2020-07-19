# 沈师校园助手中间部分

本程序基于传统SSM进行开发，数据库使用mysql，搭建搭建基于注解，去掉了xml。

此程序作为中间部分，需配合爬虫，github地址：https://github.com/xiaofeixx/sync_spider，对应url地址配置请看本项目url.properties自行配置环境即可。

下面为对应接口功能：

```
http://127.0.0.1:8080/login     	# 登录验证并存储数据
```

```
http://127.0.0.1:8080/grade  	    # 查询成绩
```

```
http://127.0.0.1:8080/suject        # 查询课表
```

```
http://127.0.0.1:8080/refresh       # 刷新信息
```

注：因为每个接口都会涉及到私密信息，所以一律使用了post请求。