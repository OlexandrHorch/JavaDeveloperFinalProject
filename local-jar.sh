#Build
mvn clean package -Dmaven.test.skip=true

#Remove obsolete jar
rm supplements.jar

#Copy jar
cp ./target/supplements.jar supplements.jar
