#!groovy

pipeline {
  agent any
  
  options {
    // Only keep the 10 most recent builds
    buildDiscarder(logRotator(numToKeepStr:'10'))
  }
  stages {
    stage ('List files') {
      steps {
        // List contents of the workspace
        sh 'ls -l'

      }
    }
    stage ('Gemfile Lock Contents') {
      steps {
        // List contents of the Gemfile.lock that will be scanned
        sh 'cat Gemfile.lock'
      }
    }
    stage ("Dependency Check") {
      steps {
      // Run the depency check task
      sh 'mkdir -p ${env.WORKSPACE}/dependency-check-data'	
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

          sh 'touch ${env.WORKSPACE}/dependency-check-data/dependency-check-report.html'
          archiveArtifacts(
            allowEmptyArchive: true,
            artifacts: '**/dependency-check-report.*',
            onlyIfSuccessful: true)
      }
    }
    
    stage ('Test') {
      steps {
        // run tests with coverage
        //sh 'bundle exec rake spec'
        sh 'echo test app'
        sh 'touch index.html'
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
