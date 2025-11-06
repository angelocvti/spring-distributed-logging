# Distributed Logging

Este projeto demonstra uma arquitetura moderna de logs distribuídos utilizando **Spring Boot**, **Apache Kafka**, **Elasticsearch** e **Kibana**.  
A solução é voltada para cenários de **microserviços**, onde é necessário garantir observabilidade, resiliência e escalabilidade no tratamento de logs e eventos críticos.

## Arquitetura

Spring Producer → Kafka → Spring Consumer → Elasticsearch → Kibana

### Componentes

- **Producer (Spring Boot)**  
  Expõe uma API REST para envio de transações (exemplo: `/payments`).  
  Os eventos são enviados de forma assíncrona para o Kafka.

- **Kafka Broker**  
  Atua como fila de eventos distribuída e escalável.

- **Consumer (Spring Boot)**  
  Consome mensagens do Kafka e as indexa no Elasticsearch.

- **Elasticsearch**  
  Armazena os logs de forma estruturada e pesquisável.

- **Kibana**  
  Interface web para visualização e análise dos dados indexados.

## Acessando o Elasticsearch e Kibana localmente

Log indexado:
http://localhost:9200/transaction-logs/\_search?pretty

Kibana:
http://localhost:5601

## Objetivo

Demonstrar boas práticas de:

Logs assíncronos e resilientes

Desacoplamento entre escrita e processamento

Observabilidade distribuída

Escalabilidade horizontal (stateless services)
