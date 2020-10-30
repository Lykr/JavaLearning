## `equals()`, `==`, `hashcode()`

### 注意事项
1. `equals()` 是 `Object` 中的方法，默认使用 `==` 比较两个对象；
2. `==` 用于对象比较时，比较的是对象的地址，用于基本类型时，比较的是值；
3. 两个对象相等，则其 hashcode 应该要一定相等；
4. 两个对象有相同的 hashcode，不一定相等；
5. 如果 `equals()` 方法被覆写，`hashcode()` 也需要被覆写，否则对于两个内容相同的对象，其 hashcode 永远不会相等。
   - **`equals()` 所比较的属性，要与 `hashcode()` 中用于计算的属性相同**

## 枚举（enums）

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