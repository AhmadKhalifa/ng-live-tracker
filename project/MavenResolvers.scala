import sbt._

object MavenResolvers {

  val elmenusDev = "Elmenus Dev" at "s3://maven.elmenus.com/dev"
  val elmenusTest = "Elmenus Test" at "s3://maven.elmenus.com/test"
  val elmenusProduction = "Elmenus Production" at "s3://maven.elmenus.com/production"

  def elmenusResolver(env: String) = {
    Seq(
      if (env == "DEV")
        elmenusDev
      else if (env == "TEST")
        elmenusTest
      else if (env == "PRODUCTION")
        elmenusProduction
      else
        throw new IllegalStateException(
          s"BUILD_ENV environment variable not correct $env"
        )
    )
  }

}
