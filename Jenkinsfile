pipeline {
    agent any

    environment {
        APP_NAME = 'jenkins-study'
        APP_VERSION = '1.0.0'
    }

    stages {
	stage('Start') {
	    steps {
	        echo '=== Pipeline Start ==='
	        sh 'java -version'
	        sh './gradlew --version'

	        sh 'echo "APP_NAME=$APP_NAME"'
	        sh 'echo "APP_VERSION=$APP_VERSION"'
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
