def call(String name, String path="./"){
  withCredentials([file(credentialsId: 'chef-client-key', variable: 'KEY' )]) {
    configFileProvider([configFile(fileId: 'knife-config', variable: 'KNIFE_CONFIG')]) {
      sh "mkdir ./cookbooks"
      sh "mv * cookbooks || exit 0"
      cp "${KNIFE_CONFIG} /root/.chef/config.rb" 
      sh "knife cookbook upload -o ${path} -k ${KEY} ${name}"
    }
  }
}