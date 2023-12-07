# java-concurrent

并发编程的学习笔记

## jps

`jps` 是 `Java Virtual Machine Process Status Tool`（Java 虚拟机进程状态工具）的缩写。这个工具用于列出当前系统上正在运行的 Java 进程的进程 ID（PID）和主类名。jps
的基本用法如下：

```bash
jps [选项]
```

常用选项包括：  
`-l`：显示完整的主类名，而不仅仅是类的名称
`-m`：显示传递给主类的参数  
`-v`：显示传递给 JVM 的参数。  
例如，你可以运行以下命令：

```bash
jps -l
```

这将列出所有 Java 进程及其主类的详细信息。

jps 对于在系统上识别当前运行的 Java 进程非常有用，特别是当你需要知道 Java 进程的 PID 时。一般情况下，jps 在开发和调试过程中用得比较多，以方便查看正在运行的 Java 进程。

## jstack

jstack 是 Java JDK 中的一个工具，用于生成 Java 进程的线程快照。这个工具对于诊断和分析 Java 应用程序中的线程问题非常有用。以下是 jstack 的基本用法：

```bash
jstack [选项] <pid>
```

- `[选项]`：`jstack` 支持一些选项，例如 `-l`、`-F`、`-m` 等，你可以根据需要选择使用。
- `<pid>`：Java 进程的进程号。
  例如，要获取进程号为12345的 Java 进程的线程快照，可以运行：

```bash
jstack 12345
```

一些常用的选项包括：

`-l`：显示关于锁的附加信息  
`-F`：强制生成线程快照，即使进程看起来不响应  
`-m`：显示 Java 和本地堆栈帧  
`-h or -help`：输出帮助信息ls
示例：

```bash
jstack -l 12345
```

这将输出 Java 进程的线程快照，其中包含有关每个线程的信息，包括线程的状态、堆栈跟踪等。

统计所有线程的状态

```bash
grep java.lang.Thread.State dump/dump_pid_1001 | awk '{print $2$3$4$5}' | sort | uniq -c
```

```text
Found one Java-level deadlock:
=============================
"线程B":
  waiting to lock monitor 0x00007f988800f3f8 (object 0x0000000795839b10, a java.lang.String),
  which is held by "线程A"
"线程A":
  waiting to lock monitor 0x00007f988800f4a8 (object 0x0000000795839b40, a java.lang.String),
  which is held by "线程B"

Java stack information for the threads listed above:
===================================================
"线程B":
	at com.hqhe.juc.DeadLock.lambda$deadLock$1(DeadLock.java:29)
	- waiting to lock <0x0000000795839b10> (a java.lang.String)
	- locked <0x0000000795839b40> (a java.lang.String)
	at com.hqhe.juc.DeadLock$$Lambda$2/736709391.run(Unknown Source)
	at java.lang.Thread.run(Thread.java:748)
"线程A":
	at com.hqhe.juc.DeadLock.lambda$deadLock$0(DeadLock.java:21)
	- waiting to lock <0x0000000795839b40> (a java.lang.String)
	- locked <0x0000000795839b10> (a java.lang.String)
	at com.hqhe.juc.DeadLock$$Lambda$1/611437735.run(Unknown Source)
	at java.lang.Thread.run(Thread.java:748)

Found 1 deadlock.
```

## CPU实现原子操作
`原子操作`:`不可被中断的一个或者一系列操作`
`缓存行`:`缓存的最小单位`
`CAS`：`一个旧值，一个新值，先比较旧值和缓存的中值是否一致，一致将旧值替换成新值，否则不替换`
### CPU如何实现原子操作
1. 32位IA-32基于缓存加锁或者总线加锁


# md

## 换行

1. 段落中换行
   第一行<br/>第二行
2. 句子换行两个空格  
   第一行  
   第二行
3. 句子换行 \\+Enter\
   第一行\
   第二行

# linux

## grep

`grep`: `Global Regular Expression Print`全文正则匹配输出  
作用：
`grep`是一个强大的文本搜索工具，它用于在`文件`或`输入流`中搜索`匹配某个模式的文本`，并将`匹配的行`打印到标准输出。\
它支持使用正则表达式进行模式匹配
> 语法

```bash
grep [选项] 模式 [文件...]
```

常见用法

1. 在文件中搜索关键词：

```bash
grep "keyword" filename
```

2. 在多个文件中搜索关键词：

```bash
grep "Thread" /Users/hhq/Desktop/main/work_space/dump/dump /Users/hhq/Desktop/main/work_space/dump/client.log
```

3. 标记匹配颜色 `grep --color=auto`：

```bash
grep --color=auto java.lang.Thread.State /Users/hhq/Desktop/main/work_space/dump/dump 
```

4. 显示匹配行的行号`grep -n`：

```bash
grep -n java.lang.Thread.State /Users/hhq/Desktop/main/work_space/dump/dump --color=auto
```

5. 不区分大小写搜索`grep -i`：

```bash
grep -i "keyword" filename
```

6. 递归搜索目录中的关键词`grep -r`：

```bash
grep -rn --color=auto "hqhe" /Users/hhq/Desktop/main/work_space/
```

使用正则表达式进行搜索：

bash
Copy code
grep "^pattern" filename
从标准输入读取数据并搜索：
echo "some text" | grep "keyword"
