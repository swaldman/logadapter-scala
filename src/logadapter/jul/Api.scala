package logadapter.jul

object Api extends logadapter.Api[LogAdapter]:
  def logAdapterFor( loggerName : String ) : LogAdapter = new LogAdapter( loggerName )

  // We should be able to provide this nested trait once, in logadapter.Api
  // But because of an apparent Scala bug, we cannot,
  // we have to provide this trait separately in each implementation
  // for now!
  //
  // See https://github.com/scala/scala3/issues/23245
  //
  trait SelfLogging:
    lazy given logAdapter : LogAdapter = logAdapterFor(this)
end Api
