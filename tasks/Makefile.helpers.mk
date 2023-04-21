.PHONY: help

help: ## This help screen
	@find . -iname "Makefile*" | xargs grep " ##" | cut -d ':' -f 2,3,4 | awk '!/@find/' | sort | awk 'BEGIN {FS = " ## "}; {printf "\033[36m%-25s\033[0m %s\n", $$1, $$2}'
