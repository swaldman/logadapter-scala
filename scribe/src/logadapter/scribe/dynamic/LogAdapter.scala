package logadapter.scribe.dynamic

import scribe.Logger

/*
 * This implementation is much slower than the one in logadapter.scribe, and so
 * far I can't prove to myself the more dynamic inlining buys any extra on-the-fly
 * configurability.
 *
 * See logadapter.scribe.AbstractLogAdapter
 */
class LogAdapter( loggerName : String ) extends logadapter.scribe.AbstractLogAdapter:
  inline def logger : scribe.Logger = Logger( loggerName )
end LogAdapter
