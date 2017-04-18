# BaseProject

一个Android的基础项目，里面会对项目基本架构模式进行一个示例，并对项目经常用到的如**BaseActivity**，**BaseFragment**，**网络请求**等进行一些基本封装，方便以后使用，项目会逐步更新

## 基本架构模式

**BaseProject**会基于**MVP架构**作为基本架构模式，将会参考[Google官方的MVP示例](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)，采用契约Contract的形式

## BaseActivity，BaseFragment等

在BaseActivity，BaseFragment中会在里面书写共有逻辑操作的代码

## 网络请求框架

网络请求框架将会采用 RxJava + Retrofit + OkHttp的方式，在网络请求中会对三者进行一些简单的配合封装


