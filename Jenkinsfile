pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
    }

    environment {
        DOCKER_IMAGE = 'docker-repo/springboot-backend'
        DOCKER_TAG   = "${env.BUILD_NUMBER}"
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

        stage('Build Docker Image') {
            steps {
                script {
                    sh """
                        docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                        docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest
                    """
                }
            }
        }
    }

    post {
        success {
            echo "Build completed successfully - Image: ${DOCKER_IMAGE}:${DOCKER_TAG}"
        }
        failure {
            echo 'Build failed. Please check the logs.'
        }
    }
}
