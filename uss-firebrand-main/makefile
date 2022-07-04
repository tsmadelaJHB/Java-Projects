all: install compile reference  verify package  compile-exec

install:
	mvn install
compile:
	mvn exec:java &
	mvn compile
	exit 0

reference:
	java -jar lib/reference-server-0.1.0.jar &
	mvn test
#	mvn verify
	exit 0

verify:
	mvn verify
	exit 0
package:
	mvn exec:java -Dexec.mainClass="za/co/wethinkcode/robotworlds/MultiServers"
	mvn package
	exit 0
compile-exec:
	mvn compile exec:java

