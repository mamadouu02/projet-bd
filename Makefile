all: run 

build:
	javac -d bin -sourcepath src -classpath lib/ojdbc6.jar src/App.java

run: build
	java -classpath bin:lib/ojdbc6.jar App

clean:
	rm -rf bin/
