# java-concurrent

并发编程的学习笔记

## jps

`jps` 是 `Java Virtual Machine Process Status Tool`（Java 虚拟机进程状态工具）的缩写。这个工具用于列出当前系统上正在运行的 Java 进程的进程 ID（PID）和主类名。jps
的基本用法如下：

```bash
jps [选项]
```

常用选项包括：  
`-l`：显示完整的主类名，而不仅仅是类的名称。  
`-m`：显示传递给主类的参数。  
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

`-l`：显示关于锁的附加信息。
`-F`：强制生成线程快照，即使进程看起来不响应。
`-m`：显示 Java 和本地堆栈帧。
示例：

```bash
jstack -l 12345
```

这将输出 Java 进程的线程快照，其中包含有关每个线程的信息，包括线程的状态、堆栈跟踪等。

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