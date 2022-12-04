## Drones

For project running you have to have java 19, docker and gradle on your machine

For database preparation you can open console in project root directory and run command
-- docker-compose -f docker-compose-db.yml up

For project running, after db preparation, you can open console in project root directory and run commands
-- gradle bootJar
After in the same console
-- cd build
...
-- cd libs
...
-- java -jar drones.jar


Or you can use tool of IntellijIdea
