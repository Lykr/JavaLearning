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
- 大顶堆的一种创建方式：`new PriorityQueue<>((x,y) -> y-x)`