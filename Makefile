-include tasks/Makefile.*.mk

.PHONY: build install test update clean

.DEFAULT_GOAL := build

build: java/build ## (Recommended) Builds the whole project

install: dev/install ## (Recommended) Installs all the required dependencies

test: java/test ## (Recommended) Runs unit tests

update: java/update ## (Recommended) Updates dependencies to the latest version

clean: java/clean ## (Recommended) Removes build directory
