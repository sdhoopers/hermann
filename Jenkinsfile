#!groovy

pipeline {
  agent {
    any
    }
  }
  options {
    // Only keep the 10 most recent builds
    buildDiscarder(logRotator(numToKeepStr:'10'))
  }
  stages {
    stage ('Install') {
      agent {
        docker { 
          image 'ruby:2.3'
        }
      }
      steps {
        // install required bundles
        sh 'bundle install'

      }
    }
    stage ('Build') {
      steps {
        // build
        //sh 'bundle exec rake build'
        //sh 'docker build -t todoapp:latest .'
        sh 'cat Gemfile.lock'
      }
    }
    stage ("Dependency Check") {
      steps {
        depencyCheckAnalyzer(
          datadir: 'dependency-check-data',
          suppressionFile: '',
          hintsFile: '',
          includeCsvReports: false,
          includeHtmlReports: true,
          includeJsonReports: true,
          isAutoupdateDisabled: false,
          outdir: '',
          scanpath: '',
          skipOnScmChange: false,
          skipOnUpstreamChange: false,
          zipExtensions: '',
          includeVulnReports: true)

          dependencyCheckPublisher(
            //canComputeNew: false,
            //defaultEncoding: '',
            //failedTotalAll: '2', // fail if greater than 3 vulns
            failedTotalHigh: '0', // fail if any high vulns
            //healthy: '',
            pattern: '',
            //unHealthy: '2' //build is unhealthy while there are more than 2 vulns
          )

          archiveArtifacts(
            allowEmptyArchive: true,
            artifacts: '**/dependency-check-report.*',
            onlyIfSuccessful: true)
      }
    }
    
    stage ('Test') {
      steps {
        // run tests with coverage
        sh 'bundle exec rake spec'
      }

      post {
        success {
          // publish html
          publishHTML target: [
              allowMissing: false,
              alwaysLinkToLastBuild: false,
              keepAll: true,
              reportDir: 'coverage',
              reportFiles: 'index.html',
              reportName: 'RCov Report'
            ]
        }
      }
    }
  }
  post {
    always {
      echo "Send notifications for result: ${currentBuild.result}"
    }
  }
}
