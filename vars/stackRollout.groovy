def call(stackFile, stackName){
  def IP = ""
  dir("terraform") {
    IP = sh(script:'terraform output docker-ip',
        returnStdout: true).trim()
  }
  dir("app"){
    sshagent(credentials: ['ssh-private-key']) {
      sh """ssh-add -k ${env.TF_VAR_my_private_key_path} """
      sh """ssh -o StrictHostKeyChecking=no \
         -l ubuntu -NL 2340:/var/run/docker.sock ${IP} & """
      sh """DOCKER_HOST=tcp://localhost:2340 \
            docker stack deploy -c \
            ${stackFile} ${stackName} """ 
      }
    }
  }
}