export AFTERLINE_HTTP_PORT=20022
export AFTERLINE_CERT=/srv/cert/untrusted/cert.pem
export AFTERLINE_KEY=/srv/cert/untrusted/key.pem
export AFTERLINE_GAPI_CREDENTIALS=/srv/afterline/google_credentials.json

cd /srv/afterline/Afterline/server
./gradlew distZip


if [ $1 = "-i" ]; then
    echo "Enter to continue"
    read
fi

cd /srv/afterline/run

[[ -d "server" ]] && rm -r server

unzip /srv/afterline/Afterline/server/build/distributions/server.zip

clear

export JAVA_OPTS=-Dlog4j2.formatMsgNoLookups=true

./server/bin/server
