# Atividade API Mobile - Consultor de Cotação de Moedas

Este é um aplicativo Android nativo desenvolvido em **Kotlin** para a atividade acadêmica. O projeto consiste em um consultor de moedas em tempo real que consome uma API pública e exibe dados estruturados de forma limpa e dinâmica para o usuário.

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Kotlin
* **Interface:** XML nativo (estruturado com `ScrollView` e `LinearLayout` para melhor usabilidade)
* **Comunicação com a API:** Biblioteca **Volley** para requisições assíncronas em HTTPS
* **Gerenciador de Dependências:** Gradle (Kotlin DSL)

## 📡 API Consumida
Foi utilizada a **AwesomeAPI** (Cotação de Moedas), consumindo o endpoint seguro:
`https://economia.awesomeapi.com.br/json/last/{MOEDA}-BRL`

## 📋 Critérios Atendidos na Atividade
1. **Configuração de Permissões:** Declaração explícita da permissão de `INTERNET` no arquivo `AndroidManifest.xml`.
2. **Interface Organizada:** Layout contendo título centralizado, campo de entrada de texto com dicas claras (`hint`), botão de disparo de consulta e área textual dedicada para exibir as respostas.
3. **Validação de Inputs:** O sistema verifica se o campo de texto está vazio antes de realizar a chamada de rede, emitindo um alerta amigável via `Toast` para evitar falhas ou travamentos.
4. **Consumo de API Real:** Implementação de requisições web funcionais que interpretam um objeto JSON dinâmico.
5. **Exibição de Dados Úteis:** O aplicativo extrai e formata com clareza 4 informações valiosas do retorno da API:
   * Nome Completo da Moeda
   * Valor de Compra (R$)
   * Valor de Venda (R$)
   * Variação Percentual Diária
6. **Tratamento de Erros:** Tratamento estruturado para lidar com ausência de conexão com a internet ou digitação de siglas inválidas (retornando erro 404 amigável sem quebrar o app).


### 📱 Demonstração do Aplicativo
<img width="468" height="615" alt="Captura de tela 2026-06-14 185959" src="https://github.com/user-attachments/assets/b815b335-afc4-4442-a788-54b6df3d1c68" />
<img width="470" height="615" alt="Captura de tela 2026-06-14 190036" src="https://github.com/user-attachments/assets/75486e92-b3f0-42fa-ac42-f5256fd6365b" />
<img width="470" height="618" alt="Captura de tela 2026-06-14 191406" src="https://github.com/user-attachments/assets/de0209c6-5e9a-4981-910a-4cc0cc1af60d" />


