organization := "com.azavea.raster-foundry"
name := "rf-tiler"
version := Version.tiler

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-language:existentials"
)

licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

scalaVersion := Version.scala

// raise memory limits here if necessary
javaOptions += "-Xmx5G"

mainClass in (Compile, run) := Some("com.azavea.rasterfoundry.Main")

fork := true
connectInput in run := true

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % Version.spark,
  "com.azavea.geotrellis" %% "geotrellis-spark" % Version.geotrellis,
  "org.apache.commons" % "commons-io" % "1.3.2",
  "io.spray" %% "spray-json" % "1.3.1",
  "com.typesafe" % "config" % "1.2.1",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.9.34",
  "com.amazonaws" % "aws-java-sdk-sqs" % "1.9.34",
  "com.azavea.geotrellis" %%  "geotrellis-testkit" % Version.geotrellis % "test",
  "com.github.scopt" %% "scopt" % "3.3.0",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "org.scalatest" %%  "scalatest" % "2.2.0" % "test"
)

resolvers += Resolver.bintrayRepo("azavea", "geotrellis")
resolvers += Resolver.bintrayRepo("scalaz", "releases")
resolvers += "OpenGeo" at "https://boundless.artifactoryonline.com/boundless/main"

test in assembly := {}

assemblyMergeStrategy in assembly := {
  case "reference.conf" => MergeStrategy.concat
  case "application.conf" => MergeStrategy.concat
  case "META-INF/MANIFEST.MF" => MergeStrategy.discard
  case "META-INF\\MANIFEST.MF" => MergeStrategy.discard
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.discard
  case "META-INF/ECLIPSEF.SF" => MergeStrategy.discard
  case _ => MergeStrategy.first
}
