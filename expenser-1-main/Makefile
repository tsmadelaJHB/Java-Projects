SHELL := bash
.ONESHELL:
.SHELLFLAGS := -eu -o pipefail -c
.DELETE_ON_ERROR:
MAKEFLAGS += --warn-undefined-variables
MAKEFLAGS += --no-builtin-rules

.PHONY: help start_services grade test run

# Globals
JAVA_PKG_NAMESPACE := za.co.wethinkcode.weshare
STORY_TESTS := $(JAVA_PKG_NAMESPACE).user

# help: @ Lists available make tasks
help:
	@egrep -oh '[0-9a-zA-Z_\.\-]+:.*?@ .*' $(MAKEFILE_LIST) | \
	awk 'BEGIN {FS = ":.*?@ "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' | sort

# clean: @ Clean the build
clean:
	mvn clean
	docker-compose down

# start_services: @ Starts backing services via Docker Compose
start_services:
	docker-compose up -d

# test: @ Run all tests
test:
	mvn test

# run: @ Run the application locally
run: start_services
	mvn exec:java -Dexec.mainClass="$(JAVA_PKG_NAMESPACE).WeShareServer"

# grade: @ Grade a specific scenario (usage: `make grade SCENARIO=Login`)
GRADING_FILES = grading.rsync-filter.txt
capitalise_cmd = sed -E 's/(.)/\u&/' -
STORY ?= '*'

ifeq ($(STORY),viewNettExpenses)
	STORY_TEST = $(STORY_TESTS).NettExpensesTest
else
	STORY_TEST = $(STORY_TESTS).$(shell ST=$(STORY); echo $${ST^})Test
endif

grade: SUBMISSION_DIR ?= ${PWD}
grade: GRADE_CMD ?= 'mvn -Dtest="$(STORY_TEST)" test'
grade:
	@echo +++ Running grading for -- $(shell cat stories.yaml | grep $(STORY))
	@echo +++ Test class -- $(STORY_TEST)
	@echo +++ Command -- $(GRADE_CMD)
	@echo +++ Date -- $(shell date)

	@echo +++ Copying grading files into student submission dir: $(SUBMISSION_DIR)
	rsync -av --include-from "$(GRADING_FILES)" . "$(SUBMISSION_DIR)" --ignore-times

	@echo +++ Running grading tests

	pushd "$(SUBMISSION_DIR)"
	export GRADE_CMD=$(GRADE_CMD)
	export SUBMISSION_DIR="$(SUBMISSION_DIR)"
	docker-compose -f docker-compose.grading.yml run grader
	popd
	@echo +++ Date -- $(shell date)
