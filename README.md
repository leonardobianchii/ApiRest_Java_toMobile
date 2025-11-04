# 🚀 Mototrack – Projeto Sprint 4 (DevOps Tools & Cloud Computing) Prof antonio

## 👥 Integrantes do Grupo

| Nome completo | RM | Turma |
|----------------|------|-------|
| Leonardo Bianchi | RM558576 | 2TDSPW |
| Cauã Sanches de Santana | RM558317 | 2TDSPW |
| Ângelo Turano | RM556511 | 2TDSPW |

---

## 🎯 Descrição da Solução

O **Mototrack** é uma API REST desenvolvida em **Java com Spring Boot** que permite o **gerenciamento completo de motos, sensores RFID, localizações e filiais/departamentos**.  
A solução foi criada para **facilitar o rastreamento preciso de motos** em ambientes como pátios e oficinas, utilizando **tecnologia RFID** integrada a um **banco de dados Azure SQL** em nuvem.

A aplicação foi totalmente automatizada com **CI/CD no Azure DevOps**, realizando build, push e deploy de forma contínua, com execução final no **Azure Container Instance (ACI)** e armazenamento de imagens no **Azure Container Registry (ACR)**.

---

## ⚙️ Principais Funcionalidades

- Cadastro e gerenciamento de **motos** e **modelos**  
- Cadastro e controle de **sensores RFID**  
- Registro de **localizações das motos** (coordenadas X e Y)  
- CRUD completo para todas as entidades  
- Deploy automático da aplicação no **Azure Container Instance (ACI)**  
- Banco de dados persistente no **Azure SQL Database (PaaS)**  
- Build e push automatizados no **Azure Container Registry (ACR)**  
- Pipeline YAML configurada no **Azure DevOps**  
- Logs e histórico de execução no DevOps  
- Integração total entre **GitHub → DevOps → ACR → ACI → SQL**

---

## 🧱 Arquitetura da Aplicação

A API segue uma **arquitetura em camadas** com separação clara de responsabilidades:

| Camada | Função |
|--------|--------|
| **Model** | Entidades JPA que representam as tabelas do banco de dados |
| **Repository** | Interfaces de acesso ao banco (Spring Data JPA) |
| **Service** | Camada de regra de negócio e lógica da aplicação |
| **Controller** | Endpoints REST expostos para consumo da API |
| **Mapper** | Conversão entre entidades e DTOs |
| **Config** | Configurações gerais do projeto (datasource, cache etc.) |

---

## 🗄️ Entidades Principais

| Entidade | Descrição |
|-----------|-----------|
| `T_CM_MOTO` | Motos cadastradas, incluindo modelo, placa, status e quilometragem |
| `T_CM_SENSOR_RFID` | Sensores usados para leitura e identificação das motos |
| `T_CM_LOCALIZACAO_MOTO_RFID` | Registra localizações e histórico de leitura |
| `T_CM_FILIAL_DEPARTAMENTO` | Representa filiais e departamentos |
| `T_CM_CLIENTE` | Clientes que alugam as motos |
| `T_CM_ALUGUEL` | Histórico de aluguel e devolução das motos |

---

## ☁️ Arquitetura e Fluxo CI/CD

### 🔢 Fluxo resumido
1. 👤 **Desenvolvedor** faz *commit/push* no **GitHub**  
2. ⚙️ **Azure DevOps** executa o pipeline (build + push da imagem Docker)  
3. 🐳 **Azure Container Registry (ACR)** armazena a imagem gerada  
4. ☁️ **Azure Container Instance (ACI)** faz o *deploy automático*  
5. 🧱 **Aplicação Spring Boot** é executada na porta 8080  
6. 🗄️ **Azure SQL Database** armazena e fornece os dados da aplicação  
7. 💻 **Usuário final** consome a API via navegador ou ferramenta REST

🖼️ *(Insira aqui o diagrama de arquitetura gerado com as setas numeradas)*

---

## 🔍 Detalhamento dos Componentes

| Nome do componente | Tipo | Descrição funcional | Tecnologia/Ferramenta |
|--------------------|------|----------------------|------------------------|
| **Repositório** | SCM | Armazena o código da aplicação | GitHub |
| **Pipeline CI/CD** | Orquestrador | Build, push e deploy automatizados | Azure DevOps |
| **Container Registry** | Repositório de imagens | Armazena as imagens Docker da aplicação | Azure Container Registry |
| **Container Instance** | Ambiente de execução | Executa o container da API | Azure Container Instance |
| **Banco de Dados** | PaaS | Persistência de dados da aplicação | Azure SQL Database |

---

## 🧰 Tecnologias Utilizadas

| Categoria | Ferramenta |
|------------|-------------|
| Linguagem | Java 17 |
| Framework | Spring Boot 3.x |
| Banco de Dados | Azure SQL Database |
| ORM | Spring Data JPA |
| Validação | Jakarta Validation (Bean Validation) |
| Infraestrutura | Docker |
| CI/CD | Azure DevOps Pipelines |
| Deploy | Azure Container Instance |
| Repositório de Imagens | Azure Container Registry |
| Versionamento | Git + GitHub |
| Auxiliares | Lombok, Maven |

---

## 🧾 Pipeline CI/CD (YAML Simplificado)

```yaml
trigger:
  branches:
    include:
      - main

pool:
  vmImage: 'ubuntu-latest'

variables:
  appName: 'rm558576mottu'
  acrName: 'rm558576mottu'
  acrLoginServer: 'rm558576mottu.azurecr.io'
  imageRepository: 'fiap/$(appName)'
  imageTag: '$(Build.BuildId)'
  dockerRegistryServiceConnection: 'sc-acr-lorch'
  azureSubscription: 'sc-azure-smartcheck'
  dockerfilePath: '$(Build.SourcesDirectory)/Dockerfile'
  aciName: 'rm558576mottu'
  resourceGroup: 'chal-mottu'
  location: 'eastus'
  containerPort: 8080

steps:
  - checkout: self

  - task: Docker@2
    displayName: 'Build and push image to ACR'
    inputs:
      command: buildAndPush
      containerRegistry: '$(dockerRegistryServiceConnection)'
      repository: '$(imageRepository)'
      dockerfile: '$(dockerfilePath)'
      tags: |
        $(imageTag)
      buildContext: '$(Build.SourcesDirectory)'

  - task: AzureCLI@2
    displayName: 'Deploy no Azure Container Instances'
    inputs:
      azureSubscription: '$(azureSubscription)'
      scriptType: bash
      scriptLocation: inlineScript
      inlineScript: |
        set -euo pipefail
        ACR_NAME='$(acrName)'
        ACR_LOGIN_SERVER='$(acrLoginServer)'
        IMAGE_NAME='$(imageRepository)'
        TAG='$(imageTag)'
        ACI_NAME='$(aciName)'
        RESOURCE_GROUP='$(resourceGroup)'
        LOCATION='$(location)'
        PORT='$(containerPort)'

        ACR_USER=$(az acr credential show -n "$ACR_NAME" --query username -o tsv)
        ACR_PASS=$(az acr credential show -n "$ACR_NAME" --query "passwords[0].value" -o tsv)
        DNS_LABEL="${ACI_NAME}-${BUILD_BUILDID}"

        az container delete --name "$ACI_NAME" --resource-group "$RESOURCE_GROUP" --yes --only-show-errors || true

        az container create \
          --name "$ACI_NAME" \
          --resource-group "$RESOURCE_GROUP" \
          --location "$LOCATION" \
          --image "$ACR_LOGIN_SERVER/$IMAGE_NAME:$TAG" \
          --cpu 1 \
          --memory 1.5 \
          --dns-name-label "$DNS_LABEL" \
          --ports "$PORT" \
          --registry-login-server "$ACR_LOGIN_SERVER" \
          --registry-username "$ACR_USER" \
          --registry-password "$ACR_PASS" \
          --os-type Linux
