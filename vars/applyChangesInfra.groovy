def call(String path='',String terraformDir='terraform'){
    dir("${terraformDir}"){
        dir("./${path}"){
            sh 'terraform apply -input=false plan'
        }
    }
}