plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = Configs.namespace
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    flavorDimensions.add("version")

    productFlavors {
        create("qa"){
            dimension = "version"
            manifestPlaceholders["appName"] = Configs.testAppName
            applicationId = Configs.qaApplicationId
            buildConfigField(
                "String",
                "DATABASE_NAME",
                "\"HARMONY_HUB_DB_QA\""
            )
            buildConfigField(
                "int",
                "DATABASE_VERSION_CODE",
                "1"
            )
            buildConfigField(
                "String",
                "SERVICE_URL",
                "\"https://itunes.apple.com\""
            )
        }

        create("prod"){
            dimension = "version"
            manifestPlaceholders["appName"] = Configs.prodAppName
            applicationId = Configs.applicationId
            buildConfigField(
                "String",
                "DATABASE_NAME",
                "\"HARMONY_HUB_DB\""
            )
            buildConfigField(
                "int",
                "DATABASE_VERSION_CODE",
                "1"
            )
            buildConfigField(
                "String",
                "SERVICE_URL",
                "\"https://itunes.apple.com\""
            )
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(Dependencies.appLibraries)
    testImplementation(Dependencies.testLibraries)
    androidTestImplementation(Dependencies.androidTestLibraries)
    debugImplementation(Dependencies.debugLibraries)
    annotationProcessor(Dependencies.annotationLibraries)
    kapt(Dependencies.kaptLibraries)
}

kapt {
    correctErrorTypes = true
}