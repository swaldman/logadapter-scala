package logadapter.scribe.dynamic

object Api extends logadapter.Api[LogAdapter]:
  def logAdapterFor( loggerName : String ) : LogAdapter = new LogAdapter( loggerName )
end Api
