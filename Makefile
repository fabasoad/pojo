-include tasks/Makefile.*.mk

.PHONY: build install test update

.DEFAULT_GOAL := help

build: java/build ## (Recommended) Builds the whole project

install: dev/install ## (Recommended) Installs all the required dependencies

test: java/test ## (Recommended) Runs unit tests

update: java/update ## (Recommended) Updates dependencies to the latest version
