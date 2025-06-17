pipeline {
    agent any

    tools {
        // Utilise Maven s’il est installé via Jenkins
        maven '3.9.6'
        jdk 'jdk-17'
    }

    environment {
        DISPLAY = ':0'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/chloeledaga/chlo.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test Selenium') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
