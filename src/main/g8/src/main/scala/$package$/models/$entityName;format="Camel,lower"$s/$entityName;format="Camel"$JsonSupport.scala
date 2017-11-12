package $package$.models.$entityName;format="Camel,lower"$s

import $package$.models.JsonSupport
import spray.json._

trait $entityName;format="Camel"$JsonSupport extends JsonSupport {

  implicit val $entityName;format="Camel,lower"$Format: RootJsonFormat[$entityName;format="Camel"$] = jsonFormat4(
    $entityName;format="Camel"$)
}
