def call(String path=''){
    dir("terraform"){
        dir("./${path}"){
            sh 'terraform init -input=false'
            sh 'terraform validate'
        }
    }
}