# 베이스 이미지로 OpenJDK 11 이미지를 사용합니다.
FROM openjdk:11

# 작업 디렉토리를 설정합니다.
WORKDIR /app

# 필요한 패키지 설치
RUN apt-get update && \
    apt-get install -y wget unzip

# 프로젝트 파일들을 복사합니다.
COPY . .

# Gradle 래퍼를 이용하여 빌드를 실행합니다.
RUN ./gradlew clean build

# 빌드된 JAR 파일을 복사합니다.
COPY build/libs/*.jar app.jar

# 컨테이너가 시작될 때 실행할 명령을 설정합니다.
CMD ["java", "-jar", "app.jar"]