package models

import anorm.SqlParser._
import anorm._
import play.api.Play.current
import play.api.db._

case class Todo(id: Long, label: String)

object Todo {
  val todo = {
    get[Long]("id") ~
      get[String]("label") map {
      case id ~ label => Todo(id, label)
    }
  }

  def all(): List[Todo] = DB.withConnection { implicit c =>
    SQL("select * from task").as(todo *)
  }

  def create(label: String) {
    DB.withConnection { implicit c =>
      SQL("insert into task (label) values ({label})").on(
        'label -> label
      ).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from task where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }
}