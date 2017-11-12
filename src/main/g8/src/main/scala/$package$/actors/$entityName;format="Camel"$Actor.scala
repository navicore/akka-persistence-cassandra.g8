package $package$.actors

import akka.actor._
import akka.persistence.{PersistentActor, RecoveryCompleted, SnapshotOffer}
import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging
import $package$.actors.$entityName;format="Camel"$Service.GetByName
import $package$.models.$entityName;format="Camel,lower"$s._

object $entityName;format="Camel"$Actor {
  def props(name: String) = Props(new $entityName;format="Camel"$Actor(name))
}

class $entityName;format="Camel"$Actor(name: String)
    extends Actor
    with PersistentActor
    with LazyLogging {

  val conf: Config = ConfigFactory.load()
  val snapShotInterval: Int = conf.getInt("main.snapShotInterval")

  override def persistenceId: String =
    conf.getString("main.$entityName;format="Camel,lower"$PersistenceId") + "_" + name

  var state: Option[$entityName;format="Camel"$] = None

  override def receiveRecover: Receive = {

    case s: Option[$entityName;format="Camel"$ @unchecked] => {
      state = s
    }

    case SnapshotOffer(_, snapshot: Option[$entityName;format="Camel"$ @unchecked]) => {
      state = snapshot
    }

    case _: RecoveryCompleted =>
      logger.info(s"$entityName;format="Camel,lower"$ \$name recovery completed")
  }

  override def receiveCommand: Receive = {

    case $entityName;format="Camel,lower"$: $entityName;format="Camel"$ =>
      state = Some($entityName;format="Camel,lower"$)
      persistAsync($entityName;format="Camel,lower"$) { _ =>
        {
          sender() ! state
          if (lastSequenceNr % snapShotInterval == 0 && lastSequenceNr != 0)
            saveSnapshot(state)
        }
      }

    case GetByName(_) => sender() ! state

  }

}
