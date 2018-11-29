def call(){
    dir("terraform") {
        sh 'terraform destroy -force -input=false'
    }
}