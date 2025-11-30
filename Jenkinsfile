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

        stage('Build docker image') {
            steps {
                sh 'docker build -t ilyeschrif21/docker-repo:$BUILD_NUMBER .'
            }
        }

        stage('Login to DockerHub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push image') {
            steps {
                sh 'docker push ilyeschrif21/docker-repo:$BUILD_NUMBER'
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
