name := "prezentare"

organization in ThisBuild := "com.bionicspirit"

scalaVersion in ThisBuild := "2.10.3"

compileOrder in ThisBuild := CompileOrder.JavaThenScala

scalacOptions in ThisBuild ++= Seq(
  "-unchecked", "-deprecation", "-feature",
  "-target:jvm-1.6"
)

resolvers ++= Seq(
  "BionicSpirit Releases" at "http://maven.bionicspirit.com/releases/",
  "BionicSpirit Snapshots" at "http://maven.bionicspirit.com/snapshots/",
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
  "Spray Releases" at "http://repo.spray.io",
  "Spy" at "http://files.couchbase.com/maven2/"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.3",
  "junit" % "junit" % "4.10" % "test"
)
