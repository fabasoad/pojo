.PHONY: java/build java/global java/test java/update

java/build: ## Builds the whole project using gradle
	@./gradlew build --refresh-dependencies --warning-mode all

java/global: ## Sets Java version of this project as a global Java version
	@grep -Eo 'java [0-9.-]+' .tool-versions | xargs asdf global

java/test: ## Runs unit tests with coverage
	@./gradlew test --warning-mode all

java/update: ## Updates dependencies to the latest version
	@./gradlew dependencyUpdates -Drevision=releas

java/clean: ## Removes build directory
	@./gradlew clean
