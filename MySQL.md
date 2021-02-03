# MySQL

## 数据库的意义

1. 实现数据持久化
2. 使用完整的管理系统统一管理，便于进行对数据的各种操作

## 相关概念

- 数据库（Database，DB）：存储数据的仓库，保存了一系列有组织的数据
- 数据库管理系统（Database Management System，DBMS）：对数据库进行管理的软件
  - 常见的 DBMS：MySQL、Oracle、DB2、SqlServer 等
- 结构化查询语言（Structure Query Language，SQL）：用于与数据库进行通信的语言

## SQL 的种类

1. 数据查询语言（Data Query Language，DQL）：用于检索数据库中的数据，例如 SELECT
2. 数据操纵语言（Data Manipulation Language，DML）：用于改变数据库中的数据，例如 INSERT、UPDATE、DELETE
3. 数据定义语言（Data Definition Language，DDL）：用于对库表进行操作，例如 CREATE、DROP、ALTER
4. 数据控制语言（Data Control Language，DCL）：用于定于用户的访问权限和安全级别，例如 GRANT、REVOKE
5. 事务控制语言（Transaction Control Language，TCL）：用于维护数据的一致性，例如 COMMIT、ROLLBACK、SAVEPOINT

## 常用 SQL 语句

```sql
-- 库相关
# 查看数据库
show databases;

# 新建数据库
create database database_name;

# 使用数据库
use databse_name;

# 查询当前所在数据库
select database();

-- 表相关
# 查询当前数据库中的所有表
show tables;

# 查询指定数据库中的所有表
show tables from database_name;

# 创建一个表
create table table_name(id int primary key auto_increment, name varchar(255));

# 查看表结构
desc table_name;

# 删除表
drop table_name;

-- 记录相关
# 查询表中所有记录的所有字段
select * from table_name;

# 查询表中的指定字段
select field_1, ..., field_n from table_name;

# 查询表中的指定字段，并为字段起别名（或使用 field AS alias 的形式）
select field_1 alias_1, ..., field_n alias_n from table_name;

# 查询并去重
select distinct field from table_name;

# 向表中插入记录
insert into table_name(field_1, ..., field_n) values(value_1, ..., value_n);

# 根据条件修改记录
update table_name set field_name = field_value where condition;

# 根据条件删除记录
delete from table_name where condition;

-- 其他
# 查询 MySQL 版本
select version();

# 查询当前用户
select user();
```

## 查询

### 条件与逻辑表达式

与 Java 类似

需要注意 MySQL 中的条件比较有以下特点：

1. = 和 <>：不能比较 null 值，当比较的元素中有 null 时返回 null
2. <=>：可以比较 null 值

MySQL 中的与或非除了能用 &&、||、! 来表示，还能用 and、or、not 的方式表示

### 模糊查询

1. like：配合通配符使用
   - 通配符：% 匹配任意多个字符，_ 匹配一个字符
   - 当需要查询通配符字符时，需要使用 \ 进行转义
2. between A and B：在 A 和 B 之间，等价于 >= A and <= B
3. in (A, ..., Z)：包含于 A，...，Z 中，等价于 = A or ... or = Z
4. is null：值为 null

### 排序查询

```sql
# 从表中查询字段信息，并按 field_x 字段的值进行排序
# asc 表示升序（默认），desc 表示降序
select field_1, ..., field_n from table_name order by field_x asc/desc;
```

### 字符串相关函数

```sql
# 拼接字符串
select concat(string_1, ..., string_n);

# 判断 string 的值是否为 null，如果为 null 返回 value，否则返回原值
select ifnull(string, value); 

# 判断 string 的值是否为 null，如果是返回 1，否则 0
select isnull(string);

# 获取 string 参数值的字节长度
select length(string);

# 大小写转换
select upper(string);
select lower(string);

# 截取字符串
select substr(string, idx_begin);
select substr(string, idx_begin, idx_end);

# 获取子串 string_sub 第一次出现的索引，没有则返回 0
select instr(string, string_sub);

# 去除前后空格
select trim(string);

# 在左边填充 n 个字符 c
select lpad(string, n, c);

# 在右边填充 n 个字符 c
select rpad(string, n, c);

# 替换 string 中的子串 a 为 b
select replace(string, a, b);

# 返回 string 的 md5 加密信息
select md5(string);
```

### 数学相关函数

```sql
# 四舍五入，不保留小数
select round(x);

# 四舍五入保留 n 位小数
select round(x, n);

# 向上取整
select ceil(x);

# 向下取整
select floor(x);

# 将 x 截断为 n 位数
select truncate(x, n);

# 取余
select mod(x);

# 获取均匀分布的 0 到 1 的小数
select rand();
```

### 日期时间相关函数

```sql
# 返回 yyyy-MM-dd HH:mm:ss 格式的日期和时间（datetime）
select now();

# 返回年月日
select year(now());
select month(now());
select monthname(now()); # 以英文的形式返回
select day(now());

# 返回相差天数
select datediff(date_1, date_2)

# 将字符按指定格式转化为时间
select str_to_date('2021-02-03','%Y-%m-%d');

# 将日期按指定格式转化为字符串
select date_format('2021-02-03', '%Y-%m-%d');

# 返回当前日期（date）
select curdate();

# 返回当前时间（time）
select curtime();
```

### 其他函数

```sql
# 数据库管理系统版本
select version();

# 当前数据库
select database();

# 当前用户
select user();
```

### 控制流程

```sql
# 三目运算
if(condition, a, b);

# switch 判断
select field_1, ..., field_k
case field_x
when n_1 then expression_1
...
when n_m then expression_m
else expression_default
end
from table_name
```

### 统计/分组/聚合函数
```sql
# 求 field 的值的和
select sum(field) from table_name;

# 求 field 的值的的平均
select avg(field) from table_name;

# 求 field 的值的最大值
select max(field) from table_name;

# 求 field 的值的最小值
select min(field) from table_name;

# 求 field 的值的非空记录数
select count(field) from table_name;
```

### 分组查询
```sql
# 对表中满足 condition 的记录中的 field_stat 进行统计，并将结果按 field_group 进行分组，按 expression 进行筛选，最后将结果按 field_order 进行排序
select stat(field_stat), ..., field_group, field_order
from table_name
where condition
group by field_group
having expression
order by field_order;
```

**注意**：当 MySQL 的 sql_mode 为 only_full_group_by 时，所有 select 后出现且未使用统计函数的字段需要使用 group by 作为分组的字段。

### 多表/连接查询