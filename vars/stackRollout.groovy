def call(stack){
  dir("terraform") {
    def IP = sh(script:'terraform output docker-ip',
        returnStdout: true).trim()
  }
  dir("app"){
    sshagent(credential: ['ssh-private-key']) {
      sh """ssh -o StrictHostKeyChecking=no \
         -l ubuntu ${IP} \
           uname -a"""
    }
  }
}