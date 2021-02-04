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

### 库相关

```sql
-- 查看数据库
show databases;

-- 新建数据库
create database database_name;

-- 使用数据库
use databse_name;

-- 查询当前所在数据库
select database();
```

### 表相关

```sql
-- 查询当前数据库中的所有表
show tables;

-- 查询指定数据库中的所有表
show tables from database_name;

-- 创建一个表
create table table_name(id int primary key auto_increment, name varchar(255));

-- 查看表结构
desc table_name;

-- 删除表
drop table_name;
```

### 记录相关

```sql
-- 查询表中所有记录的所有字段
select * from table_name;

-- 查询表中的指定字段
select column_1, ..., column_n from table_name;

-- 查询表中的指定字段，并为字段起别名（或使用 column AS alias 的形式）
select column_1 alias_1, ..., column_n alias_n from table_name;

-- 查询并去重
select distinct column from table_name;

-- 向表中插入记录
insert into table_name(column_1, ..., column_n) values(value_1, ..., value_n);

-- 根据条件修改记录
update table_name set column_name = column_value where condition;

-- 根据条件删除记录
delete from table_name where condition;

-- 其他
-- 查询 MySQL 版本
select version();

-- 查询当前用户
select user();
```

## DQL 语言（查询）

### 条件与逻辑表达式

与 Java 类似。

需要注意 MySQL 中的条件比较有以下特点：

1. = 和 <>：不能比较 null 值，当比较的元素中有 null 时返回 null
2. <=>：可以比较 null 值

MySQL 中的与或非除了能用 &&、||、! 来表示，还能用 and、or、not 的方式表示。

### 字符串相关函数

```sql
-- 拼接字符串
select concat(string_1, ..., string_n);

-- 判断 string 的值是否为 null，如果为 null 返回 value，否则返回原值
select ifnull(string, value); 

-- 判断 string 的值是否为 null，如果是返回 1，否则 0
select isnull(string);

-- 获取 string 参数值的字节长度
select length(string);

-- 大小写转换
select upper(string);
select lower(string);

-- 截取字符串
select substr(string, idx_begin);
select substr(string, idx_begin, idx_end);

-- 获取子串 string_sub 第一次出现的索引，没有则返回 0
select instr(string, string_sub);

-- 去除前后空格
select trim(string);

-- 在左边填充 n 个字符 c
select lpad(string, n, c);

-- 在右边填充 n 个字符 c
select rpad(string, n, c);

-- 替换 string 中的子串 a 为 b
select replace(string, a, b);

-- 返回 string 的 md5 加密信息
select md5(string);
```

### 数学相关函数

```sql
-- 四舍五入，不保留小数
select round(x);

-- 四舍五入保留 n 位小数
select round(x, n);

-- 向上取整
select ceil(x);

-- 向下取整
select floor(x);

-- 将 x 截断为 n 位数
select truncate(x, n);

-- 取余
select mod(x);

-- 获取均匀分布的 0 到 1 的小数
select rand();
```

### 日期时间相关函数

```sql
-- 返回 yyyy-MM-dd HH:mm:ss 格式的日期和时间（datetime）
select now();

-- 返回年月日
select year(now());
select month(now());
select monthname(now()); -- 以英文的形式返回
select day(now());

-- 返回相差天数
select datediff(date_1, date_2)

-- 将字符按指定格式转化为时间
select str_to_date('2021-02-03','%Y-%m-%d');

-- 将日期按指定格式转化为字符串
select date_format('2021-02-03', '%Y-%m-%d');

-- 返回当前日期（date）
select curdate();

-- 返回当前时间（time）
select curtime();
```

### 其他函数

```sql
-- 数据库管理系统版本
select version();

-- 当前数据库
select database();

-- 当前用户
select user();
```

### 控制流程

```sql
-- 三目运算
if(condition, a, b);

-- switch 判断
select column_1, ..., column_k
case column_x
when n_1 then expression_1
...
when n_m then expression_m
else expression_default
end
from table_name
```

### 模糊查询

```sql
select column_1, ..., column_n
from table_name
where column_x 接模糊查询语句（like, between and, in, is）
```

1. like：配合通配符使用
   - 通配符：% 匹配任意多个字符，_ 匹配一个字符
   - 当需要查询通配符字符时，需要使用 \ 进行转义
2. between A and B：在 A 和 B 之间，等价于 >= A and <= B
3. in (A, ..., Z)：包含于 A，...，Z 中，等价于 = A or ... or = Z
4. is null：值为 null

### 排序查询

```sql
-- 从表中查询字段信息，并按 column_x 字段的值进行排序
-- asc 表示升序（默认），desc 表示降序
select column_1, ..., column_n from table_name order by column_x asc/desc;
```

### 统计/分组/聚合函数
```sql
-- 求 column 的值的和
select sum(column) from table_name;

-- 求 column 的值的的平均
select avg(column) from table_name;

-- 求 column 的值的最大值
select max(column) from table_name;

-- 求 column 的值的最小值
select min(column) from table_name;

-- 求 column 的值的非空记录数
select count(column) from table_name;
```

### 分组查询

```sql
/* 对表中满足 condition 的记录中的 column_stat 进行统计，
   并将结果按 column_group 进行分组，
   按 expression 进行筛选，
   最后按 column_order 进行排序，
   [] 内为可选语句 */
select 统计函数([别名.]统计字段), ..., [别名.]查询字段, ...
from 表名 [别名], ...
[where 条件语句]
[group by 分组字段]
[having 条件语句]
[order by 排序字段 [排序方式]];
```

**注意**：当 MySQL 的 sql_mode 为 only_full_group_by 时，所有 select 后出现且未使用统计函数的字段需要使用 group by 作为分组的字段。

### 多表/连接查询

- sql92 和 sql99 标准
  - sql92 标准：书写简单，易读性不好，仅支持内连接
  - sql99 标准：支持内、外、交叉连接，易读性好，书写麻烦

#### sql92 标准

```sql
select [别名_1.]查询字段, ..., [别名_n.]查询字段
from 表名_1 [别名_1], ..., 表名_n [别名_n]
[where 连接条件 [其他逻辑]]
[group by 分组字段]
[having 条件语句]
[order by 排序字段 [排序方式]];
```

#### sql99 标准

- 内连接、外连接和交叉连接
  - 内连接（`join`）：指连接结果仅包含符合连接条件的行
    - `join` 等价于 `inner join`
    - 执行顺序：对所有表进行 `where` 筛选，将结果集按升序排序，在对结果集无影响的情况下，优先选择结果集最小的表作为驱动表
  - 外连接（`left/right join`）：连接结果包含主表的所有行，以及满足连接条件的附表的行，不满足条件的附表区域用 null 填充
    - `left join` 和 `right join` 分别等价于 `left outer join` 和 `right outer join`
    - `left join` 左侧为主表，`right join` 右侧为主表，主表
    - 执行顺序：nest loop join 机制，对外层结果集和内层结果集，先筛选再连接
  - 交叉连接（不带 `on` 或 `cross join`）：返回两个表的“笛卡尔积”

```sql
select [别名_1.]查询字段, ..., [别名_n.]查询字段
from 表名_1 [别名_1]
[left/right] join 表名_2 [别名_2] on 条件语句
...
[[left/right] join 表名_n [别名_n] on 条件语句]
[where 连接条件 [其他逻辑]]
[group by 分组字段]
[having 条件语句]
[order by 排序字段 [排序方式]];
```

### 子查询

将查询结果（标量、表）当做查询参数，嵌套查询。

### 分页查询

```sql
-- offset 起始索引（从零开始），size 显示条目数
-- 常用公式 offset = (page - 1) * size，page 属于正整数
select ... from ...
[limit param_offset, param_size];
```

### 联合查询

```sql
-- union 默认去重，加 all 不去重
select ... from ...
union [all]
select ... from ...
[union [all]
...]
```

## DML 语言（增删改）

### 插入

```sql
-- 方式一，支持插入子查询结果
insert into table_name(column_1, ..., column_n)
values(value_1_1, ..., value_1_n)[,
...
(value_m_1, ..., value_m_n)];

-- 方式二，不支持插入子查询结果
insert into table_name set column_1 = value_1, ..., column_n = value_n;
```

### 修改

```sql
-- 单表修改
update table_name set column_1 = value_1, ..., column_n = value_n
where condition

-- 多表连接修改
--- sql92
update table_1 t1, ..., table_n tn
set t1.column_x = value_x, ..., tn.column_y = value_y
where condition

--- sql99
update table_1 t1
...
[left/right] join table_n tn on condition
set t1.column_x = value_x, ..., tn.column_y = value_y
[where condition];
```

### 删除

```sql
-- 单表删除
delete from table_name where condition;

-- 多表删除，tx 为要删除记录的表
delete tx
from table_1 t1
...
[left/right] join table_n tn on condition
[where condition];

-- 清空表中所有记录
truncate table table_name;
```

- `delete` 和 `truncate` 的区别：
  1. `delete` 可以加条件语句，`truncate` 不能
  2. `delete` 有返回值，`truncate` 没有
  3. `delete` 能作用于表、视图，`truncate` 只能作用于表
  4. `delete` 不会充值自增列，`truncate` 会（重置为从 1 开始）
  5. `delete` 可以回滚，`truncate` 不行

## DDL 语言

### 数据类型

#### 数值型
|类型|所占字节数|备注|
|-|-|-|
|tinyint|1|以下 5 种 *int 型如不标明 unsigned 默认带符号|
|smallint|2||
|mediumint|3||
|integer/int|4||
|bigint|8||
|decimal/dec(m, d)||m 表示有效数字，默认为 10；d 表示小数点后的位数，默认为 0|
|numeric(m, d)||MySQL 8.0 中 numeric 和 decimal 的实现一样|
|float|4||
|double|8||
|bit(m)||m 属于[1, 64]；用 `b'value'` 来表示一个比特值，如 `b'111'` 表示 7；如果赋值的长度小于 m，将会在左边补 0 |
|boolean/bool|1||

**注意**：对于 floating-point(float/double) 或者 fixed-point(decimal/numeric) 列，如果插入超出范围的值，当开启 strict SQL mode 时会报 out of range 异常并插入失败，否则会插入一个临界值。

#### 字符型

|类型|空间占用|效率|备注|
|-|-|-|-|
|char(m)|储存字节固定，高|高|m 可以省略，默认为 1|
|varchar(m)|储存字节根据实际内容变化，低|低|m 不能省略|

**binary、varbinary、blob、text、enum 和 set 待补充**

#### 日期型
|类型|格式|范围|备注|
|-|-|-|-|
|date|'YYYY-MM-DD'|'1000-01-01' 到 '9999-12-31'||
|datetime|'YYYY-MM-DD hh:mm:ss'|'1000-01-01 00:00:00' 到 '9999-12-31 23:59:59'|不受时区变化影响|
|timestamp||'1970-01-01 00:00:01' UTC 到 '2038-01-19 03:14:07' UTC|受时区变化影响，储存时会将当前时区的时间转换至 UTC 时区储存，读取时再按当前时区转换回来（datetime 不会这么做）|
|time|'hh:mm:ss'|'-838:59:59.000000' 到 '838:59:59.000000'||
|year|YYYY|1901 到 2155||

### 库相关

```sql
-- 创建库
create database [if not exists] db_name [character set charset_name]

-- 修改库所用字符集
alter database db_name character set charset_name;

-- 删除库
drop database [if exists] db_name;
```

### 表相关

- SQL 约束（Constraints）：限制加入表的数据类型
  1. `not null`：不接受 null 值
  2. `unique`：不接受重复值，一个表可以有多个该约束
  3. `primary key`：不接受重复值，一个表只能有一个该约束，记录会按照主键升序排列
     - 对于整数型主键，可在声明时附加 `auto_increment` 让主键自增
  4. `foreign key`：声明外键，必须是另一个表的主键，且类型一致
     - 例：`foreign key(table_a_column) references table_b(primary_column)`
  5. `check`：设置该列需要满足的条件，MySQL 8.0.16 后开始支持
  6. `default`：可设置该列默认值

```sql
-- 创建表
create table [if not exists] table_name(
    column_name column_type [constraints],
    ...
);

-- 修改表
--- 添加列
alter table table_name add column column_name column_type [first/after column_offset]

--- 修改列的类型或约束
alter table table_name modify column column_name column_type [constraints]

--- 修改列名
alter table table_name change column column_name new_column_name column_type;

--- 删除列
alter table table_name drop column column_name;

--- 修改表名
alter table table_name rename new_table_name;

-- 删除表
drop table [if exists] table_name;

-- 复制表
--- 复制结构
create table new_table like old_table;

--- 复制结构和数据
create table new_table
select */column_1, ..., column_n from old_table [where condition];
```

## 参考
1. [MySQL 8.0 Reference Manual / Data Types](https://dev.mysql.com/doc/translation-refman/8.0/en/data-types.html)