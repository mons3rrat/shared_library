import groovy.json.*
def call(String fileName){
  def resourceFile =  libraryResource "com/dou/${fileName}.json"
	assert resourceFile.indexOf("'") == -1 : "Invalid JSON File"
    return new JsonSlurperClassic().parseText(new JsonBuilder(new JsonSlurper().setType(JsonParserType.LAX).parseText(resourceFile)).toString())
}
