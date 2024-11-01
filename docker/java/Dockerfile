# ベースイメージとして OpenJDK 21 を使用
FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu AS build

# 作業ディレクトリの設定
WORKDIR /app

# Java 21 (OpenJDK) のインストール
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk && \
    apt-get clean

# Mavenをインストール
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean
    # rm -rf /var/lib/apt/lists/*

# pom.xmlとMavenラッパーをコピー
COPY ./pom.xml ./
COPY ./.mvn ./.mvn
COPY ./mvnw ./

# 依存関係をダウンロード
RUN ./mvnw dependency:go-offline

# ソースコードをコピー
COPY ./src ./src

# アプリケーションをビルド
RUN ./mvnw clean package -e

# 実行用のイメージ
FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu

# ビルドしたJARファイルをコピー
COPY --from=build /app/target/*.jar app.jar

# ポート8000を開放
EXPOSE 8000

# アプリケーションを実行
ENTRYPOINT ["java", "-jar", "/app.jar"]

# # Spring Bootアプリケーションを起動
# CMD ["mvn", "spring-boot:run"]