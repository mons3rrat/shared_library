def call(image){
  tagBeta = "${currentBuild.displayName}-${env.BRANCH_NAME}".replace("/","-")
  dir ("app"){
    sh """docker image build \
          -t ${image}:${tagBeta} ."""
  }
  
  withCredentials([usernamePassword(
    credentialsId: "docker",
    usernameVariable: "USER",
    passwordVariable: "PASS"
  )]) {
    sh "docker login -u $USER -p '${PASS}'"
  }

  sh """docker image push \
      ${image}:${tagBeta}"""
}