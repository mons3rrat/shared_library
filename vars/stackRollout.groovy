def call(String stackName, String stackFile){
  def String IP = ""
  dir("terraform") {
    IP = sh(script:'terraform output docker-ip',
        returnStdout: true).trim()
  }
  dir("app"){
    sshagent(credentials: ['ssh-private-key']) {
      sh """ssh-add -k ${env.TF_VAR_my_private_key_path} """
      sh """scp -o StrictHostKeyChecking=no \
       ${stackFile} ubuntu@${IP}:/tmp"""

      sh """ssh -o StrictHostKeyChecking=no \
          ubuntu@${IP} docker stack deploy -c \
          /tmp/${stackFile} ${stackName} """ 
    }
  }
}