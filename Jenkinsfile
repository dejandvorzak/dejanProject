pipeline {

    agent none

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '20'))
        timeout(time: 30, unit: 'MINUTES')
    }

    triggers {
        // Polls the GitHub repo for new commits every 5 minutes.
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Build') {
            agent {
                dockerfile {
                    filename 'Dockerfile.jenkins-agent'
                    args '--shm-size=2g'
                }
            }
            steps {
                checkout scm
                sh 'mvn -B -DskipTests clean compile'
            }
        }

        stage('Test - Chrome') {
            agent {
                dockerfile {
                    filename 'Dockerfile.jenkins-agent'
                    args '--shm-size=2g'
                }
            }
            steps {
                checkout scm
                sh 'mvn -B test -Dbrowser=chrome -Dheadless=true'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/junitreports/*.xml', allowEmptyResults: true
                    sh 'allure generate target/allure-results --clean -o target/allure-report'

                    publishHTML(target: [
                        reportDir: 'target/allure-report',
                        reportFiles: 'index.html',
                        reportName: 'Allure Report - Chrome',
                        keepAll: true,
                        alwaysLinkToLastBuild: true,
                        allowMissing: false
                    ])

                    archiveArtifacts artifacts: 'target/cucumber-report/**, target/allure-results/**, target/allure-report/**', allowEmptyArchive: true
                }
            }
        }

        stage('Test - Firefox') {
            agent {
                dockerfile {
                    filename 'Dockerfile.jenkins-agent'
                    args '--shm-size=2g'
                }
            }
            steps {
                checkout scm
                sh 'mvn -B test -Dbrowser=firefox -Dheadless=true'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/junitreports/*.xml', allowEmptyResults: true
                    sh 'allure generate target/allure-results --clean -o target/allure-report'

                    publishHTML(target: [
                        reportDir: 'target/allure-report',
                        reportFiles: 'index.html',
                        reportName: 'Allure Report - Firefox',
                        keepAll: true,
                        alwaysLinkToLastBuild: true,
                        allowMissing: false
                    ])

                    archiveArtifacts artifacts: 'target/cucumber-report/**, target/allure-results/**, target/allure-report/**', allowEmptyArchive: true
                }
            }
        }
    }
}
