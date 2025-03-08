import breeze.linalg._
import org.apache.spark.sql.SparkSession

object HelloWorld {
  def main(args: Array[String]): Unit = {
    /*  
    Implements mat_mul in three different ways and profiles their performance.
    */

    // Initial parameters
    n = 100
    val matA = Array.fill(1000, 1000)(scala.util.Random.nextDouble())
    val matB = Array.fill(1000, 1000)(scala.util.Random.nextDouble())
    val denseMatA = new DenseMatrix(1000, 1000, matA.flatten)
    val denseMatB = new DenseMatrix(1000, 1000, matB.flatten)

    // Method 1: Pure Scala
    val matC1 = Array.ofDim[Double](1000, 1000)
    val startTime = System.currentTimeMillis()
    for (ct <- 0 until n) {
      val matC = Array.ofDim[Double](1000, 1000)
      for (i <- 0 until 1000) {
        for (j <- 0 until 1000) {
          sum = 0
          for(k <- 0 until 1000) { 
            sum += A[i][k] * B[k][j]
          }
          matC1[i][j] = sum
        }
      }
    }
    val endTime = System.currentTimeMillis()
    println(s"Pure Scala implementation took ${(endTime-startTime)/1000.0} ms")

    // Method 2: Scala+Spark
    val denseMatC2 = new DenseMatrix(1000, 1000, new Array[Double](1000 * 1000))
    startTime = System.currentTimeMillis()
    for (ct <- 0 until n) {
      denseMatC2 = matrixA.multiply(matrixB)
    }
    endTime = System.currentTimeMillis()
    println(s"Scala+Spark implementation took ${(endTime-startTime)/1000.0} ms")

    // Method 3: Scala+Breeze
    val denseMatC3 = new DenseMatrix(1000, 1000, new Array[Double](1000 * 1000))
    startTime = System.currentTimeMillis()
    for (ct <- 0 until n) {
      denseMatC3 = denseMatA * denseMatB
    }
    endTime = System.currentTimeMillis()
    println(s"Scala+Breeze implementation took ${(endTime-startTime)/1000.0} ms")

    // All implementations should produce the same result, within acceptable tolerance
    val denseMatC1 = new DenseMatrix(1000, 1000, matC1.flatten)
    val tolerance = 1e-3
    assert (denseMatC1-denseMatC2 <= tolerance && denseMatC2-denseMatC3 <= tolerance)
  }
}