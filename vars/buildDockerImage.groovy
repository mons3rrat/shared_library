def call(String image, String sonarServer){
  def String tagBeta = "${currentBuild.displayName}-${env.BRANCH_NAME}".replace("/","-")
  dir ("app"){
    sh """docker image build \
        --build-arg SONAR_TOKEN=${env.SONAR_TOKEN} \
        --build-arg SONAR_SERVER=${sonarServer} \
        --build-arg BRANCH_NAME=${env.BRANCH_NAME} \
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