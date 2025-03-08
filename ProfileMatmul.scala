import breeze.linalg._
import org.apache.spark.sql.SparkSession

object ProfileMatmul {
  def main(args: Array[String]): Unit = {
    /*  
    Implements mat_mul in three different ways and profiles their performance.
    */

    // Initial parameters
    val n = 10
    val matA = Array.fill(1000, 1000)(scala.util.Random.nextDouble())
    val matB = Array.fill(1000, 1000)(scala.util.Random.nextDouble())

    // Preprocessed values
    val denseMatA = new DenseMatrix(1000, 1000, matA.flatten)
    val denseMatB = new DenseMatrix(1000, 1000, matB.flatten)
    var matC1 = Array.ofDim[Double](1000, 1000)
    var denseMatC2 = new DenseMatrix(1000, 1000, new Array[Double](1000 * 1000))
    var denseMatC3 = new DenseMatrix(1000, 1000, new Array[Double](1000 * 1000))
    
    // Method 1: Pure Scala
    var startTime = System.currentTimeMillis()
    for (ct <- 0 until n) {
      for (i <- 0 until 1000) {
        for (j <- 0 until 1000) {
          var sum = 0.0
          for(k <- 0 until 1000) {
            sum += matA(i)(k)*matB(k)(j)
          }
          matC1(i)(j) = sum
        }
      }
    }
    var endTime = System.currentTimeMillis()
    println(s"Pure Scala implementation took ${(endTime-startTime)/n} ms")

    /*
    // Method 2: Scala+Spark
    val spark = SparkSession.builder()
      .appName("MatrixMultiplication")
      .master("local[*]")
      .getOrCreate()
    startTime = System.currentTimeMillis()
    for (ct <- 0 until n) {
      denseMatC2 = denseMatA.multiply(denseMatB)
    }
    endTime = System.currentTimeMillis()
    println(s"Scala+Spark implementation took ${(endTime-startTime)/n} ms")
    spark.stop()

    // Method 3: Scala+Breeze
    startTime = System.currentTimeMillis()
    for (ct <- 0 until n) {
      denseMatC3 = denseMatA * denseMatB
    }
    endTime = System.currentTimeMillis()
    println(s"Scala+Breeze implementation took ${(endTime-startTime)/n} ms")

    // All implementations should produce the same result, within acceptable tolerance
    val denseMatC1 = new DenseMatrix(1000, 1000, matC1.flatten)
    val tolerance = 1e-3
    assert (max(denseMatC1-denseMatC2) <= tolerance && max(denseMatC2-denseMatC3) <= tolerance)
    */
  }
}