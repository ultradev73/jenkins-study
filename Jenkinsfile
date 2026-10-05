pipeline {
    agent any

    stages {
        stage('Start') {
            steps {
                echo '=== Pipeline Start ==='
                sh 'java -version'
                sh './gradlew --version'
            }
        }

        stage('Test') {
            steps {
                echo '=== Test Stage ==='
                sh './gradlew test'
            }
	    post {
	        always {
	            junit 'build/test-results/test/*.xml'
	        }
	    }
        }

        stage('Build') {
            steps {
                echo '=== Build Stage ==='
                sh './gradlew build'
            }
        }

	stage('Artifact Check') {
	    steps {
	        echo '=== Artifact Check ==='
	        sh 'ls -lh build/libs/'

	        archiveArtifacts artifacts: 'build/libs/*.jar',
	                         fingerprint: true
	    }
	}
    }
}
