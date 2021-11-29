plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
    id("kotlin-android")
}


android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)

        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions{
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}


dependencies {

    implementation(kotlin("stdlib-jdk7"))
    implementation(SupportLibs.ANDROIDX_APPCOMPAT)
    implementation(SupportLibs.ANDROIDX_CORE_KTX)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    testImplementation(TestingLib.JUNIT)
    testImplementation(TestingLib.RoboElectric)
    testImplementation(TestingLib.mockito)
    testImplementation(TestingLib.mockito_inline)
    testImplementation(TestingLib.testing_core_testing)
    testImplementation(TestingLib.android_test_room)
    testImplementation(AndroidTestingLib.ANDROIDX_TEST_CORE)

    /**
     * DI
     * */
    implementation(DAGGER.DAGGER)
    implementation(DAGGER.DAGGER_ANDROID_SUPPORT)
    implementation(DAGGER.DAGGER_ANDROID)
    kapt(DAGGER.DAGGER_ANNOTATION)
    kapt(DAGGER.DAGGER_KAPT)

    //RX
    implementation(RX.RXANDROID)
    implementation(RX.RXJAVA)
    implementation(RX.RXKOTLIN)


    // OkHttp, Retrofit
    implementation("com.squareup.okhttp3:okhttp:3.14.7")
    implementation(RETROFIT.RETROFIT)
    implementation(LOGGING_INTERCEPTORS.LOGGING_INTERCEPTORS)
    implementation(RETROFIT.RETROFIT_MOSHI_CONVERTER)
    implementation(RX.RETROFIT)

    /**
     * room Db
     * */
    implementation("androidx.room:room-rxjava2:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")
    api("androidx.room:room-runtime:2.3.0")
    implementation("androidx.room:room-common:2.3.0")

}