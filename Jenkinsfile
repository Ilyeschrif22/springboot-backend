pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
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
 
        stage('SonarQube Analysis') {
            steps {
                sh '''
                    mvn sonar:sonar \
                    -Dsonar.projectKey=springboot-backend \
                    -Dsonar.projectName=springboot-backend \
                    -Dsonar.host.url=http://192.168.33.10:9000 \
                    -Dsonar.login=squ_1c4e3c37d60899ea798e531120fe7a5d196662df
                '''
            }
        }


        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test -DskipTests'
                junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('List workspace files') {
            steps {
                sh 'ls -la'
            }
        }

        stage('Build docker image') {
            steps {
                sh 'docker build -t ilyeschrif21/docker-repo:$BUILD_NUMBER .'
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKERHUB_CREDENTIALS_USR', passwordVariable: 'DOCKERHUB_CREDENTIALS_PSW')]) {
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                }
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
