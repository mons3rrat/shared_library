def call(String name, String path="./"){
  withCredentials([file(credentialsId: 'chef-client-key', variable: 'KEY' )]) {
    configFileProvider([configFile(fileId: 'knife-config', variable: 'KNIFE_CONFIG')]) {
      sh "mkdir ./cookbooks"
      sh "mv * cookbooks"
      sh "knife cookbook upload -o ${path} -c ${KNIFE_CONFIG} -k ${KEY} ${name}"
    }
  }
}