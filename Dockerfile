FROM maven:3.8.3-openjdk-17

ENV PROJECT_HOME /usr/src/emprestimo-livros-api
ENV JAR_NAME emprestimo-livros-api-1.0.jar

# Create destination directory
RUN mkdir -p $PROJECT_HOME
WORKDIR $PROJECT_HOME

# Bundle app source
COPY . .

# Package the application as a JAR file
RUN mvn clean package -DskipTests

# Move file
RUN mv $PROJECT_HOME/target/$JAR_NAME $PROJECT_HOME/

ENTRYPOINT ["java", "-jar", "emprestimo-livros-api-1.0.jar"]