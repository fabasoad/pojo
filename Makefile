-include tasks/Makefile.*.mk

.PHONY: build install dev-install test update clean outdated

.DEFAULT_GOAL := build

build: java/build ## (Recommended) Builds the whole project

install: java/install

dev-install: dev/install ## (Recommended) Installs all the required dependencies

test: java/test ## (Recommended) Runs unit tests

update: java/update ## (Recommended) Updates dependencies to the latest version

clean: java/clean ## (Recommended) Removes build directory

outdated: java/outdated ## (Recommended) Shows outdated dependencies
