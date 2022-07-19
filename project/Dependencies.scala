import sbt._

object Dependencies {
  val scalaTestPlus       = "org.scalatestplus.play"   %% "scalatestplus-play"    % "5.1.0"
  val akkaKafkaStream     = "com.typesafe.akka"        %% "akka-stream-kafka"     % "2.1.0"
  val akkaStream          = "com.typesafe.akka"        %% "akka-stream"           % "2.6.14"
  val akkaTestKit         = "com.typesafe.akka"        %% "akka-testkit"          % "2.6.14"
  val akkaStreamTestkit   = "com.typesafe.akka"        %% "akka-stream-testkit"   % "2.6.14"
  val mysql               = "mysql"                     % "mysql-connector-java"  % "8.0.15"
  val playSlick           = "com.typesafe.play"        %% "play-slick"            % "5.0.0"
  val playSlickEvolutions = "com.typesafe.play"        %% "play-slick-evolutions" % "5.0.0"
  val playJson            = "com.typesafe.play"        %% "play-json"             % "2.9.2"
  val redisScala          = "com.github.etaty"         %% "rediscala"             % "1.9.0"
  val log4J               = "org.apache.logging.log4j"  % "log4j-api"             % "2.17.1"
  val log4JCore           = "org.apache.logging.log4j"  % "log4j-core"            % "2.17.1"
  val jodaTime            = "joda-time"                 % "joda-time"             % "2.10.10"
  val cats                = "org.typelevel"            %% "cats-core"             % "2.1.1"
  val scalaMockito        = "org.scalatestplus"        %% "mockito-3-4"           % "3.2.9.0"
  val slickJodaMapper     = "com.github.tototoshi"     %% "slick-joda-mapper"     % "2.4.2"
  val playMockWs          = "de.leanovate.play-mockws" %% "play-mockws"           % "2.8.1"
  val playJsonJoda        = "com.typesafe.play"        %% "play-json-joda"        % "2.10.0-RC6"
  val datadog             = "com.datadoghq"             % "dd-java-agent"         % "0.7.0"
}
