import breeze.linalg._
import org.apache.spark.sql.SparkSession

object HelloWorld {
  def main(args: Array[String]): Unit = {
    // Breeze Hello World
    val v = DenseVector(1.0, 2.0, 3.0, 4.0, 5.0)
    println(s"Hello World from Breeze! Sum of vector: ${sum(v)}")
    
    // Spark Hello World
    val spark = SparkSession.builder()
      .appName("MinimalHelloWorld")
      .master("local[*]")
      .getOrCreate()
      
    val data = Seq(("Hello", "World"), ("from", "Spark"))
    val df = spark.createDataFrame(data).toDF("word1", "word2")
    
    println("Hello World from Spark!")
    df.show()
    
    spark.stop()
  }
}