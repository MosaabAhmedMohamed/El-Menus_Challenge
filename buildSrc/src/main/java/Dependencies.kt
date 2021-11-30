object Sdk {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 30
    const val COMPILE_SDK_VERSION = 30
}


object Versions {
    const val ANDROIDX_TEST_EXT = "1.1.1"
    const val ANDROIDX_TEST = "1.2.0"
    const val APPCOMPAT = "1.3.1"
    const val CONSTRAINT_LAYOUT = "2.1.1"
    const val CORE_KTX = "1.6.0"
    const val ESPRESSO_CORE = "3.4.0"
    const val JUNIT = "4.13.2"
    const val KTLINT = "0.36.0"
    const val RETROFIT = "2.9.0"
    const val DAGGER = "2.35.1"
    const val RXANDROID = "2.1.1"
    const val RXJAVA = "2.2.19"
    const val RXKOTLIN = "2.2.0"
    const val RX_RETROFIT = "2.5.0"
    const val LOGGING_INTERCEPTORS = "4.4.0"
    const val CARD_VIEW = "1.0.0"
    const val RECYCLER_VIEW = "1.2.0-alpha05"
    const val RoboElectric = "4.4"
    const val ROOM = "2.3.0"
    const val MOCKITO = "3.12.4"
    const val lifecycle = "1.1.1"
    const val PAGING_3 = "3.0.1"
    const val NAVIGATION="2.3.5"
    const val GLIDE = "4.12.0"

}

object BuildPluginsVersion {
    const val AGP = "4.1.3"
    const val DETEKT = "1.8.0"
    const val KOTLIN = "1.4.32"
    const val KTLINT = "9.2.1"
    const val VERSIONS_PLUGIN = "0.28.0"
}

object SupportLibs {
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val ANDROIDX_CONSTRAINT_LAYOUT =
        "com.android.support.constraint:constraint-layout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val CARD_VIEW = "androidx.cardview:cardview:${Versions.CARD_VIEW}"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLER_VIEW}"

}

object TestingLib {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val android_test_room = "androidx.room:room-testing:${Versions.ROOM}"
    const val testing_core_testing = "android.arch.core:core-testing:${Versions.lifecycle}"
    const val mockito = "org.mockito:mockito-core:${Versions.MOCKITO}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.MOCKITO}"
    const val RoboElectric = "org.robolectric:robolectric:${Versions.RoboElectric}"

}

object AndroidTestingLib {
    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_CORE = "androidx.test:core:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}

object RETROFIT {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_JSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val RETROFIT_MOSHI_CONVERTER =
        "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
}

object DAGGER {
    const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
    const val DAGGER_ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:${Versions.DAGGER}"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${Versions.DAGGER}"
    const val DAGGER_ANNOTATION = "com.google.dagger:dagger-android-processor:${Versions.DAGGER}"
    const val DAGGER_KAPT = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"

}


object RX {
    const val RXANDROID = "io.reactivex.rxjava2:rxandroid:${Versions.RXANDROID}"
    const val RXJAVA = "io.reactivex.rxjava2:rxjava:${Versions.RXJAVA}"
    const val RXKOTLIN = "io.reactivex.rxjava2:rxkotlin:${Versions.RXKOTLIN}"
    const val RETROFIT = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RX_RETROFIT}"
}

object LOGGING_INTERCEPTORS {
    const val LOGGING_INTERCEPTORS =
        "com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING_INTERCEPTORS}"
}

object PAGING {
    const val PAGING_RUNTIME = "androidx.paging:paging-runtime-ktx:${Versions.PAGING_3}"
    const val PAGING_COMMON = "androidx.paging:paging-common-ktx:${Versions.PAGING_3}"
    const val PAGING_RX = "androidx.paging:paging-rxjava2:${Versions.PAGING_3}"
}

object NAVIGATION {
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
}


object GLIDE {
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_ANO = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
}


