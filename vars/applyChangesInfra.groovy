def call(String path=''){
    dir("terraform"){
        dir("./${path}"){
            sh 'terraform apply -input=false plan'
        }
    }
}