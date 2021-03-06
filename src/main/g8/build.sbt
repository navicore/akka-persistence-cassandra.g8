name := "$name;format="Camel"$"

fork := true
javaOptions in test ++= Seq(
  "-Xms512M", "-Xmx2048M",
  "-XX:MaxPermSize=2048M",
  "-XX:+CMSClassUnloadingEnabled"
)

parallelExecution in test := false

version := "1.0"

ensimeScalaVersion in ThisBuild := "$scalaVersion$"
scalaVersion := "$scalaVersion$"
val akkaVersion = "$akkaVersion$"
val akkaHttpVersion = "$akkaHttpVersion$"
val cassandraPluginsVersion = "$cassandraPluginsVersion$"

libraryDependencies ++= Seq(
    "ch.megard" %% "akka-http-cors" % "0.2.1",

    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "com.typesafe" % "config" % "1.2.1",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",

    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,

    // for mongodb api - works for cosmosdb
    // "com.github.scullxbones" %% "akka-persistence-mongo-casbah" % "2.0.4",
    // "org.mongodb" %% "casbah" % "3.1.1",

    "com.typesafe.akka" %% "akka-persistence-cassandra" % cassandraPluginsVersion,
    "com.typesafe.akka" %% "akka-persistence-cassandra-launcher" % cassandraPluginsVersion % "test",

    "org.json4s" %% "json4s-native" % "3.5.3",
    "com.github.nscala-time" %% "nscala-time" % "2.16.0",

    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )

dependencyOverrides ++= Seq(
  "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion
)

mainClass in assembly := Some("$package$.Main")
assemblyJarName in assembly := "$name;format="Camel"$.jar"

assemblyMergeStrategy in assembly := {
  case PathList("reference.conf") => MergeStrategy.concat
  case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
  case PathList("META-INF", _ @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

