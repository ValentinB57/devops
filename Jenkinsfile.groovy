pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Git Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/ValentinB57/devops.git'
            }
        }
        stage('Run Tests') {
            steps {
                sh "grep -q '<h1>Fromage</h1>' /chemin/vers/index.html"
            }
            post {
                always {
                    echo 'Fin des tests.'
                }
                success {
                    echo 'Le test a réussi !'
                }
                failure {
                    echo 'Le test a échoué :('
                }
            }
        }
    }
}

