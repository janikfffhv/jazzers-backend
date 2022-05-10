docker-compose down
./gradlew build
./gradlew war
docker-compose build
docker-compose up