pipeline{
    agent any
    tools{
        maven 'maven'
    }
    
    stages{
        stage("Clone"){
            steps{
                git branch: 'main', url: 'https://github.com/vivekshet1495/project.git'
            }
        }
        stage("Source Build"){
            steps{
                sh 'mvn install -D maven.test.skip=true'
            }
        }
        stage("Test Build"){
            steps{
                sh 'mvn compiler:testCompile'
            }
        }
        stage("Test Result"){
            steps{
                sh 'mvn surefire:test'
                junit "target/**/*.xml"
            }
        }
        stage("Image build and push"){
            steps{
                sh 'ssh-keygen -F 13.127.159.105 >/dev/null || ssh-keyscan -H 13.127.159.105 >> /var/lib/jenkins/.ssh/known_hosts'
                sh 'rsync -r * vivek@13.127.159.105:/home/vivek/project'
                sh 'ssh vivek@13.127.159.105 "cd project && docker build . -f Dockerfile -t godspeed95/project:latest  && docker push godspeed95/project:latest"'
            }
        }
    }
}
