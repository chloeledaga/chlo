pipeline {
    agent any

    tools {
        jdk 'jdk-17'       // Nom de JDK défini dans Jenkins (Global Tool Config)
        maven '3.9.6'      // Nom de Maven défini dans Jenkins
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
            // Publication des rapports de tests JUnit
            junit 'target/surefire-reports/*.xml'
        }
    }
}


