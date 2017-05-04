pipeline {
  agent any
  stages {
    stage('Install') {
      steps {
        sh 'echo "${test}"'
      }
    }
  }
  environment {
    test2 = 'value2'
    test3 = "${test2}"
    test = "${test2 + test3}"
  }
}