/**
 * This class keeps dependencies.
 */
object Dependencies {

    //app
    private const val materialLib= "com.google.android.material:material:${Versions.materialLibVersion}"
    private const val coreKtxLib = "androidx.core:core-ktx:${Versions.coreLibVersion}"
    private const val appCompatLib = "androidx.appcompat:appcompat:${Versions.appcompatLibVersion}"
    private const val constraintLayoutLib = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    private const val viewModelLib = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelVersion}"
    private const val activityLib = "androidx.activity:activity-ktx:${Versions.activityVersion}"

    //Navigation
    private const val navigationFragmentLib = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    private const val navigationUiLib = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"

    //Coroutines
    private const val coroutinesLib = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    private const val coroutinesTestLib = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"

    //test
    private const val testJUnitLib = "junit:junit:${Versions.testJunitVersion}"
    private const val testExtJUnitLib = "androidx.test.ext:junit:${Versions.testExtJunitVersion}"
    private const val testEspressoCoreLib = "androidx.test.espresso:espresso-core:${Versions.testEspressoVersion}"

    val appLibraries = arrayListOf<String>().apply {
        add(coreKtxLib)
        add(appCompatLib)
        add(constraintLayoutLib)
        add(materialLib)
        add(retrofit)
        add(retrofitConverter)
        add(viewModelLib)
        add(activityLib)
        add(navigationUiLib)
        add(navigationFragmentLib)
        add(coroutinesLib)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(testJUnitLib)
        add(coroutinesTestLib)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(testExtJUnitLib)
        add(testEspressoCoreLib)
    }
}