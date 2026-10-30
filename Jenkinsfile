pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
        stage('Verify JAR') {
            steps {
                script {
                    def jarPath = 'target/hotel-booking-system-1.0-SNAPSHOT.jar'

                    if (fileExists(jarPath)) {
                        echo "Проверка JAR: файл найден -> ${jarPath}"
                        sh "ls -la ${jarPath}"
                        sh "jar tf ${jarPath}"
                    } else {
                        error "Проверка JAR: файл НЕ найден -> ${jarPath}. Этап Package не создал артефакт."
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline завершен успешно: Build -> Test -> Package -> Verify JAR'
        }
        failure {
            echo 'Pipeline завершился с ошибкой. Проверьте Console Output для деталей.'
        }
    }
}
