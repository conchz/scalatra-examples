package com.github.dolphineor

import org.scalatra._
import org.scalatra.scalate.ScalateSupport

/**
 * Created on 2015-07-18.
 *
 * @author dolphineor
 */
trait ScalatraStack extends ScalatraServlet with ScalateSupport {

  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound()
  }

}
