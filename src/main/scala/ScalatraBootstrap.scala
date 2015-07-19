import javax.servlet.ServletContext

import org.cgdol.app.HlScalatraServlet
import org.scalatra._
import org.slf4j.LoggerFactory

/**
 * Created by dolphineor on 2015-7-18.
 */
class ScalatraBootstrap extends LifeCycle {

  val logger = LoggerFactory.getLogger(getClass)
  val hlServlet = new HlScalatraServlet


  override def init(context: ServletContext) {
    context.mount(hlServlet, "/*")
    logger.info("Init succeed")
  }


  override def destroy(context: ServletContext): Unit = {
    super.destroy(context)
    hlServlet.closeDbConnection()
  }

}
