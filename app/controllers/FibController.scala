package controllers

import javax.inject.Inject

import models.Fib
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}

class FibController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.fib(0))
  }

  def makeFib(num: Long) = Action {
    Ok(views.html.fib(Fib.makeFib(num)))
  }
}
