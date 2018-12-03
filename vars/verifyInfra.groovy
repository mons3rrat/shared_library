def call(String path='',String terraformDir='terraform'){
    dir("${terraformDir}"){
        dir("./${path}"){
            sh 'terraform init -input=false'
            sh 'terraform validate'
        }
    }
}