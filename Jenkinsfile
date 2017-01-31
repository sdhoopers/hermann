#!groovy

pipeline {
    agent any
    options {
        skipDefaultCheckout()
    }
    stages {
        stage ('Build') {
            agent any
            options {
                skipDefaultCheckout()
            }
            steps {
                sh 'cat Jenkinsfile'
            }
        }
    }
}
