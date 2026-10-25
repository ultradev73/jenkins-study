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

	        // Linux Shell에서 해석
	        sh 'echo "APP_NAME=$APP_NAME"'
	        sh 'echo "APP_VERSION=$APP_VERSION"'

	        // Jenkins Groovy에서 해석
	        echo "Groovy APP_NAME=${env.APP_NAME}"
	        echo "Groovy APP_VERSION=${env.APP_VERSION}"

	        // Jenkins 내장 환경변수
	        echo "BUILD_NUMBER=${env.BUILD_NUMBER}"
	        echo "JOB_NAME=${env.JOB_NAME}"
	        echo "BUILD_URL=${env.BUILD_URL}"
	        echo "WORKSPACE=${env.WORKSPACE}"
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
