export AFTERLINE_HTTP_PORT=20022
export AFTERLINE_CERT=/srv/cert/untrusted/cert.pem
export AFTERLINE_KEY=/srv/cert/untrusted/key.pem
export AFTERLINE_GAPI_CREDENTIALS=/srv/afterline/google_credentials.json

cd /srv/afterline/Afterline/server
./gradlew jar -Dorg.gradle.java.home=/usr/lib/jvm/java-13-openjdk-amd64/ 

cd /srv/afterline/run

java13 --enable-preview -jar server.jar
