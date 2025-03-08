name := "ca3"
version := "0.1"
scalaVersion := "2.12.15"

val sparkVersion = "3.5.0"

libraryDependencies ++= Seq(
  // Breeze
  "org.scalanlp" %% "breeze" % "2.1.0",
  "org.scalanlp" %% "breeze-natives" % "2.1.0",
  "org.scalanlp" %% "breeze-viz" % "2.1.0",
  
  // Spark
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion
)

// Resolve dependency conflicts
ThisBuild / conflictManager := ConflictManager.latestRevision

// Add Netlib dependency
libraryDependencies += "com.github.fommil.netlib" % "all" % "1.1.2"