#!groovy

pipeline {
    agent any
    stages {
        stage ('Build') {
            agent {
                dockerfile true
            }
            steps {
                checkout scm
                sh 'cat Jenkinsfile'
            }
        }
    }
}
