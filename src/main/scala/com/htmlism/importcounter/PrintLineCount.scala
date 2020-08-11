package com.htmlism.importcounter

import better.files._
import cats.effect._
import cats.implicits._

object PrintLineCount extends PrintLineCount[IO] with IOApp

class PrintLineCount[F[_] : Sync] {
  def run(args: List[String]): F[ExitCode] =
    toFiles(args.head)
      .map { f =>
        val importLineCount =
          f
            .lines
            .count(_.startsWith("import"))

        Summary(f.name, importLineCount)
      }
      .sortBy(-_.count)
      .traverse { sum =>
        Sync[F].delay {
          println(f"${sum.count}%2d" + " " + sum.name)
        }
      }
      .as(ExitCode.Success)

  def toFiles(path: String): List[File] =
    File(path)
      .listRecursively
      .toList
      .filter(_.`extension`.exists(_ == ".scala"))
}

case class Summary(name: String, count: Int)