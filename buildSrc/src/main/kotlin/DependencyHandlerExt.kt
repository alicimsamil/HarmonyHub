import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * This function adds implementation dependencies sequentially.
 */
fun DependencyHandler.implementation(list: List<String>?) {
    list?.forEach { dependency ->
        add("implementation", dependency)
    }
}

/**
 * This function adds androidTestImplementation dependencies sequentially.
 */
fun DependencyHandler.androidTestImplementation(list: List<String>?) {
    list?.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

/**
 * This function adds testImplementation dependencies sequentially.
 */
fun DependencyHandler.testImplementation(list: List<String>?) {
    list?.forEach { dependency ->
        add("testImplementation", dependency)
    }
}
