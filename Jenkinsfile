pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
        jdk 'JDK17'
    }

   
    stages {

        stage('Checkout') {
            steps {
                git(
                    branch: 'main',
                    url: 'https://github.com/Ilyeschrif22/springboot-backend.git'
                )
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

      
    }

    post {
        success {
            echo "Build completed successfully"
        }
        failure {
            echo 'Build failed. Please check the logs.'
        }
    }
}
