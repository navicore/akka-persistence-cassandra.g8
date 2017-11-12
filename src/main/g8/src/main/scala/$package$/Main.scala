package $package$

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import $package$.actors.$entityName;format="Camel"$Service
import $package$.http.{$entityName;format="Camel"$Route, ErrorSupport}

import scala.concurrent.ExecutionContextExecutor

object Main extends App with LazyLogging with ErrorSupport {

  implicit val actorSystem: ActorSystem = ActorSystem("rest-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  implicit val executionContext: ExecutionContextExecutor =
    actorSystem.dispatcher

  val $entityName;format="Camel,lower"$Service: ActorRef = actorSystem.actorOf(
    $entityName;format="Camel"$Service.props(timeout),
    $entityName;format="Camel"$Service.name)

  val route =
    HealthCheck ~
      $entityName;format="Camel"$Route($entityName;format="Camel,lower"$Service)

  Http().bindAndHandle(route, "0.0.0.0", port)

}
