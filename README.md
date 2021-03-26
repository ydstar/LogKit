# LogKit

<img src="https://github.com/ydstar/LogKit/blob/main/preview/show.gif" alt="动图演示效果" width="250px">


## 导入方式

仅支持`AndroidX`
```
dependencies {
     implementation 'com.android.ydkit:log-kit:1.0.0'
}
```

## 使用方法
在application中初始化
```java
ILogConfig iLogConfig = new ILogConfig() {

            @Override
            public String getGlobalTag() {
                //全局的tag
                return super.getGlobalTag();
            }

            @Override
            public boolean enable() {
                //iLog是否可用
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
            public ILogPrinter[] printers() {
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
ILogManager.getInstance().init(iLogConfig, new IConsolePrinter());
```

在需要打印Log的地方调用
```java
ILog.v("令狐冲");
ILog.d("令狐冲");
ILog.i("令狐冲");
ILog.w("令狐冲");
ILog.e("令狐冲");
ILog.a("令狐冲");
```

```java
ILog.vt("tag", "风清扬");
ILog.dt("tag", "风清扬");
ILog.it("tag", "风清扬");
ILog.wt("tag", "风清扬");
ILog.et("tag", "风清扬");
ILog.at("tag", "风清扬");
```

<img src="https://github.com/ydstar/LogKit/blob/main/preview/logcat.png">

## 日志可视化
需要页面中创建可视化打印器IViewPrinter,然后添加到ILogManager中

```java
IViewPrinter viewPrinter = new IViewPrinter(this);
ILogManager.getInstance().addPrinter(mViewPrinter);
```

记得在onDestroy()中移除打印器,要不然会重复打印多次
```java
ILogManager.getInstance().removePrinter(mViewPrinter);
```

## IViewPrinter可视化打印器具体方法作用
| 方法      |参数或返回值  | 作用  |
| :-------- | :--------| :--: |
| showFloatingView| void  |  打开Log悬浮框 |
| closeFloatingView| void |  关闭Log悬浮框 |
