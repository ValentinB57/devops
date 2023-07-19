pipeline {
    agent any
    
    stages {
        stage('Git Clone') {
            steps {
                git branch: 'main', credentialsId: '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', url: 'https://github.com/ValentinB57/devops.git'
            }
        }
    }
}

