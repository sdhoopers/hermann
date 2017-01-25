#!groovy

pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr:'10'))
    }
    stages {
        stage ('Build') {
            steps {
                // install required bundles
                sh 'bundle install'

                // build and run tests with coverage
                sh 'bundle exec rake build spec'
            }
        }
    }
    post {
        success {
            // Archive the built artifacts
            archive includes: 'pkg/*.gem'
        }

        always {
            // publish html
            publishHTML ([
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'coverage',
                reportFiles: 'index.html',
                reportName: "RCov Report"
              ])
        }
    }
}
