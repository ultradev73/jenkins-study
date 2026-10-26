pipeline {
    agent any

    parameters {
        choice(
            name: 'DEPLOY_ENV',
            choices: ['dev', 'stg', 'prd'],
            description: '배포 대상 환경'
        )

	string(
	    name: 'IMAGE_TAG',
	    defaultValue: 'latest',
	    description: 'Docker 이미지 태그'
	)

	booleanParam(
	    name: 'RUN_TEST',
	    defaultValue: true,
	    description: '테스트 실행 여부'
	)
    }

    environment {
        APP_NAME = 'jenkins-study'
        APP_VERSION = '1.0.0'
    }

    stages {

	stage('Credentials Check') {
	    steps {
	        echo '=== Credentials Check ==='

	        withCredentials([
	            string(credentialsId: 'study-secret', variable: 'MY_SECRET')
	        ]) {
	            sh '''
	                if [ -n "$MY_SECRET" ]; then
	                    echo "Credentials loaded successfully"
	                else
	                    echo "Credentials not found"
	                    exit 1
	                fi
	            '''
	        }
	    }
	}

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

		echo "DEPLOY_ENV=${params.DEPLOY_ENV}"
		echo "IMAGE_TAG=${params.IMAGE_TAG}"
		echo "RUN_TEST=${params.RUN_TEST}"

	    }
	}

        stage('Test') {
	    when {
		expression { params.RUN_TEST }
	    }

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
	        script {
	            if (params.RUN_TEST) {
	                sh './gradlew build'
	            } else {
	                sh './gradlew build -x test'
	            }
	        }
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

    post {
        always {
            echo '=== Pipeline Finished ==='
        }

        success {
            echo '=== Pipeline SUCCESS ==='
        }

        failure {
            echo '=== Pipeline FAILURE ==='
        }

        cleanup {
            echo '=== Pipeline Cleanup ==='
        }
    }

}
