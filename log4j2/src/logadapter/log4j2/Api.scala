package logadapter.log4j2

object Api extends logadapter.Api[LogAdapter]:
  def logAdapterFor( loggerName : String ) : LogAdapter = new LogAdapter( loggerName )
end Api
