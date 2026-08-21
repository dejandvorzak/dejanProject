pipeline {

    agent {
        dockerfile {
            filename 'Dockerfile.jenkins-agent'
            args '--shm-size=2g'
        }
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    triggers {
        // Polls the GitHub repo for new commits every 5 minutes.
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test -Dbrowser=chrome -Dheadless=true'
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/junitreports/*.xml', allowEmptyResults: true
            sh 'allure generate target/allure-results --clean -o target/allure-report'

            publishHTML(target: [
                reportDir: 'target/allure-report',
                reportFiles: 'index.html',
                reportName: 'Allure Report',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: false
            ])

            archiveArtifacts artifacts: 'target/cucumber-report/**, target/allure-results/**, target/allure-report/**', allowEmptyArchive: true
        }
    }
}
