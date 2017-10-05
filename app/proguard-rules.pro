# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /usr/local/Cellar/android-sdk/24.4.1/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keep class com.kishorebabu.android.commitstrip.app.** { *; }

-dontwarn android.arch.util.paging.CountedDataSource
-dontwarn android.arch.persistence.room.paging.LimitOffsetDataSource
##

# ButterKnife rules
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keep class org.ocpsoft.prettytime.i18n.**

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-keep class com.f22labs.android.potwatch2.** { *; }

# Retrofit rules
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8

-dontwarn retrofit2.adapter.rxjava.CompletableHelper$CompletableCallAdapter
-dontwarn retrofit2.adapter.rxjava.CompletableHelper$CompletableCallOnSubscribe
-dontwarn retrofit2.adapter.rxjava.CompletableHelper$CompletableCallOnSubscribe$1


# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# OkHttp rules
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

# Otto rules
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

# RxJava rules
# RxAndroid will soon ship with rules so this may not be needed in the future
# https://github.com/ReactiveX/RxAndroid/issues/219
-dontwarn sun.misc.Unsafe
-keep class rx.internal.util.unsafe.** { *; }

# Gson rules
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
#-keepclassmembers class com.f22labs.android.potwatch.data.model.**
#
## Some models used by gson are inner classes inside the retrofit service
#-keepclassmembers class com.f22labs.android.potwatch.data.remote.PotwatchService** {
#    !static !private <fields>;
#}

# Produces useful obfuscated stack traces
# http://proguard.sourceforge.net/manual/examples.html#stacktrace
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}