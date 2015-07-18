package org.cgdol.app.db

import java.sql.SQLException
import javax.naming.InitialContext

import com.zaxxer.hikari.HikariDataSource
import org.scalatra.{FutureSupport, ScalatraServlet}
import org.slf4j.LoggerFactory
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext

/**
 * Created by dolphineor on 2015-7-18.
 */
trait SlickSupport extends ScalatraServlet with FutureSupport {

  lazy val logger = LoggerFactory.getLogger(this.getClass)

  override protected implicit def executor: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  val hcpds = new InitialContext().lookup("java:comp/env/jdbc/test") match {
    case dataSource: HikariDataSource => dataSource
    case _ => throw new SQLException("HikariCP connection pool initialize failed")
  }

  val db = Database.forDataSource(hcpds)

  def closeDbConnection(): Unit = {
    logger.info("Closing HikariCP connection pool")
    hcpds.close()
  }
}
