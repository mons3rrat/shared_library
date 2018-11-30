def call(String path=''){
    dir("terraform") {
        dir("./${path}"){
            sh 'terraform destroy -force -input=false'
        }
    }
}