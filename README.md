# BaseProject

一个Android的基础项目，里面会对项目基本架构模式进行一个示例，并对项目经常用到的如**BaseActivity**，**BaseFragment**，**网络请求**等进行一些基本封装，方便以后使用，项目会逐步更新

## 基本架构模式

**BaseProject**会基于**MVP架构**作为基本架构模式，将会参考[Google官方的MVP示例](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)，采用契约Contract的形式

## BaseActivity，BaseFragment等

在BaseActivity，BaseFragment中会在里面书写共有逻辑操作的代码

## 网络请求框架

网络请求框架将会采用 RxJava + Retrofit + OkHttp的方式，在网络请求中会对三者进行一些简单的配合封装

在网络请求示例中，会用到[Gank](http://gank.io/)的API，在此表示感谢！

## 图片加载框架

图片加载将会使用[Glide](https://github.com/bumptech/glide)进行加载，在使用Gide时还会进行二次封装，封装后对应的类文件为ImageLoader，放置在Utils包中

## Utils

Utils包中会添加一些常用的工具类，例如：

### LogUtils

关于Log日志打印相关的工具类，可以统一设置是否打印日志以及打印的等级等

### NetworkUtils

网络连接相关的工具类，判断网络是否连接，当前为哪种网络等

### ImageLoader

这是对Glide的二次封装，方便统一使用或更换框架

### apkUpdate

这是在Utils包下的子包，里面会用apk版本更新的相关类文件，在这里的apk版本更新中，是使用了DownloadManager进行的版本更新
