#代码混淆

#---------------------------------基本指令区-----------------------

-optimizationpasses 5  #指定代码的压缩级别
-dontpreverify  #预校验
-verbose  #指定日志级别
-dontskipnonpubliclibraryclassmembers
-dontusemixedcaseclassnames  #包名不混合大小写
-ignorewarnings  #忽略警告
-printmapping proguardMapping.txt  #生成原类名和混淆后的类名的映射文件
-optimizations !code/simplification/cast,!field/*,!class/merging/*  #混淆时所采用的算法
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable


#---------------------------------默认保留区------------------------

#四大组件、View体系等
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.multidex.MultiDexApplication
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep class android.support.** {*;}
-keep public class * extends android.support.v4.**

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.com.fishinwater.situp.util.AttributeSet);
    public <init>(android.content.Context, android.com.fishinwater.situp.util.AttributeSet, int);
}

-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.com.fishinwater.situp.util.AttributeSet);
    public <init>(android.content.Context, android.com.fishinwater.situp.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep class **.R$* {
 *;
}

-keepclassmembers class * {
    void *(*Event);
}

#enum
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

#Parcelable
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#webview
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}


#---------------------------------Support Vx ---------------------------------

# support-v4
-dontwarn android.support.v4.**
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }
-keep class android.support.v4.** {
    <fields>;
    <methods>;
}
-keep interface  android.support.v4.app.** {
    <fields>;
    <methods>;
}

# support-v7
-dontwarn android.support.v7.**
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }

# support design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }


#---------------------------------第三方包-------------------------

#okhttp3.x
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.{*;}
-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okhttp3.internal.platform.ConscryptPlatform

#retrofit
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okio.**

#Rxjava RxAndroid
-dontwarn rx.*
-dontwarn sun.misc.**

#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#Gson
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }

#Lambda表达式
-keep class java.lang.invoke.** {*;}
-dontwarn java.lang.invoke.**

#高徳地图
#3D 地图
-keepclass com.amap.api.mapcore.**{*;}
-keepclass com.amap.api.maps.**{*;}
-keepclass com.autonavi.amap.mapcore.*{*;}
#定位
-keepclass com.amap.api.location.**{*;}
-keepclass com.loc.**{*;}
-keepclass com.amap.api.fence.**{*;}
-keepclass com.autonavi.aps.amapapi.model.**{*;}
# 搜索
-keepclass com.amap.api.services.**{*;}
# 2D地图
-keepclass com.amap.api.maps2d.**{*;}
-keepclass com.amap.api.mapcore2d.**{*;}
# 导航
-keepclass com.amap.api.navi.**{*;}
-keepclass com.autonavi.**{*;}


#ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#eventbus 3.0
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.com.fishinwater.situp.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#ARouter组件化
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

#ijkplayer
-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**
-keep class com.dueeeke.videoplayer.** { *; }
-dontwarn com.dueeeke.videoplayer.**

#七牛上传
-keep class com.qiniu.**{*;}
-keep class com.qiniu.**{public <init>();}

#友盟
-keep class com.umeng.** {*;}
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**
-dontwarn com.meizu.**
-dontwarn com.umeng.**
-keepattributes *Annotation*
-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class com.meizu.** {*;}
-keep class org.apache.thrift.** {*;}
-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}
-keep public class **.R$*{
   public static final int *;
}
