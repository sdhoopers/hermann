def callback() {
  echo 'hello'
}

pipeline {
  agent {
    docker {
      image 'ruby:2.3'
    }
  }
  stages {
    stage('Install') {
      steps {
        sh 'bundle install'
        callback()
      }
    }
    stage('Build') {
      steps {
        sh 'bundle exec rake build'
      }
      post {
        success {
          archive 'pkg/*.gem'

        }

      }
    }
    stage('Test') {
      steps {
        sh 'bundle exec rake spec'
    }
    post {
      always {
        echo "Send notifications for result: ${currentBuild.result}"

      }

    }
    options {
      buildDiscarder(logRotator(numToKeepStr: '10'))
    }
  }
