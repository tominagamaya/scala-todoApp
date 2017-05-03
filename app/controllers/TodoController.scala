package controllers

import models.Todo
import play.api.Play.current
import play.api.data.Forms._
import play.api.data._
import play.api.i18n.Messages.Implicits._
import play.api.mvc.{Action, Controller}

class TodoController extends Controller {

  val todoForm = Form(
    "label" -> nonEmptyText.verifying("必須項目です", {_.length > 0})
  )

  def index = Action {
    Redirect(routes.TodoController.todo())
  }

  def todo = Action {
    Ok(views.html.index(Todo.all(), todoForm))
  }

  def newTodo = Action { implicit request =>
    todoForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Todo.all(), errors)),
      label => {
        Todo.create(label)
        Redirect(routes.TodoController.todo())
      }
    )
  }

  def deleteTodo(id: Long) = Action {
    Todo.delete(id)
    Redirect(routes.TodoController.todo())
  }
}
