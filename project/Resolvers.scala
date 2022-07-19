import sbt._

object Resolvers {

  val elmenusSnapshots = "Elmenus Snapshots" at "s3://maven.elmenus.com/snapshots"
  val elmenusReleases = "Elmenus Releases" at "s3://maven.elmenus.com/releases"

  val elmenusResolvers = Seq(elmenusSnapshots, elmenusReleases)

}
