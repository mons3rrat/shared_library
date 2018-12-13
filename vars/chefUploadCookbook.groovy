def call(String name, String path="../"){
  sh "knife cookbook upload ${name} -o ${path}"
}