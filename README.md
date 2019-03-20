# FloatApp
## 通过WindowManager实现悬浮小球，可扩展性强，后续还会增加各种互动操作。欢迎issues

### 初始化方法
* 在Application的onCreate()中实现
```java
LioNSDK.initApplication(this);
```

* 在主Activity的onCreate()方法中实现
```java
LioNSDK.initMainActivity(MainActivity.this);
```

* 在需要显示悬浮小球的位置实现
```java
LioNSDK.showToolbar(new FloatOnClick() {
    @Override
    public void onClick() {
        //自行实现点击效果
     Toast.makeText(MainActivity.this,"xxx",Toast.LENGTH_SHORT).show();
             }
    });
```