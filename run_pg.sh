#!/bin/bash
# jdbc:postgresql://localhost:54320/resumes
docker run -p${1:-54320}:5432 -e POSTGRES_DB=resumes -e POSTGRES_PASSWORD=postgres postgres 
