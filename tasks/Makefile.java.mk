.PHONY: java/build java/test java/update

java/build: ## Builds the whole project using gradle
	@./gradlew build

java/test: ## Runs unit tests with coverage
	@./gradlew test

java/update: ## Updates dependencies to the latest version
	@./gradlew dependencyUpdates -Drevision=releas
