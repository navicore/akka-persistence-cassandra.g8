package $package$.actors

import akka.actor._
import akka.persistence.{PersistentActor, RecoveryCompleted, SnapshotOffer}
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging
import $package$.actors.$entityName;format="Camel"$Service._
import $package$.actors.fuctions.ActorServiceSupport
import $package$.models.$entityName;format="Camel,lower"$s._

object $entityName;format="Camel"$Service {
  def props(implicit timeout: Timeout) = Props(new $entityName;format="Camel"$Service)
  def name = "$entityName;format="Camel"$Service"

  final case class GetByName(name: String)
  final case class DeleteByName(name: String)
}

class $entityName;format="Camel"$Service(implicit timeout: Timeout)
    extends ActorServiceSupport
    with PersistentActor
    with LazyLogging {

  val conf: Config = ConfigFactory.load()
  val snapShotInterval: Int = conf.getInt("main.snapShotInterval")
  override def persistenceId: String =
    conf.getString("main.$entityName;format="Camel,lower"$PersistenceId") + "_service"
  var state: List[String] = List[String]()

  override def receiveRecover: Receive = {

    case $entityName;format="Camel,lower"$: $entityName;format="Camel"$ =>
      state = $entityName;format="Camel,lower"$.name :: state
      forward($entityName;format="Camel,lower"$, $entityName;format="Camel,lower"$.name)

    case deleteCmd: DeleteByName =>
      state = state.filter(n => n != deleteCmd.name)
      stopActor(deleteCmd.name)

    case SnapshotOffer(_, snapshot: List[String @unchecked]) =>
      snapshot.foreach(create$entityName;format="Camel"$Actor)
      state = snapshot

    case _: RecoveryCompleted =>
      logger.info("$entityName;format="Camel,lower"$ service recovery completed")

  }

  override def receiveCommand: Receive = {

    case $entityName;format="Camel,lower"$: $entityName;format="Camel"$ =>
      state = $entityName;format="Camel,lower"$.name :: state
      persistAsync($entityName;format="Camel,lower"$) { _ =>
        {
          forward($entityName;format="Camel,lower"$, $entityName;format="Camel,lower"$.name)
          if (lastSequenceNr % snapShotInterval == 0 && lastSequenceNr != 0)
            saveSnapshot(state)
        }
      }

    case deleteCmd: DeleteByName =>
      if (state.contains(deleteCmd.name)) {
        state = state.filter(n => n != deleteCmd.name)
        persistAsync(deleteCmd) { _ =>
          {
            stopActor(deleteCmd.name)
            sender() ! Some(deleteCmd)
            if (lastSequenceNr % snapShotInterval == 0 && lastSequenceNr != 0)
              saveSnapshot(state)
          }
        }
      } else {
        sender() ! None
      }

    case GetByName(name) => forwardIfExists(GetByName(name), name)

  }

}
