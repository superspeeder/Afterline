cd /srv/afterline/Afterline/
if [ -d "/srv/afterline/Afterline/gen/" ] 
then
    rm -r /srv/afterline/Afterline/gen/*
else
    mkdir /srv/afterline/Afterline/gen/
fi

cd proto
protoc --proto_path=/srv/afterline/Afterline/proto --java_out=/srv/afterline/Afterline/gen /srv/afterline/Afterline/proto/afterline/*.proto
echo Finished generated protobuf java code.
