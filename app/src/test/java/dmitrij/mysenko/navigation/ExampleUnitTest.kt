package dmitrij.mysenko.navigation

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun pow3() {
        val result = IntArray(10) { 0 }
        for (i in 0L..2020L) {
            val bucket = i*i*i%10
            result[bucket.toInt()] += 1
        }
        println(result.joinToString())//0,1,8,7,4,5,6,3,2,9
    }

    @Test
    fun pow2() {
        val result = IntArray(10) { 0 }
        for (i in 0L..2020L) {
            val bucket = i*i%10
            result[bucket.toInt()] += 1
        }
        println(result.joinToString())//1,4,9,6,5,0
    }

    @Test
    fun koef12(){
        val result = IntArray(10) { 0 }
        for (i in 0L..2020L) {
            val bucket = 12*i%10
            result[bucket.toInt()] += 1
        }
        println(result.joinToString())
    }
}