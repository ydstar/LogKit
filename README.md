# LogKit


## YdKit通用组件库
YdKit 是一组功能丰富的 Android 通用组件。

* [LogKit](https://github.com/ydstar/LogKit) — 轻量级的 Android 日志系统。
* [RestfulKit](https://github.com/ydstar/RestfulKit) — 简洁但不简单的 Android 网络组件库。
* [StorageKit](https://github.com/ydstar/StorageKit) — 高性能 Android 离线缓存框架。
* [ExecutorKit](https://github.com/ydstar/ExecutorKit) — 简洁易用的 Android 多线程操作框架。
* [CrashKit](https://github.com/ydstar/CrashKit) — 简洁易用的 Android Crash日志捕捉组件。
* [PermissionKit](https://github.com/ydstar/PermissionKit) — 简洁易用的 Android 权限请求组件。
* [NavigationBarKit](https://github.com/ydstar/NavigationBarKit) — 简洁易用的 Android 顶部导航条组件。
* [RefreshKit](https://github.com/ydstar/RefreshKit) — 简洁易用的 Android 下拉刷新和上拉加载组件。
* [AdapterKit](https://github.com/ydstar/AdapterKit) — 简洁易用的 Android 列表组件。
* [BannerKit](https://github.com/ydstar/BannerKit) — 简洁易用的 Android 无限轮播图组件。
* [MultiViewKit](https://github.com/ydstar/MultiViewKit) — 简洁易用的 Android 多视图组件。
* [TabBottomKit](https://github.com/ydstar/TabBottomKit) — 简洁易用的 Android 底部导航组件。

## 效果预览

<img src="https://github.com/ydstar/LogKit/blob/main/preview/show.gif" alt="动图演示效果" width="250px">

轻量级日志系统,支持任意类型的数据打印,堆栈的追踪,日志的缓存,日志的可视化等高级功能

## 导入方式

仅支持`AndroidX`
```
dependencies {
     implementation 'com.android.ydkit:log-kit:1.0.1'
}
```

## 使用方法
在application中初始化
```java
LogConfig logConfig = new LogConfig() {
            @Override
            public String getGlobalTag() {
                //全局的tag
                return super.getGlobalTag();
            }

            @Override
            public boolean enable() {
                 //LogKit是否可用
                return super.enable();
            }

            @Override
            public boolean includeThread() {
                //是否包含线程信息
                return true;
            }

            @Override
            public int stackTraceDepth() {
                //堆栈的深度
                return 0;
            }

            @Override
            public LogPrinter[] printers() {
                //打印器
                return super.printers();
            }

            @Override
            public JsonParser injectJsonParser() {
                //外界注入对象的序列化
                JsonParser parser = new JsonParser() {
                    @Override
                    public String toJson(Object object) {
                        String json = new Gson().toJson(object);
                        return json;
                    }
                };
                return parser;
            }
        };
//初始化配置并添加控制台打印器,支持添加多个打印器
LogKitManager.getInstance().init(logConfig, new ConsolePrinter());
```

在需要打印Log的地方调用
```java
LogKit.v("令狐冲");
LogKit.d("令狐冲");
LogKit.i("令狐冲");
LogKit.w("令狐冲");
LogKit.e("令狐冲");
LogKit.a("令狐冲");
```

```java
LogKit.vt("tag", "风清扬");
LogKit.dt("tag", "风清扬");
LogKit.it("tag", "风清扬");
LogKit.wt("tag", "风清扬");
LogKit.et("tag", "风清扬");
LogKit.at("tag", "风清扬");
```

<img src="https://github.com/ydstar/LogKit/blob/main/preview/logcat.png">

## 日志可视化
需要页面中创建可视化打印器ViewPrinter,然后添加到LogKitManager中

```java
ViewPrinter viewPrinter = new ViewPrinter(this);
LogKitManager.getInstance().addPrinter(viewPrinter);
```

记得在onDestroy()中移除打印器,要不然会重复打印多次
```java
LogKitManager.getInstance().removePrinter(viewPrinter);
```

## ViewPrinter可视化打印器具体方法作用
| 方法      |返回值  | 作用  |
| :-------- | :--------| :--: |
| showFloatingView| void  |  打开Log悬浮框 |
| closeFloatingView| void |  关闭Log悬浮框 |



## License
```text
Copyright [2021] [ydStar]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
