FROM openjdk:11-jdk
EXPOSE 8080
COPY ${JAR_FILE} ${JAR_FILE}
ADD target/investment-calculator-*.jar investment-calculator.jar
ENTRYPOINT ["java","-jar","/investment-calculator.jar"]
