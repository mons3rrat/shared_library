def call(){
  try {
    sh 'kitchen test'
  } finally {
    sh 'kitchen destroy'
  }
}