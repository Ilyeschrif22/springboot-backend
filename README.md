# Jenkins CI/CD Pipeline Documentation

## 📋 Overview

This repository demonstrates how to create and configure Jenkins jobs using two different methods: **Freestyle** and **Pipeline** approaches for a Spring Boot backend application.

---

## 🚀 Initial Jenkins Setup

### Access Jenkins Dashboard
- Open browser and navigate to: `http://localhost:8080`
- Complete the initial setup wizard
- Install suggested plugins

### Configure Jenkins Global Tools
- Navigate to: `Manage Jenkins` → `Global Tool Configuration`
- Configure JDK, Maven/Gradle, and Git

---

## 🔧 Build Methods

### 1️⃣ Freestyle Job

The Freestyle method provides a traditional approach to creating Jenkins jobs using the web interface.

#### Steps to Create a Freestyle Job:

**1. Create New Job**
- Click `New Item` on Jenkins dashboard
- Enter job name (e.g., `SpringBoot-Freestyle-Build`)
- Select `Freestyle project`
- Click `OK`

**2. Configure Source Code Management**
- Select `Git`
- Enter repository URL: `https://github.com/Ilyeschrif22/springboot-backend`
- Add credentials if private repository
- Specify branch: `*/main` or `*/master`

**3. Build Triggers**
- Check `Poll SCM` or `Build periodically`
- For testing: `H/1 * * * *` (builds every minute)

**4. Build Steps**
- Add build step: `Invoke top-level Maven targets`
- Goals: `clean install`
- Or use shell script:
```bash
mvn clean package
```

**5. Post-build Actions** (Optional)
- Archive artifacts: `target/*.jar`
- Publish JUnit test results

---

### 2️⃣ Pipeline Job

The Pipeline method uses a `Jenkinsfile` stored in your repository, providing version-controlled, reproducible builds.

#### Steps to Create a Pipeline Job:

**1. Create New Pipeline Job**
- Click `New Item` on Jenkins dashboard
- Enter job name (e.g., `SpringBoot-Pipeline`)
- Select `Pipeline`
- Click `OK`

**2. Configure Pipeline**
- Under `Pipeline` section
- Definition: `Pipeline script from SCM`
- SCM: `Git`
- Repository URL: `https://github.com/Ilyeschrif22/springboot-backend`
- Script Path: `Jenkinsfile` (located in root directory)

**3. Build Triggers**
- Check `Poll SCM` or `Build periodically`
- For testing: `H/1 * * * *` (builds every minute)

**4. Save Configuration**

---

## 📄 Jenkinsfile

Create a `Jenkinsfile` in the root of your repository:

```groovy
pipeline {
    agent any

    tools {
        maven 'Maven-3.9'  
        jdk 'JDK17'        
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                    url: https://github.com/Ilyeschrif22/springboot-backend.git'
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
            echo 'Build, Test and Package completed successfully!'
        }
        failure {
            echo 'Build failed. Please check the logs.'
        }
    }
}

```

---


---

## 📊 Comparison: Freestyle vs Pipeline

| Feature | Freestyle | Pipeline |
|---------|-----------|----------|
| Configuration | GUI-based | Code-based (Jenkinsfile) |
| Version Control | ❌ Not version controlled | ✅ Version controlled |
| Complexity | Simple, easy to start | More powerful, flexible |
| Reusability | Limited | Highly reusable |
| Best For | Simple projects | Complex workflows |

---

## 📝 Documentation Summary

This documentation covers:
- ✅ Creating Freestyle Jobs
- ✅ Creating Pipeline Jobs
- ✅ Automating build launches
- ✅ Using Jenkinsfile for Pipeline as Code

---

⭐ **Star this repository if you found it helpful!**

