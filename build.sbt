name := "fp-to-the-max"

version := "0.1"

scalaVersion := "2.12.7"
//
//scalacOptions ++= Seq(
//  "-language:higherKinds",
//  "-deprecation",
//  "-Ypartial-unification",
//  "-encoding",
//  "UTF-8",
//  "-explaintypes",
//  "-unchecked",
//  "-feature",
//  "-language:implicitConversions",
//  "-language:postfixOps",
//  "-Ywarn-dead-code",
//  "-Ywarn-extra-implicit",
//  "-Ywarn-inaccessible",
//  "-Ywarn-infer-any",
//  "-Ywarn-nullary-override",
//  "-Ywarn-nullary-unit",
//  "-Ywarn-numeric-widen",
//  "-Ywarn-unused:implicits",
//  "-Ywarn-unused:imports",
//  "-Ywarn-unused:locals",
//  "-Ywarn-unused:params",
//  "-Ywarn-unused:patvars",
//  "-Ywarn-unused:privates",
//  "-Ywarn-value-discard",
//  "-Yno-adapted-args",
//  "-Xlint"
//)

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full
)

libraryDependencies += "com.github.mpilquist" %% "simulacrum" % "0.14.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
