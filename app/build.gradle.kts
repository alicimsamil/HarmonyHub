plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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
                "\"https://itunes.apple.com/\""
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
                "\"https://itunes.apple.com/\""
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.appLibraries)
    testImplementation(Dependencies.testLibraries)
    androidTestImplementation(Dependencies.androidTestLibraries)
}