FROM mcr.microsoft.com/devcontainers/universal:2

# Instala librerías necesarias para interfaces gráficas en Linux
USER root
RUN apt-get update && apt-get install -y \
    python3-tk \
    python3-dev \
    libxrender1 \
    libxtst6 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*
USER codespace