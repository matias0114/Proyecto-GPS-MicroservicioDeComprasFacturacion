pipeline {
  agent {
    docker {
      image 'docker:24-dind'
      args  '-v /var/run/docker.sock:/var/run/docker.sock'
    }
  }

  environment {
    IMAGE_NAME   = "matiasjara1901244/proyecto-gps-microserviciodecomprasfacturacion"
    SSH_CRED     = 'ssh-prod'
    REMOTE_USER  = 'matiasjara1901'
    REMOTE_HOST  = '190.13.177.173'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Push') {
      steps {
        dir('Backend') { // Ruta del microservicio
          sh "docker build -t ${IMAGE_NAME}:latest ."
        }
        withCredentials([usernamePassword(
          credentialsId: 'docker-hub-creds',
          usernameVariable: 'DOCKERHUB_USER',
          passwordVariable: 'DOCKERHUB_PASS'
        )]) {
          sh '''
            echo "$DOCKERHUB_PASS" | docker login --username "$DOCKERHUB_USER" --password-stdin
            docker push ${IMAGE_NAME}:latest
          '''
        }
      }
    }

    stage('Deploy simple') {
      steps {
        sshagent([SSH_CRED]) {
          sh """
            ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} '
              docker pull ${IMAGE_NAME}:latest && \\
              docker stop microservicio-de-compras-facturacion || true && \\
              docker rm microservicio-de-compras-facturacion || true && \\
              docker run -d \\
                --name microservicio-de-compras-facturacion \\
                --restart always \\
                ${IMAGE_NAME}:latest
            '

            docker network connect backend-net microservicio-de-compras-facturacion
          """
        }
      }
    }
  }

  post {
    success { echo '✅ Despliegue completado correctamente' }
    failure { echo '❌ Hubo un error en el pipeline' }
  }
}
