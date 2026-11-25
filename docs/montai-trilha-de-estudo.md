# Montaí — Trilha de Estudo

Versão 1.1 de 25/11/2026 · Acompanha o `montai-backlog-mvp.csv`

## Para que serve

Este documento diz **o que eu preciso aprender** para construir cada tarefa. O documento de requisitos diz **o que o sistema faz**; os dois não se misturam.

## Como usar

1. **Estudar na hora da tarefa, não antes.** Quando chegar a vez de uma tarefa, olho aqui quais conceitos ela pede e estudo só eles.
2. **Testar no laboratório primeiro.** Conceito novo passa antes por um exemplo pequeno e isolado na pasta `montai-lab` (fora do projeto). Só depois que funcionou lá eu levo para o Montaí.
3. **No máximo 2 ou 3 conceitos novos por semana.** Se passar disso, eu paro, consolido e só depois sigo.
4. **Eu escrevo o código.** O Claude explica o porquê, mostra um exemplo genérico e revisa o que eu escrevi. HTML/CSS e configuração mecânica podem vir prontos.
5. **Marcar o checkbox** quando o conceito estiver entendido de verdade, ou seja, quando eu conseguir explicar numa entrevista.

**Legenda de nível**

| Nível | Significa | Onde já usei |
| --- | --- | --- |
| Já usei | Só revisar | Algum projeto meu |
| Parcial | Já vi, mas não nesse formato | Algo parecido em projeto meu |
| Novo | Primeira vez | — |

## Base que eu já tenho

Esses assuntos aparecem no Montaí, mas eu já usei. Revisão rápida quando a tarefa chegar:

- Spring Boot, REST, DTOs com Records, mappers → TechAssets, Clínica
- Spring Data JPA, entidades, `@Query` com JPQL → Clínica
- Bean Validation e handler global de exceções → Clínica, SplitHome
- Spring Security, JWT, BCrypt, claims → SplitHome
- Docker Compose com Postgres → Cardápio Digital
- JUnit 5 e Mockito → Clínica
- Swagger/OpenAPI → todos
- React, TypeScript, TanStack Query, Axios → TechAssets
- Deploy na Vercel → TechAssets

## Conceito que atravessa tudo: transações

Antes de começar a Fase 1, vale revisar a fundo o `@Transactional`: o que acontece quando dá erro no meio, o que é rollback e por que a reserva, o pagamento e o ledger precisam acontecer "tudo ou nada". Quase toda tarefa difícil da Fase 1 depende disso.

- [ ] **Lab:** um método que salva duas coisas e lança exceção no meio. Ver no banco o que ficou com e sem `@Transactional`.

## Fase 0 — Fundação

| Tarefa | Conceito | Nível | Exercício no lab |
| --- | --- | --- | --- |
| B01 | Monólito modular e Spring Modulith: o que é um módulo, pacote público vs. interno | Novo | Projeto com 2 módulos (`pedido` e `estoque`) onde um tenta acessar a classe interna do outro |
| B02 | Docker Compose com vários serviços (Postgres, MinIO, Mailpit) | Parcial | Subir os 3 e abrir o painel do MinIO e do Mailpit no navegador |
| B03 | Flyway: migrations versionadas em vez de `ddl-auto` | Novo | Criar uma tabela pela V1, mudar pela V2 e ver a tabela `flyway_schema_history` |
| B04 | GitHub Actions: o que é um workflow e um job | Novo | Workflow que roda `mvn test` num repositório de teste e falha de propósito |
| B05 | Teste de fronteira do Modulith (`ApplicationModules.verify()`) | Novo | No lab do B01, ver o teste quebrar quando um módulo acessa o interno do outro |
| B06 | springdoc-openapi | Já usei | — |
| B07 | Value object (`Money` em centavos) e `ProblemDetail` do Spring | Parcial | Somar R$ 0,10 + R$ 0,20 com `double` e com `long` para ver o erro de arredondamento |
| B08 | Algoritmo do dígito verificador de CPF/CNPJ | Novo (lógica) | Resolver primeiro no papel com um CPF de exemplo e depois escrever os testes |
| B09 | Cifrar vs. hashear: por que o CPF é cifrado e também tem hash | Novo | Cifrar e decifrar um texto com AES; comparar com o hash, que não volta. A mesma cifra é reaproveitada no B37 |
| B11 | Envio de e-mail com Spring Mail + token com validade | Parcial | Mandar um e-mail que aparece no Mailpit |
| B12 | Refresh token: por que dois tokens e por que guardar o hash | Novo | — (entender o fluxo antes; o código vem da base do SplitHome) |
| B13 | Revogação de token no logout | Novo | — (junto do B12) |
| B14 | IDOR: o usuário trocar o id na URL e ver dados de outro | Parcial | Endpoint vulnerável de propósito, explorar pelo Postman e depois corrigir |
| B16 | ADR: como escrever um registro de decisão | Novo | Ler 2 ADRs de exemplo e escrever o ADR-001 |

## Fase 1 — Núcleo do marketplace

| Tarefa | Conceito | Nível | Exercício no lab |
| --- | --- | --- | --- |
| B17, B18 | JSONB no Postgres e índice GIN | Novo | Tabela com coluna JSONB, inserir 3 linhas e consultar por um campo de dentro do JSON |
| B19 | Validação dinâmica: regras que vêm do banco, não de anotação | Novo (lógica) | Validar um `Map` contra uma lista de regras (obrigatório, mínimo, máximo) |
| B20 | Normalizar texto (acentos, espaços, hífens) com `Normalizer` | Novo | Fazer "RTX-4060 Ti" e "rtx 4060ti" virarem a mesma chave |
| B22, B30 | Busca com filtros opcionais e paginação (`Pageable`); filtro dentro do JSONB | Parcial | Repetir o filtro dinâmico da Clínica, agora com um campo do JSONB |
| B24 | Carregar seed na subida da aplicação (`CommandLineRunner` + Jackson) | Parcial | Ler um JSON com 5 produtos e salvar no banco |
| B26 | Regra de negócio por tipo de usuário | Já usei | — |
| B27 | Chamar API externa com `RestClient` | Novo | Consultar o ViaCEP com um CEP e mapear para um Record |
| B28 | Upload multipart e armazenamento S3 (MinIO) | Novo | Enviar uma imagem pelo Postman e vê-la no painel do MinIO |
| B32 | Interface com implementação falsa (padrão Strategy) | Parcial | Interface `Notificador` com duas implementações e trocar pela configuração |
| B33 | Idempotência: a mesma requisição duas vezes não cobra duas vezes | Novo | Endpoint que recebe uma chave e ignora a repetida |
| B34 | Ledger: registrar movimentações em vez de alterar um saldo | Novo (conceito) | Planilha com lançamentos e saldo calculado pela soma |
| B35 | Padrão State: cada estado decide para onde pode ir | Novo | Semáforo com estados que recusam transição inválida |
| B36 | Lock otimista (`@Version`) e concorrência | Novo | Duas threads atualizando a mesma linha; ver uma delas falhar |
| B37 | Cifrar o código com a mesma classe do B09, mas com outra chave; contador de tentativas | Parcial | Reaproveitar a cifra do B09: cifrar dois textos com chaves diferentes e ver que uma chave não abre o outro |
| B39 | Jobs agendados (`@Scheduled`) | Novo | Job que imprime no console a cada 10 segundos |
| B35, B34 | Eventos entre módulos (`ApplicationEventPublisher` / eventos do Modulith) | Novo | Módulo A publica "PedidoCriado", módulo B escuta e imprime |
| B52 | Expressões regulares para detectar telefone, e-mail e link | Novo | Testar 10 frases, incluindo tentativas de burlar ("9 9 8 7...") |
| B44 | Testcontainers: teste com Postgres de verdade num container | Novo | Um teste que sobe o Postgres, salva e lê uma entidade |
| B45 | ADRs 002 a 005 | Já usei (no B16) | — |

## Fase 2 — Front mínimo e deploy

| Tarefa | Conceito | Nível | Exercício no lab |
| --- | --- | --- | --- |
| B54 | CORS: por que o navegador bloqueia o front de chamar a API | Novo | Front chamando a API sem CORS, ver o erro no console, depois liberar |
| B54 | Interceptor do Axios para renovar o token automaticamente | Novo | Token curto de 30 s e ver o front renovar sozinho |
| B55 a B58 | Telas, rotas, formulários e estado | Parcial | Base do TechAssets; HTML/CSS pode vir pronto |
| B57 | Upload de arquivo pelo front (`FormData`) | Novo | Formulário com uma imagem enviada para o endpoint do B28 |
| B60 | Deploy de API, banco e arquivos sem custo mensal; variáveis de ambiente em produção | Parcial | Comparar opções gratuitas antes de decidir (vira a ADR-006) |

## Fase 3 — Marketplace completo e beta

Detalhar quando a Fase 2 terminar. Conceitos previstos: e-mails disparados por evento, jobs com prazo em dias, papéis de moderador e admin, configurações lidas do banco. Também entram as conversas com vendedores (B63), que podem começar a qualquer momento.

## Histórico de versões

| Versão | Data | Mudança |
| --- | --- | --- |
| 1 | 24/11/2026 | Versão inicial, Fases 0 a 2 detalhadas |
| 1.1 | 25/11/2026 | B37 passa a reaproveitar a cifra do B09 (código cifrado em vez de hash) |
