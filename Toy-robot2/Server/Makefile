all: install validate verify clean compile test version package testWorld1 testWorld2 testWorld3 testWorld4 tag

install:
	mvn install

validate:
	mvn validate

verify:
	mvn verify -DskipTests=true

clean:
	mvn clean

compile:
	mvn compile

package:
	mvn package -DskipTests=true

test:
	mvn exec:java -Dexec.mainClass="za.co.wethinkcode.server.ServerHandler" &
	mvn test

version:
	mvn versions:set

package:
	mvn package -DskipTests=true

testWorld1:
	java -jar ./Server/target/Server-*-dependencies.jar &
	mvn test -Dtest="za.co.wethinkcode.server.World1.*Test" -DfailIfNoTests=false
	lsof -ti tcp:5000 | xargs kill

testWorld2:
	java -jar ./Server/target/Server-*-dependencies.jar -s 2 &
	mvn test -Dtest="za.co.wethinkcode.server.World2.*Test" -DfailIfNoTests=false
	lsof -ti tcp:5000 | xargs kill

testWorld3:
	java -jar ./Server/target/Server-*-dependencies.jar -s 2 -o 1,0 &
	mvn test -Dtest="za.co.wethinkcode.server.World3.*Test" -DfailIfNoTests=false
	lsof -ti tcp:5000 | xargs kill

testWorld4:
	java -jar ./Server/target/Server-*-dependencies.jar -s 2 -o 1,0 &
	mvn test -Dtest="za.co.wethinkcode.server.World3.*Test" -DfailIfNoTests=false
	lsof -ti tcp:5000 | xargs kill

tag:
	@echo "Git Version?: "; \
	read GITVERSION; \
	git tag -a release-$(GITVERSION) -m "release-$(GITVERSION)"

