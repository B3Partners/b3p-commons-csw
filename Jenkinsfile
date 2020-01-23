timestamps {

    properties([
        [$class: 'jenkins.model.BuildDiscarderProperty', strategy: [$class: 'LogRotator',
            artifactDaysToKeepStr: '8',
            artifactNumToKeepStr: '3',
            daysToKeepStr: '15',
            numToKeepStr: '5']
        ]]);

    node {
        withEnv(["JAVA_HOME=${ tool 'JDK8' }", "PATH+MAVEN=${tool 'Maven CURRENT'}/bin:${env.JAVA_HOME}/bin"]) {

            stage('Prepare') {
                sh "ulimit -a"
                sh "free -m"
                checkout scm
            }

            stage('Build') {
                echo "Building branch: ${env.BRANCH_NAME}"
                sh "mvn install -Dmaven.test.skip=true -B -V -e -fae -q"
            }

            stage('Test') {
                echo "Running unit tests"
                sh "mvn -e test -B"
            }

            stage('Check Javadocs') {
                sh "mvn javadoc:javadoc"
            }

            stage('Check Test Javadocs') {
                sh "mvn javadoc:test-javadoc"
            }

            stage('Publish Test Results') {
                junit allowEmptyResults: true, testResults: '**/target/surefire-reports/TEST-*.xml, **/target/failsafe-reports/TEST-*.xml'
            }

            stage('OWASP Dependency Check') {
                echo "Uitvoeren OWASP dependency check"
                sh "mvn org.owasp:dependency-check-maven:check"
                dependencyCheckPublisher failedNewCritical: 1, unstableNewHigh: 1, unstableNewLow: 1, unstableNewMedium: 1
            }
        }
    }
}
