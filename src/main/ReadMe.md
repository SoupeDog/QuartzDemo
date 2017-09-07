|序号| 目前已完成的功能（非分布式模式）  |
|--- | ---  |
|1| 设置 Cron 表达式类型的定时任务  |
|2| 设置 时间间隔、有限次数、开始时间 类型的定时任务  |
|3| 删除定时任务|
|4| 服务异常中断的任务恢复|


目前的任务实现的是定时打印，另：已测试在定时任务中操作 RestTemplate 对象（可行）


使用步骤：
①在本地mysql 数据库中执行 tables_mysql_innodb.sql 脚本

②修改 quartz.properties 里的 DataSource 信息

③启动程序

可用的接口

|请求类型| url |请求内容|
|--- | ---  |--- |
|post|http://127.0.0.1:8080/set/cron |{"name_Job":"任务2","group_Job":"cron","type":1,"description":"表达式","cron":"0/8 * * * * ? ","ts":1564}|
|post|http://127.0.0.1:8080/set/simple |{"name_Job":"任务1","group_Job":"cron","startTs":XXXXXXXXX,"type":2,"description":"简单","repeatTime":10,"interval":5,"ts":1564}|
|post|http://127.0.0.1:8080/del |{"name":"任务3","group":"test"}|
