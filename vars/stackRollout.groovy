def call(String stackName, String stackFile, String stackDir=''){
  def String IP = ""
  dir("terraform") {
    IP = sh(script:'terraform output docker-ip',
        returnStdout: true).trim()
  }
  sshagent(credentials: ['ssh-private-key']) {
    sh """ssh-add -k ${env.TF_VAR_my_private_key_path} """
    if(stackDir != ''){
      dir(stackDir){
        sh "tar -czf stacks.tar.gz ${stackDir}"

        sh """scp -o StrictHostKeyChecking=no \
            stacks.tar.gz ubuntu@${IP}:/tmp"""

        sh """ssh -o StrictHostKeyChecking=no \
            ubuntu@${IP} tar -C /tmp -xzvf /tmp/stacks.tar.gz""" 

        sh """ssh -o StrictHostKeyChecking=no \
            ubuntu@${IP} docker stack deploy -c \
            /tmp/${stackDir}/${stackFile} ${stackName} """ 
      }
    }
    else {
      dir("app"){
        sh """scp -o StrictHostKeyChecking=no \
        ${stackFile} ubuntu@${IP}:/tmp"""
        sh """ssh -o StrictHostKeyChecking=no \
            ubuntu@${IP} docker stack deploy -c \
            /tmp/${stackFile} ${stackName} """ 
      }
    }
  }
}