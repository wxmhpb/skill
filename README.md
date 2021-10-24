秒杀系统：解决两个问题，一个是并发读，一个是并发写
技术：
  后端：Springboot、MybatisPlus、lombok
  前端：thymeleaf、jquery
  中间件：redis
主要功能：
 1.用户登陆
 2.商品列表
 3.商品详情
 4.秒杀
 5.订单详情
 6.支付页面---调用微信支付第三方api：qrcode.js方法生成二维码支付，以及生成订单编号
 7.支付成功或失败----微信扫码支付后
   若支付成功，修改订单支付状态，跳转支付成功页面
   若支付失败，跳转支付失败页面

分布式会话：
  1.用户登陆---正则表达式校验，MD5Utils加密，采用代码生成器自动生成基础代码
  2.共享session---根据电话号获取用户信息,
  客户端请求服务器，就使用response向客户端浏览器颁发一个Cookie，相当于用户的身份。
  客户端浏览器会把Cookie保存起来。session将cookie信息保存在服务器端，客户端浏览器再次访问时只需要从该Session中查找该客户的状态就可以了，比对正确显示在页面，不正确咋不显示
  
页面优化：
 1.缓存---将页面缓存到redis中
 2.静态化分离----将html的动静分离，这样每次访问资源时，不必重复访问静态资源，提高效率
 
系统压测：
  A. 创建一个测试计划
  B. 创建线程组
  C. 配置http默认请求头
  D. 配置http cookie请求头---项目启动登陆后，用户信息的cookie信息
  E. 配置CSV config参数
  F. 创建http请求
  G. 生成聚合报告结果
  
  1.使用jmeter测试商品列表接口---1000个线程，10组
   优化前：1833
   优化后：3177
  2.测试秒杀接口
    1).配置一个用户
    2）自定义变量：CSV config，模拟5000个用户，同时访问秒杀接口
      利用UUIDUtils工具类生成5000个用户信息插入数据库，并写入文本中，配置到jmeter CSV config中 
      优化前：953.9
      优化后：1310.4
      
  存在问题：
    库存超卖：在减库存时，先判断一下库存是否小于1，如果大于等于1在减库存，然后更新库存数，循环下去，直到库存小于1为止
    重复抢购：一个用户若发出两个请求可能形成重复购买，则在秒杀订单表中加user_id,goods_id唯一索引。当这两个字段唯一时，才会成功生成订单。
      
