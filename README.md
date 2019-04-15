# FBReaderImpl
FBReader扩展,基于FBreader最新版本(Latest commit e83aec9  on 12 Jun 2017),UI优化，支持拼音
Usage
--------
With Gradle:
```groovy
  compile 'com.github.binbinrd:fbreaderimpl:1.0.0'
```
init
--------
继承自FBReaderApplication:
```java
    public class FBReaderImplApplication extends FBReaderApplication {
    }
```
# FBReaderImpl_s
FBReader扩展,基于FBreader最新版本(Latest commit e83aec9  on 12 Jun 2017),UI优化，支持拼音,**且不需要继承自FBReaderApplication**

Usage
--------
With Gradle:
```groovy
  compile 'com.github.binbinrd:fbreaderimpl_s:1.0.0'
```
init
--------
在自己的application类中,中初始化FBreader:
```java
    public class FBReaderImplApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            FBReaderConfig.init(this);
        }
    }
```

How to use
--------
```java
     FBReader.openBookActivity(Context context, String path, ReaderLifeCycle listener);
```