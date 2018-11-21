def call(){
    dir("terraform"){
        sh 'terraform init -input=false'
        sh 'terraform plan -out=plan -input=false'
        input(message: "Do you want to apply this plan?", ok: "yes")
        sh 'terraform apply -input=false plan'
    }
}