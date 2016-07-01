#!groovy

/* Only keep the 10 most recent builds. */
properties([[$class: 'BuildDiscarderProperty',
                strategy: [$class: 'LogRotator', numToKeepStr: '10']]])

// Helper to run shell with specific ruby version configured.
// Rvm configuration is a bit involved and can go sideways easily
def withRuby(String rubyVersion) {
  // Rvm wants the shell it runs in to be a login shell
  def shellScript = '''#!/bin/bash --login
    # Ensure rvm is configured
    rvm current

    # Rvm also wants to be run from a function, so pull it in
    [[ -s "$HOME/.rvm/scripts/rvm" ]] && source "$HOME/.rvm/scripts/rvm"

    # Ensure the requested version of ruby is installed
    # Use the requested version of ruby
    rvm use ''' + rubyVersion + ''' --install
    rvm current && rvm info

    # Fail if the requested version of ruby is not active when we're done
    [[ "$(rvm current)" == *"''' + rubyVersion + '''"* ]] || {
      echo Failed to set ruby to version ''' + rubyVersion + '''!
      exit 1
    }

    # ensure bundle gem is installed for this version of ruby
    gem install bundle

    # run the commands that were passed
    # install required bundles
    bundle install || exit 1

    # compile native components
    bundle exec rake compile || exit 1

    # build the package
    bundle exec rake build || exit 1

    # run tests and coverage
    bundle exec rake spec || exit 1
  '''

  sh shellScript

}

stage 'Build'

node {
  // Checkout
  checkout scm

  // Use a particular version of ruby (configured by rvm) to run build
  withRuby ('2.1.9'
  )

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
