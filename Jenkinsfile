pipeline {
    agent any

    tools {
        jdk 'jdk-21'          // Vérifie que ce nom correspond bien à ce que tu as configuré dans Jenkins
        maven '3.9.6'   // Pareil ici
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test Selenium') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
