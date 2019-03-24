import com.github.fge.jackson.JsonLoader
import com.github.fge.jsonschema.main.JsonSchemaFactory


object jsonSchemaValidator extends App {
  val fruitSchema = JsonLoader.fromResource("/fruitJSONSchema.json")
  val fruitJson = JsonLoader.fromResource("/fruitJSON.json")

  println("VALIDATING SCHEMA WITH GOOD MESSAGE")
  println("")
  println("Schema: " + fruitSchema)
  println("JSON: " + fruitJson)

  val factory = JsonSchemaFactory.byDefault()
  val schema = factory.getJsonSchema(fruitSchema)

  val result = schema.validate(fruitJson,true)
  println("RESULT : " + result)
  println("")
  println("PASS : " + result.isSuccess)

  println("\n\nValidating With String in place of Array")
  val strJson = JsonLoader.fromResource("/stringInArray.json")
  println("JSON WITH STRING IN PLACE OF ARRAY:" + strJson)
  println("ERRORS:" + schema.validate(strJson))
  println("PASS:" + schema.validate(strJson).isSuccess)

  println("\n\nValidating With Object in place of Array")
  val objJson = JsonLoader.fromResource("/objectInArray.json")
  println("JSON WITH STRING IN PLACE OF ARRAY:" + objJson)
  println("ERRORS:" + schema.validate(objJson))
  println("PASS:" + schema.validate(objJson).isSuccess)

  println("\n\nValidating With fruit JSON with person json schema")
  val personSchemaJsonNode = JsonLoader.fromResource("/personSchema.json")
  val schema1 = factory.getJsonSchema(personSchemaJsonNode)
  println("PERSON JSON SCHEMA:" + personSchemaJsonNode)
  println("JSON WITH STRING IN PLACE OF ARRAY:" + fruitJson)
  println("ERRORS:" + schema1.validate(fruitJson))
  println("PASS:" + schema1.validate(fruitJson).isSuccess)

  println("\n\nValidating With json which has more number of columns than schema")

  val fruitJson1 = JsonLoader.fromResource("/fruitJSON_invalid.json")
  println("PERSON JSON SCHEMA:" + personSchemaJsonNode)
  println("JSON WITH STRING IN PLACE OF ARRAY:" + fruitJson1)
  println("ERRORS:" + schema.validate(fruitJson1))
  println("PASS:" + schema.validate(fruitJson1).isSuccess)

  println(factory.getSyntaxValidator.schemaIsValid(personSchemaJsonNode))
}
