pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
<<<<<<< HEAD
        jdk 'JDK7'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Ilyeschrif22/springboot-backend.git'
            }
        }
=======
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

>>>>>>> 9e2d31ef430d03b7fc31281c0ba0eeb7108d55cd
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
<<<<<<< HEAD
=======

>>>>>>> 9e2d31ef430d03b7fc31281c0ba0eeb7108d55cd
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
<<<<<<< HEAD
=======

>>>>>>> 9e2d31ef430d03b7fc31281c0ba0eeb7108d55cd
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
<<<<<<< HEAD
    }
    post {
        success {
            echo 'Build, Test and Package completed successfully'
=======

      
    }

    post {
        success {
            echo "Build completed successfully"
>>>>>>> 9e2d31ef430d03b7fc31281c0ba0eeb7108d55cd
        }
        failure {
            echo 'Build failed. Please check the logs.'
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 9e2d31ef430d03b7fc31281c0ba0eeb7108d55cd
