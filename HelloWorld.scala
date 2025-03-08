import breeze.linalg._
import breeze.numerics._
import breeze.stats._

object HelloWorldNoPlot {
  def main(args: Array[String]): Unit = {
    // Create vectors
    val v1 = DenseVector(1.0, 2.0, 3.0, 4.0, 5.0)
    val v2 = DenseVector(5.0, 4.0, 3.0, 2.0,  1.0)
    
    // Vector operations
    val sum_result = sum(v1)
    val mean_result = mean(v1)
    val dot_product = v1 dot v2
    val element_mult = v1 *:* v2
    val v3 = v1 + v2
    
    // Print results
    println(s"Hello World from Breeze!")
    println(s"Vector v1: $v1")
    println(s"Vector v2: $v2") 
    println(s"Sum of v1: $sum_result")
    println(s"Mean of v1: $mean_result")
    println(s"Dot product of v1 and v2: $dot_product")
    println(s"Element-wise multiplication: $element_mult")
    println(s"v1 + v2: $v3")
    
    // Matrix operations
    val m1 = DenseMatrix((1.0, 2.0, 3.0), (4.0, 5.0, 6.0))
    val m2 = DenseMatrix((7.0, 8.0, 9.0), (10.0, 11.0, 12.0))
    
    println(s"\nMatrix m1:\n$m1")
    println(s"Matrix m2:\n$m2")
    println(s"m1 * m2.t:\n${m1 * m2.t}")
    
    // Statistical operations
    val data = DenseVector.rand(100)
    println(s"\nStatistics for random data:")
    println(s"Mean: ${mean(data)}")
    println(s"Variance: ${variance(data)}")
    println(s"Standard deviation: ${stddev(data)}")
  }
}