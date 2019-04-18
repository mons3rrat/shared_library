def call(String path='',String terraformDir='terraform'){
    dir("${terraformDir}"){
        dir("./${path}"){
            input(message: "Do you want to apply this plan?", ok: "yes")
            sh 'terraform destroy -force -input=false'
        }
    }
}