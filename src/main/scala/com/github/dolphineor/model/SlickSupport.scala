package com.github.dolphineor.model

import java.sql.SQLException
import javax.naming.InitialContext

import com.zaxxer.hikari.HikariDataSource
import org.scalatra.{FutureSupport, ScalatraServlet}
import org.slf4j.LoggerFactory
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext

/**
 * Created on 2015-07-18.
 *
 * @author dolphineor
 */
trait SlickSupport extends ScalatraServlet with FutureSupport {

  lazy val logger = LoggerFactory.getLogger(this.getClass)

  override protected implicit def executor: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  private[this] val hcpds = new InitialContext().lookup("java:comp/env/jdbc/scalatraDS") match {
    case dataSource: HikariDataSource => dataSource
    case _ => throw new SQLException("HikariCP connection pool initialize failed")
  }

  val db = Database.forDataSource(hcpds)

  def closeDbConnection(): Unit = {
    logger.info("Closing HikariCP connection pool")
    hcpds.close()
  }
}
