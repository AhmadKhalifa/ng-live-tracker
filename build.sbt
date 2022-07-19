import Dependencies._
import MavenResolvers.elmenusResolver
import sbt.Keys.{libraryDependencies, scalaVersion}

// Build Settings shared with all sub-projects
lazy val buildSettings = Seq(
  scalaVersion := "2.13.8",
  version := "0.1",
  organization := "com.elmenus",
  organizationName := "elmenus",
  javacOptions ++= Seq("-source", "8", "-target", "8", "-Xlint"),
  scalacOptions ++= Seq(
    "-deprecation", // Emit warning and location for usages of deprecated APIs.
    "-encoding",
    "utf-8", // Specify character encoding used by source files.
    "-explaintypes", // Explain type errors in more detail.
    "-feature", // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
    "-language:experimental.macros", // Allow macro definition (besides implementation and application)
    "-language:higherKinds", // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-unchecked" // Enable additional warnings where generated code depends on assumptions.
  ),
  datadogApmVersion := "0.83.2",
  datadogServiceName := "elmenus-ng-live-tracker",
  datadogAgentHost := "${DD_AGENT_HOST}",
  datadogAgentPort := "${MY_DD_PORT}",
  datadogEnableAkkaHttp := true,
  datadogEnableDebug := false,
  sonarScan / aggregate := false
)

lazy val commonDependencies = Seq(
  scalaTestPlus,
  scalaMockito,
  akkaStream,
  akkaTestKit,
  akkaStreamTestkit,
  log4JCore,
  log4J,
  jodaTime,
  cats,
  datadog
)

lazy val domainDependencies = Seq(

)

lazy val dataDependencies = Seq(
  mysql,
  playSlick,
  playSlickEvolutions,
  slickJodaMapper
)

lazy val gatewayDependencies = Seq(
  akkaKafkaStream,
  playJson,
  playJsonJoda,
  playMockWs
)

lazy val root = (project in file("."))
  .aggregate(base, domain, data, gateway)
  .disablePlugins(PlayLayoutPlugin)
  .enablePlugins(PlayScala)
  .enablePlugins(DatadogAPM)
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(JavaAgent)
  .settings(
    buildSettings,
    name := "elmenus-ng-live-tracker",
    PlayKeys.playDefaultPort := 9000,
    javaAgents += datadog
  )

// Base module - visible to all modules
lazy val base = (project in file("modules/base"))
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(DatadogAPM)
  .settings(
    buildSettings,
    name := "elmenus-ng-live-tracker-base",
    //Shared dependencies for all modules
    resolvers ++= elmenusResolver(sys.env.getOrElse("BUILD_ENV", "DEV")),
    libraryDependencies ++= commonDependencies
  )

// Domain module - contains domain models, business logic, and traits for dependant modules
lazy val domain = (project in file("modules/domain"))
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(DatadogAPM)
  .dependsOn(base)
  .settings(
    buildSettings,
    name := "elmenus-ng-live-tracker-domain",
    libraryDependencies ++= domainDependencies
  )

// Data module - contains repositories implementations, DAOs, and DB models
lazy val data = (project in file("modules/data"))
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(DatadogAPM)
  .dependsOn(domain)
  .settings(
    buildSettings,
    name := "elmenus-ng-live-tracker-data",
    libraryDependencies ++= dataDependencies
  )

// Gateway module - contains implementations for all outer world logic (e.g. controllers, publishers ...etc.)
lazy val gateway = (project in file("modules/gateway"))
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(DatadogAPM)
  .dependsOn(domain)
  .settings(
    buildSettings,
    name := "elmenus-ng-live-tracker-gateway",
    libraryDependencies ++= gatewayDependencies
  )

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true,
    scalafmtTestOnCompile := true,
    scalafmtVersion := "1.2.0"
  )
