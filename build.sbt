
name := "number-munchers"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

mainClass in (Compile, run) := Some("nmunch.Main")

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "org.scalaz"        %% "scalaz-core"       % "7.1.7",
  "org.scalaz"        %% "scalaz-effect"     % "7.1.7",
  "org.scalaz"        %% "scalaz-concurrent" % "7.1.7",
  "org.scalaz.stream" %% "scalaz-stream"     % "0.8",
  "org.scalatest"     %% "scalatest"         % "2.2.6"   % "test"
)

