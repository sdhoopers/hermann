#!groovy

pipeline {
    agent any
    stages {
        stage ('Build') {
            agent {
                dockerfile {
                    reuseNode true
                }
            }
            steps {
                sh 'cat Jenkinsfile'
            }
        }
    }
}
