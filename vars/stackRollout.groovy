def call(stack){
  dir("terraform") {
    def IP = sh(script:'terraform output docker-ip',
        returnStdout: true).trim()
  }
  dir("app"){
    sshagent(credentials: ['ssh-private-key']) {
      sh """ssh -o StrictHostKeyChecking=no \
         -l ubuntu ${IP} \
           uname -a"""
    }
  }
}