<h1 align="center">Cloud QR Code Generator API</h1>

<p align="center">

<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>

<img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>

<img src="https://img.shields.io/badge/ZXing-000000?style=for-the-badge&logo=qrcode&logoColor=white"/>

<img src="https://img.shields.io/badge/Microsoft_Azure-0078D4?style=for-the-badge&logo=icloud&logoColor=white"/>

<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>

</p>

<p align="center">
API desenvolvida em Spring Boot para geração dinâmica de QR Codes a partir de textos ou URLs enviados pelo cliente.
</p>

<p align="center">
<img width="220" height="220" alt="qrcode" src="https://github.com/user-attachments/assets/f7cd752b-60a9-4aa8-b3b3-7c27ca06b000" />
</p>

---

## Sobre o Projeto

Esta API recebe uma requisição contendo um texto ou URL, gera um QR Code correspondente e armazena a imagem em um serviço de armazenamento em nuvem.

Após o processamento, a aplicação retorna um link público que permite acessar imediatamente a imagem gerada.

O projeto foi construído com foco em simular um cenário comum em aplicações backend modernas, envolvendo geração de arquivos, integração com serviços externos e organização arquitetural em camadas.

---

## Estrutura da Aplicação

A aplicação foi organizada em camadas para separar responsabilidades e facilitar a evolução do projeto.

```
controller
 └─ QrCodeController

service
 └─ QrCodeGeneratorService

ports
 └─ StoragePort

infrastructure
 └─ AzureBlobStorageAdapter

dto
 └─ qrcode
     ├─ QrCodeGenerateRequest
     └─ QrCodeGenerateResponse
```

Essa estrutura permite desacoplar a lógica de geração do QR Code da infraestrutura de armazenamento.

---

## Fluxo Arquitetural

A interação entre os componentes da aplicação ocorre da seguinte forma:

1. O **cliente HTTP** envia a requisição para o endpoint da API.
2. O **QrCodeController** recebe o DTO da requisição e delega o processamento para o serviço.
3. O **QrCodeGeneratorService** gera o QR Code utilizando a biblioteca ZXing.
4. O serviço utiliza a interface **StoragePort** para enviar o arquivo.
5. O **AzureBlobStorageAdapter** implementa essa interface e realiza o upload para o Azure Blob Storage.
6. Após o upload, o serviço retorna a URL pública do recurso gerado.

---

## Exemplos de Execução

### Requisição da API via Postman

Exemplo de chamada da API enviando o texto que será convertido em QR Code.

<img width="973" height="1027" alt="Captura de tela 2026-02-24 114440" src="https://github.com/user-attachments/assets/acdb7eb5-91fd-460a-ac2d-ef27530e15e8" />

---

### QR Code gerado e acessível via link público

A URL retornada pela API permite acessar diretamente a imagem gerada.

<img width="1918" height="964" alt="Captura de tela 2026-02-24 114518" src="https://github.com/user-attachments/assets/ebd26113-3894-4599-a008-22828ffe5013" />

---

## Objetivo do Projeto

Este projeto foi desenvolvido para praticar conceitos comuns no desenvolvimento de APIs backend, incluindo:

* geração dinâmica de arquivos
* integração com serviços de armazenamento em nuvem
* separação de responsabilidades entre camadas
* abstração de infraestrutura através de interfaces

A proposta é simular uma aplicação simples, porém estruturada de forma próxima a um cenário real de backend.

---

<p align="center">
<em>🔗 Uma solução simples para transformar textos e URLs em QR Codes acessíveis via link público.</em>
</p>
