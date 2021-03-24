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
                return super.getGlobalTag();
            }

            @Override
            public boolean enable() {
                return super.enable();
            }

            @Override
            public boolean includeThread() {
                return true;
            }

            @Override
            public int stackTraceDepth() {
                return 0;
            }

            @Override
            public ILogPrinter[] printers() {
                return super.printers();
            }

            @Override
            public JsonParser injectJsonParser() {
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

如果需要可视化,则需要在onCreate方法中添加
```java
mViewPrinter = new IViewPrinter(this);
ILogManager.getInstance().addPrinter(mViewPrinter);
mPrintProvider = mViewPrinter.getViewPrintProvider();
mPrintProvider.showFloatingView();
```

打开Log悬浮框
```java
mPrintProvider.showFloatingView();
```

关闭Log悬浮框
```java
mPrintProvider.closeFloatingView();
```

在onDestroy()中移除
```java
ILogManager.getInstance().removePrinter(mViewPrinter);
```