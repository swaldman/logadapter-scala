# logadapter-scala

## Table of Contents

  * [Quick Start](#quick-start)
  * [Full Start](#full-start)
  * [API](#api)
  * [Configuration](#configuration)
  * [ZIO integration](#zio-integration)
  * [History](#history)
  * [Acknowledgements](#acknowledgements)

## Quick Start

```scala
import logadapter.jul.Api.*

object MyObject extends SelfLogging:
  def doSomething() : Unit =
    INFO.log("Something has been done.")
```

## Full Start

1. Choose a logging backend.

   Currently `jul` (java.util.logging),
   [`scribe`](https://github.com/outr/scribe/), [`mlog`](#History), [`slf4j`](https://www.slf4j.org/),
   and `stderr`, a simple standard-error backend, are supported.

   For `jul` and `stderr`, the dependency you'll need is just

   * sbt:  `libraryDependencies += "com.mchange" %% "logadapter-scala" % "<version>"`
   * mill: `ivy"com.mchange::logadapter-scala:<version>"`

   For `scribe`, `mlog`, `log4j2`, `slf4j` or other backends, you'll need a library-appropriate
   dependency, like

   * sbt:  `libraryDependencies += "com.mchange" %% "logadapter-scala-scribe" % "<version>"`
   * mill: `ivy"com.mchange::logadapter-scala-scribe:<version>"`

   Look for the [latest version](https://central.sonatype.com/search?q=logadapter-scala) (which will be the same across all backends).

> [!Note]
> For logback, choose the `slf4j` backend, but make sure the [latest version of `logback-classic`](https://central.sonatype.com/artifact/ch.qos.logback/logback-classic) is
> in your `CLASSPATH`.

2. Each backend has its own package, in which there is an object
   called Api. Import the full API from that object:

   ```scala
   import logadapter.jul.Api.*

   // you might have chosen `scribe`, `mlog`, `log4j2`, `slf4j`, or `stderr` instead of `jul`
   ```

3. Mark your classes and objects with the trait `SelfLogging`
   (part of what you've imported). That brings in a `LogAdapter`
   as a `given`, enabling you to log inside the class or object
   at will:

   ```scala
   import logadapter.jul.Api.*

   object MyObject extends SelfLogging:
     def doSomething() : Int =
       TRACE.log("Entered "doingSomething()")
       INFO.log("I'm doing something!")
       WARNING.logDebug("Math is hard!")
       INFO.logEval("Computation")(3 + 7) // this logs "Computation: 10" and evaluates to 10
   ```

4. If you wish to be able to log outside of a `SelfLogging` class or object,
   you can explicitly bring in a `LogAdapter` from your Api as a `given`:

   ```scala
   import logadapter.jul.Api.*

   given LogAdapter = logAdapterFor( "com.mchange.my.logger.name" )

   def sunset() : Unit =
     INFO.log("The sun is setting.")
   ```

   `logAdapterFor` accepts a String logger name, or any `object` or instance,
   in which case the log name becomes the class name. (If a `Class` object
   is provided, the log name is the `classObject.getName()`)

   You can also use the source file name as your logger name, via

   ```scala
   given LogAdapter = logAdapterByFilename
   ```

> [!Tip]
> If you wish to log to the logger of a `SelfLogging` object outside of that object's scope,
> you can explicitly `import MySelfLoggingObject.logAdapter`, and the logging API will become available.

> [!Warning]
> It's best to let final classes and singleton `object`s extend the `SelfLogging` trait.
> If traits or classes that will be extended extend `SelfLogging, subclasses will be unable 
> to do so and log messages will be directed to the parent class logger.
>
> A useful pattern is to let companion `object`s extend `SelfLogging` and let the trait or class
> import the `object`'s `given` member `logAdapter`, as in the tip just above.

## API

The API is very simple. Supported log levels are

* `CONFIG`
* `DEBUG`
* `ERROR`
* `FATAL`
* `FINE`
* `FINER`
* `FINEST`
* `INFO`
* `SEVERE`
* `TRACE`
* `WARNING`

> [!Note]
> These levels may not map exactly to the levels of your logging backend. They get remapped to the most appropriate level your backend supports.

Once you've established a context with a logger (see Steps 3 and 4 above), you simply log on levels:

```scala
INFO.log("The sun'll come up tomorrow.")
```

You can also attach a Throwable to your logs (usually resulting in its stack trace getting logged).

```scala
   try
     // do some scary stuff here
   catch
     case t : Throwable =>
       WARNING.log("Something really bad happened!", t)
       throw t
```

If you want more debugging information (like the filename and line number from which
the message was logged). You can use `logDebug` instead if `log`:

```scala
WARNING.logDebug("No throwable.")
WARNING.logDebug("With throwable.", t)
```

> [!NOTE]
> Some backends (e.g. `scribe`) bring in filename and line number information by default. `logDebug` may be less useful with these backends.

Sometime for debugging purposes, you want to quickly have the value of an expression
logged. For that, there is the `logEval` method, or just `apply` your level:

```scala
val count = INFO(1 + 2 + 3) // 6 will be logged, and will become the value of count
```

If you want a prefix to help you interpret the printed expression, you can
use

```scala
val count = INFO.logEval(prefix = "count")(1 + 2 + 3) // 6 will be logged, and will become the value of count
```

That's it!

## Configuration

`logadapter-scala` does nothing to standardize configuration of backend libraries.
Whatever backend you choose, you'll have to supply its library-specific config files
or use its configuration API directly.

However, `logadapter-scala` Api objects are ordinary objects. If you are using
programmatic configuration, one way to ensure your Api is configured before you
begin to use it is define your own alias for it, and import that:

```scala
val LoggingApi =
  scribe.Logger
     .root
     .clearHandlers()
     .withHandler(minimumLevel = Some(scribe.Level.Info), formatter = scribe.format.Formatter.compact)
     .replace()
  logadapter.scribe.Api
```

Now, your configuration is centralized, easy to update or switch out. Elsewhere in your application, you just import from `LoggingApi`:

```scala
import LoggingApi.*
```

and you can be sure your logging has been configured before the API is accessed.

## ZIO integration

_In addition to your backend appropriate library,_ if you bring in...

* sbt:  `libraryDependencies += "com.mchange" %% "logadapter-scala-zio" % "<version>"`
* mill: `ivy"com.mchange::logadapter-scala-zio:<version>"`

you can log using your backend of choice to ZIO effects, and have ZIO effects log errors and defects
to your backend of choice.

(Yes, ZIO has its own native logging. But some of us don't love it.)

Setting up the API is a bit inelegant, due in part to a [compiler bug](https://github.com/scala/scala3/issues/23245)
that will hopefully get fixed soon. For now the setup looks like...

```scala
// workaround of nonexport of SelfLogging from logadapter, due to a compiler bug. hopefully unnecessary soon
object LoggingApi:
  val raw = logadapter.zio.ZApi( logadapter.jul.Api )
  type SelfLogging = raw.inner.SelfLogging
  export raw.*
```

then, as before, you can use in your application...

```scala
import LoggingApi.*
```

> [!NOTE]
> As before, to log, you'll need either to be in a type that implements `SelfLogging`, or have explicitly brought in a `given LogAdapter`.
> See [Full Start](#Full-Start), items 3 and 4 above.

Now in addition to the logging API above, you have the following new methods of `Level`:

```scala
INFO.zlog("This is a message") // returns a ZIO effect, ZIO[Any,Nothing,Unit], or UIO[Unit]
SEVERE.zlog("This is a message with a Throwable", t) // returns a ZIO effect, ZIO[Any,Nothing,Unit], or UIO[Unit]
```

You also have available methods you can call directly on ZIO effects, which yield the same effect,
but with the side effect of logging errors or defects to your backend of choice:

```scala
val someZioEffect = ...
someZioEffect.zlogError( WARNING ) // returns someZioEffect with the side effect of logging any errors
someZioEffect.zlogDefect( SEVERE ) // returns someZioEffect with the side effect of logging any defects
someZioEffect.zlogErrorDefect( SEVERE ) // returns someZioEffect with the side effect of logging both errors and defects
```

To get clearer information about where something went wrong if errors or defects are logged, you can add a tag
to any of these methods:

```scala
someZioEffect.zlogError( WARNING, what = "Call to DB server" ) // any logged error will mention it was the Call to DB server what done it
```

## History

Over the years, JVM applications have adopted a large menagerie of
logging libraries. I've been partial to logging "facades", (pioneered
by [Apache Commons logging](https://commons.apache.org/proper/commons-logging/)),
by which you can learn and use a single
logging API, then plug-in and configure any of the different logging
libraries you might choose as a "back end".

Mostly in support of [c3p0](https://github.com/swaldman/c3p0), I've
built a very extensive and configurable logging facade. "mlog" (for
mchange logging) lives in `com.mchange.v2.log` package of
[mchange-commons-java](https://github.com/swaldman/mchange-commons-java/tree/master/src/main/java/com/mchange/v2/log).
See the [c3p0's documentation](https://www.mchange.com/projects/c3p0/#configuring_logging) for information
on configuring and using that package.

[mlog-scala](https://github.com/swaldman/mlog-scala) is a Scala API
and facade to `mlog`, which I've used extensively and
successfully.

However, Scala 3 inlines open a path for a much simpler and lighter-weight
sort of facade. `mlog-scala` is tried and true, but brings in a large java
dependency for a very simple task, and imposes some overhead to the frequent
operation of logging. (To be fair to my old self, `mlog` is built with some
care, and its overhead is surprisingly small.)

This is an experiment in a much thinner, much simpler logging facade,
that largely retains the `mlog-scala` API, which I've been happy with.

Note that `mlog` is itself supported by this project. It retains the virtue
of letting the backend be chosen and substituted by external configuration,
rather than committing to it in code. (In that sense, `mlog` is similar to
[slf4j](https://www.slf4j.org/).)

## Acknowledgements

This project has been supported in part by external financial sponsorship.
Many thanks to Chris Peel for his support!

---

&copy; 2025 Machinery For Change LLC
