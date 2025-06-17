pipeline {
    agent any

    tools {
    jdk 'jdk-17'
    maven 'maven-3.9.6'
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
