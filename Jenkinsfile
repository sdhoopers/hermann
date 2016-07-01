#!groovy

/* Only keep the 10 most recent builds. */
properties([[$class: 'BuildDiscarderProperty',
                strategy: [$class: 'LogRotator', numToKeepStr: '10']]])

stage 'Build'

node {
  // Checkout
  checkout scm

  // Use a particular version of ruby (configured by rvm) to run build
  sh ('''
  # install required bundles
  bundle install || exit 1

  # compile native components
  bundle exec rake compile || exit 1

  # build the package
  bundle exec rake build || exit 1

  # run tests and coverage
  bundle exec rake spec || exit 1
  ''')

  // Archive the built artifacts
  archive (includes: 'pkg/*.gem')

  // publish html
  // "target:" shouldn't be needed, https://issues.jenkins-ci.org/browse/JENKINS-29711.
  publishHTML (target: [
      allowMissing: false,
      alwaysLinkToLastBuild: false,
      keepAll: true,
      reportDir: 'coverage',
      reportFiles: 'index.html',
      reportName: "RCov Report"
    ])
}
