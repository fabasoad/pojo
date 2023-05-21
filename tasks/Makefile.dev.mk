.PHONY: dev/install dev/install-deps dev/install-tools

dev/install: dev/install-tools dev/install-deps ## Installs all the required tools and dependencies required for the correct work with this project using 'asdf' tool

dev/install-tools: ## Installs all the tools that are required for the correct work with this project using 'asdf' tool. All tools and their versions are listed in '.tool-versions' file
	@asdf plugin list | grep -q java || asdf plugin add java
	@asdf plugin list | grep -q pre-commit || asdf plugin add pre-commit
	@while IFS= read -r l; do asdf install $(echo "$l" | cut -d' ' -f1) $(echo "$l" | cut -d' ' -f2); done < .tool-versions

dev/install-deps: ## Installs all the dependencies that are required for the correct work of the tools listed in '.tool-versions' file
	@pre-commit install
