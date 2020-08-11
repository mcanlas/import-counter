import sbt.Keys._
import sbt._

object ProjectPlugin extends AutoPlugin {
  override def trigger = allRequirements

  override lazy val projectSettings = Seq(
    scalaVersion := "2.13.3"
  )

  object autoImport {
    implicit class ProjectOps(p: Project) {
      def withCats: Project =
        p
          .settings(libraryDependencies += "org.typelevel" %% "cats-core" % "2.2.0-RC2")

      def withEffectMonad: Project =
        p.settings(libraryDependencies += "org.typelevel" %% "cats-effect" % "2.1.4")

      def withFileIO: Project =
        p.settings(libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.9.1")

      def withTesting: Project =
        p.settings(libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.1" % "test")
    }
  }
}
