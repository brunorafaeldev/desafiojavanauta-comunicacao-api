# API de Comunicação

API simples desenvolvida e melhorada em Java para o envio de comunicados.

## 📋 Estrutura do Comunicado

Cada comunicado possui os seguintes campos:

* `dataHoraEnvio`: Data e hora do envio (ex: `10-10-2026 15:00:00`)
* `nomeDestinatario`: Nome da pessoa que vai receber (ex: `Fulano da Silva`)
* `emailDestinatario`: E-mail de quem recebe (ex: `fulanodasilva@email.com`)
* `telefoneDestinaratio`: Telefone de quem recebe (ex: `+5581999999999`)
* `emailDestinatario`: E-mail de quem recebe (ex: `fulanodasilva@email.com`)
* `mensagem`: O texto do comunicado
* `modoEnvio`: Meio de envio (ex: `EMAIL`, `SMS`, `PUSH`, `WHATSAPP`)
* `statusEnvio`: Status do envio (ex: `PENDENTE`, `ENVIADO`, `CANCELADO`

---

## 🚀 Endpoints da Aplicação

* **GET**: localhost:8087/comunicacao: Busca status comunicado passando e-mail
* **POST**: localhost:8087/comunicacao/agendar: Criar um comunicado passando o e-mail
* **PATCH**: localhost:8087/comunicado/cancelar: Altera status comunicado para Cancelado
* **DELETE**: localhost:8087/comunicacao/"emailDestinatario": Deleta um comunicado.

---

**Exemplo de requisição (via Postman/Swagger):**

http://localhost:8087/comunicados/agendar

{
"dataHoraEnvio": "2026-10-15 15:00:00",
"nomeDestinatario": "Jorge",
"emailDestinatario": "jorgepixuruco@gmail.com",
"telefoneDestinatario": "9194848484",
"mensagem": "Testando os endpoints",
"modoDeEnvio": "SMS",
"statusEnvio": "CANCELADO"
}