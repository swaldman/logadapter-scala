package logadapter.stderr

object Api extends logadapter.Api[LogAdapter]:
  inline def logAdapterFor( loggerName : String ) : LogAdapter = new LogAdapter( loggerName )
end Api
