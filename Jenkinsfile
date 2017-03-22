
pipeline {
  agent any
  environment {
    test = "${test2 + test3}"
    test2 = "value2"
    test3 = "${test2}"
  }
  stages {
    stage('Install') {
      steps {
        sh 'echo "${test}"'
      }
    }
  }
}
