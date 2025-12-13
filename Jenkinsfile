pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
    }

    stages {
        
        // ================================================================
        // >> TEMPORARY STAGE TO FIX CORRUPTION <<
        // RUN THIS ONCE, THEN DELETE THIS ENTIRE STAGE.
        // ================================================================
        stage('Fix Git Corruption') {
            steps {
                // Ensure this machine has the 'find' utility (common on Linux/Git Bash)
                
                // 1. Find and delete zero-byte files (the common cause of the corruption error)
                echo 'Attempting to find and remove zero-byte corrupted Git objects...'
                sh 'find .git/objects/ -size 0 -exec rm -f {} \\;' 
                
                // 2. Force Git to redownload the missing objects from the remote repository
                echo 'Forcing a Git fetch to redownload missing objects...'
                sh 'git fetch origin' 
            }
        }
        // ================================================================

        stage('Checkout') {
            steps {
                // This 'git' step will now run a clean checkout/fetch
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