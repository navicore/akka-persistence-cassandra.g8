package $package$.models.$entityName;format="Camel,lower"$s

import java.time.{ZoneOffset, ZonedDateTime}
import java.util.UUID

case class $entityName;format="Camel"$(name: String,
                      value: Double,
                      id: Option[UUID] = Some(UUID.randomUUID()),
                      datetime: Option[ZonedDateTime] = Some(
                        ZonedDateTime.now(ZoneOffset.UTC)))
