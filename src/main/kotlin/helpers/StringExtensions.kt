package helpers

import kotlin.math.abs
import kotlin.random.Random

fun String.appendRandomNumericPostfix(separator: String = "_"): String {
    return "${this}${separator}${abs(Random.nextInt())}"
}