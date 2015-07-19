import com.mojolly.scalate.ScalatePlugin.ScalateKeys._

organization := "com.github.dolphineor"
name := "scalatra-examples"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.11.7"

javacOptions ++= Seq(
  "-source", "1.8",
  "-target", "1.8",
  "-Xlint:unchecked"
)

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-unchecked"
)

resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("releases"),
  "maven repo" at "https://repo1.maven.org/maven2/",
  "typesafe repo" at "https://repo.typesafe.com/typesafe/releases/",
  "scalaz repo" at "https://dl.bintray.com/scalaz/releases/",
  "sbt-plugin repo" at "https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"
)

libraryDependencies ++= {
  val ScalatraVersion = "2.4.0-RC2-2"
  val metricsVersion = "3.0.2"
  Seq(
    "org.scalatra" %% "scalatra" % ScalatraVersion,
    "org.scalatra" %% "scalatra-auth" % ScalatraVersion,
    "org.scalatra" %% "scalatra-json" % ScalatraVersion,
    "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
    "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
    "org.scala-lang.modules" %% "scala-xml" % "1.0.4",
    "com.typesafe" % "config" % "1.3.0",
    "com.typesafe.slick" %% "slick" % "3.0.0",
    "ch.qos.logback" % "logback-classic" % "1.1.3" % "runtime",
    "com.codahale.metrics" % "metrics-core" % metricsVersion,
    "com.codahale.metrics" % "metrics-healthchecks" % metricsVersion,
    "com.zaxxer" % "HikariCP" % "2.3.8",
    "mysql" % "mysql-connector-java" % "5.1.36",
    "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided,container",
    "org.slf4j" % "slf4j-api" % "1.7.12" % "runtime",
    "org.json4s" %% "json4s-native" % "3.3.0.RC3",
    "org.javassist" % "javassist" % "3.20.0-GA"
  )
}

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

enablePlugins(JettyPlugin)
containerLibs := {
  val jettyVersion = "9.3.1.v20150714"
  Seq(
    "org.eclipse.jetty" % "jetty-runner" % jettyVersion intransitive(),
    "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container",
    "org.eclipse.jetty" % "jetty-plus" % jettyVersion % "container"
  )
}

containerConfigFile := Some(file("./src/main/webapp/WEB-INF/jetty-env.xml"))
containerPort := 8081

scalateTemplateConfig in Compile <<= (sourceDirectory in Compile) { base =>
  Seq(
    TemplateConfig(
      base / "webapp" / "WEB-INF" / "templates",
      Seq.empty, /* default imports should be added here */
      Seq(
        Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
      ), /* add extra bindings here */
      Some("templates")
    )
  )
}