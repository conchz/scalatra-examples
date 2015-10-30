package com.github.dolphineor

import javax.servlet.ServletContext

import org.scalatra._
import org.slf4j.LoggerFactory

/**
 * Created on 2015-07-18.
 *
 * @author dolphineor
 */
class ScalatraBootstrap extends LifeCycle {

  val logger = LoggerFactory.getLogger(getClass)
  val hlServlet = new HlScalatraServlet


  override def init(context: ServletContext) {
    context.mount(hlServlet, "/*")
    logger.info("Init succeed.")
  }


  override def destroy(context: ServletContext): Unit = {
    super.destroy(context)
    hlServlet.closeDbConnection()
  }

}
