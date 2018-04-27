package com.example

import java.util.concurrent.{Executor, Executors}

import scala.concurrent.ExecutionContext.Implicits
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, impl}

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }
}

object pool{
  implicit def global:ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
}
