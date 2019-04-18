def call(){
  sh 'rubocop . || exit 0'
}