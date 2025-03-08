import breeze.linalg._
import breeze.linalg.{DenseMatrix => BreezeDenseMatrix}
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.linalg.{DenseMatrix => SparkDenseMatrix}
import scala.util.Random

object ProfileMatmul {
  def main(args: Array[String]): Unit = {
    /*  
    Implement mat_mul in three different ways and profile their performance.
    */

    // Initial parameters
    val n = 10
    val matA = Array.fill(1000, 1000)(scala.util.Random.nextDouble())
    val matB = Array.fill(1000, 1000)(scala.util.Random.nextDouble())

    // Auxiliary variables
    
    var startTime = System.currentTimeMillis()
    var endTime = System.currentTimeMillis()

    // Method 1: Pure Scala
    val multiplyMatrices = (matA: Array[Array[Double]], matB: Array[Array[Double]]) => {
      val matC1 = Array.ofDim[Double](1000, 1000)
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
    startTime = System.currentTimeMillis()
    for (ct <- 0 until n) {
      multiplyMatrices(matA, matB)
    }
    endTime = System.currentTimeMillis()
    println(s"Pure scala implementation took ${(endTime-startTime)/n} ms")

    /**/
    // Method 2: Scala+Spark
    val sparkMatA = new SparkDenseMatrix(1000, 1000, matA.flatten)
    val sparkMatB = new SparkDenseMatrix(1000, 1000, matB.flatten)
    val spark = SparkSession.builder().appName("MatrixMultiplication").master("local[*]").getOrCreate()
    val sc = spark.sparkContext
    startTime = System.currentTimeMillis()
    for (ct <- 0 until n) {
      sparkMatA.multiply(sparkMatB)
    }
    endTime = System.currentTimeMillis()
    println(s"Spark-based implementation took ${(endTime-startTime)/n} ms")
    spark.stop()
    
    // Method 3: Scala+Breeze
    val breezeMatA = new BreezeDenseMatrix(1000, 1000, matA.flatten)
    val breezeMatB = new BreezeDenseMatrix(1000, 1000, matB.flatten)
    startTime = System.currentTimeMillis()
    for (ct <- 0 until n) {
      breezeMatA*breezeMatB
    }
    endTime = System.currentTimeMillis()
    println(s"Breeze-based implementation took ${(endTime-startTime)/n} ms")
  }
}