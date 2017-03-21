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
        success {
          publishHTML([
                          allowMissing: false,
                          alwaysLinkToLastBuild: false,
                          keepAll: true,
                          reportDir: 'coverage',
                          reportFiles: 'index.html',
                          reportName: 'RCov Report'
                        ])
            
          }
          
        }
      }
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