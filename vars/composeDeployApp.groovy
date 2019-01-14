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
  dir("app"){
    sh "docker-compose up -d" 
  }
}