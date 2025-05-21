# logadapter-scala

## Quick Start

```scala
import logadapter.jul.Api.*
   
object MyObject extends SelfLogging:
  def doSomething() : Unit =
    INFO.log("Something has been done.")
```

## Full Start

1. Choose a logging back-end. 

   Currently `jul` (java.util.logging),
   [`scribe`](https://github.com/outr/scribe/), [`mlog`](#History),
   and `stderr`, a simple standard-error back-end, are supported.
   More back-ends are (hopefully) coming soon.
   
   For `jul` and `stderr`, the dependency you'll need is just
   
   * sbt:  `libraryDependencies += "com.mchange" %% "logadapter-scala" % "<version>"`
   * mill: `ivy"com.mchange::logadapter-scala:0.0.1-SNAPSHOT"`
   
   For `scribe`, `mlog`, or other backends, you'll need a library-appropriate
   dependency, like

   * sbt:  `libraryDependencies += "com.mchange" %% "logadapter-scala-scribe" % "<version>"`
   * mill: `ivy"com.mchange::logadapter-scala-scribe:0.0.1-SNAPSHOT"`
   
2. Each back-end has its own package, in which there is an object
   called Api. Import the full API from that object:

   ```scala
   import logadapter.jul.Api.*
   
   // you might have chosen `scribe`, `mlog`, or `stderr` instead of `jul`
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
       INFO.logEval("Computation")(3 + 7) // this logs "Computation: 10" and evaluate to 10
   ```

4. If you wish to be able to log outside of a `SelfLogging` class or object,
   you can explicitly bring in a `LogAdapter` from your Api as a `given`:
   
   ```scala
   import logadapter.jul.Api.*
   
   given LogAdapter = logAdapterFor( "com.mchange.my.logger.name" )
   
   def sunset() : Unit =
     INFO.log("The sun is setting.")
     
## API

The API is very simple. Supported log levels are

* `CONFIG`
* `DEBUG`
* `FINE`
* `FINER`
* `FINEST`
* `INFO`
* `SEVERE`
* `TRACE`
* `WARNING`

You simply log on levels, like

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

_**Note:** `scribe` brings in filename and line number information by default, so `logDebug` may be less useful with that backend._

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

`logadapter-scala` does nothing to standardize configuration of back-end libraries.
Whatever back-end you choose, you'll have to supply its library-specific config files or API.

However, `logadapter-scala` Api objects are ordinary objects. If you are using
programmartic configuration, one way to ensure your Api is configured before you
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

Now, your application files can just import from `LoggingApi`:

```scala
import LoggingApi.*
```

and you can be sure your logging has been configured before the API is available.

## History

Over the years, JVM applications have adopted a large menagerie of
logging libraries. I've been partial to logging "facades", (pioneered
by Apache Commons logging), by which you can learn and use a single
logging API, then plug-in and configure any of the different logging
libraries you might choose as a "back end".

Mostly in support of [c3p0](https://github.com/swaldman/c3p0), I've
built a very extensive and configurable logging facade. "mlog" (for
mchange logging) lives in `com.mchange.v2.log` package of 
[mchange-commons-java](https://github.com/swaldman/mchange-commons-java/tree/master/src/main/java/com/mchange/v2/log).
See the [c3p0's documentation](https://www.mchange.com/projects/c3p0) for information
on configuring and using that package.

[mlog-scala](https://github.com/swaldman/mlog-scala) is a Scala API
and facade to `mlog`, which in recent years I've used extensively and
successfully.

However, Scala 3 inlines open a path for a much simpler and lighter-weight
sort of facade. `mlog-scala` is tried and true, but brings in a large java
dependency for a very simple task, and imposes some overhead to the frequent
operation of logging. (To be fair to my old self, `mlog` is built with some
care, and its overhead is surprisingly small.)

This is an experiment in a much thinner, much simpler logging facade,
that largely retains the `mlog-scala` API, which I've been happy with.
