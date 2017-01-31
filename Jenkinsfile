#!groovy

pipeline {
    agent any
    stages {
        stage ('Build') {
            agent {
                docker {
                    image 'ruby:2.3'
                }
            }
            steps {
                checkout scm
                sh 'cat Jenkinsfile'
            }
        }
    }
}
