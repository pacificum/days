name := "light"
version := "0.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "3.3.1"

libraryDependencies ++= Seq(
  guice,
  ws,
  "org.webjars" %% "webjars-play" % "3.0.1",
  "org.webjars" % "jquery" % "3.7.1",
  "org.webjars" % "font-awesome" % "6.5.1" exclude("org.webjars", "bootstrap"),
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
)

JsEngineKeys.engineType := JsEngineKeys.EngineType.Trireme

// Apply digest calculation and gzip compression to assets
pipelineStages := Seq(digest, gzip)

// Adds additional packages into Twirl
TwirlKeys.templateImports ++= Seq(
  "light.pages._",
  "light.days._",
  "light.util._"
)

// Adds additional packages into conf/routes
play.sbt.routes.RoutesKeys.routesImport += "controllers._"
