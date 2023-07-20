pipeline {
    agent any

    triggers {
        pollSCM('H/10 * * * *')
    }

    stages {
        stage('Git Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/ValentinB57/devops.git'
            }
        }
        stage('Run Tests') {
            steps {
                sh "grep -q '<h1>Fromage</h1>' /var/lib/jenkins/workspace/tpadmin/index.html"
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

post {
        success {
            // Étape à exécuter si le build a réussi (envoi d'e-mail)
            emailext (
                subject: "Build réussi : ${currentBuild.fullDisplayName}",
                body: "Le build a réussi. Bravo !",
                recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']]
            )
        }
    }
    
}

