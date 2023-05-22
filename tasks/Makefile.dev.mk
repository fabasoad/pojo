.PHONY: dev/install dev/install-deps dev/install-tools

dev/install: dev/install-tools dev/install-deps ## Installs all the required tools and dependencies required for the correct work with this project using 'asdf' tool

dev/install-tools: ## Installs all the tools that are required for the correct work with this project using 'asdf' tool. All tools and their versions are listed in '.tool-versions' file
	@asdf plugin list | grep -q java || asdf plugin add java
	@asdf plugin list | grep -q pre-commit || asdf plugin add pre-commit
	@asdf install java $(grep -Eo "java [a-z0-9-]+" ".tool-versions" | cut -d ' ' -f 2)
	@asdf install pre-commit $(grep -Eo "pre-commit [0-9.pre-]+" ".tool-versions" | cut -d ' ' -f 2)

dev/install-deps: ## Installs all the dependencies that are required for the correct work of the tools listed in '.tool-versions' file
	@pre-commit install
