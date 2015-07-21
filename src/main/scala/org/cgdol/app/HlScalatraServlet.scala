package org.cgdol.app

import org.cgdol.app.db.SlickSupport
import org.scalatra._
import scalate.ScalateSupport

/**
 * Created by dolphineor on 2015-7-18.
 *
 * HLScalatraServlet
 */
class HlScalatraServlet extends ScalatraStack with SlickSupport {

  get("/") {
    <html lang="zh-cmn-Hans">
      <head>
        <meta charset="UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
      </head>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

}
