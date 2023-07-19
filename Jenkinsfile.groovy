pipeline {
    agent any
    
    stages {
        stage('Git Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/ValentinB57/devops.git'
            }
        }
    }
}

