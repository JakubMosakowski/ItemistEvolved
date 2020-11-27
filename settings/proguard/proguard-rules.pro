## Application
-keep class com.jakmos.itemistevolved.domain.mapper.** { *; }
-keep class com.jakmos.itemistevolved.domain.model.** { *; }
-keep class com.jakmos.itemistevolved.network.dto.** { *; }
-keep class com.jakmos.itemistevolved.persistence.database.entity.** { *; }

# Gson - https://github.com/google/gson/blob/master/examples/android-proguard-example/proguard.cfg
-keepattributes Signature
-dontwarn sun.misc.**
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Joda Time
-dontwarn org.joda.convert.FromString
-dontwarn org.joda.convert.ToString
-keep class org.joda.time.** { *; }
-keep interface org.joda.time.** { *; }

# Limbo
-keep class co.windly.limbo.** { *; }
-keep interface co.windly.limbo.** { *; }
-dontwarn co.windly.limbo.**
