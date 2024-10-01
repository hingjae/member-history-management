ifneq (,$(wildcard .env))
include .env
export
endif

local-db-up:
	@echo "Copying ./docker/conf.d to ./docker/local/conf.d"
	mkdir -p ./docker/local/db/conf.d
	cp -r ./docker/conf.d/* ./docker/local/db/conf.d/
	@echo "Directory copied successfully."

	@echo "Copying ddl.sql and dml.sql from ./docker/init.d to ./docker/local/init.d"
	mkdir -p ./docker/local/db/init.d
	cp ./docker/init.d/ddl.sql ./docker/local/db/init.d
	cp ./docker/init.d/dml.sql ./docker/local/db/init.d
	@echo "SQL files copied successfully."

	docker compose -f $(DOCKER_LOCAL_PATH)/docker-compose-local.yml --env-file .env up -d
local-db-down:
	@echo "Stopping and removing Docker containers, networks, and volumes"
	docker compose -f $(DOCKER_LOCAL_PATH)/docker-compose-local.yml --env-file .env down --volumes
	@echo "Docker resources cleaned up successfully."

	@echo "Removing ./docker/local/db directory"
	rm -rf ./docker/local/db
	@echo "Directory ./docker/local/db removed successfully."
test-db-up:
	@echo "Copying ./docker/conf.d to ./docker/local/conf.d"
	mkdir -p ./docker/test/db/conf.d
	cp -r ./docker/conf.d/* ./docker/test/db/conf.d/
	@echo "Directory copied successfully."

	@echo "Copying ddl.sql and dml.sql from ./docker/init.d to ./docker/local/init.d"
	mkdir -p ./docker/test/db/init.d
	cp ./docker/init.d/ddl.sql ./docker/test/db/init.d
	@echo "SQL files copied successfully."
	docker compose -f $(DOCKER_TEST_PATH)/docker-compose-test.yml --env-file .env up -d
test-db-down:
	@echo "Stopping and removing Docker containers, networks, and volumes"
	docker compose -f $(DOCKER_TEST_PATH)/docker-compose-test.yml --env-file .env down --volumes
	@echo "Docker resources cleaned up successfully."

	@echo "Removing ./docker/local/db directory"
	rm -rf ./docker/test/db
	@echo "Directory ./docker/local/db removed successfully."