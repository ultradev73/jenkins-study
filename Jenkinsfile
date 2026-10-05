pipeline {
    agent any

    stages {
        stage('Start') {
            steps {
                echo '=== Pipeline Start ==='
            }
        }

        stage('Test') {
            steps {
                echo '=== Test Stage ==='
                sh 'echo "테스트를 실행합니다"'
            }
        }

        stage('Build') {
            steps {
                echo '=== Build Stage ==='
                sh 'echo "빌드를 실행합니다"'
            }
        }
    }
}
