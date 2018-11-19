def call(image){
  tagBeta = "${currentBuild.displayName}-${env.BRANCH_NAME}".replace("/", "-")

  sh """docker image pull \
        ${image}:${tagBeta}"""
  sh """docker image tag \
      ${image}:${tagBeta} \
      ${image}:${currentBuild.displayName}"""
  sh """docker image tag \
      ${image}:${tagBeta} \
      ${image}:latest"""
  
  withCredentials([usernamePassword(
    credentialsId: "docker",
    usernameVariable: "USER",
    passwordVariable: "PASS"
  )]) {
    sh "docker login -u $USER -p '${PASS}'"
  }
  sh """docker image push \
      ${image}:${currentBuild.displayName}"""

  sh """docker image push \
      ${image}:${tagBeta}"""

  sh """docker image push \
      ${image}:latest"""
}