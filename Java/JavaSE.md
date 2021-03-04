## 原子操作（Atomic operation）

### 定义
- 指不会被线程调度机制打断的操作，这种操作一旦开始，就一直运行到结束，中间不会有任何 context switch（切换到另一个线程）；
- 如果这个操作所处的层（layer）的更高层不能发现其内部实现与结构，那么这个操作是一个原子（atomic）操作；
- 原子操作可以是一个步骤，也可以是多个操作步骤，但是其顺序不可以被打乱，也不可以被切割而只执行其中的一部分；
- 将整个操作视作一个整体是原子性的核心特征。

## Collections 工具类

### 常用方法
- 排序
  ```java
  void reverse(List list) //反转
  void shuffle(List list) //随机排序
  void sort(List list) //按自然排序的升序排序
  void sort(List list, Comparator c) //定制排序，由 Comparator 控制排序逻辑
  void swap(List list, int i , int j) //交换两个索引位置的元素
  void rotate(List list, int distance) //旋转。当 distance 为正数时，将 list 后 distance 个元素整体移到前面。当 distance 为负数时，将 list 的前 distance 个元素整体移到后面
  ```

- 查找
  ```java
  int binarySearch(List list, Object key) //对 List 进行二分查找，返回索引，注意 List 必须是有序的
  int max(Collection coll) //根据元素的自然顺序，返回最大的元素
  int max(Collection coll, Comparator c) //根据定制排序，返回最大元素，排序规则由 Comparatator 类控制
  int min(Collection coll) //根据元素的自然顺序，返回最小的元素
  int min(Collection coll, Comparator c) //根据定制排序，返回最小元素，排序规则由 Comparatator 类控制
  void fill(List list, Object obj) //用指定的元素代替指定 list 中的所有元素
  int frequency(Collection c, Object o) //统计元素出现次数
  int indexOfSubList(List list, List target) //找到 target 在 list 中第一次出现的索引，找不到则返回 -1
  int lastIndexOfSubList(List source, List target) //找到 target 在 list 中最后一次出现的索引，找不到则返回 -1
  boolean replaceAll(List list, Object oldVal, Object newVal) //用新元素替换旧元素
  ```

- 同步控制
  
  **性能低，最好不要用，考虑用 JUC 包下的并发容器**

  ```java
  synchronizedCollection(Collection<T> c) //返回指定 collection 支持的同步（线程安全的）collection。
  synchronizedList(List<T> list)//返回指定 list 支持的同步 list
  synchronizedMap(Map<K,V> m) //返回由指定 map 支持的同步 map
  synchronizedSet(Set<T> s) //返回指定 set 支持的同步 set
  ```

## `equals()`, `==`, `hashcode()`

### 注意事项
1. `equals()` 是 `Object` 中的方法，默认使用 `==` 比较两个对象；
2. `==` 用于对象比较时，比较的是对象的地址，用于基本类型时，比较的是值；
3. 两个对象相等，则其 hashcode 应该要一定相等；
4. 两个对象有相同的 hashcode，不一定相等；
5. 如果 `equals()` 方法被覆写，`hashcode()` 也需要被覆写，否则对于两个内容相同的对象，其 hashcode 永远不会相等。
   - **`equals()` 所比较的属性，要与 `hashcode()` 中用于计算的属性相同**

## 枚举（Enums）

### 示例
  ```java
  public enum PizzaStatus {
    ORDERED,
    READY,
    DELIVERED;
  }
  ```

### 注意
比较时用 `==` 进行比较，使用 `equals()` 方式比较可能会产生 `NullPointerException` 或枚举值相同导致的错误比较。

## 优先队列（PrioritQueue）

### 性质
对放入队列的元素进行堆排序，队首元素为最小元素。

### 常用方法
- peek()：返回队首元素
- poll()：返回队首元素，并移除队列
- add()：添加元素
- size()：返回队列元素个数
- isEmpty()：判断是否为空

### 相关题目
- 连续中值：https://leetcode-cn.com/problems/continuous-median-lcci/

### 其他
- 大顶堆的一种创建方式：
  ```java
  new PriorityQueue<>((x,y) -> y-x)
  ```

## 流（Stream）

### 常用操作

```java
// 注意，流不能被重复使用

// 数组
int[] arr;

// 数组转列表
List<?> newList = Arrays.stream(arr).boxed().collect(Collectors.toList());

// 最大、最小、平均值
int max = Arrays.stream(arr).max().getAsInt(); //最大值
int min = Arrays.stream(arr).min().getAsInt(); //最小值
double average = Arrays.stream(arr).average().getAsDouble(); //平均值

// 列表
List<?> list;

// 列表转数组
int[] newArray = list.stream().mapToInt(v -> v).toArray();
```