package $package$.actors.fuctions

import akka.actor._
import $package$.actors.$entityName;format="Camel"$Actor

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._

trait ActorServiceSupport extends Actor {

  def create$entityName;format="Camel"$Actor(actorId: String): Unit = {
    def notFound(): Unit =
      context.actorOf($entityName;format="Camel"$Actor.props(actorId), actorId)
    context
      .child(actorId)
      .fold(notFound())(_ => {})
  }

  def forwardIfExists[T: TypeTag](query: T, actorId: String)(
      implicit tag: ClassTag[T]): Unit = {
    def notFound(): Unit = sender() ! None
    context
      .child(actorId)
      .fold(notFound())(_ forward query)
  }

  // create if does not exist
  def forward[T: TypeTag](query: T, actorId: String)(
      implicit tag: ClassTag[T]): Unit = {
    def notFound(): Unit =
      context.actorOf($entityName;format="Camel"$Actor.props(actorId), actorId) forward query
    context
      .child(actorId)
      .fold(notFound())(_ forward query)
  }

  def stopActor(actorId: String): Unit = {
    def notFound(): Unit = Unit
    context
      .child(actorId)
      .fold(notFound())(context.stop)
  }

}
