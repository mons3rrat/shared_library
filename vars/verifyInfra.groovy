def call(){
    dir("terraform"){
        sh 'terraform init -input=false'
        sh 'terraform validate'
    }
}