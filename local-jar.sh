#Build
mvn clean package -Dmaven.test.skip=true

#Remove obsolete jar
rm additives.jar

#Copy jar
cp ./target/additives.jar additives.jar
