name := "scala-breeze-spark-app"
version := "0.1"
scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  // Breeze - Numerical Processing
  "org.scalanlp" %% "breeze" % "2.1.0",
  "org.scalanlp" %% "breeze-natives" % "2.1.0",
  "org.scalanlp" %% "breeze-viz" % "2.1.0",
  
  // Spark Core
  "org.apache.spark" %% "spark-core" % "3.3.2",
  "org.apache.spark" %% "spark-sql" % "3.3.2"
)

// Resolve dependency conflicts
ThisBuild / conflictManager := ConflictManager.latestRevision