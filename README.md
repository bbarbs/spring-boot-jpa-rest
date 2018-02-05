# spring-boot-rest

# Endpoints

# Mapstruct configuration

Make the generated mapper implementation using apt as source, so IDE can detect it.
```
sourceSets {
	main {
		java {
			srcDirs("src/main/java", "build/generated/source/apt/main")
		}
	}
}
```