![开放平台 logo](Pictures/开放平台 logo.png)





[TOC]

### 一、 引言

---



#### 1.1 开放平台介绍

>开放平台（Open Platform） 在软件行业和网络中，开放平台是指软件系统通过公开其应用程序编程接口（API）或函数（function)来使外部的程序可以增加该软件系统的功能或使用该软件系统的资源，而不需要更改该软件系统的源代码。在互联网时代，把网站的服务封装成一系列计算机易识别的数据接口开放出去，供第三方开发者使用，这种行为就叫做Open API，提供开放API的平台本身就被称为开放平台。



#### 1.2 开放平台的使用场景

>- 企业要调用别人的长板，通过API技术，互相调用、与行业形成链接。在借助他人长板的同时，也把自己的长板贡献给他人，就是互相赋能。
>
>- 开放平台的作用简单地说就是通过第三方的力量来扩展自己的生态和能力，因为光凭自己做的软件并不能覆盖所有的场景，例如阿里、京东可以提供标准化的应用软件，可能满足于一些小的卖家使用，但是数百万形形色色的卖家对于个性化要求的软件，并不是一个公司的力量可以满足的，所以就把这些需求开放给众多的第三方开发者。
>
>- 开放平台使用广泛、几乎所有的互联网公司都有自己的开放平台，知名的如京东的宙斯（JD Open Platform）, 淘宝开放平台（Taobao Open Platform），百度的数据开放平台（[https://open.baidu.com](https://open.baidu.com/)），大公司的开放平台都是一个完整的生态链，有很多第三方开发者（ISV），专门根据大的平台开放的接口，来开发出一些通用的软件，比如“E店宝”，依赖于淘宝、天猫、京东、拼多多等大型电商开放的接口，开发出的电商ERP的管理软件,通过这个 ERP 可以直接执行淘宝,京东等电商对外提供的功能,如查看订单,退货,上架产品等等

|                         开放平台项目                         |
| :----------------------------------------------------------: |
| ![image-20200406192830180](Pictures/image-20200406192830180.png) |



### 二 、开放平台之管理平台

---



#### 2.1 管理平台介绍

> 管理平台主要是对开放平台中的一些数据进行综合性管理,如客户管理,应用管理,充值管理,api 路由管理,网关参数管理,用户 Token 管理,日志搜索,权限管理,实现对服务的限流,熔断等动态配置,通过管理平台可以查看生成的数据,也可以通过管理平台将修改的数据同步到开放平台的网关系统中来实现实时更新功能

|                     管理平台部分功能展示                     |
| :----------------------------------------------------------: |
| ![image-20200406201435552](Pictures/image-20200406201435552.png) |



#### 2.2 客户管理

#####  2.2.1 介绍

> - 客户指的是注册了开放平台并通过平台来获取相应数据的人,如我们的淘宝账号可以通过淘宝的开放平台提供的 API 来实现对淘宝的一些功能的调用,如订单管理,商品管理等
>
> - 客户主要包含的主要信息包括 [用户名,密码,公司名称,账户余额,公司地址,账户状态]()等
>
> - 客户管理主要是针对在开放平台上注册的用户进行管理, 可以对用户的账号密码,企业名称,账户余额等内容进行管理,并且可以代用户进行注册,编辑删除账户等操作



##### 2.2.2 功能展示

|                       客户管理功能展示                       |
| :----------------------------------------------------------: |
| ![image-20200406221508173](Pictures/image-20200406221508173.png) |

##### 2.2.2 数据库结构



|   列名   |  类型  |        描述        |
| :------: | :----: | :----------------: |
|    id    |  int   |        主键        |
| username | String |       用户名       |
| password | String |        密码        |
| nickname | String |      公司名称      |
| address  | String |        地址        |
|  money   |  int   |      余额(毫)      |
|  state   |  int   | 状态 1 正常,0 停用 |



#### 2.3 应用管理



##### 2.3.1 介绍

> - 应用指的是客户在我们的平台创建的应用,一个客户可以创建多个应用,比如 抖音,今日头条,火山视频都是字节跳动的产品,它们都使用了微信分享和登陆这些开放功能,那么在微信的开放平台中,字节跳动就是一个客户,而抖音,今日头条都是字节跳动下面不同的应用,他们需要分别在微信开放平台创建出来才可以使用微信开放的功能
>
> - 应用主要包含的主要信息为[所属客户,应用名称,应用的 key,应用的秘钥(签名用),应用接收信息的回调 URL,应用对开放平台中接口的免费调用次数,应用描述]()等,关联功能[客户管理]()
>
> - 应用管理中主要包含创建应用,展示应用的相关信息,编辑修改应用,以及批量删除等操作,让管理人员可以随时通过对应用的信息修改来实现动态实时的限制



##### 2.3.2 功能展示

|                       应用管理功能展示                       |
| :----------------------------------------------------------: |
| ![image-20200406223821508](Pictures/image-20200406223821508.png) |



##### 2.3.3 数据库结构



|    列名     |  类型  |           描述            |
| :---------: | :----: | :-----------------------: |
|     id      |  int   |           主键            |
|  corpName   | String | 关联企业名称,冗余减少查询 |
|   appName   | String |         应用名称          |
|   appKey    | String |     应用唯一标示 KEY      |
|  AppSecret  | String |       应用签名秘钥        |
| redirectUrl | String |      应用回调用 URL       |
|    linit    |  int   |  免费接口日调用限制次数   |
| description | String |         应用介绍          |
|    cusId    |  int   |        关联客户 id        |
|    state    |  int   |           状态            |



#### 2.4 路由管理



##### 2.4.1 介绍

> - 在开放平台中,因为开放的服务的数量不确定,有的服务可能今年开放的,有的服务可能是明年新开发后开放的,有的服务可能过来一段时间后下线了,这一切都可能是随时变化的,因为如果每个服务都单独开发一套接口来让客户调用的话就变得非常繁琐,无法实现动态的调整,就像一个公司会有很多部门,每个部门都会招聘人,如果每个部门的招聘信息上面都写对应部门的人的话,就会变得很麻烦,我们需要在公司前台那里给每个部分留一个面试官,会浪费人员,最合适的办法是我们只要都留公司前台地址就行,我们只需要告诉前台每个部门的对接人信息就行,来面试的人员只要说明自己来面试哪个部门即可,前台会根据部门来找到对接人然后通知对接人即可,那么个前台就是统一的入口,她记录的对接人的信息就属于路由信息,我们只需要和前台沟通随时变更对接人信息即可,以后多了新部门只要和前台说新部门的信息即可
>
> - 路由主要包含的主要信息为 [路由的标识,对应的真正服务 id,对应服务的地址,描述信息,服务状态,幂等性,是否收费]()等
>
> - 路由管理主要是对路由信息的添加,修改,启用,停用,幂等性状态修改, 是否收费等进行相关管理



##### 2.4.2 功能展示

|                       路由管理功能展示                       |
| :----------------------------------------------------------: |
| ![image-20200406201435552](Pictures/image-20200406201435552.png) |



##### 2.4.3 数据库结构



|      列名      |  类型  |          描述          |
| :------------: | :----: | :--------------------: |
|       id       |  int   |          主键          |
| gatewayApiName | String |      路由名称标识      |
|  insideApiUrl  | String |      服务接口地址      |
|   serviceId    | String |        服务名称        |
|  description   | String |        介绍信息        |
|     state      |  int   |   状态 1 有效,0 无效   |
|  idempotents   |  int   | 幂等性 1 幂等 0 非幂等 |
|    needfee     |  int   | 是否收费 1 收费 0 免费 |



#### 2.5 系统参数管理



##### 2.5.1 介绍

> - 我们的所有的服务在请求之前会要求我们必须传递某些名字的参数,就像我们去一家公司面试的时候不管面试的是什么部门都要带简历一样,这些参数我们成为系统参数
>
> - 系统参数包含的主要信息是 [参数名称,参数描述,参数状态]()
>
> - 系统参数管理主要是针对我们网关中请求时候需要的系统参数进行管理,可以动态添加或者修改删除参数,修改后同步到网关中,网关即可实现动态参数校验的功能



##### 2.5.2 功能展示

|                     系统参数管理功能展示                     |
| :----------------------------------------------------------: |
| ![image-20200407011654545](Pictures/image-20200407011654545.png) |



##### 2.5.3 数据库结构



|    列名     |  类型  |        描述        |
| :---------: | :----: | :----------------: |
|     id      |  int   |        主键        |
|    name     | String |       参数名       |
| description | String |      参数介绍      |
|    state    |  int   | 状态 1 启用,0 禁用 |



#### 2.6 Token 管理



##### 2.6.1 介绍

> - 我们在访问功能的时候需要用登陆,单体项目的时候我们可以使用 session 来记录数据,但是在分布式系统中,session 共享比较复杂,所以我们会使用其他方式来记录这些状态,我们会在用户首次登陆的时候给他生成一个 token,下次用户带着 token 来我们进行校验即可
>
> - 令牌在数据库中主要的信息为[客户 id,token 内容,开始时间,过期时间]()等信息,关联客户信息
>
> - Token 管理主要是管理用户登录后生成的 token数据,修改有效期,删除等



##### 2.6.2 功能展示

|                      Token 管理功能展示                      |
| :----------------------------------------------------------: |
| ![image-20200407161548386](Pictures/image-20200407161548386.png) |



##### 2.6.3 数据库结构



|     列名     |   类型   |    描述    |
| :----------: | :------: | :--------: |
|      id      |   int    |    主键    |
|    userId    |   int    |  客户 id   |
| access_token |  String  | token 内容 |
|  startTime   | DateTime |  开始时间  |
|  expireTime  | DateTime |  结束时间  |



#### 2.7 充值管理



##### 2.7.1 介绍

> - 开放平台中部分接口的访问是需要收费的,因为用户需要先充值才可以使用
>
> - 充值的数据信息主要是[客户id,订单号,充值金额,日期,付费方式,状态]()等信息
>
> - 充值管理主要是查看用户的充值记录,并且可以在自动充值出现问题的时候手动为用户充值



##### 2.7.2 功能展示

|                       充值管理功能展示                       |
| :----------------------------------------------------------: |
| ![image-20200409003804117](Pictures/image-20200409003804117.png) |



##### 2.7.3 数据库结构



|    列名     |   类型   |              描述              |
| :---------: | :------: | :----------------------------: |
|     id      |   int    |              主键              |
|    cusId    | 客户 id  |            客户 id             |
| createtime  | DateTime |          创建订单时间          |
| updateTime  | DateTime |            更新时间            |
|    state    |   int    | 状态,0未支付,1 已支付,2 已取消 |
| paymenttype |   int    | 支付类型 0 银联,1 微信,2支付宝 |









### 三 、技术架构

---



> 我们的项目是一个管理平台,大部分的管理平台都是对内提供功能或者给用户提供部分可选功能,所以相对起来比较简单,大部分使用的是SSM单体架构,我们当前的项目也是采取的SSM架构



#### 3.1 架构技术点



| 技术名称  | 针对的功能 |                   介绍                   | 版本  |
| :-------: | :--------: | :--------------------------------------: | ----- |
| SpringMVC |   控制层   | 主要用作于接收用户请求,封装参数,返回数据 | 5.1.x |
|  Spring   |  对象管理  | 主要对我们项目需要的对象进行生命周期管理 | 5.1.x |
|  Mybatis  |   持久层   |      主要是用于我们和数据库进行交互      | 3.5.x |
|  Quartz   |  定时任务  |             比如定时备份数据             | 1.5.x |
|   LayUI   |  前端展示  |    用于显示页面数据,传递数据到控制层     | 2.5.x |



#### 3.2 所需工具



|   工具名称    |       功能        |                 介绍                 |
| :-----------: | :---------------: | :----------------------------------: |
| Intellij Idea |     代码开发      |            开发集成编辑器            |
|     Maven     |     项目管理      |           项目构建管理工具           |
| Maven Helper  | 快速运行maven操作 | 一款IDEA插件,可以自定义执行maven指令 |
|     MySQL     |    持久化数据     |           免费开源的数据库           |
|    Tomcat     |     运行容器      |      基于Java Servlet规范的容器      |
|    VS Code    |     文本编辑      |         免费开源的文本编辑器         |





### 四 、编码规范

---

> 本规范主要是针对开发中我们的一些基本内容进行规范约束定义,包含项目的包名,对应的文件位置等

|      名称       |                规范                |
| :-------------: | :--------------------------------: |
|      包名       |  com.qianfeng.openapi.web.master   |
|   控制层包名    |             controller             |
|   服务层包名    |            servcie/impl            |
|   数据层包名    |               mapper               |
|   工具类包名    |               utils                |
| 数据库对象包名  |                pojo                |
|  view对象包名   |                bean                |
|   mapper xml    | resouces目录下与接口包名对应的目录 |
| spring配置文件  |        resouces下spring目录        |
| mybatis配置文件 |       resouces下mybatis目录        |
|  其他配置文件   |         resouces下conf目录         |
|     变量名      |              驼峰命名              |
|     状态码      |           接口中定义常量           |
|    其他规范     |          具体情况具体定义          |





### 五、 编码顺序

---



> 在我们的实际开发中,我们会遇到选择的问题,就是到底是先写controller还是service还是dao,其实先写谁都可以,这个取决于我们自己的角度,如果你先想我们的项目有什么业务,那么我们一般会先写service,另外一个方面,我们分析出数据库的表结构后,可以先写数据库相关的操作,也就是我们的dao,也可以先想一下我们会和前端做什么交互,先写controller,这个完全取决于我们的角度,当然如果不是前后端分离的项目,页面是我们写的,我们也可以按照需求先把页面写出来
