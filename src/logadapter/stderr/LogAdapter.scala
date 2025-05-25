package logadapter.stderr

import java.time.*, format.DateTimeFormatter

object LogAdapter:
  // we don't inline def this. we prefer the field lookup to reconstructing the formatter
  val TimestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'@'HH':'mm':'ss")

class LogAdapter( loggerName : String ) extends logadapter.LogAdapter:
  
  private inline def log( inline tag : String, message : =>String ) : Unit =
    val timestamp = LogAdapter.TimestampFormatter.format( LocalDateTime.now )
    System.err.println( s"${timestamp} ${tag} [${loggerName}] ${message}" )
  private inline def log( inline tag : String, message : =>String, t : Throwable ) : Unit =
    log( tag, message )
    System.err.print("  -> ")
    t.printStackTrace(System.err)

  inline def config  ( message : =>String )                : Unit = log("CONFIG",  message)
  inline def config  ( message : =>String, t : Throwable ) : Unit = log("CONFIG",  message, t)
  inline def debug   ( message : =>String )                : Unit = log("DEBUG",   message)
  inline def debug   ( message : =>String, t : Throwable ) : Unit = log("DEBUG",   message, t)
  inline def error   ( message : =>String )                : Unit = log("ERROR",   message)
  inline def error   ( message : =>String, t : Throwable ) : Unit = log("ERROR",   message, t)
  inline def fatal   ( message : =>String )                : Unit = log("FATAL",   message)
  inline def fatal   ( message : =>String, t : Throwable ) : Unit = log("FATAL",   message, t)
  inline def fine    ( message : =>String )                : Unit = log("FINE",    message)
  inline def fine    ( message : =>String, t : Throwable ) : Unit = log("FINE",    message, t)
  inline def finer   ( message : =>String )                : Unit = log("FINER",   message)
  inline def finer   ( message : =>String, t : Throwable ) : Unit = log("FINER",   message, t)
  inline def finest  ( message : =>String )                : Unit = log("FINEST",  message)
  inline def finest  ( message : =>String, t : Throwable ) : Unit = log("FINEST",  message, t)
  inline def info    ( message : =>String )                : Unit = log("INFO",    message)
  inline def info    ( message : =>String, t : Throwable ) : Unit = log("INFO",    message, t)
  inline def severe  ( message : =>String )                : Unit = log("SEVERE",  message)
  inline def severe  ( message : =>String, t : Throwable ) : Unit = log("SEVERE",  message, t)
  inline def trace   ( message : =>String )                : Unit = log("TRACE",   message)
  inline def trace   ( message : =>String, t : Throwable ) : Unit = log("TRACE",   message, t)
  inline def warning ( message : =>String )                : Unit = log("WARNING", message)
  inline def warning ( message : =>String, t : Throwable ) : Unit = log("WARNING", message, t)
end LogAdapter

