package com.github.dolphineor

import com.github.dolphineor.model.SlickSupport

/**
 * Created on 2015-07-18.
 * HLScalatraServlet
 *
 * @author dolphineor
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
        Say
        <a href="hello-scalate">hello to Scalate</a>
        .
      </body>
    </html>
  }

}
