# component_host
组件化_宿主工程

allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
  
  implementation 'com.github.2628748861:component_host:1.0.0'

1.具体使用

#如果需要使用ButterKnife

(项目根目录的build.gradle中添加)
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.jakewharton:butterknife-gradle-plugin:8.4.0'
    }
}
apply plugin: 'com.jakewharton.butterknife' (library才添加,因为需要R2)
annotationProcessor 'com.jakewharton:butterknife-compiler:8.4.0'

#需要使用Arouter

javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AROUTER_MODULE_NAME: project.getName()]
            }
        }
annotationProcessor 'com.alibaba:arouter-compiler:1.2.1'

#添加数据库
assets/litepal.xml中添加mapping  <mapping class="com.cample.app.Album" />(在主工程中进行添加)

#添加自定义生命周期监听
实现IModuleConfiguration接口(可选)


2.混淆配置:

#litepal

-keep class org.litepal.** {*;}
-keep class * extends org.litepal.crud.DataSupport {*;}
-keep class * extends org.litepal.crud.LitePalSupport {*;}

#Retrofit+okhttp

-dontwarn okio.**
-dontwarn javax.annotation.**

#IModuleConfiguration的实现类不能混淆

-keepclasseswithmembers class * implements app.component.simple.IModuleConfiguration {
<init>(...); }

#BaseResponseEntity的子类不能混淆

-keepclasseswithmembers class * extends app.component.simple.http.entity.BaseResponseEntity { (...); }

#Arouter混淆配置

-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider
