.PHONY: dev

dev:
	@echo "Running dev build..."
	clj -A:build:fig
