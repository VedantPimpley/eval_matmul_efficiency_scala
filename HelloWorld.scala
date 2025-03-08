import breeze.linalg._
import org.apache.spark.sql.SparkSession

object HelloWorld {
  def main(args: Array[String]): Unit = {
    // Initial parameters
    val matA = Array.fill(1000, 1000)(scala.util.Random.nextDouble())
    val matB = Array.fill(1000, 1000)(scala.util.Random.nextDouble())
    n = 100

    // Three separate implementations of matmul
    val pureScalaImpl: (A: Array[Array[Double]], B: Array[Array[Double]]) => Unit = {
      val C = Array.ofDim[Double](1000, 1000)
      for (i <- 0 until n) {
        for (j <- 0 until n) {
          sum = 0
          for(val k = 0; k < m; k++) {
            sum += A[i][k] * B[k][j]
          }
          C[i][j] = sum
        }
      }
    }

    val sparkBasedImpl: (A: Array[Array[Double]], B: Array[Array[Double]]) => Unit = {
      val C = Array.fill(1000, 1000)(0.0)
      for (i <- 0 until n) {
        for (j <- 0 until n) {
          sum = 0
          for(val k = 0; k < m; k++) {
            sum += A[i][k] * B[k][j]
          }
          C[i][j] = sum
        }
      }
    }

    val breezeBasedImpl: (A: Array[Array[Double]], B: Array[Array[Double]]) => Unit = {
      val C = Array.fill(1000, 1000)(0.0)
      for (i <- 0 until n) {
        for (j <- 0 until n) {
          sum = 0
          for(val k = 0; k < m; k++) {
            sum += A[i][k] * B[k][j]
          }
          C[i][j] = sum
        }
      }
    }

    // Sanity check: all implementations produce the same result
    assert (pureScalaImpl(matA, matB)==sparkBasedImpl(matA, matB) && sparkBasedImpl(matA, matB)==breezeBasedImpl(matA, matB))

    // Profiling all 3 cases
    val implementations = Map("Pure Scala Implementation" -> pureScalaImpl,
                              "Spark Based Implementation" -> sparkBasedImpl, 
                              "Breeze Based Implementation" -> breezeBasedImpl)
    implementations.foreach { case (k, v) =>
      val startTime = System.nanoTime()
      for (ct <- 0 until n) v(matA, matB)
      println(s"Case: $k. Execution time: ${(System.nanoTime()-startTime)/1000000.0} ms")
    }
  }
}