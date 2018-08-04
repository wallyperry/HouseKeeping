# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#-------------------------------------------定制化区域----------------------------------------------
#---------------------------------1.实体类---------------------------------

-keep class com.cjcm.housekeeping.bean.** { *; }

#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------

#eventBus
#-keepattributes *Annotation*
#-keepclassmembers class ** {
#    @org.greenrobot.eventbus.Subscribe <methods>;
#}
#-keep enum org.greenrobot.eventbus.ThreadMode { *; }
#-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
#    <init>(java.lang.Throwable);
#}

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#retrofit
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn javax.inject.**

# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
#-keepattributes Signature-keepattributes Exceptions

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# rx
-dontwarn rx.**
-keepclassmembers class rx.** { *; }
#-dontwarn io.reactivex.**
#-keep io.reactivex.**
#-keepclassmembers class io.reactivex.** { *; }


# retrolambda
-dontwarn java.lang.invoke.*

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod

#BaseRecyclerViewAdapterHelper
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers public class * extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(android.view.View);
}

#matisse
#-dontwarn com.bumptech.glide.**
#-dontwarn com.squareup.picasso.**

#ucrop
#-dontwarn com.yalantis.ucrop**
#-keep class com.yalantis.ucrop** { *; }
#-keep interface com.yalantis.ucrop** { *; }

#easeui
#-keep class com.hyphenate.** {*;}
#-dontwarn  com.hyphenate.**

#jpush
#-dontoptimize
#-dontpreverify
#-dontwarn cn.jpush.**
#-keep class cn.jpush.** { *; }
#-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }
#-dontwarn cn.jiguang.**
#-keep class cn.jiguang.** { *; }

#iflytek
#-keep class com.iflytek.**{*;}
#-keepattributes Signature

#fastjson
-keepattributes Signature
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*; }

#permission
-keepclassmembers class ** {
    @com.yanzhenjie.permission.PermissionYes <methods>;
}
-keepclassmembers class ** {
    @com.yanzhenjie.permission.PermissionNo <methods>;
}

# banner 的混淆代码
-keep class com.youth.banner.** {*;}

#ad
#-keep class com.qq.e.** {
#    public protected *;
#}
#-keep class android.support.v4.app.NotificationCompat**{
#    public *;
#}

# WebView
#-keep public class * extends android.webkit.WebChromeClient
#-keep class com.just.library.** {*;}
#-dontwarn com.just.library.**
#-keepclassmembers class com.just.library.agentweb.AndroidInterface{ *; }

#alipay
#-dontwarn android.net.**
#-dontwarn com.alipay.**
#-keep class android.net.SSLCertificateSocketFactory{*;}
#-keep class com.alipay.** { *;}

#bottombar
-dontwarn com.roughike.bottombar.**


#3D地图
#-keep class com.amap.api.maps.**{*;}
#-keep class com.autonavi.**{*;}
#-keep class com.amap.api.trace.**{*;}
#
##定位
#-keep class com.amap.api.location.**{*;}
#-keep class com.amap.api.fence.**{*;}
#-keep class com.autonavi.aps.amapapi.model.**{*;}
#
##搜索
#-keep class com.amap.api.services.**{*;}

#Umeng
#-dontshrink
#-dontoptimize
#-dontwarn com.google.android.maps.**
#-dontwarn android.webkit.WebView
#-dontwarn com.umeng.**
#-dontwarn com.tencent.weibo.sdk.**
#-dontwarn com.facebook.**
#-keep public class javax.**
#-keep public class android.webkit.**
#-dontwarn android.support.v4.**
#-keep enum com.facebook.**
#-keepattributes Exceptions,InnerClasses,Signature
#-keepattributes *Annotation*
#-keepattributes SourceFile,LineNumberTable
#-keep public interface com.facebook.**
#-keep public interface com.tencent.**
#-keep public interface com.umeng.socialize.**
#-keep public interface com.umeng.socialize.sensor.**
#-keep public interface com.umeng.scrshot.**
#-keep public class com.umeng.socialize.* {*;}
#-keep class com.facebook.**
#-keep class com.facebook.** { *; }
#-keep class com.umeng.scrshot.**
#-keep public class com.tencent.** {*;}
#-keep class com.umeng.socialize.sensor.**
#-keep class com.umeng.socialize.handler.**
#-keep class com.umeng.socialize.handler.*
#-keep class com.umeng.weixin.handler.**
#-keep class com.umeng.weixin.handler.*
#-keep class com.umeng.qq.handler.**
#-keep class com.umeng.qq.handler.*
#-keep class UMMoreHandler{*;}
#-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
#-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
#-keep class im.yixin.sdk.api.YXMessage {*;}
#-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
#-keep class com.tencent.mm.sdk.** {*;}
#-keep class com.tencent.mm.opensdk.** {*;}
#-keep class com.tencent.wxop.** {*;}
#-keep class com.tencent.mm.sdk.** {*;}
#-dontwarn twitter4j.**
#-keep class twitter4j.** { *; }
#-keep class com.tencent.** {*;}
#-dontwarn com.tencent.**
#-keep class com.kakao.** {*;}
#-dontwarn com.kakao.**
#-keep public class com.umeng.com.umeng.soexample.R$*{
#    public static final int *;
#}
#-keep public class com.linkedin.android.mobilesdk.R$*{
#    public static final int *;
#}
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#
#-keep class com.tencent.open.TDialog$*
#-keep class com.tencent.open.TDialog$* {*;}
#-keep class com.tencent.open.PKDialog
#-keep class com.tencent.open.PKDialog {*;}
#-keep class com.tencent.open.PKDialog$*
#-keep class com.tencent.open.PKDialog$* {*;}
#-keep class com.umeng.socialize.impl.ImageImpl {*;}
#-keep class com.sina.** {*;}
#-dontwarn com.sina.**
#-keep class  com.alipay.share.sdk.** { *; }
#-keepnames class * implements android.os.Parcelable {
#    public static final ** CREATOR;
#}
#-keep class com.linkedin.** { *; }
#-keep class com.android.dingtalk.share.ddsharemodule.** { *; }
#-keepattributes Signature

#OkHttp相关

-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.** { *; }
-dontwarn com.squareup.okhttp3.**


#Okio相关

-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn okio.**

#UIL相关

#-keep class com.nostra13.universalimageloader.** { *; }
#-keepclassmembers class com.nostra13.universalimageloader.** {*;}
#-dontwarn com.nostra13.universalimageloader.**

#Glide相关

-keep class com.bumptech.glide.Glide { *; }
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn com.bumptech.glide.**

#Picasso相关

#-keep class com.squareup.picasso.Picasso { *; }
#-dontwarn com.squareup.okhttp.**
#-dontwarn com.squareup.picasso.**

#xUtils3相关

#-keepattributes Signature,*Annotation*
#-keep public class org.xutils.** {
#    public protected *;
#}
#-keep public interface org.xutils.** {
#    public protected *;
#}
#-keepclassmembers class * extends org.xutils.** {
#    public protected *;
#}
#-keepclassmembers @org.xutils.db.annotation.* class * {*;}
#-keepclassmembers @org.xutils.http.annotation.* class * {*;}
#-keepclassmembers class * {
#    @org.xutils.view.annotation.Event <methods>;
#}
#-dontwarn org.xutils.**

#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * { native <methods>; }
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* { *; }
-keepclassmembers class * { void *(**On*Event); }
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
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
#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------